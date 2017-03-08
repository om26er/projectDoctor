package com.byteshaft.doctor.patients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class DoctorBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_booking);
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());

        com.byteshaft.doctor.uihelpers.CalendarView cv = ((com.byteshaft.doctor.uihelpers.CalendarView)
                findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new com.byteshaft.doctor.uihelpers.CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date)
            {
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(DoctorBookingActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
