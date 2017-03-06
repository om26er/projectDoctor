package com.byteshaft.doctor.accountfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;
import com.byteshaft.doctor.utils.AppGlobals;
import com.byteshaft.doctor.utils.Helpers;
import com.byteshaft.requests.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;

/**
 * Created by husnain on 2/20/17.
 */

public class AccountActivationCode extends Fragment implements View.OnClickListener, HttpRequest.OnReadyStateChangeListener, HttpRequest.OnErrorListener {

    private View mBaseView;

    private EditText mUserName;
    private EditText mPassword;
    private EditText mVerificationCode;

    private Button mLoginButton;
    private TextView mSignTextView;
    private TextView mResendTextView;


    private String mUserNameString;
    private String mVerificationCodeString;
    private String mPasswordString;

    private HttpRequest request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_account_activation_code, container, false);
        mUserName = (EditText) mBaseView.findViewById(R.id.username_edit_text);
        mPassword = (EditText) mBaseView.findViewById(R.id.password_edit_text);
        mVerificationCode = (EditText) mBaseView.findViewById(R.id.verification_code);
        mLoginButton = (Button) mBaseView.findViewById(R.id.button_login);
        mSignTextView = (TextView) mBaseView.findViewById(R.id.sign_up_text_view);
        mResendTextView = (TextView) mBaseView.findViewById(R.id.resend_text_view);

        mLoginButton.setOnClickListener(this);
        mSignTextView.setOnClickListener(this);
        mResendTextView.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                if (validate()) {
                    Helpers.showProgressDialog(getActivity(), "LoggingIn..");
                }

                break;
            case R.id.sign_up_text_view:
                MainActivity.getInstance().loadFragment(new SignUp());
                break;
            case R.id.resend_text_view:
                Helpers.showProgressDialog(getActivity(), "Sending verification code..");
                break;
        }
    }

    public boolean validate() {
        boolean valid = true;

        mUserNameString = mUserName.getText().toString();
        mPasswordString = mPassword.getText().toString();
        mVerificationCodeString = mVerificationCode.getText().toString();

        System.out.println(mUserNameString);
        System.out.println(mPasswordString);
        System.out.println(mVerificationCodeString);

        if (mUserNameString.trim().isEmpty()) {
            mUserName.setError("Must enter user name");
            valid = false;
        } else {
            mUserName.setError(null);
        }
        if (mVerificationCodeString.trim().isEmpty()) {
            mVerificationCode.setError("Verification code must be 6 characters");
            valid = false;
        } else {
            mVerificationCode.setError(null);
        }
        if (mPasswordString.isEmpty() || mPassword.length() < 4) {
            mPassword.setError("Enter minimum 4 alphanumeric characters");
            valid = false;
        } else {
            mPassword.setError(null);
        }
        return valid;
    }

    private void activateUser(String email, String emailOtp) {
        request = new HttpRequest(getActivity());
        request.setOnReadyStateChangeListener(this);
        request.setOnErrorListener(this);
        request.open("POST", String.format("%suser/activate", AppGlobals.BASE_URL));
        request.send(getUserActivationData(email, emailOtp));
        Helpers.showProgressDialog(getActivity(), "Activating User");
    }


    private String getUserActivationData(String email, String emailOtp) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("sms_otp", emailOtp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    @Override
    public void onReadyStateChange(HttpRequest request, int readyState) {
        switch (readyState) {
            case HttpRequest.STATE_DONE:
                Helpers.dismissProgressDialog();
                switch (request.getStatus()) {
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        Toast.makeText(getActivity(), "Please enter correct account activation key", Toast.LENGTH_LONG).show();
                        break;
                    case HttpURLConnection.HTTP_OK:
                        try {
                            JSONObject jsonObject = new JSONObject(request.getResponseText());
                            String accountType = jsonObject.getString(AppGlobals.KEY_AACOUNT_TYPE);
                            String userId = jsonObject.getString(AppGlobals.KEY_USER_ID);
                            String email = jsonObject.getString(AppGlobals.KEY_EMAIL);
                            String token = jsonObject.getString(AppGlobals.KEY_TOKEN);

                            //saving values
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_EMAIL, email);
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_AACOUNT_TYPE, accountType);
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_USER_ID, userId);
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_TOKEN, token);
                            Log.i("token", " " + AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_TOKEN));
                            MainActivity.getInstance().loadFragment(new UserBasicInfoStepOne());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                }
        }

    }

    @Override
    public void onError(HttpRequest request, int readyState, short error, Exception exception) {

    }
}
