package com.byteshaft.doctor.doctors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        doctorName = (TextView) findViewById(R.id.doctor_name);
        doctorSpeciality = (TextView) findViewById(R.id.doctor_sp);
        ratingBar = (RatingBar) findViewById(R.id.doctor_ratings);

        callButton = (ImageButton) findViewById(R.id.call_button);
        chatButton = (ImageButton) findViewById(R.id.message_button);

        bookingButton = (Button) findViewById(R.id.button_book);
        showallReviewButton = (Button) findViewById(R.id.review_all_button);

        textClock = (TextClock) findViewById(R.id.clock);

    }
}
