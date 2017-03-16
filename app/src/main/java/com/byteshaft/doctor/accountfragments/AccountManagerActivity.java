package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.byteshaft.doctor.R;
import com.byteshaft.doctor.introscreen.IntroScreen;

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
        setContentView(R.layout.activity_account_manager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        IntroScreen.getInstance().finish();
        sInstance = this;
        loadFragment(new Login());
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack("Fragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            Log.i("TAG", getSupportFragmentManager().getBackStackEntryAt(0).getName());
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        }
//        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
//            finish();
//        }
    }
}
