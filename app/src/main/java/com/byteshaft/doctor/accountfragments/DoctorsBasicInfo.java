package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;

import com.byteshaft.doctor.R;
import com.byteshaft.doctor.utils.AppGlobals;

import java.util.ArrayList;
import java.util.List;

public class DoctorsBasicInfo extends Fragment implements AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private View mBaseView;
    private Button mSaveButton;

    private Spinner mStateSpinner;
    private Spinner mCitySpinner;
    private Spinner mSpecialitySpinner;
    private Spinner mAffiliatedClinicsSpinner;
    private Spinner mSubscriptionSpinner;

    private EditText mPhoneOneEditText;
    private EditText mPhoneTwoEditText;
    private EditText mConsultationTimeEditText;
    private EditText mCollegeIdEditText;

    private CheckBox mNotificationCheckBox;
    private CheckBox mNewsCheckBox;
    private CheckBox mTermsConditionCheckBox;

    private String mPhoneOneEditTextString;
    private String mPhoneTwoEditTextString;
    private String mConsultationTimeEditTextString;
    private String mCollegeIdEditTextString;

    private String mStatesSpinnerValueString;
    private String mCitiesSpinnerValueString;
    private String mSpecialitySpinnerValueString;
    private String mAffiliatedClinicsSpinnerValueString;
    private String mSubscriptionSpinnerValueString;

    private String mNotificationCheckBoxString;
    private String mNewsCheckBoxString;
    private String mTermsConditionCheckBoxString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_doctor_basic_info, container, false);

        mSaveButton = (Button) mBaseView.findViewById(R.id.save_button);
        mStateSpinner = (Spinner) mBaseView.findViewById(R.id.states_spinner);
        mCitySpinner = (Spinner) mBaseView.findViewById(R.id.cities_spinner);
        mSpecialitySpinner = (Spinner) mBaseView.findViewById(R.id.speciality_spinner);
        mAffiliatedClinicsSpinner = (Spinner) mBaseView.findViewById(R.id.clinics_spinner);
        mSubscriptionSpinner = (Spinner) mBaseView.findViewById(R.id.subscriptions_spinner);

        mPhoneOneEditText = (EditText) mBaseView.findViewById(R.id.phone_one_edit_text);
        mPhoneTwoEditText = (EditText) mBaseView.findViewById(R.id.phone_two_edit_text);
        mConsultationTimeEditText = (EditText) mBaseView.findViewById(R.id.consultation_time_edit_text);
        mCollegeIdEditText = (EditText) mBaseView.findViewById(R.id.college_id_edit_text);

        mNotificationCheckBox = (CheckBox) mBaseView.findViewById(R.id.notifications_check_box);
        mNewsCheckBox = (CheckBox) mBaseView.findViewById(R.id.news_check_box);
        mTermsConditionCheckBox = (CheckBox) mBaseView.findViewById(R.id.terms_check_box);

        mSaveButton.setTypeface(AppGlobals.typefaceNormal);
        mPhoneOneEditText.setTypeface(AppGlobals.typefaceNormal);
        mPhoneTwoEditText.setTypeface(AppGlobals.typefaceNormal);
        mConsultationTimeEditText.setTypeface(AppGlobals.typefaceNormal);
        mCollegeIdEditText.setTypeface(AppGlobals.typefaceNormal);


        List<String> StateList = new ArrayList<>();
        StateList.add("State1");
        StateList.add("State2");
        StateList.add("State3");
        StateList.add("State4");
        StateList.add("State5");
        ArrayAdapter<String> StateListAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, StateList);
        StateListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStateSpinner.setAdapter(StateListAdapter);

        List<String> citiesList = new ArrayList<>();
        citiesList.add("City1");
        citiesList.add("City2");
        citiesList.add("City3");
        citiesList.add("City4");
        citiesList.add("City5");
        ArrayAdapter<String> CitiesListAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, citiesList);
        CitiesListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCitySpinner.setAdapter(CitiesListAdapter);

        List<String> specialityList = new ArrayList<>();
        specialityList.add("ENT");
        specialityList.add("Dermatologist");
        specialityList.add("Surgeon");
        specialityList.add("physiotherapist");
        specialityList.add("Dentist");
        ArrayAdapter<String> SpecialityListAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, specialityList);
        SpecialityListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpecialitySpinner.setAdapter(SpecialityListAdapter);

        List<String> clinicList = new ArrayList<>();
        clinicList.add("Doctor dray clinic");
        clinicList.add("Cantt clinic");
        clinicList.add("City hospital");
        clinicList.add("Medicare");
        clinicList.add("Patient care");
        ArrayAdapter<String> clinicListAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, clinicList);
        clinicListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAffiliatedClinicsSpinner.setAdapter(clinicListAdapter);

        List<String> subscriptionList = new ArrayList<>();
        subscriptionList.add("Basic plan");
        subscriptionList.add("Monthly plan");
        subscriptionList.add("Premium plan");
        subscriptionList.add("Professional plan");
        ArrayAdapter<String> subscriptionListAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, subscriptionList);
        subscriptionListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSubscriptionSpinner.setAdapter(subscriptionListAdapter);


        mStateSpinner.setOnItemSelectedListener(this);
        mCitySpinner.setOnItemSelectedListener(this);
        mSpecialitySpinner.setOnItemSelectedListener(this);
        mAffiliatedClinicsSpinner.setOnItemSelectedListener(this);
        mSubscriptionSpinner.setOnItemSelectedListener(this);

        mNotificationCheckBox.setOnCheckedChangeListener(this);
        mNewsCheckBox.setOnCheckedChangeListener(this);
        mTermsConditionCheckBox.setOnCheckedChangeListener(this);

        mSaveButton.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.states_spinner:
                mStatesSpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(mStatesSpinnerValueString);
                break;
            case R.id.cities_spinner:
                mCitiesSpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(mCitiesSpinnerValueString);
                break;
            case R.id.speciality_spinner:
                mSpecialitySpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(mSpecialitySpinnerValueString);
                break;
            case R.id.clinics_spinner:
                mAffiliatedClinicsSpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(mAffiliatedClinicsSpinnerValueString);
                break;
            case R.id.subscriptions_spinner:
                mSubscriptionSpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(mSubscriptionSpinnerValueString);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.notifications_check_box:
                if (mNotificationCheckBox.isChecked()) {
                    mNotificationCheckBoxString = mNotificationCheckBox.getText().toString();
                    System.out.println(mNotificationCheckBoxString);
                }
                break;
            case R.id.news_check_box:
                if (mNewsCheckBox.isChecked()) {
                    mNewsCheckBoxString = mNewsCheckBox.getText().toString();
                    System.out.println(mNewsCheckBoxString);
                }
                break;
            case R.id.terms_check_box:
                if (mTermsConditionCheckBox.isChecked()) {
                    mTermsConditionCheckBoxString = mTermsConditionCheckBox.getText().toString();
                    System.out.println(mTermsConditionCheckBoxString);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {

    }

    private boolean validateEditText() {
        boolean valid = true;
        mPhoneOneEditTextString = mPhoneOneEditText.getText().toString();
        mCollegeIdEditTextString = mCollegeIdEditText.getText().toString();
        mConsultationTimeEditTextString = mConsultationTimeEditText.getText().toString();

        if (mPhoneOneEditTextString.trim().isEmpty()) {
            mPhoneOneEditText.setError("please enter your phone number");
            valid = false;
        } else {
            mPhoneOneEditText.setError(null);
        }
        if (mCollegeIdEditTextString.trim().isEmpty()) {
            mCollegeIdEditText.setError("please provide your collegeID");
            valid = false;
        } else {
            mCollegeIdEditText.setError(null);
        }
        if (mConsultationTimeEditTextString.trim().isEmpty()) {
            mConsultationTimeEditText.setError("please enter your consultation time");
            valid = false;
        } else {
            mConsultationTimeEditText.setError(null);
        }

        return valid;
    }
}
