package com.asadullah.blooddonationsystem.Drawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asadullah.blooddonationsystem.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Fragment {

    RecyclerView rvNotifications;
    RecyclerView.LayoutManager layoutManager;

    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        initialize(v);
        return v;
    }

    public void initialize(View v){
        rvNotifications = (RecyclerView)v.findViewById(R.id.rvNotifications);
        layoutManager = new LinearLayoutManager(getContext());
    }

}
