package com.byteshaft.doctor.doctors;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextClock;
import android.widget.TextView;

import com.byteshaft.doctor.R;

public class DoctorDetailsActivity extends AppCompatActivity {

    private TextView doctorName;
    private TextView doctorSpeciality;
    private RatingBar ratingBar;

    private ImageButton callButton;
    private ImageButton chatButton;
    private Button bookingButton;
    private Button showallReviewButton;

    private TextClock textClock;

    private ReviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        doctorName = (TextView) findViewById(R.id.doctor_name);
        doctorSpeciality = (TextView) findViewById(R.id.doctor_sp);
        ratingBar = (RatingBar) findViewById(R.id.user_ratings);

        callButton = (ImageButton) findViewById(R.id.call_button);
        chatButton = (ImageButton) findViewById(R.id.message_button);

        bookingButton = (Button) findViewById(R.id.button_book);
        showallReviewButton = (Button) findViewById(R.id.review_all_button);
        textClock = (TextClock) findViewById(R.id.clock);
    }

    private class ReviewAdapter extends ArrayAdapter {

        private ViewHolder viewHolder;

        public ReviewAdapter(Context context, int resource) {
            super(context, resource);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.delegate_dashboard, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.userName = (TextView) convertView.findViewById(R.id.by_username);
                viewHolder.time = (TextView) convertView.findViewById(R.id.time);
                viewHolder.userComment = (TextView) convertView.findViewById(R.id.tv_review);
                viewHolder.userRating = (RatingBar) convertView.findViewById(R.id.user_ratings);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView userName;
        TextView time;
        TextView userComment;
        RatingBar userRating;
    }
}
