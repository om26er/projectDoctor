package com.byteshaft.doctor.accountfragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;
import com.byteshaft.doctor.patients.DoctorsAppointment;
import com.byteshaft.doctor.patients.DoctorsLocator;

import java.util.Calendar;

/**
 * Created by husnain on 2/20/17.
 */

public class UserBasicInfoStepOne extends Fragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private View mBaseView;

    private EditText mDocID;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mDateOfBirth;
    private EditText mAddress;

    private Button mNextButton;
    private RadioGroup mMaleFemale;
    private RadioButton mMale;
    private RadioButton mFemale;

    private TextView mLoginTextView;
    private TextView AddressTextView;

    private DatePickerDialog datePickerDialog;

    private String mDocIDString;
    private String mFirstNameString;
    private String mLastNameString;
    private String mDateOfBirthString;

    private String mMaleRadioButtonSting;
    private String mFemaleRadioButtonSting;
    private String mAddressString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_user_basic_info_step_one, container, false);

        mDocID = (EditText) mBaseView.findViewById(R.id.doctor_id_edit_text);
        mFirstName = (EditText) mBaseView.findViewById(R.id.first_name_edit_text);
        mLastName = (EditText) mBaseView.findViewById(R.id.last_name_edit_text);
        mDateOfBirth = (EditText) mBaseView.findViewById(R.id.birth_date_edit_text);
        mAddress = (EditText) mBaseView.findViewById(R.id.address_edit_text);

        mLoginTextView = (TextView) mBaseView.findViewById(R.id.login_text_view);
        AddressTextView = (TextView) mBaseView.findViewById(R.id.pick_for_current_location);

        mNextButton = (Button) mBaseView.findViewById(R.id.next_button);
        mMaleFemale = (RadioGroup) mBaseView.findViewById(R.id.radio_group);
        mMale = (RadioButton) mBaseView.findViewById(R.id.radio_button_male);
        mFemale = (RadioButton) mBaseView.findViewById(R.id.radio_button_female);

        mNextButton.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);
        AddressTextView.setOnClickListener(this);
        mDateOfBirth.setOnClickListener(this);
        mMaleFemale.setOnCheckedChangeListener(this);

        final Calendar calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getActivity(), R.style.MyDialogTheme,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        return mBaseView;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mDateOfBirth.setText(i2 + "/" + i1 + "/" + i);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.next_button:
                mAddressString = mAddress.getText().toString();

                break;
            case R.id.login_text_view:
                MainActivity.getInstance().loadFragment(new Login());
                break;
            case R.id.pick_for_current_location:
                startActivity(new Intent(getActivity(), DoctorsLocator.class));
                break;
            case R.id.birth_date_edit_text:
                datePickerDialog.show();
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        switch (checkedId) {
            case R.id.radio_button_male:
                if (mMale.isChecked()) {
                    mMaleRadioButtonSting = mMale.getText().toString();
                    System.out.println(mMaleRadioButtonSting);
                }
                break;
            case R.id.radio_button_female:
                if (mFemale.isChecked()) {
                    mFemaleRadioButtonSting = mFemale.getText().toString();
                    System.out.println(mFemaleRadioButtonSting);
                }
                break;
        }
    }

    private boolean validateEditText() {
        boolean valid = true;
        mDocIDString = mDocID.getText().toString();
        mFirstNameString = mFirstName.getText().toString();
        mLastNameString = mLastName.getText().toString();
        mDateOfBirthString = mDateOfBirth.getText().toString();

        if (mDocIDString.trim().isEmpty()) {
            mDocID.setError("please provide DocID");
            valid = false;
        } else {
            mDocID.setError(null);
        }
        if (mFirstNameString.trim().isEmpty()) {
            mFirstName.setError("please provide firstName");
            valid = false;
        } else {
            mFirstName.setError(null);
        }
        if (mLastNameString.trim().isEmpty()) {
            mLastName.setError("please provide lastName");
            valid = false;
        } else {
            mLastName.setError(null);
        }

        if (mDateOfBirthString.trim().isEmpty()) {
            mDateOfBirth.setError("please provide firstName");
            valid = false;
        } else {
            mDateOfBirth.setError(null);
        }
        return valid;
    }
}
