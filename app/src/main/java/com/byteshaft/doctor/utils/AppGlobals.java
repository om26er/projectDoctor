package com.byteshaft.doctor.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

/**
 * Created by s9iper1 on 2/20/17.
 */

public class AppGlobals extends Application {


    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static Context sContext;
    public static final String IS_DOCTOR = "is_doctor";
    public static Typeface typefaceBold;
    public static Typeface typefaceNormal;
    public static Typeface robotoBlack;
    public static Typeface robotoBlackItalic;
    public static Typeface robotoBold;
    public static Typeface robotoBoldItalic;
    public static Typeface robotoItalic;
    public static Typeface robotoLight;
    public static Typeface robotoLightItalic;
    public static Typeface robotoMedium;
    public static Typeface robotoMediumItalic;
    public static Typeface robotoRegular;
    public static Typeface robotoThin;
    public static Typeface robotoThinItalic;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        typefaceBold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/bold.ttf");
        typefaceNormal = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/normal.ttf");
        robotoBlack = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_Black.ttf");
        robotoBlackItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_BlackItalic.ttf");
        robotoBold = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_Bold.ttf");
        robotoBoldItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_BoldItalic.ttf");
        robotoItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_Italic.ttf");
        robotoLight = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_Light.ttf");
        robotoLightItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_LightItalic.ttf");
        robotoMedium = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_Medium.ttf");
        robotoMediumItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_MediumItalic.ttf");
        robotoRegular = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_Regular.ttf");
        robotoThin = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_Thin.ttf");
        robotoThinItalic = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/Roboto_ThinItalic.ttf");
    }

    public static SharedPreferences getPreferenceManager() {
        return getContext().getSharedPreferences("shared_prefs", MODE_PRIVATE);
    }

    public static void firstTimeLaunch(boolean value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(AppGlobals.IS_FIRST_TIME_LAUNCH, value).apply();
    }

    public static boolean isFirstTimeLaunch() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(AppGlobals.IS_FIRST_TIME_LAUNCH, true);
    }

    public static Context getContext() {
        return sContext;
    }
}
