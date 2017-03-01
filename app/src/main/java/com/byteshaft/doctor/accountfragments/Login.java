package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.doctor.R;

/**
 * Created by husnain on 2/20/17.
 */

public class Login extends Fragment implements View.OnClickListener {

    private View mBaseView;
    private EditText mUserName;
    private EditText mPassword;

    private Button mLoginButton;

    private TextView mForgotPasswordTextView;
    private TextView mSignUpTextView;

    private String mPasswordString;
    private String mUserNameString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_login, container, false);

        mUserName = (EditText) mBaseView.findViewById(R.id.username_edit_text);
        mPassword = (EditText) mBaseView.findViewById(R.id.password_edit_text);
        mLoginButton = (Button) mBaseView.findViewById(R.id.button_login);
        mForgotPasswordTextView = (TextView) mBaseView.findViewById(R.id.forgot_password_text_view);
        mSignUpTextView = (TextView) mBaseView.findViewById(R.id.sign_up_text_view);

        mLoginButton.setOnClickListener(this);
        mForgotPasswordTextView.setOnClickListener(this);
        mSignUpTextView.setOnClickListener(this);

        return mBaseView;
    }


    public boolean validate() {
        boolean valid = true;

        mUserNameString = mUserName.getText().toString();
        mPasswordString = mPassword.getText().toString();

        System.out.println(mUserNameString);
        System.out.println(mPasswordString);

        if (mUserNameString.trim().isEmpty()) {
            mUserName.setError("enter a valid user name");
            valid = false;
        } else {
            mUserName.setError(null);
        }
        if (mPasswordString.isEmpty() || mPassword.length() < 4) {
            mPassword.setError("Enter minimum 4 alphanumeric characters");
            valid = false;
        } else {
            mPassword.setError(null);
        }
        return valid;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                if (validate()) {
                    Toast.makeText(getActivity(), "login", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forgot_password_text_view:
                Toast.makeText(getActivity(), "forgot_password_text_view", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_up_text_view:
                Toast.makeText(getActivity(), "sign_up_text_view", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
