package com.themaker.fshmo.klassikaplus.data.persistence

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.themaker.fshmo.klassikaplus.data.persistence.dao.CategoryDao
import com.themaker.fshmo.klassikaplus.data.persistence.dao.ItemDao
import com.themaker.fshmo.klassikaplus.data.persistence.dao.PhotoDao
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbCategory
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbPhoto

@Database(entities = [DbItem::class, DbPhoto::class, DbCategory::class],
    version = 2,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao
    abstract fun photoDao(): PhotoDao
    abstract fun categoryDao(): CategoryDao

    companion object {

        private val DATABASE_NAME = "klassikaplus.db"

        internal fun provideRoomDatabase(application: Application): AppDatabase {
            return Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .build()
        }
    }
}