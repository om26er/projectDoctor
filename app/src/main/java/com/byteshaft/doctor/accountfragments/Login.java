package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

public class Login extends Fragment implements View.OnClickListener, HttpRequest.OnErrorListener,
        HttpRequest.OnReadyStateChangeListener {

    private View mBaseView;
    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    private TextView mForgotPasswordTextView;
    private TextView mSignUpTextView;
    private String mPasswordString;
    private String mEmailString;
    private HttpRequest request;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_login, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar()
                .setTitle(getResources().getString(R.string.login));
        mEmail = (EditText) mBaseView.findViewById(R.id.email_edit_text);
        mPassword = (EditText) mBaseView.findViewById(R.id.password_edit_text);
        mLoginButton = (Button) mBaseView.findViewById(R.id.button_login);
        mForgotPasswordTextView = (TextView) mBaseView.findViewById(R.id.forgot_password_text_view);
        mSignUpTextView = (TextView) mBaseView.findViewById(R.id.sign_up_text_view);
        /// setting typeface
        mEmail.setTypeface(AppGlobals.typefaceNormal);
        mPassword.setTypeface(AppGlobals.typefaceNormal);
        mLoginButton.setTypeface(AppGlobals.robotoRegular);
        mForgotPasswordTextView.setTypeface(AppGlobals.robotoBoldItalic);
        mSignUpTextView.setTypeface(AppGlobals.typefaceNormal);

        mLoginButton.setOnClickListener(this);
        mForgotPasswordTextView.setOnClickListener(this);
        mSignUpTextView.setOnClickListener(this);

        return mBaseView;
    }

    public boolean validate() {
        boolean valid = true;

        mEmailString = mEmail.getText().toString();
        mPasswordString = mPassword.getText().toString();

        System.out.println(mEmailString);
        System.out.println(mPasswordString);

        if (mEmailString.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mEmailString).matches()) {
            mEmail.setError("please provide a valid email");
            valid = false;
        } else {
            mEmail.setError(null);
        }
        if (mPasswordString.isEmpty() || mPassword.length() < 4) {
            mPassword.setError("Enter minimum 4 alphanumeric characters");
            valid = false;
        } else {
            mPassword.setError(null);
        }
        return valid;
    }

    private void loginUser(String email, String password) {
        request = new HttpRequest(getActivity());
        request.setOnReadyStateChangeListener(this);
        request.setOnErrorListener(this);
        request.open("POST", String.format("%suser/login", AppGlobals.BASE_URL));
        request.send(getUserLoginData(email, password));
        Helpers.showProgressDialog(getActivity(), "Logging In");
    }


    private String getUserLoginData(String email, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", email);
            jsonObject.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                if (validate()) {
                    loginUser(mEmailString, mPasswordString);
                }
                break;
            case R.id.forgot_password_text_view:
                AccountManagerActivity.getInstance().loadFragment(new ForgotPassword());
                break;
            case R.id.sign_up_text_view:
                AccountManagerActivity.getInstance().loadFragment(new SignUp());
                break;

        }
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
                        AccountManagerActivity.getInstance().loadFragment(new AccountActivationCode());
                        break;

                    case HttpURLConnection.HTTP_OK:
                        System.out.println(request.getResponseText() + "working ");
                        try {
                            JSONObject jsonObject = new JSONObject(request.getResponseText());
                            String accountType = jsonObject.getString(AppGlobals.KEY_ACCOUNT_TYPE);
                            String userId = jsonObject.getString(AppGlobals.KEY_USER_ID);
                            String email = jsonObject.getString(AppGlobals.KEY_EMAIL);
                            String token = jsonObject.getString(AppGlobals.KEY_TOKEN);

                            //saving values
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_EMAIL, email);
                            if (accountType.equals("doctor")) {
                                AppGlobals.userType(true);
                            } else {
                                AppGlobals.userType(false);
                            }
                            AppGlobals.loginState(true);
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_USER_ID, userId);
                            AppGlobals.saveDataToSharedPreferences(AppGlobals.KEY_TOKEN, token);
                            Log.i("token", " " + AppGlobals.getStringFromSharedPreferences(AppGlobals.KEY_TOKEN));
                            AccountManagerActivity.getInstance().loadFragment(new UserBasicInfoStepOne());

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
