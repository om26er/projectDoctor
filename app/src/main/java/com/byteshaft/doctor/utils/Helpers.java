package com.byteshaft.doctor.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by s9iper1 on 2/20/17.
 */

public class Helpers {

    // get default sharedPreferences.
    private static SharedPreferences getPreferenceManager() {
        return PreferenceManager.getDefaultSharedPreferences(AppGlobals.getContext());
    }

    public static boolean isDoctor() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(AppGlobals.IS_DOCTOR, false);
    }

    public static void userType(boolean type) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(AppGlobals.IS_DOCTOR, type).apply();
    }
}
