package com.themaker.fshmo.klassikaplus.data.repositories;

import android.util.Log;
import com.themaker.fshmo.klassikaplus.App;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.data.mappers.DbToDomainItemMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.DtoToDbItemMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.ListMapping;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;
import com.themaker.fshmo.klassikaplus.data.web.catalog.CatalogApi;
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.DataDto;
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.ItemDto;
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.items.ResponseDto;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CatalogRepository extends BaseRepository {

    private static final String TAG = CatalogRepository.class.getName();

    @Inject
    AppDatabase db;
    @Inject
    CatalogApi api;

    private ListMapping<DbItem, Item> dbItemDomainMapper = new ListMapping<>(new DbToDomainItemMapper());
    private ListMapping<ItemDto, DbItem> itemDtoDbItemMapper = new ListMapping<>(new DtoToDbItemMapper());

    private static CatalogRepository INSTANCE;

    public CatalogRepository() {
        INSTANCE = this;
        App.getInstance().getComponent().inject(this);
    }

    public static CatalogRepository getInstance() {
        synchronized (App.class) {
            if (INSTANCE == null)
                INSTANCE = new CatalogRepository();
            return INSTANCE;
        }
    }

    public Flowable<List<Item>> provideNoveltyData() {
        return Flowable.concat(
                getNoveltyItemsFromApi()
                        .map(itemDtoDbItemMapper::map)
                        .map(dbItemDomainMapper::map),
                getItemsFromDbByNovelty())
                .debounce(400, TimeUnit.MILLISECONDS);
    }

    public Flowable<List<Item>> provideByCategoryData(Integer category) {
        return Flowable.concat(
                getItemsByCategory(category)
                        .map(itemDtoDbItemMapper::map)
                        .map(dbItemDomainMapper::map),
                getItemsFromDbByCategory(category)
        );
    }

    private Flowable<List<ItemDto>> getNoveltyItemsFromApi() {
        return api.catalog()
                .getNovelty()
                .map(ResponseDto::getData)
                .map(DataDto::getItems)
                .doOnSuccess(this::storeItemsInDb)
                .toFlowable()
                .subscribeOn(Schedulers.io());
    }

    private Flowable<List<ItemDto>> getItemsByCategory(Integer category) {
        return api.catalog()
                .getItemsByCategory(category)
                .map(ResponseDto::getData)
                .map(DataDto::getItems)
                .doOnSuccess(this::storeItemsInDb)
                .toFlowable()
                .subscribeOn(Schedulers.io());
    }

    private void storeItemsInDb(List<ItemDto> items) {
        List<DbItem> dbItems = itemDtoDbItemMapper.map(items);
        db.itemDao().insertAll(dbItems);
        Log.i(TAG, "storeItemsInDb: items stored");
    }

    private void storeCategoriesInDb(Map<Integer, String> categories){

    }

    private Flowable<List<Item>> getItemsFromDbByNovelty() {
        return db.itemDao().getByNovelty(true)
                .map(dbItemDomainMapper::map)
                .subscribeOn(Schedulers.io());
    }

    private Flowable<List<Item>> getItemsFromDbByCategory(Integer categoryId) {
        return db.itemDao().getByCategory(categoryId)
                .map(dbItemDomainMapper::map)
                .subscribeOn(Schedulers.io());
    }
}
