package com.themaker.fshmo.klassikaplus.data.repositories;

import android.util.Log;
import com.themaker.fshmo.klassikaplus.App;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.data.mappers.DbToDomainMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.DtoToDbItemMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.ListMapping;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;
import com.themaker.fshmo.klassikaplus.data.repositories.filters.NoveltinessFilter;
import com.themaker.fshmo.klassikaplus.data.web.catalog.CatalogApi;
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.DataDto;
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.ItemDto;
import com.themaker.fshmo.klassikaplus.data.web.dto.catalog.ResponseDto;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogRepository extends BaseRepository {

    private static final String TAG = CatalogRepository.class.getName();

    @Inject
    AppDatabase db;
    @Inject
    CatalogApi api;

    private ListMapping<DbItem, Item> dbItemDomainMapper = new ListMapping<>(new DbToDomainMapper());
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
                getItemsFromApi()
                        .map(itemDtoDbItemMapper::map)
                        .map(dbItemDomainMapper::map),
                getItemsFromDb())
                .debounce(400, TimeUnit.MILLISECONDS);
    }

    private Flowable<List<ItemDto>> getItemsFromApi() {
        return api.catalog()
                .getNovelty()
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

    private Flowable<List<Item>> getItemsFromDb() {
        return db.itemDao().getAll()
                .map(dbItemDomainMapper::map)
                .doOnSuccess(items -> Log.i(TAG, "getItemsFromDb: getting items from db"))
                .toFlowable()
                .subscribeOn(Schedulers.io());
    }

}
