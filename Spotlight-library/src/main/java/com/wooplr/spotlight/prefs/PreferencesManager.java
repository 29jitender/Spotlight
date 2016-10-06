package com.wooplr.spotlight.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    private static final String PREFERENCES_NAME = "spotlight_view_preferences";
    private static final String TIMES_DISPLAY_POSTFIX = "_times_displayed";

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

    public int getTimesDisplayed(String id) {
        String timesDisplay = id + TIMES_DISPLAY_POSTFIX;
        return sharedPreferences.getInt(timesDisplay, 0);
    }

    public void setTimesDisplayed(String id, int times) {
        String timesDisplay = id + TIMES_DISPLAY_POSTFIX;
        sharedPreferences.edit().putInt(timesDisplay, times).apply();
    }

    public void reset(String id) {
        sharedPreferences.edit().putBoolean(id, false).apply();
    }

    public void resetAll() {
        sharedPreferences.edit().clear().apply();
    }
}
