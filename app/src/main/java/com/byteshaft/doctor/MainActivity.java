package com.byteshaft.patient;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.byteshaft.patient.accountfragments.AccountManagerActivity;
import com.byteshaft.patient.accountfragments.UserBasicInfoStepOne;
import com.byteshaft.patient.doctors.Appointments;
import com.byteshaft.patient.doctors.Dashboard;
import com.byteshaft.patient.doctors.DoctorsList;
import com.byteshaft.patient.doctors.MyPatients;
import com.byteshaft.patient.doctors.MySchedule;
import com.byteshaft.patient.doctors.Services;
import com.byteshaft.patient.introscreen.IntroScreen;
import com.byteshaft.patient.messages.MainMessages;
import com.byteshaft.patient.patients.FavouriteDoctors;
import com.byteshaft.patient.patients.MyAppointments;
import com.byteshaft.patient.utils.AppGlobals;
import com.byteshaft.patient.utils.Helpers;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.byteshaft.patient.utils.Helpers.getBitMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static MainActivity sInstance;
    private static final int STORAGE_PERMISSION = 0;

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
        Log.i("Token ",  AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_TOKEN));
        Log.i("DOC ID", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_PROFILE_ID));

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
//            docExpDate.setTypeface(AppGlobals.typefaceNormal);

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
            loadFragment(new Dashboard());

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
            String[] dob = age.split("/");
            Log.i("AGE", dob[0] + dob[1] + dob[2]);
            System.out.println("age is : " + age);

            int date = Integer.parseInt(dob[0]);
            int month = Integer.parseInt(dob[1]);
            int year = Integer.parseInt(dob[2]);
            String years = Helpers.getAge(year, month, date);
            patientAge.setText(years + " years");
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


        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
            alertDialogBuilder.setTitle(getResources().getString(R.string.storage_permission_dialog_title));
            alertDialogBuilder.setMessage(getResources().getString(R.string.storage_permission_message))
                    .setCancelable(false).setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            STORAGE_PERMISSION);
                }
            });
            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else {
            if (AppGlobals.isLogin() && AppGlobals.getStringFromSharedPreferences(AppGlobals.SERVER_PHOTO_URL) != null) {
                String url = String.format("%s" + AppGlobals
                        .getStringFromSharedPreferences(AppGlobals.SERVER_PHOTO_URL), AppGlobals.SERVER_IP);
                getBitMap(url, profilePicture);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String url = String.format("%s" + AppGlobals
                                    .getStringFromSharedPreferences(AppGlobals.SERVER_PHOTO_URL),
                            AppGlobals.SERVER_IP);
                    getBitMap(url, profilePicture);
                } else {
                    Helpers.showSnackBar(findViewById(android.R.id.content), R.string.permission_denied);
                }
                break;
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
        } else if (id == R.id.nav_messages) {
            loadFragment(new MainMessages());
        } else if (id == R.id.nav_profile) {
            loadFragment(new UserBasicInfoStepOne());
        } else if (id == R.id.nav_schedule) {
            loadFragment(new MySchedule());
        } else if (id == R.id.nav_my_services) {
            loadFragment(new Services());
        } else if (id == R.id.nav_exit) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
            alertDialogBuilder.setTitle("Confirmation");
            alertDialogBuilder.setMessage("Do you really want to exit?").setCancelable(false).setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.MyAlertDialogTheme);
            alertDialogBuilder.setTitle("Confirmation");
            alertDialogBuilder.setMessage("Do you really want to logout?")
                    .setCancelable(false).setPositiveButton("Yes",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            AppGlobals.clearSettings();
                            AppGlobals.firstTimeLaunch(true);
                            dialog.dismiss();
                            startActivity(new Intent(getApplicationContext(), IntroScreen.class));
                        }
                    });
            alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
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
}
