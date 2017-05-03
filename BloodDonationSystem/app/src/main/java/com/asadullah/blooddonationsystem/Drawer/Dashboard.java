package com.asadullah.blooddonationsystem.Drawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.asadullah.blooddonationsystem.BloodPost;
import com.asadullah.blooddonationsystem.Constants;
import com.asadullah.blooddonationsystem.Feeds;
import com.asadullah.blooddonationsystem.FeedsAdapter;
import com.asadullah.blooddonationsystem.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    RecyclerView rvFeeds;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ArrayList<BloodPost> bloodPosts;

    public Dashboard() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initialize(v);
        return v;
    }

    public void initialize(View v){


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Constants.POST_REQUEST);
        layoutManager = new LinearLayoutManager(getContext());
        rvFeeds = (RecyclerView)v.findViewById(R.id.rvFeeds);

        bloodPosts = new ArrayList<>();

        myRef.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Get map of users in datasnapshot
                        collectData((Map<String,Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });


    }


    private void collectData(Map<String,Object> users) {

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            Log.v("testing data", singleUser.toString());
            String bloodGroup = (String)singleUser.get("bloodGroup");
            String city = (String)singleUser.get("city");
            String contact = (String)singleUser.get("contact");
            String country = (String)singleUser.get("country");
            String extras = (String)singleUser.get("extras");
            String hospital = (String)singleUser.get("hospital");
            String numOfUnits = (String)singleUser.get("numOfUnits");
            String relation = (String)singleUser.get("relation");
            String state = (String)singleUser.get("state");
            String urgency = (String)singleUser.get("urgency");
            String userId = (String)singleUser.get("userId");

            bloodPosts.add(new BloodPost(bloodGroup, contact, extras, relation, hospital, city, state, country, urgency, numOfUnits, userId));

        }

        FeedsAdapter adapter = new FeedsAdapter(getContext(), bloodPosts);
        rvFeeds.setLayoutManager(layoutManager);

        rvFeeds.setAdapter(adapter);
    }

}
