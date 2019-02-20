package com.themaker.fshmo.klassikaplus;

import android.app.Application;
import androidx.work.*;
import com.themaker.fshmo.klassikaplus.dagger.AppComponent;
import com.themaker.fshmo.klassikaplus.dagger.DaggerAppComponent;
import com.themaker.fshmo.klassikaplus.dagger.module.ApplicationModule;
import com.themaker.fshmo.klassikaplus.dagger.module.DataModule;
import com.themaker.fshmo.klassikaplus.service.RevisionRequestService;

import java.util.concurrent.TimeUnit;

//import com.facebook.stetho.Stetho;

public class App extends Application {

    private static AppComponent appComponent;
    private static App APP_INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        APP_INSTANCE = this;
//        Stetho.initializeWithDefaults(this);
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();
    }

    private static void performScheduledWork(){
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();
        WorkRequest workRequest = new PeriodicWorkRequest.Builder(RevisionRequestService.class, 24, TimeUnit.HOURS)
                .setConstraints(constraints)
                .addTag(RevisionRequestService.WORK_TAG)
                .build();
        NetworkUtils.getInstance().getCancelReceiver().setWorkRequestId(workRequest.getId());
        WorkManager.getInstance()
                .enqueue(workRequest);
    }

    public static App getInstance() {
        return APP_INSTANCE;
    }


    public AppComponent getComponent(){
        return appComponent;
    }
}
