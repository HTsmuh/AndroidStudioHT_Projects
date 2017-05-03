package com.asadullah.blooddonationsystem.Drawer;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.asadullah.blooddonationsystem.BloodPost;
import com.asadullah.blooddonationsystem.Constants;
import com.asadullah.blooddonationsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostRequest extends Fragment {

    Spinner sBloodGroup, sUrgency, sCountry, sState, sCity, sHospital, sRelation;
    EditText etNumOfUnits, etContact, etExtras;
    Button bPost;
    BloodPost bloodPost;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public PostRequest() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_post_request, container, false);
        initialize(v);
        return v;
    }

    public void initialize(View v){
        sBloodGroup = (Spinner) v.findViewById(R.id.sBloodGroup);
        sUrgency = (Spinner) v.findViewById(R.id.sUrgency);
        sCountry = (Spinner) v.findViewById(R.id.sCountry);
        sState = (Spinner) v.findViewById(R.id.sState);
        sCity = (Spinner) v.findViewById(R.id.sCity);
        sHospital = (Spinner) v.findViewById(R.id.sHospital);
        sRelation = (Spinner) v.findViewById(R.id.sRelation);
        etNumOfUnits = (EditText)v.findViewById(R.id.etNumOfUnits);
        etContact = (EditText)v.findViewById(R.id.etContact);
        etExtras = (EditText)v.findViewById(R.id.etExtras);
        bPost = (Button)v.findViewById(R.id.bPostRequest);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Constants.POST_REQUEST);

        actions();
    }

    public void actions(){
        bPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group = sBloodGroup.getSelectedItem().toString();
                String urgency = sUrgency.getSelectedItem().toString();
                String country = sCountry.getSelectedItem().toString();
                String state = sState.getSelectedItem().toString();
                String city = sCity.getSelectedItem().toString();
                String hospital = sHospital.getSelectedItem().toString();
                String relation = sRelation.getSelectedItem().toString();
                String numOfUnits = etNumOfUnits.getText().toString();
                String contact = etContact.getText().toString();
                String extras = etExtras.getText().toString();

                SharedPreferences pref =  getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                String uid = pref.getString("uid", "");

                bloodPost = new BloodPost(group, urgency, country, state, city, hospital, relation, numOfUnits, contact, extras, uid);

                String pushkey = myRef.push().getKey();

                myRef.child(pushkey).setValue(bloodPost);

                Toast.makeText(getContext(), "New Request Posted", Toast.LENGTH_LONG).show();
            }
        });


    }

}
