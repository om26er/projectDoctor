package com.byteshaft.doctor.messages;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.byteshaft.doctor.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by s9iper1 on 3/21/17.
 */

public class MainMessages extends Fragment {

    private View mBaseView;
    private ListView mMessagesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.activity_main_messages, container, false);
        mMessagesList = (ListView) mBaseView.findViewById(R.id.main_messages);
        return mBaseView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private class Adapter extends ArrayAdapter<String> {

        private ArrayList<String[]> messagesList;
        private ViewHolder viewHolder;


        public Adapter(Context context,  ArrayList<String[]> messagesList) {
            super(context, R.layout.delegate_main_messages);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.delegate_main_messages,
                        parent, false);
                viewHolder = new ViewHolder();
                viewHolder.profilePicture = (CircleImageView) convertView.findViewById(R.id.profile_picture);
                viewHolder.drName = (TextView) convertView.findViewById(R.id.dr_name);
                viewHolder.specialist = (TextView) convertView.findViewById(R.id.specialist);
                viewHolder.date = (TextView) convertView.findViewById(R.id.date);
                viewHolder.time = (TextView) convertView.findViewById(R.id.time);
                viewHolder.navigateButton = (ImageButton) convertView.findViewById(R.id.navigate_button);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();

            }
            return convertView;
        }

        @Override
        public int getCount() {
            return messagesList.size();
        }
    }

    private class ViewHolder {
        CircleImageView profilePicture;
        TextView drName;
        TextView specialist;
        TextView date;
        TextView time;
        ImageButton navigateButton;
    }
}
