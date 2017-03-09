package com.byteshaft.doctor.doctors;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.doctor.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by s9iper1 on 3/9/17.
 */

public class Appointments extends Fragment {

    private View mBaseView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.appointments, container, false);
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        com.byteshaft.doctor.uihelpers.CalendarView calendarView = ((com.byteshaft.doctor.uihelpers.CalendarView)
                mBaseView.findViewById(R.id.calendar_view));
        calendarView.updateCalendar(events);
        TextView dateTextview = (TextView) calendarView.findViewById(R.id.calendar_date_display);
        Log.i("TAG", dateTextview.getText().toString());
        dateTextview.setTextColor(getResources().getColor(R.color.header_background));

        // assign event handler
        calendarView.setEventHandler(new com.byteshaft.doctor.uihelpers.CalendarView.EventHandler() {
            @Override
            public void onDayPress(Date date) {
                Log.i("TAG", "click");
                // show returned day
                DateFormat df = SimpleDateFormat.getDateInstance();
                Toast.makeText(getActivity(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });
        return mBaseView;
    }
}
