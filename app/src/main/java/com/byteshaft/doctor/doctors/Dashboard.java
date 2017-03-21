package com.byteshaft.doctor.doctors;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.byteshaft.doctor.R;
import com.byteshaft.doctor.utils.AppGlobals;

import java.util.ArrayList;

public class Dashboard extends Fragment {

    private View mBaseView;
    private TextView doctorName;
    private TextView doctorEmail;
    private TextView doctorSp;
    private ImageView doctorImage;
    private RecyclerView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setTitle(getResources().getString(R.string.dashboard));

        doctorName = (TextView) mBaseView.findViewById(R.id.doctor_name_dashboard);
        doctorEmail = (TextView) mBaseView.findViewById(R.id.doctor_email);
        doctorSp = (TextView) mBaseView.findViewById(R.id.doctor_sp);
        doctorImage = (ImageView) mBaseView.findViewById(R.id.doctor_image);

        doctorName.setTypeface(AppGlobals.typefaceNormal);
        doctorEmail.setTypeface(AppGlobals.typefaceNormal);
        doctorSp.setTypeface(AppGlobals.typefaceNormal);
        
        list = (RecyclerView) mBaseView.findViewById(R.id.dashboard_list);
        doctorName.setText(AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_FIRST_NAME)
                + " " + AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_LAST_NAME));
        doctorEmail.setText(AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_EMAIL));
        doctorSp.setText(AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_DOC_SPECIALITY));
        return mBaseView;
    }

    class DashboardAdapter extends ArrayAdapter<String> {

        private ViewHolder viewHolder;

        DashboardAdapter(Context context, int resource, ArrayList<String> arrayList) {
            super(context, resource);
        }

//        @Override
//        public View getView(final int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = getLayoutInflater().inflate(R.layout.delegate_dashboard, parent, false);
//                viewHolder = new ViewHolder();
//                viewHolder.nextButton = (ImageButton) convertView.findViewById(R.id.button_next);
//                viewHolder.tvAchievementTitle = (TextView) convertView.findViewById(R.id.achievement_title);
//                viewHolder.tvAchievement = (TextView) convertView.findViewById(R.id.achievement);
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ViewHolder) convertView.getTag();
//            }
//            return convertView;
//        }
    }

    private class ViewHolder {
        TextView tvAchievement;
        TextView tvAchievementTitle;
        ImageButton nextButton;
    }
}
