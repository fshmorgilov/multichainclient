package com.themaker.fshmo.klassikaplus.dagger.module;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.room.Room;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository;
import com.themaker.fshmo.klassikaplus.data.web.chain.CatalogApi;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Предоставляет зависимость для БД.
 */
@Module
public class DataModule {

    private static final String DATABASE_NAME = "klassikaplus.db"; // FIXME: 5/13/2019

    @NonNull
    @Provides
    @Singleton
    static AppDatabase provideRoomDatabase(@NonNull final Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class,
                DATABASE_NAME)
                .build();
    }

    @Provides
    @Singleton
    static CatalogRepository proviceCatalogRepository(AppDatabase db, CatalogApi api) {
        return new CatalogRepository();
    }

    @Provides
    @Singleton
    static CatalogApi provideCatalogApi() {
        return CatalogApi.getInstance();
    }


}