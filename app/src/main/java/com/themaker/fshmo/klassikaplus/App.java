package com.themaker.fshmo.klassikaplus;

import android.app.Application;
import com.themaker.fshmo.klassikaplus.dagger.AppComponent;

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

    }
}
