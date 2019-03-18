package com.themaker.fshmo.klassikaplus.data.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.themaker.fshmo.klassikaplus.data.domain.ItemCategory;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;
import io.reactivex.Flowable;
import io.reactivex.Single;

import java.util.List;

@Dao
public interface ItemDao {

    @Query("SELECT * FROM ITEMS")
    Single<List<DbItem>> getAll();
    // TODO: 2/8/2019 Refactor to Flawable

    @Query("SELECT * FROM ITEMS WHERE novelty = :isNovelty")
    Flowable<List<DbItem>> getByNovelty(boolean isNovelty);

    // FIXME: 18.03.2019 toString
    @Query("SELECT * FROM ITEMS WHERE category = :category")
    Flowable<List<DbItem>> getByCategory(String category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<DbItem> items);

    @Query("DELETE FROM ITEMS")
    void deleteAll();

    @Query("SELECT * FROM ITEMS WHERE title IN (:name) OR name IN (:name)")
    Single<DbItem> findByName(String name);

    @Query("SELECT * FROM ITEMS WHERE id in (:id)")
    Single<DbItem> findById(String id);
}
