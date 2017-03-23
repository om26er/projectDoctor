package com.byteshaft.doctor.doctors;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.doctor.R;

import java.util.ArrayList;

/**
 * Created by s9iper1 on 3/23/17.
 */

public class MySchedule extends Fragment {

    private View mBaseView;
    private ListView mListView;
    private ArrayList<String[]> scheduleList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.my_schedule, container, false);
        scheduleList = new ArrayList<>();
        scheduleList.add(new String[]{"10:00", "10:30", "1"});
        scheduleList.add(new String[]{"11100", "11:30", "0"});
        scheduleList.add(new String[]{"12:00", "12:30", "1"});
        scheduleList.add(new String[]{"13:00", "13:30", "0"});
        scheduleList.add(new String[]{"14:00", "14:30", "1"});
        scheduleList.add(new String[]{"15:00", "15:30", "0"});
        scheduleList.add(new String[]{"16:00", "16:30", "0"});
        scheduleList.add(new String[]{"17:00", "17:30", "1"});
        scheduleList.add(new String[]{"18:00", "18:30", "0"});
        mListView = (ListView) mBaseView.findViewById(R.id.schedule_list);
        mListView.setAdapter(new ScheduleAdapter(getActivity().getApplicationContext(), scheduleList));
        return mBaseView;
    }

    private class ScheduleAdapter extends ArrayAdapter<String> {

        private ArrayList<String[]> scheduleList;
        private ViewHolder viewHolder;

        public ScheduleAdapter(@NonNull Context context, ArrayList<String[]> scheduleList) {
            super(context, R.layout.delegate_doctor_schedule);
            this.scheduleList = scheduleList;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.delegate_doctor_schedule, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.startTime = (TextView) convertView.findViewById(R.id.start_time);
                viewHolder.endTime = (TextView) convertView.findViewById(R.id.end_time);
                viewHolder.state = (CheckBox) convertView.findViewById(R.id.check_box_appointment);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.startTime.setText(scheduleList.get(position)[0]);
            viewHolder.endTime.setText(scheduleList.get(position)[1]);
            if (Integer.valueOf(scheduleList.get(position)[2]) == 0) {
                viewHolder.state.setChecked(false);
            } else {
                viewHolder.state.setChecked(true);
            }
            return convertView;
        }

        @Override
        public int getCount() {
            return scheduleList.size();
        }
    }

    private class ViewHolder {
        TextView startTime;
        TextView endTime;
        CheckBox state;
    }
}
