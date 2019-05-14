package com.themaker.fshmo.klassikaplus.data.preferences

import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import io.reactivex.subjects.PublishSubject

import javax.inject.Inject

/**
 * Враппер над SharedPreferences.
 */
class Preferences @Inject
constructor(private val sharedPreferences: SharedPreferences) {

    val revision: Int
        get() {
            val rev = sharedPreferences.getInt(KEY_REVISION, DEFAULT_REVISION)
            Log.i(TAG, "getRevision: current revision: $rev")
            return rev
        }

    val isFirstTimeAppLaunch: Boolean
        get() {
            Log.i(TAG, "isFirstTimeAppLaunch: ")
            return sharedPreferences.getBoolean(KEY_FIRST_RUN, true)
        }

    fun updateRevision(revision: Int) {
        Log.i(TAG, "updateRevision: updating to: $revision")
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_REVISION, revision)
        editor.apply()
    }

    fun setFirstTimeAppLaunch() {
        Log.i(TAG, "setFirstTimeAppLaunch: disabling first launch features")
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_FIRST_RUN, false)
        editor.apply()
    }

    companion object {

        private val KEY_FIRST_RUN = "FIRST_RUN"
        private val KEY_MOCK = "MOCK"
        private val GSON = Gson()
        private val KEY_USER = "USER"
        private val KEY_F_USER_ID = "F_USER_ID"
        private val KEY_REVISION = "REVISION"
        private val DEFAULT_REVISION = 1
        private val TAG = Preferences::class.java.name
    }
}