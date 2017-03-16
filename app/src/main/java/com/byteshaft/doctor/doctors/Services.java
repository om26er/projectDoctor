package com.byteshaft.doctor.doctors;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.doctor.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by s9iper1 on 3/15/17.
 */

public class Services extends AppCompatActivity {

    private LinearLayout searchContainer;
    private ListView serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_layout);
        serviceList = (ListView) findViewById(R.id.service_list);
        searchContainer = new LinearLayout(this);
        Toolbar toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        Toolbar.LayoutParams containerParams = new Toolbar.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        containerParams.gravity = Gravity.CENTER_VERTICAL;
        containerParams.setMargins(20, 20, 10, 20);
        searchContainer.setLayoutParams(containerParams);
        // Setup search view
        EditText toolbarSearchView = new EditText(this);
        toolbarSearchView.setBackgroundColor(getResources().getColor(R.color.search_background));
        // Set width / height / gravity
        int[] textSizeAttr = new int[]{android.R.attr.actionBarSize};
        int indexOfAttrTextSize = 0;
        TypedArray a = obtainStyledAttributes(new TypedValue().data, textSizeAttr);
        int actionBarHeight = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, actionBarHeight);
        params.gravity = Gravity.CENTER_VERTICAL;
        params.setMargins(5, 5, 5, 5);
        params.weight = 1;
        toolbarSearchView.setLayoutParams(params);
        // Setup display
        toolbarSearchView.setPadding(2, 0, 0, 0);
        toolbarSearchView.setTextColor(Color.WHITE);
        toolbarSearchView.setGravity(Gravity.CENTER_VERTICAL);
        toolbarSearchView.setSingleLine(true);
        toolbarSearchView.setImeActionLabel("Search", EditorInfo.IME_ACTION_UNSPECIFIED);
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(toolbarSearchView, R.drawable.cursor_color);
        } catch (Exception ignored) {

        }
        // Search text changed listener
        toolbarSearchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        toolbarSearchView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {

                } else {

                }
            }
        });
        (searchContainer).addView(toolbarSearchView);

        // Setup the clear button
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, r.getDisplayMetrics());
        LinearLayout.LayoutParams clearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        clearParams.gravity = Gravity.CENTER;
        // Add search view to toolbar and hide it
        setSupportActionBar(toolbar);
        toolbar.addView(searchContainer);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<String[]> data = new ArrayList<>();
        data.add(new String[]{"service bla bla ", "120.00", "0"});
        data.add(new String[]{"service abc ", "125.00", "1"});
        data.add(new String[]{"service bcd ", "130.00", "0"});
        data.add(new String[]{"service efg ", "150.00", "1"});
        serviceList.setAdapter(new ServiceAdapter(getApplicationContext(), data));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.my_patients_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:

                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:return false;
        }
    }

    class ServiceAdapter extends ArrayAdapter<ArrayList<String[]>> {

        private ViewHolder viewHolder;
        private ArrayList<String[]> data;

        public ServiceAdapter(Context context, ArrayList<String[]> data) {
            super(context, R.layout.delegate_service);
            this.data = data;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.delegate_service, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.serviceName = (TextView) convertView.findViewById(R.id.service_name);
                viewHolder.servicePrice = (TextView) convertView.findViewById(R.id.service_price);
                viewHolder.serviceStatus = (CheckBox) convertView.findViewById(R.id.service_checkbox);
                viewHolder.removeService = (ImageButton) convertView.findViewById(R.id.remove_service);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.serviceName.setText(data.get(position)[0]);
            viewHolder.servicePrice.setText(data.get(position)[1]);
            if (Integer.valueOf(data.get(position)[2]) == 0) {
                viewHolder.serviceStatus.setChecked(false);
            } else {
                viewHolder.serviceStatus.setChecked(true);
            }
            return convertView;
        }

        @Override
        public int getCount() {
            return data.size();
        }
    }

    class ViewHolder {
        TextView serviceName;
        TextView servicePrice;
        CheckBox serviceStatus;
        ImageButton removeService;

    }
}
