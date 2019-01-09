package com.themaker.fshmo.klassikaplus.data.preferences;

import android.content.SharedPreferences;
import com.google.gson.Gson;
import io.reactivex.subjects.PublishSubject;

/**
 * Враппер над SharedPreferences.
 */
public class Preferences {

    private static final String KEY_FIRST_RUN = "FIRST_RUN";
    private static final String KEY_MOCK = "MOCK";
    private static final Gson GSON = new Gson();
    private static final String KEY_USER = "USER";
    private static final String KEY_F_USER_ID = "F_USER_ID";

    private final SharedPreferences sharedPreferences;

    public Preferences(SharedPreferences preferences) {
        sharedPreferences = preferences;
        //todo
    }
}