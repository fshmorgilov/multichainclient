package com.themaker.fshmo.klassikaplus.data.repositories;

import android.util.Log;
import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.data.mappers.DbToDomainMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.ListMapping;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;
import com.themaker.fshmo.klassikaplus.data.repositories.filters.NoveltinessFilter;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CatalogRepository extends BaseRepository {

    private static final String TAG = CatalogRepository.class.getName();

    private AppDatabase db;
    private ListMapping<DbItem, Item> listMapper = new ListMapping<>(new DbToDomainMapper());
    private NoveltinessFilter noveltinessFilter = new NoveltinessFilter();

    @Inject
    public CatalogRepository(AppDatabase db) {
        this.db = db;
    }

    public Flowable<List<Item>> provideNoveltyData() {
        return Flowable.concatArray(
                getItemFromDb(),
                getItemsFromApi()
                // TODO: 1/13/2019 concat array
        ).debounce(400, TimeUnit.MILLISECONDS);
    }

    private Flowable<ArrayList<Item>> getItemsFromApi() {
        return Flowable.just(new ArrayList<Item>())
                .doOnNext(items -> {
                    Log.i(TAG, "getItemsFromApi: storing users in DB");
                    storeItemsInDb(items);
                });
    }

    private void storeItemsInDb(List<Item> items) {
        // TODO: 1/13/2019 <apper amd download
    }

    private Flowable<List<Item>> getItemFromDb() {
        return db.itemDao()
                .getAll()
                .map(listMapper::map)
                .map(noveltinessFilter::filter)
                .subscribeOn(Schedulers.io());
    }

}
