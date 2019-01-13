package com.themaker.fshmo.klassikaplus.data.repositories;

import com.themaker.fshmo.klassikaplus.data.domain.Item;
import com.themaker.fshmo.klassikaplus.data.mappers.DbToDomainMapper;
import com.themaker.fshmo.klassikaplus.data.mappers.ListMapping;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;
import com.themaker.fshmo.klassikaplus.data.repositories.filters.NoveltinessFilter;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CatalogRepository extends BaseRepository {

    private AppDatabase db;
    private ListMapping<DbItem, Item> listMapper = new ListMapping<>(new DbToDomainMapper());
    private NoveltinessFilter noveltinessFilter = new NoveltinessFilter();

    @Inject
    public CatalogRepository(AppDatabase db) {
        this.db = db;
    }

    public Flowable<List<Item>> provideNoveltyData(){
        return db.itemDao()
                .getAll()
                .map(listMapper::map)
                .map(noveltinessFilter::filter)
                .subscribeOn(Schedulers.io());
    }
}
