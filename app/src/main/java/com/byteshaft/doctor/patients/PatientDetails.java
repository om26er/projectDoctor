package com.byteshaft.doctor.patients;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.doctor.R;

public class PatientDetails extends AppCompatActivity implements View.OnClickListener {

    private TextView patientName;
    private TextView patientAge;
    private ImageButton callButton;
    private ImageButton chatButton;
    private Button appointmentButton;


    private EditText docId;
    private EditText birthDate;
    private EditText patientAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_patient_details);
        patientName = (TextView) findViewById(R.id.patient_name_);
        patientAge = (TextView) findViewById(R.id.patient_age_);
        callButton = (ImageButton) findViewById(R.id.call_button_);
        chatButton = (ImageButton) findViewById(R.id.chat_button_);
        appointmentButton = (Button) findViewById(R.id.button_appointment);
        appointmentButton.setOnClickListener(this);
        docId = (EditText) findViewById(R.id.doc_id);
        birthDate = (EditText) findViewById(R.id.birth_date);
        patientAddress = (EditText) findViewById(R.id.patient_address);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default: return false;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_appointment:
                startActivity(new Intent(getApplicationContext(), DoctorBookingActivity.class));
                break;
        }
    }
}
