package com.byteshaft.doctor.doctors;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.byteshaft.doctor.R;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by s9iper1 on 2/22/17.
 */

public class DoctorsList extends Fragment {

    private View mBaseView;
    private ListView mListView;
    private HashMap<Integer, String[]> doctorsList;
    private ArrayList<String> addedDates;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.search_doctor, container, false);
        mListView = (ListView) mBaseView.findViewById(R.id.doctors_list);
        doctorsList = new HashMap<>();
        addedDates = new ArrayList<>();
        doctorsList.put(0, new String[]{"Bilal", "Dermatologist", "2", "2.5", "9:30", "12-February-2017", "0"});
        doctorsList.put(1, new String[]{"Husnain", "Dermatologist", "2", "3.5", "7:30", "12-February-2017" , "1"});
        doctorsList.put(2, new String[]{"shahid", "Dermatologist", "3", "4.5", "5:30", "12-February-2017", "1"});
        doctorsList.put(3, new String[]{"Omer", "Dermatologist", "4", "1.5", "6:30", "13-February-2017", "0"});
        doctorsList.put(4, new String[]{"Mohsin", "Dermatologist", "6", "3.2", "7:30", "13-February-2017", "1"});
        doctorsList.put(5, new String[]{"Imran Hakeem", "Dermatologist", "8", "2.3", "5:30", "13-February-2017", "1"});
        CustomAdapter customAdapter = new CustomAdapter(getActivity().getApplicationContext(),
                R.layout.doctors_search_delagete, doctorsList);
        mListView.setAdapter(customAdapter);
        return mBaseView;
    }

    class CustomAdapter extends ArrayAdapter<HashMap<Integer, String[]>> {

        private HashMap<Integer, String[]> doctorsList;
        private ViewHolder viewHolder;

        public CustomAdapter(Context context, int resource, HashMap<Integer,
                String[]> doctorsList) {
            super(context, resource);
            this.doctorsList = doctorsList;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.doctors_search_delagete, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.circleImageView = (CircleImageView) convertView.findViewById(R.id.profile_image_view_search);
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);
                viewHolder.specialist = (TextView) convertView.findViewById(R.id.specialist);
                viewHolder.distance = (TextView) convertView.findViewById(R.id.distance);
                viewHolder.review = (RatingBar) convertView.findViewById(R.id.rating_bar);
                viewHolder.chat = (ImageButton) convertView.findViewById(R.id.chat);
                viewHolder.call = (ImageButton) convertView.findViewById(R.id.call);
                viewHolder.availableTime = (TextView) convertView.findViewById(R.id.available_time);
                viewHolder.openDetailButton = (ImageButton) convertView.findViewById(R.id.open_details);
                viewHolder.dateLayout = (LinearLayout) convertView.findViewById(R.id.date_layout);
                viewHolder.date = (TextView) convertView.findViewById(R.id.date);
                viewHolder.status = (ImageView) convertView.findViewById(R.id.status);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (addedDates.contains(doctorsList.get(position)[5])) {
                viewHolder.dateLayout.setVisibility(View.GONE);
            } else {
                viewHolder.dateLayout.setVisibility(View.VISIBLE);
                viewHolder.date.setText(doctorsList.get(position)[5].replaceAll("-" , " "));
                addedDates.add(doctorsList.get(position)[5]);
            }
            if (Integer.valueOf(doctorsList.get(position)[6]) == 0) {
                viewHolder.status.setImageResource(R.mipmap.ic_offline_indicator);
            } else {
                viewHolder.status.setImageResource(R.mipmap.ic_online_indicator);
            }
            viewHolder.name.setText(doctorsList.get(position)[0]);
            viewHolder.specialist.setText(doctorsList.get(position)[1]);
            viewHolder.distance.setText(" "+doctorsList.get(position)[2]+" km");
            viewHolder.review.setRating(Float.parseFloat(doctorsList.get(position)[3]));
            viewHolder.availableTime.setText(String.valueOf(doctorsList.get(position)[4]+ " am"));
            return convertView;
        }

        @Override
        public int getCount() {
            return doctorsList.size();
        }
    }

    class ViewHolder {
        CircleImageView circleImageView;
        TextView name;
        TextView specialist;
        TextView distance;
        RatingBar review;
        ImageButton chat;
        ImageButton call;
        TextView availableTime;
        ImageButton openDetailButton;
        LinearLayout dateLayout;
        TextView date;
        ImageView status;

    }
}
