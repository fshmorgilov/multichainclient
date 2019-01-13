package com.themaker.fshmo.klassikaplus.dagger.module;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Room;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Предоставляет зависимость для БД.
 */
@Module
@Singleton
public class DataModule {

    private static final String DATABASE_NAME = "klassikaplus.db";
    AppDatabase db;
    CatalogRepository catalogRepository;

    public DataModule(AppDatabase db, CatalogRepository catalogRepository){
        this.db =db;
        this.catalogRepository = catalogRepository;
    }

    @NonNull
    @Provides
    @Singleton
    AppDatabase provideRoomDatabase(@NonNull final Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class,
                DATABASE_NAME)
                .build();
    }

    @Singleton
    @Provides
    CatalogRepository proviceCatalogRepository(AppDatabase db){
        return new CatalogRepository(db);
    }


}