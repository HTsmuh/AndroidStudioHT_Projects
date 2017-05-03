package com.asadullah.blooddonationsystem.Screens;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.TextView;

import com.asadullah.blooddonationsystem.BloodPost;
import com.asadullah.blooddonationsystem.R;

public class PostDetails extends AppCompatActivity {

    BloodPost bloodPost;
    TextView tvName, tvRequiredUnits, tvAddress, tvUrgency, tvContact, tvAdditionalInfo, tvVolunteer, tvCurrentRequirement;
    RecyclerView rvVolunteers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        initialize();
        setData();
    }

    public void initialize(){

        Bundle bundle = getIntent().getExtras();
        bloodPost = (BloodPost) bundle.getSerializable("post");

        tvName = (TextView)findViewById(R.id.tvName);
        tvRequiredUnits = (TextView)findViewById(R.id.tvRequiredUnits);
        tvAddress = (TextView)findViewById(R.id.tvAddress);
        tvUrgency = (TextView)findViewById(R.id.tvUrgency);
        tvContact = (TextView)findViewById(R.id.tvContact);
        tvAdditionalInfo = (TextView)findViewById(R.id.tvExtras);
        tvVolunteer = (TextView)findViewById(R.id.tvVolunteers);
        tvCurrentRequirement = (TextView)findViewById(R.id.tvCurrentRequirement);
    }

    public void setData(){
        tvName.setText(bloodPost.numOfUnits);
        tvRequiredUnits.setText("");
        tvAddress.setText("");
        tvUrgency.setText("");
        tvContact.setText("");
        tvAdditionalInfo.setText("");
        tvVolunteer.setText("");
        tvCurrentRequirement.setText("");
    }
}
