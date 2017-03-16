package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;
import com.byteshaft.doctor.utils.AppGlobals;
import com.byteshaft.doctor.utils.Helpers;
import com.byteshaft.requests.FormData;
import com.byteshaft.requests.HttpRequest;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by husnain on 2/20/17.
 */

public class UserBasicInfoStepTwo extends Fragment implements AdapterView.OnItemSelectedListener,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener, HttpRequest.OnReadyStateChangeListener,
        HttpRequest.OnFileUploadProgressListener {
    private View mBaseView;
    private Spinner mStateSpinner;
    private Spinner mCitySpinner;
    private Spinner mInsuranceCarrierSpinner;
    private Spinner mAffiliatedClinicsSpinner;
    private TextView mStateSpinnerTextView;
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
    private HttpRequest mRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_user_basic_info_step_two, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setTitle(getResources().getString(R.string.sign_up));
        setHasOptionsMenu(true);
        mStateSpinner = (Spinner) mBaseView.findViewById(R.id.states_spinner);
        mCitySpinner = (Spinner) mBaseView.findViewById(R.id.cities_spinner);
        mInsuranceCarrierSpinner = (Spinner) mBaseView.findViewById(R.id.insurance_spinner);
        mAffiliatedClinicsSpinner = (Spinner) mBaseView.findViewById(R.id.clinic_spinner);
        mStateSpinnerTextView = (TextView) mBaseView.findViewById(R.id.states_spinner_text_view);

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

        List<String> citiesList = new ArrayList<String>();
        citiesList.add("city1");
        citiesList.add("city2");
        citiesList.add("city3");
        citiesList.add("city4");
        citiesList.add("city5");
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;
            default:return false;
        }
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
            case R.id.clinic_spinner:
                mAffiliatedClinicsSpinnerValueString = adapterView.getItemAtPosition(i).toString();
                System.out.println(" worekiuhfjvbkjkds" + mAffiliatedClinicsSpinnerValueString);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        mPhoneTwoEditTextString = mPhoneTwoEditText.getText().toString();
        if (validateEditText()) {
            sendingDataToServer();
            Toast.makeText(getActivity(), "errors", Toast.LENGTH_SHORT).show();
        }

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
                    mSaveButton.setEnabled(true);
                    mTermsConditionCheckBoxString = mTermsConditionCheckBox.getText().toString();
                    System.out.println(mTermsConditionCheckBoxString);
                } else {
                    mSaveButton.setEnabled(false);
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

    private void sendingDataToServer() {
        FormData data = new FormData();
        data.append(FormData.TYPE_CONTENT_TEXT, "identity_document", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_DOC_ID));
        data.append(FormData.TYPE_CONTENT_TEXT, "first_name", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_FIRST_NAME));
        data.append(FormData.TYPE_CONTENT_TEXT, "last_name", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_LAST_NAME));
        data.append(FormData.TYPE_CONTENT_TEXT, "dob", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_DATE_OF_BIRTH));
        data.append(FormData.TYPE_CONTENT_TEXT, "gender", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_GENDER));
        data.append(FormData.TYPE_CONTENT_TEXT, "location", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_LOCATION));
        data.append(FormData.TYPE_CONTENT_FILE, "photo", AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_IMAGE_URL));
        data.append(FormData.TYPE_CONTENT_TEXT, "state", mStatesSpinnerValueString);
        data.append(FormData.TYPE_CONTENT_TEXT, "city", mCitiesSpinnerValueString);
        data.append(FormData.TYPE_CONTENT_TEXT, "insurance_carrier", mInsuranceCarrierSpinnerValueString);
        data.append(FormData.TYPE_CONTENT_TEXT, "affiliate_clinic", mAffiliatedClinicsSpinnerValueString);
        data.append(FormData.TYPE_CONTENT_TEXT, "phone_number_primary", mPhoneOneEditTextString);
        data.append(FormData.TYPE_CONTENT_TEXT, "phone_number_secondary", mPhoneTwoEditTextString);
        data.append(FormData.TYPE_CONTENT_TEXT, "emergency_contact", mEmergencyContactString);
        data.append(FormData.TYPE_CONTENT_TEXT, "show_notification", mNotificationCheckBoxString);
        data.append(FormData.TYPE_CONTENT_TEXT, "show_news", mNewsCheckBoxString);

        mRequest = new HttpRequest(getActivity().getApplicationContext());
        mRequest.setOnReadyStateChangeListener(this);
        mRequest.setOnFileUploadProgressListener(this);
        mRequest.open("POST", String.format("%suser/profile", AppGlobals.BASE_URL));
        mRequest.setRequestHeader("Authorization", "Token " +
                "86fbb9c707dedbca83063205ae3ee1c5ce622f51");
        mRequest.send(data);
    }

    @Override
    public void onReadyStateChange(HttpRequest request, int readyState) {
        switch (readyState) {
            case HttpRequest.STATE_DONE:
                Helpers.dismissProgressDialog();
                switch (request.getStatus()) {
                    case HttpRequest.ERROR_NETWORK_UNREACHABLE:
                        AppGlobals.alertDialog(getActivity(), "Login Failed!", "please check your internet connection");
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        AppGlobals.alertDialog(getActivity(), "Login Failed!", "provide a valid EmailAddress");
                        break;
                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        AppGlobals.alertDialog(getActivity(), "Login Failed!", "Please enter correct password");
                        break;
                    case HttpURLConnection.HTTP_FORBIDDEN:
                        Toast.makeText(getActivity(), "Please activate your account !", Toast.LENGTH_LONG).show();
                        MainActivity.getInstance().loadFragment(new AccountActivationCode());
                        break;

                    case HttpURLConnection.HTTP_OK:
                        Toast.makeText(getActivity(), "o0k HAi!", Toast.LENGTH_LONG).show();

                }
        }

    }

    @Override
    public void onFileUploadProgress(HttpRequest request, File file, long loaded, long total) {

    }
}
