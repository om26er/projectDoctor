package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;
import com.byteshaft.doctor.utils.Helpers;

/**
 * Created by husnain on 2/20/17.
 */

public class AccountActivationCode extends Fragment implements View.OnClickListener {

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
}
