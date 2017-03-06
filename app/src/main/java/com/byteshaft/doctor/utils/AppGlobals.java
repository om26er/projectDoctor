package com.byteshaft.doctor.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;

import com.byteshaft.doctor.R;

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

    public static final String BASE_URL = "http://46.101.34.116/api/";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_AACOUNT_TYPE = "account_type";
    public static final String KEY_USER_ID = "id";
    public static final String KEY_PHONE_NUMBER = "mobile_number";
    public static final String KEY_TOKEN = "token";
    public static final String USER_ACTIVATION_KEY = "activation_key";

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

    public static void saveDataToSharedPreferences(String key, String value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putString(key, value).apply();
    }

    public static String getStringFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getString(key, "");
    }

    public static void firstTimeLaunch(boolean value) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(AppGlobals.IS_FIRST_TIME_LAUNCH, value).apply();
    }

    public static boolean isFirstTimeLaunch() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(AppGlobals.IS_FIRST_TIME_LAUNCH, false);
    }

    public static Context getContext() {
        return sContext;
    }

    public static void alertDialog(Activity activity, String title, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

