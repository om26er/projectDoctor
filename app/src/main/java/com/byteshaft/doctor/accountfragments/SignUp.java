package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.byteshaft.doctor.R;

/**
 * Created by husnain on 2/20/17.
 */

public class SignUp extends Fragment implements View.OnClickListener {

    private View mBaseView;

    private EditText mUserName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mVerifyPassword;

    private Button mLoginButton;

    private TextView mForgotPasswordTextView;
    private TextView mSignUpTextView;

    private String mPasswordString;
    private String mUserNameString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mUserName = (EditText) mBaseView.findViewById(R.id.username_edit_text);
        mEmail = (EditText) mBaseView.findViewById(R.id.email_edit_text);
        mPassword = (EditText) mBaseView.findViewById(R.id.password_edit_text);
        mVerifyPassword = (EditText) mBaseView.findViewById(R.id.verify_password_edit_text);
        mLoginButton = (Button) mBaseView.findViewById(R.id.button_login);
        mForgotPasswordTextView = (TextView) mBaseView.findViewById(R.id.forgot_password_text_view);
        mSignUpTextView = (TextView) mBaseView.findViewById(R.id.sign_up_text_view);

        mLoginButton.setOnClickListener(this);
        mForgotPasswordTextView.setOnClickListener(this);
        mSignUpTextView.setOnClickListener(this);
        return mBaseView;
    }

    @Override
    public void onClick(View view) {

    }
}
