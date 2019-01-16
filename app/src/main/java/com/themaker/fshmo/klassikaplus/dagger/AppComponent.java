package com.themaker.fshmo.klassikaplus.dagger;

import android.app.Application;
import com.themaker.fshmo.klassikaplus.dagger.module.ApplicationModule;
import com.themaker.fshmo.klassikaplus.dagger.module.DataModule;
import com.themaker.fshmo.klassikaplus.data.persistence.AppDatabase;
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DataModule.class, ApplicationModule.class})
public interface AppComponent {

    void inject(AppDatabase database);
    void inject(CatalogRepository repository);
//    Application providesApplication();
//
//    AppDatabase getDb();
//
//    CatalogRepository getCatalogRepository();

}
