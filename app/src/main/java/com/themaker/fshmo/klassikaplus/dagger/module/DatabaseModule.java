package com.themaker.fshmo.klassikaplus.dagger.module;

import android.app.Application;
import androidx.room.Room;
import androidx.annotation.NonNull;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * Предоставляет зависимость для БД.
 */
@Module
@Singleton
public class DatabaseModule {

    private static final String DATABASE_NAME = "klassikaplus.db";

    @NonNull
    @Provides
    @Singleton
    AppDatabase provideRoomDatabase(@NonNull final Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class,
                DATABASE_NAME)
                .build();
    }


}