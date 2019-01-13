package com.themaker.fshmo.klassikaplus.dagger;

import com.themaker.fshmo.klassikaplus.dagger.module.ApplicationModule;
import com.themaker.fshmo.klassikaplus.dagger.module.DataModule;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DataModule.class, ApplicationModule.class})
public interface AppComponent {
    AppDatabase getDb();
    CatalogRepository getCatalogRepository();
}
