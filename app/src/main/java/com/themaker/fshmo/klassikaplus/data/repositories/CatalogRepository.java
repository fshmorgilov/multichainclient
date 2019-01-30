package com.themaker.fshmo.klassikaplus.data.repositories;

import android.util.Log;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.data.mappers.DbToDomainMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.DtoToDbItemMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.ListMapping;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;
import com.themaker.fshmo.klassikaplus.data.repositories.filters.NoveltinessFilter;
import com.themaker.fshmo.klassikaplus.data.web.catalog.CatalogApi;
import com.themaker.fshmo.klassikaplus.data.web.dto.DataDto;
import com.themaker.fshmo.klassikaplus.data.web.dto.ItemDto;
import com.themaker.fshmo.klassikaplus.data.web.dto.ResponseDto;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.List;

public class CatalogRepository extends BaseRepository {

    private static final String TAG = CatalogRepository.class.getName();

    private AppDatabase db;
    private CatalogApi api;
    private ListMapping<DbItem, Item> dbItemDomainMapper = new ListMapping<>(new DbToDomainMapper());
    private ListMapping<ItemDto, DbItem> itemDtoDbItemMapper = new ListMapping<>(new DtoToDbItemMapper());
    private NoveltinessFilter noveltinessFilter = new NoveltinessFilter();

    @Inject
    public CatalogRepository(AppDatabase db, CatalogApi api) {
        this.db = db;
        this.api = api;
    }

    // TODO: 1/30/2019 Переделать если будет более 100 элементов
    public Flowable<List<Item>> provideNoveltyData() {
        getItemsFromApi();
        return getItemsFromDb();
//        return Observable.concatArray(
//                getItemsFromDb(),
//                getItemsFromApi()
        // TODO: 1/13/2019 concat array
//        ).debounce(400, TimeUnit.MILLISECONDS);
    }

    private void getItemsFromApi() {
        Disposable disposable = api.catalog()
                .getNovelty()
                .map(ResponseDto::getData)
                .map(DataDto::getItems)
                .doOnSuccess(itemsFromApi -> {
                    Log.i(TAG, "getItemsFromApi: storing users in DB");
                    storeItemsInDb(itemsFromApi);
                })
                // TODO: 1/30/2019 refactor to Flowable
                .observeOn(Schedulers.io())
                .subscribe();
    }

    private void storeItemsInDb(List<ItemDto> items) {
        List<DbItem> dbItems = itemDtoDbItemMapper.map(items);
        db.itemDao().insertAll(dbItems);
        Log.i(TAG, "storeItemsInDb: items store");
    }

    private Flowable<List<Item>> getItemsFromDb() {
        return db.itemDao()
                .getAll()
                .map(dbItemDomainMapper::map)
                .map(noveltinessFilter::filter)
                .observeOn(Schedulers.io());
    }

}
