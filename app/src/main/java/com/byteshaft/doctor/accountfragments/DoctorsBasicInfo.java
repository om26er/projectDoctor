package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.byteshaft.doctor.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by husnain on 2/20/17.
 */

public class DoctorsBasicInfo extends Fragment {

    private View mBaseView;
    private Spinner mStateSpinner;
    private Spinner mCitySpinner;
    private Spinner mSpecialitySpinner;
    private Spinner mAffiliatedClinicsSpinner;
    private Spinner mSubscriptionSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_doctor_basic_info, container, false);
        mStateSpinner = (Spinner) mBaseView.findViewById(R.id.states_spinner);
        mCitySpinner = (Spinner) mBaseView.findViewById(R.id.cities_spinner);
        mSpecialitySpinner = (Spinner) mBaseView.findViewById(R.id.speciality_spinner);
        mAffiliatedClinicsSpinner = (Spinner) mBaseView.findViewById(R.id.clinics_spinner);
        mSubscriptionSpinner = (Spinner) mBaseView.findViewById(R.id.subscriptions_spinner);

        List<String> StateList = new ArrayList<String>();
        StateList.add("State1");
        StateList.add("State2");
        StateList.add("State3");
        StateList.add("State4");
        StateList.add("State5");
        ArrayAdapter<String> StateListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, StateList);
        StateListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mStateSpinner.setAdapter(StateListAdapter);

        List<String> citiesList = new ArrayList<String>();
        citiesList.add("City1");
        citiesList.add("City2");
        citiesList.add("City3");
        citiesList.add("City4");
        citiesList.add("City5");
        ArrayAdapter<String> CitiesListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, citiesList);
        CitiesListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCitySpinner.setAdapter(CitiesListAdapter);

        List<String> specialityList = new ArrayList<String>();
        specialityList.add("ENT");
        specialityList.add("Dermatologist");
        specialityList.add("Surgeon");
        specialityList.add("physiotherapist");
        specialityList.add("Dentist");
        ArrayAdapter<String> SpecialityListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, specialityList);
        SpecialityListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpecialitySpinner.setAdapter(SpecialityListAdapter);

        List<String> clinicList = new ArrayList<String>();
        clinicList.add("Doctor dray clinic");
        clinicList.add("Cantt clinic");
        clinicList.add("City hospital");
        clinicList.add("Medicare");
        clinicList.add("Patient care");
        ArrayAdapter<String> clinicListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, clinicList);
        clinicListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAffiliatedClinicsSpinner.setAdapter(clinicListAdapter);

        List<String> subscriptionList = new ArrayList<String>();
        subscriptionList.add("Basic plan");
        subscriptionList.add("Monthly plan");
        subscriptionList.add("Premium plan");
        subscriptionList.add("Professional plan");
        ArrayAdapter<String> subscriptionListAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, subscriptionList);
        subscriptionListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSubscriptionSpinner.setAdapter(subscriptionListAdapter);
        return mBaseView;
    }
}
