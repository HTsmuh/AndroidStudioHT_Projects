package com.example.saylani.blooddonation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class RequestFragment extends Fragment {

    public RequestFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_request, container, false);

        return rootView;
    }

}
