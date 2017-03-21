package com.byteshaft.doctor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.byteshaft.doctor.accountfragments.AccountManagerActivity;
import com.byteshaft.doctor.doctors.Appointments;
import com.byteshaft.doctor.doctors.Dashboard;
import com.byteshaft.doctor.doctors.DoctorsList;
import com.byteshaft.doctor.doctors.MyPatients;
import com.byteshaft.doctor.introscreen.IntroScreen;
import com.byteshaft.doctor.patients.FavouriteDoctors;
import com.byteshaft.doctor.patients.MyAppointments;
import com.byteshaft.doctor.utils.AppGlobals;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static MainActivity sInstance;

    public static MainActivity getInstance() {
        return sInstance;
    }

    private CircleImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AccountManagerActivity.getInstance() != null) {
            AccountManagerActivity.getInstance().finish();
        }
        if (IntroScreen.getInstance() != null) {
            IntroScreen.getInstance().finish();
        }
        sInstance = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (AppGlobals.isDoctor()) {
            View headerView;
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {

                }
            });
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setItemIconTintList(null);
            headerView = getLayoutInflater().inflate(R.layout.nav_header_doctor, navigationView, false);
            navigationView.addHeaderView(headerView);
            navigationView.inflateMenu(R.menu.doctor_menus);
            /// Doctor's Navigation items

            TextView docName = (TextView) headerView.findViewById(R.id.doc_nav_name);
            TextView docEmail = (TextView) headerView.findViewById(R.id.doc_nav_email);
            TextView docSpeciality = (TextView) headerView.findViewById(R.id.doc_nav_speciality);
            TextView docExpDate = (TextView) headerView.findViewById(R.id.doc_nav_expiry_date);
            profilePicture = (CircleImageView) headerView.findViewById(R.id.nav_imageView);

            //setting typeface
            docName.setTypeface(AppGlobals.typefaceNormal);
            docEmail.setTypeface(AppGlobals.typefaceNormal);
            docSpeciality.setTypeface(AppGlobals.typefaceNormal);
            docExpDate.setTypeface(AppGlobals.typefaceNormal);

            // setting up information
            docName.setText(AppGlobals.getStringFromSharedPreferences(
                    AppGlobals.KEY_FIRST_NAME) + " " +
                    AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_LAST_NAME));
            docEmail.setText(AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_EMAIL));
            docSpeciality.setText(AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_DOC_SPECIALITY));
            final SwitchCompat doctorOnlineSwitch = (SwitchCompat) headerView.findViewById(R.id.doc_nav_online_switch);
            doctorOnlineSwitch.setTypeface(AppGlobals.typefaceNormal);
            doctorOnlineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    switch (compoundButton.getId()) {
                        case R.id.doc_nav_online_switch:
                            if (b) {
                                doctorOnlineSwitch.setText(R.string.online);
                            } else {
                                doctorOnlineSwitch.setText(R.string.offline);
                            }
                            break;
                    }
                }
            });

        } else {
            View headerView;
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {

                }
            });
            navigationView.setNavigationItemSelectedListener(this);
            navigationView.setItemIconTintList(null);
            headerView = getLayoutInflater().inflate(R.layout.nav_header_patient, navigationView, false);
            navigationView.addHeaderView(headerView);
            navigationView.inflateMenu(R.menu.patient_menu);
            TextView patientName = (TextView) headerView.findViewById(R.id.patient_nav_name);
            TextView patientEmail = (TextView) headerView.findViewById(R.id.patient_nav_email);
            TextView patientAge = (TextView) headerView.findViewById(R.id.patient_nav_age);
            profilePicture = (CircleImageView) headerView.findViewById(R.id.nav_imageView);
            patientName.setText(AppGlobals.getStringFromSharedPreferences(
                    AppGlobals.KEY_FIRST_NAME) + " " +
                    AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_LAST_NAME));
            String age = AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_DATE_OF_BIRTH);
            patientAge.setText(age);
            final SwitchCompat patientOnlineSwitch = (SwitchCompat) headerView.findViewById(R.id.patient_nav_online_switch);
            patientEmail.setText(AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_EMAIL));
            patientOnlineSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    switch (compoundButton.getId()) {
                        case R.id.patient_nav_online_switch:
                            if (b) {
                                patientOnlineSwitch.setText(R.string.online);
                            } else {
                                patientOnlineSwitch.setText(R.string.offline);
                            }
                            break;
                    }
                }
            });
            loadFragment(new DoctorsList());
        }
        if (AppGlobals.isLogin() && AppGlobals.getStringFromSharedPreferences(AppGlobals.SERVER_PHOTO_URL) != null) {
            new GetBitMap().execute();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_dashboard) {
            loadFragment(new Dashboard());
        } else if (id == R.id.nav_search_doctor) {
            loadFragment(new DoctorsList());
        } else if (id == R.id.nav_appointment) {
            loadFragment(new MyAppointments());
        } else if (id == R.id.nav_favt_doc) {
            loadFragment(new FavouriteDoctors());
        } else if (id == R.id.nav_patients) {
            loadFragment(new MyPatients());
        } else if (id == R.id.nav_doc_appointment) {
            loadFragment(new Appointments());
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadFragment(Fragment fragment) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.container, fragment);
        tx.commit();
    }

    private class GetBitMap extends AsyncTask<String, String, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            return getBitmapFromURL();
        }

        public Bitmap getBitmapFromURL() {
            Log.i("TAG", "url " + AppGlobals.getStringFromSharedPreferences(AppGlobals.SERVER_PHOTO_URL));
            try {
                URL url = new URL(String.format("%s"+
                        AppGlobals.getStringFromSharedPreferences(AppGlobals.SERVER_PHOTO_URL),
                        AppGlobals.SERVER_IP));
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Log.i("TAG", " " + String.valueOf(bitmap == null));
            profilePicture.setImageBitmap(bitmap);
        }
    }
}
