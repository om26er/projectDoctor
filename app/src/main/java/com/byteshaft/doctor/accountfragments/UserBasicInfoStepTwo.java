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

public class UserBasicInfoStepTwo extends Fragment implements AdapterView.OnItemSelectedListener,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private View mBaseView;

    private Spinner mStateSpinner;
    private Spinner mCitySpinner;
    private Spinner mInsuranceCarrierSpinner;
    private Spinner mAffiliatedClinicsSpinner;

    private EditText mPhoneOneEditText;
    private EditText mPhoneTwoEditText;
    private EditText mEmergencyContactEditText;

    private CheckBox mNotificationCheckBox;
    private CheckBox mNewsCheckBox;
    private CheckBox mTermsConditionCheckBox;

    private String mPhoneOneEditTextString;
    private String mPhoneTwoEditTextString;
    private String mEmergencyContactString;


    private String mStatesSpinnerValueString;
    private String mCitiesSpinnerValueString;
    private String mAffiliatedClinicsSpinnerValueString;
    private String mInsuranceCarrierSpinnerValueString;

    private String mNotificationCheckBoxString;
    private String mNewsCheckBoxString;
    private String mTermsConditionCheckBoxString;

    private Button mSaveButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_user_basic_info_step_two, container, false);

        mStateSpinner = (Spinner) mBaseView.findViewById(R.id.states_spinner);
        mCitySpinner = (Spinner) mBaseView.findViewById(R.id.cities_spinner);
        mInsuranceCarrierSpinner = (Spinner) mBaseView.findViewById(R.id.insurance_spinner);
        mAffiliatedClinicsSpinner = (Spinner) mBaseView.findViewById(R.id.clinic_spinner);

        mPhoneOneEditText = (EditText) mBaseView.findViewById(R.id.phone_one_edit_text);
        mPhoneTwoEditText = (EditText) mBaseView.findViewById(R.id.phone_two_edit_text);
        mEmergencyContactEditText = (EditText) mBaseView.findViewById(R.id.emergency_contact);

        mNotificationCheckBox = (CheckBox) mBaseView.findViewById(R.id.notifications_check_box);
        mNewsCheckBox = (CheckBox) mBaseView.findViewById(R.id.news_check_box);
        mTermsConditionCheckBox = (CheckBox) mBaseView.findViewById(R.id.terms_check_box);

        mSaveButton = (Button) mBaseView.findViewById(R.id.save_button);

        mPhoneOneEditText.setTypeface(AppGlobals.typefaceNormal);
        mPhoneTwoEditText.setTypeface(AppGlobals.typefaceNormal);
        mEmergencyContactEditText.setTypeface(AppGlobals.typefaceNormal);
        mNotificationCheckBox.setTypeface(AppGlobals.typefaceNormal);
        mNewsCheckBox.setTypeface(AppGlobals.typefaceNormal);
        mTermsConditionCheckBox.setTypeface(AppGlobals.typefaceNormal);

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

        List<String> InsuranceCarrierList = new ArrayList<>();
        InsuranceCarrierList.add("Insurance Carrier-1");
        InsuranceCarrierList.add("Insurance Carrier-2");
        InsuranceCarrierList.add("Insurance Carrier-3");
        InsuranceCarrierList.add("Insurance Carrier-4");
        ArrayAdapter<String> InsuranceCarrierListAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, InsuranceCarrierList);
        InsuranceCarrierListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mInsuranceCarrierSpinner.setAdapter(InsuranceCarrierListAdapter);

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

        mStateSpinner.setOnItemSelectedListener(this);
        mCitySpinner.setOnItemSelectedListener(this);
        mAffiliatedClinicsSpinner.setOnItemSelectedListener(this);
        mInsuranceCarrierSpinner.setOnItemSelectedListener(this);

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
            case R.id.insurance_spinner:
                mInsuranceCarrierSpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(mInsuranceCarrierSpinnerValueString);
                break;
            case R.id.clinics_spinner:
                mAffiliatedClinicsSpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(mAffiliatedClinicsSpinnerValueString);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        mPhoneTwoEditTextString = mPhoneTwoEditText.getText().toString();

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

    private boolean validateEditText() {
        boolean valid = true;
        mPhoneOneEditTextString = mPhoneOneEditText.getText().toString();
        mEmergencyContactString = mEmergencyContactEditText.getText().toString();

        if (mPhoneOneEditTextString.trim().isEmpty()) {
            mPhoneOneEditText.setError("please enter your phone number");
            valid = false;
        } else {
            mPhoneOneEditText.setError(null);
        }
        if (mEmergencyContactString.trim().isEmpty()) {
            mEmergencyContactEditText.setError("please provide your Emergency Contact");
            valid = false;
        } else {
            mEmergencyContactEditText.setError(null);
        }

        return valid;
    }
}
