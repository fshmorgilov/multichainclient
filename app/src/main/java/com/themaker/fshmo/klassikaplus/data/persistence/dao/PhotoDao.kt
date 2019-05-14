package com.themaker.fshmo.klassikaplus.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbPhoto
import io.reactivex.Single

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    fun all(): Single<List<DbPhoto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<DbPhoto>)

    @Query("DELETE FROM PHOTOS")
    fun deleteAll()

    @Query("SELECT * FROM PHOTOS WHERE id in (:id)")
    fun findById(id: String): Single<DbPhoto>

    @Query("SELECT * FROM PHOTOS WHERE item_id in (:itemId)")
    fun findByItemId(itemId: String): Single<List<DbPhoto>>

}
