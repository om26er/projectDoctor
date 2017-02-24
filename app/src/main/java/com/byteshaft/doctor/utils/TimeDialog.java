package com.byteshaft.doctor.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.byteshaft.doctor.R;

/**
 * Created by s9iper1 on 2/14/17.
 */

public class TimeDialog extends Dialog implements View.OnClickListener {


    public TimeDialog(Context context, int themeResId, int dialogType) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_dialog);
    }

    @Override
    public void onClick(View view) {

    }
}
