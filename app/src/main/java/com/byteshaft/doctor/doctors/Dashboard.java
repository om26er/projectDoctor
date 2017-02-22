package com.byteshaft.doctor.doctors;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.byteshaft.doctor.R;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private TextView doctorName;
    private TextView doctorEmail;
    private TextView doctorSp;
    private ImageView doctorImage;
    private RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        doctorName = (TextView) findViewById(R.id.doctor_name);
        doctorEmail = (TextView) findViewById(R.id.doctor_email);
        doctorSp = (TextView) findViewById(R.id.doctor_sp);
        doctorImage = (ImageView) findViewById(R.id.doctor_image);
        list = (RecyclerView) findViewById(R.id.dashboard_list);
    }

    class DashboardAdapter extends ArrayAdapter<String> {

        private ViewHolder viewHolder;

        DashboardAdapter(Context context, int resource, ArrayList<String> arrayList) {
            super(context, resource);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.dashboard_delegate, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.nextButton = (ImageButton) convertView.findViewById(R.id.button_next);
                viewHolder.tvAchievementTitle = (TextView) convertView.findViewById(R.id.achievement_title);
                viewHolder.tvAchievement = (TextView) convertView.findViewById(R.id.achievement);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvAchievement;
        TextView tvAchievementTitle;
        ImageButton nextButton;
    }
}
