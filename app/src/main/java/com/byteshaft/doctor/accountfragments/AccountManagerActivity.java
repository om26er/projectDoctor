package com.byteshaft.doctor.accountfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;
import com.byteshaft.doctor.introscreen.IntroScreen;
import com.byteshaft.doctor.utils.AppGlobals;

/**
 * Created by s9iper1 on 3/16/17.
 */

public class AccountManagerActivity extends AppCompatActivity {

    private static AccountManagerActivity sInstance;

    public static AccountManagerActivity getInstance() {
        return sInstance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!AppGlobals.isLogin()) {
            loadFragment(new Login());
        } else if (!AppGlobals.isInfoAvailable()){
            loadFragment(new UserBasicInfoStepOne());
        } else {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        setContentView(R.layout.activity_account_manager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        IntroScreen.getInstance().finish();
        sInstance = this;
    }

    public void loadFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, backStateName);
        FragmentManager manager = getSupportFragmentManager();
        Log.i("TAG", backStateName);
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped) {
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment =  getSupportFragmentManager()
                .findFragmentByTag("com.byteshaft.doctor.accountfragments.AccountActivationCode");
        if (fragment instanceof AccountActivationCode) {
            Log.i("TAG", "fragment " + fragment.isVisible());

        } else {
            Log.i("TAG", "count "+ getSupportFragmentManager().getBackStackEntryCount());

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                this.finish();
            }
//        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//            finish();
//        }
            super.onBackPressed();
        }
    }
}
