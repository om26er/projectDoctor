package com.byteshaft.doctor.patients;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.doctor.R;

import java.util.ArrayList;


public class MyAppointments extends Fragment {

    private View mBaseView;
    private ListView appointMentList;
    private ArrayList<String[]> appointments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.patient_my_appointment, container, false);
        appointments = new ArrayList<>();
        appointMentList = (ListView) mBaseView.findViewById(R.id.patient_appointment);
        appointments.add(new String[]{"10-2-2017", "10:00", "Dr Shahid", "Dermatology" , "Service details", "A"});
        appointments.add(new String[]{"11-2-2017", "12:00", "Dr Bilal", "ENT",  "Service details", "C"});
        appointments.add(new String[]{"12-2-2017", "14:00", "Dr Mohsin", "Child specialist",  "Service details", "P"});
        appointments.add(new String[]{"12-2-2017", "16:00", "Dr Zeshan", "Chest Specialist" , "Service details", "C"});
        appointments.add(new String[]{"14-2-2017", "11:00", "Dr Hussnain", "FCPS" ,  "Service details", "P"});
        appointments.add(new String[]{"16-2-2017", "13:00", "Dr Karobar","Dermatologist" , "Service details", "A"});
        appointMentList.setAdapter(new Adapter(getContext(), appointments));
        return mBaseView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    class Adapter extends ArrayAdapter<String> {

        private ArrayList<String[]> appointmentsList;
        private ViewHolder viewHolder;

        public Adapter(Context context, ArrayList<String[]> appointmentsList) {
            super(context, R.layout.delegate_p_appointment_history);
            this.appointmentsList = appointmentsList;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.delegate_p_appointment_history,
                        parent, false);
                viewHolder = new ViewHolder();
                viewHolder.appointmentDate = (TextView) convertView.findViewById(R.id.appointment_date);
                viewHolder.appointmentTime = (TextView) convertView.findViewById(R.id.appointment_time);
                viewHolder.doctorName = (TextView) convertView.findViewById(R.id.doctor_name);
                viewHolder.serviceDescription = (TextView) convertView.findViewById(R.id.service_description);
                viewHolder.appointmentStatus = (TextView) convertView.findViewById(R.id.appointment_status);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.appointmentDate.setText(appointmentsList.get(position)[0]);
            viewHolder.appointmentTime.setText(appointmentsList.get(position)[1]);
            String specialist;
            if(appointmentsList.get(position)[3].length() > 7) {
                specialist = appointmentsList.get(position)[3].substring(0, 7
                ).trim() + "…";
            } else {
                specialist = appointmentsList.get(position)[3];
            }
            viewHolder.doctorName.setText(appointmentsList.get(position)[2] + " - " + specialist);
            viewHolder.serviceDescription.setText(appointmentsList.get(position)[4]);
            switch (appointmentsList.get(position)[5]) {
                case "A":
                    viewHolder.appointmentStatus.setText("A");
                    viewHolder.appointmentStatus.setBackgroundColor(getResources()
                            .getColor(R.color.attended_background_color));
                    break;
                case "C":
                    viewHolder.appointmentStatus.setText("C");
                    viewHolder.appointmentStatus.setBackgroundColor(getResources()
                            .getColor(R.color.cancel_background_color));
                    break;
                case "P":
                    viewHolder.appointmentStatus.setText("P");
                    viewHolder.appointmentStatus.setBackgroundColor(getResources()
                            .getColor(R.color.pending_background_color));
                    break;

            }
            return convertView;
        }

        @Override
        public int getCount() {
            return appointmentsList.size();
        }
    }

    private class ViewHolder {
        TextView appointmentDate;
        TextView appointmentTime;
        TextView doctorName;
        TextView serviceDescription;
        TextView appointmentStatus;
    }
}
