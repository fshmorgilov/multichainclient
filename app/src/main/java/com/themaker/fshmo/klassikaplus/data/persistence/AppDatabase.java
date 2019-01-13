package com.themaker.fshmo.klassikaplus.data.persistence;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.themaker.fshmo.klassikaplus.data.persistence.dao.ItemDao;
import com.themaker.fshmo.klassikaplus.data.persistence.model.DbItem;

@Database(entities = {
        DbItem.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemDao itemDao();
}