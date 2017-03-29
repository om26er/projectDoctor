package com.byteshaft.patient.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;

import com.byteshaft.patient.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
    public static final String SERVER_IP = "https://46.101.34.116";

    public static final String BASE_URL = String.format("%s/api/", SERVER_IP);
    public static final String REVIEW_URL = BASE_URL + "public/doctor/%s/review";
    public static final String KEY_USER_NAME = "user_name";
    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_PROFILE_ID = "id";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_DOC_SPECIALITY = "speciality";
    public static final String KEY_DOC_ID = "identity_document";
    public static final String KEY_COLLEGE_ID = "college_id";
    public static final String KEY_DATE_OF_BIRTH = "dob";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_LOCATION = "location";
    public static final String KEY_IMAGE_URL = "photo";
    public static final String SERVER_PHOTO_URL = "server_photo_url";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PHONE_NUMBER_PRIMARY = "phone_number_primary";
    public static final String KEY_PHONE_NUMBER_SECONDARY = "phone_number_secondary";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ACCOUNT_TYPE = "account_type";
    public static final String KEY_USER_ID = "id";
    public static final String KEY_AFFILIATE_CLINIC = "affiliate_clinic";
    public static final String KEY_CHAT_STATUS = "available_to_chat";
    public static final String KEY_INSURANCE_CARRIER = "insurance_carrier";
    public static final String KEY_EMERGENCY_CONTACT = "emergency_contact";
    public static final String KEY_SUBSCRIPTION_TYPE = "subscription_plan";
    public static final String KEY_CONSULTATION_TIME = "consultation_time";
    public static final String KEY_REVIEW_STARS = "review_stars";
    public static final String KEY_SHOW_NEWS = "show_news";
    public static final String KEY_SHOW_NOTIFICATION = "show_notification";
    public static final String KEY_STATE = "state";
    public static final String KEY_CITY = "city";
    public static final String KEY_USER = "user";
    public static final String KEY_GOT_INFO = "got_info";

    public static final String KEY_TOKEN = "token";
    public static final String USER_ACTIVATION_KEY = "activation_key";
    public static final int LOCATION_ENABLE = 3;
    public static ImageLoader sImageLoader;
    public static final int CALL_PERMISSION = 4;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseMessaging.getInstance().subscribeToTopic("admin");
        disableSSLCertificateChecking();
        sImageLoader = ImageLoader.getInstance();
        sImageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
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

    private static void disableSSLCertificateChecking() {
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                            throws CertificateException {
                    }
                }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return s.equals("46.101.34.116");
            }
        });

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static void loginState(boolean type) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(KEY_LOGIN, type).apply();
    }

    public static boolean isLogin() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

    public static void userType(boolean type) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(KEY_ACCOUNT_TYPE, type).apply();
    }

    public static boolean isDoctor() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(KEY_ACCOUNT_TYPE, false);
    }

    public static SharedPreferences getPreferenceManager() {
        return getContext().getSharedPreferences("shared_prefs", MODE_PRIVATE);
    }

    public static void clearSettings() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().clear().apply();
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
        sharedPreferences.edit().putBoolean(IS_FIRST_TIME_LAUNCH, value).apply();
    }

    public static boolean isFirstTimeLaunch() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, false);
    }

    public static Context getContext() {
        return sContext;
    }

    public static void alertDialog(Activity activity, String title, String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.MyAlertDialogTheme);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(msg).setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void gotInfo(boolean type) {
        SharedPreferences sharedPreferences = getPreferenceManager();
        sharedPreferences.edit().putBoolean(KEY_GOT_INFO, type).apply();
    }

    public static boolean isInfoAvailable() {
        SharedPreferences sharedPreferences = getPreferenceManager();
        return sharedPreferences.getBoolean(KEY_GOT_INFO, false);
    }
}

