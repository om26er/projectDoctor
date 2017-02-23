package com.byteshaft.doctor.patients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.byteshaft.doctor.R;

public class PatientDetails extends AppCompatActivity {

    TextView patientName;
    TextView patientAge;
    ImageButton callButton;
    ImageButton chatButton;
    Button appointmentButton;

    EditText docId;
    EditText birthDate;
    EditText patientAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        patientName = (TextView) findViewById(R.id.patient_name_);
        patientAge = (TextView) findViewById(R.id.patient_age_);
        callButton = (ImageButton) findViewById(R.id.call_button_);
        chatButton = (ImageButton) findViewById(R.id.chat_button_);
        appointmentButton = (Button) findViewById(R.id.button_appointment);

        docId = (EditText) findViewById(R.id.doc_id);
        birthDate = (EditText) findViewById(R.id.birth_date);
        patientAddress = (EditText) findViewById(R.id.patient_address);
    }
}
