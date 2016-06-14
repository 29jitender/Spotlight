package com.wooplr.spotlight.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREFERENCES_NAME = "spotlight_view_preferences";

    private SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public boolean isDisplayed(String id) {
        return sharedPreferences.getBoolean(id, false);
    }

    public void setDisplayed(String id) {
        sharedPreferences.edit().putBoolean(id, true).apply();
    }

    public void reset(String id) {
        sharedPreferences.edit().putBoolean(id, false).apply();
    }

    public void resetAll() {
        sharedPreferences.edit().clear().apply();
    }
}
