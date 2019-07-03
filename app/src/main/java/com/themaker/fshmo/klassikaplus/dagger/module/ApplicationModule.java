package com.themaker.fshmo.klassikaplus.dagger.module;

import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.themaker.fshmo.klassikaplus.data.preferences.Preferences;
import com.themaker.fshmo.klassikaplus.data.repositories.RequestFactory;
import com.themaker.fshmo.klassikaplus.data.web.chain.ChainApi;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    SharedPreferences sharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    NotificationManager notificationManager(Application application) {
        return (NotificationManager) application.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Provides
    @Singleton
    LocalBroadcastManager localBroadcastManager(Application application) {
        return LocalBroadcastManager.getInstance(application);
    }

    @Provides
    @Singleton
    Preferences preferences(SharedPreferences sharedPreferences) {
        return new Preferences(sharedPreferences);
    }

    @Provides
    @Singleton
    RequestFactory requestFactory(ChainApi api, Preferences preferences) {
        return new RequestFactory(api, preferences);
    }
}