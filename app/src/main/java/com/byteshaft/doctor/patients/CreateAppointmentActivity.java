package com.byteshaft.doctor.patients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.byteshaft.doctor.R;

import java.util.ArrayList;
import java.util.List;

public class CreateAppointmentActivity extends AppCompatActivity {

    private Spinner serviceListSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_create_appoint);
        serviceListSpinner = (Spinner) findViewById(R.id.service_spinner);
        List<String> serviceList = new ArrayList<>();
        serviceList.add("Service 1");
        serviceList.add("Service 2");
        serviceList.add("Service 3");
        serviceList.add("Service 4");
        serviceList.add("Service 5");
        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, serviceList);
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceListSpinner.setAdapter(serviceAdapter);
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
}
