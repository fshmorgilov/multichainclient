package com.themaker.fshmo.klassikaplus.data.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbCategory
import io.reactivex.Single

@Dao
interface CategoryDao {

    @Query("SELECT * FROM CATEGORIES")
    fun all(): Single<List<DbCategory>>

    @Query("SELECT * FROM CATEGORIES WHERE id in (:id)")
    fun findById(id: Int?): Single<DbCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(category: List<DbCategory>)

}
