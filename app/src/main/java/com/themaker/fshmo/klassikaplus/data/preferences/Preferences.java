package com.themaker.fshmo.klassikaplus.data.preferences;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import io.reactivex.subjects.PublishSubject;

import javax.inject.Inject;

/**
 * Враппер над SharedPreferences.
 */
public class Preferences {

    private static final String KEY_FIRST_RUN = "FIRST_RUN";
    private static final String KEY_MOCK = "MOCK";
    private static final Gson GSON = new Gson();
    private static final String KEY_USER = "USER";
    private static final String KEY_F_USER_ID = "F_USER_ID";
    private static final String KEY_REVISION = "REVISION";
    private static final int DEFAULT_REVISION = 1;


    private final SharedPreferences sharedPreferences;

    @Inject
    public Preferences(SharedPreferences preferences) {
        sharedPreferences = preferences;
    }

    public void updateRevision(@NonNull Integer revision){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_REVISION, revision);
        editor.apply();
    }

    public Integer getRevision(){
        return sharedPreferences.getInt(KEY_REVISION,DEFAULT_REVISION);
    }
}