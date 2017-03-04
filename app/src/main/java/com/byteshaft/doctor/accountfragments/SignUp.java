package com.byteshaft.doctor.accountfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.byteshaft.doctor.MainActivity;
import com.byteshaft.doctor.R;

/**
 * Created by husnain on 2/20/17.
 */

public class SignUp extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private View mBaseView;

    private EditText mUserName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mVerifyPassword;
    private CheckBox mDoctorsCheckBox;

    private Button mSignUpButton;

    private TextView mLoginTextView;

    private String mUserNameString;
    private String mEmailAddressString;
    private String mPasswordString;
    private String mVerifyPasswordString;
    private String mCheckBoxString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mUserName = (EditText) mBaseView.findViewById(R.id.username_edit_text);
        mEmail = (EditText) mBaseView.findViewById(R.id.email_edit_text);
        mPassword = (EditText) mBaseView.findViewById(R.id.password_edit_text);
        mVerifyPassword = (EditText) mBaseView.findViewById(R.id.verify_password_edit_text);
        mSignUpButton = (Button) mBaseView.findViewById(R.id.sign_up_button);
        mLoginTextView = (TextView) mBaseView.findViewById(R.id.login_text_view);
        mDoctorsCheckBox = (CheckBox) mBaseView.findViewById(R.id.doctor_checkbox);

        mSignUpButton.setOnClickListener(this);
        mLoginTextView.setOnClickListener(this);
        mDoctorsCheckBox.setOnCheckedChangeListener(this);
        return mBaseView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sign_up_button:
                System.out.println("signUp button");
                break;
            case R.id.login_text_view:
                MainActivity.getInstance().loadFragment(new Login());
                break;
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (mDoctorsCheckBox.isChecked()) {
            mCheckBoxString = mDoctorsCheckBox.getText().toString();
            System.out.println(mCheckBoxString + "working");
        }

    }

    private boolean validateEditText() {
        boolean valid = true;
        mEmailAddressString = mEmail.getText().toString();
        mUserNameString = mUserName.getText().toString();
        mPasswordString = mPassword.getText().toString();
        mVerifyPasswordString = mVerifyPassword.getText().toString();

        if (mEmailAddressString.trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(mEmailAddressString).matches()) {
            mEmail.setError("please provide a valid email");
            valid = false;
        } else {
            mEmail.setError(null);
        }
        if (mUserNameString.trim().isEmpty()) {
            mUserName.setError("must enter user name");
            valid = false;
        } else {
            mUserName.setError(null);
        }
        if (mPasswordString.trim().isEmpty() || mPasswordString.length() < 4) {
            mPassword.setError("enter at least 4 characters");
            valid = false;
        } else {
            mPassword.setError(null);
        }

        if (mVerifyPasswordString.trim().isEmpty() || mVerifyPasswordString.length() < 4 ||
                !mVerifyPasswordString.equals(mPasswordString)) {
            mVerifyPassword.setError("password does not match");
            valid = false;
        } else {
            mVerifyPassword.setError(null);
        }
        return valid;
    }
}
