package com.themaker.fshmo.klassikaplus.dagger;

import com.themaker.fshmo.klassikaplus.dagger.module.ApplicationModule;
import com.themaker.fshmo.klassikaplus.dagger.module.DataModule;
import com.themaker.fshmo.klassikaplus.data.preferences.Preferences;
import com.themaker.fshmo.klassikaplus.data.repositories.CatalogRepository;
import com.themaker.fshmo.klassikaplus.data.repositories.RequestFactory;
import com.themaker.fshmo.klassikaplus.presentation.root.MainActivity;
import com.themaker.fshmo.klassikaplus.service.RevisionRequestService;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataModule.class})
public interface AppComponent {

    void inject(MainActivity activity);

    void inject(RevisionRequestService service);

    void inject(Preferences preferences);

    void inject(CatalogRepository repository);

    void inject(RequestFactory factory);

}
