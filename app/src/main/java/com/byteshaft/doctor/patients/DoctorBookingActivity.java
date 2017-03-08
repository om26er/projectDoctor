package com.byteshaft.doctor.patients;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
<<<<<<< HEAD
import android.widget.Toast;
=======
import android.view.View;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextClock;
import android.widget.TextView;
>>>>>>> upstream/master

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;

<<<<<<< HEAD
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class DoctorBookingActivity extends AppCompatActivity {
=======
import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorBookingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mDoctorName;
    private TextView mDoctorSpeciality;
    private TextClock mtime;
    private CircleImageView mDoctorImage;
    private RatingBar mDoctorRating;
    private ImageButton mCallButton;
    private ImageButton mChatButton;
    private ImageButton mFavButton;
>>>>>>> upstream/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_booking);
<<<<<<< HEAD
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
=======
        mDoctorName = (TextView) findViewById(R.id.doctor_name_booking);
        mDoctorSpeciality = (TextView) findViewById(R.id.doctor_sp_booking);
        mDoctorRating = (RatingBar) findViewById(R.id.doctor_rating_booking);
        mtime = (TextClock) findViewById(R.id.clock);
        mDoctorImage = (CircleImageView) findViewById(R.id.doctor_image_booking);
        mCallButton = (ImageButton) findViewById(R.id.call_button_booking);
        mChatButton = (ImageButton) findViewById(R.id.chat_button_booking);
        mFavButton = (ImageButton) findViewById(R.id.button_fav_booking);
        mCallButton.setOnClickListener(this);
        mChatButton.setOnClickListener(this);
        mFavButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call_button_booking:
                // TODO: 07/03/2017 call intent
                break;
            case R.id.chat_button_booking:
                // TODO: 07/03/2017 open chat activity
                break;
            case R.id.button_fav_booking:
                mFavButton.setBackgroundResource(R.mipmap.ic_heart_fill);
                break;
        }
>>>>>>> upstream/master
    }
}
