package com.byteshaft.doctor.patients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;

import com.byteshaft.doctor.R;

public class CreateAppointActivity extends AppCompatActivity {

    private Spinner serviceListSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appoint);
        serviceListSpinner = (Spinner) findViewById(R.id.service_spinner);
    }
}
