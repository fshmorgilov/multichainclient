package com.themaker.fshmo.klassikaplus.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ItemDao {

    @Query("SELECT * FROM ITEMS")
    fun all(): Single<List<DbItem>>

    @Query("SELECT * FROM ITEMS WHERE novelty = :isNovelty")
    fun getByNovelty(isNovelty: Boolean): Flowable<List<DbItem>>

    @Query("SELECT * FROM ITEMS WHERE category = :categoryId")
    fun getByCategory(categoryId: Int?): Flowable<List<DbItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<DbItem>)

    @Query("DELETE FROM ITEMS")
    fun deleteAll()

    @Query("SELECT * FROM ITEMS WHERE title IN (:name) OR name IN (:name)")
    fun findByName(name: String): Single<DbItem>

    @Query("SELECT * FROM ITEMS WHERE id in (:id)")
    fun findById(id: String): Single<DbItem>
}
