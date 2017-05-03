package com.asadullah.blooddonationsystem.Screens;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.asadullah.blooddonationsystem.Constants;
import com.asadullah.blooddonationsystem.R;
import com.asadullah.blooddonationsystem.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    String TAG = "testing";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText etFirstName, etLastName, etEmail, etPassword;
    Spinner sBloodGroup;
    User myuser;
    String[] array_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initialize();
        setSpinners();
    }

    public void initialize(){

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    //saving user data in database
                    myuser.uid = mAuth.getCurrentUser().getUid();

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
            ///// for database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(Constants.USERS);

        etFirstName = (EditText)findViewById(R.id.etFirstName);
        etLastName = (EditText)findViewById(R.id.etLastName);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);
        sBloodGroup = (Spinner)findViewById(R.id.sBloodGroup);
    }

    public void goSignin(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    public void setSpinners(){
        array_spinner=new String[6];
        array_spinner[0]="A Positive";
        array_spinner[1]="B Positive";
        array_spinner[2]="O Positive";
        array_spinner[3]="A Negative";
        array_spinner[4]="B Negative";
        array_spinner[5]="O Negative";

        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, array_spinner);
        sBloodGroup.setAdapter(adapter);

    }

    public void getData(){
        String name = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String bloodGroup = sBloodGroup.getSelectedItem().toString();
        String password = etPassword.getText().toString();

        myuser = new User(name, lastName, email, bloodGroup, password);
    }

    public void signup(View v){
        getData();
        if(myuser.firstName.equals("") || myuser.lastName.equals("")||myuser.email.equals("")||myuser.password.equals("")) {
            Toast.makeText(getBaseContext(), "Fields left empty", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(myuser.email, myuser.password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(getBaseContext(), "Sign in failed.. Check email or password",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getBaseContext(), "New User Created", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(user != null){
                                myuser.uid = user.getUid();
                                Log.v("testing id", myuser.uid);
                                myRef.child(myuser.uid).setValue(myuser);
                                SharedPreferences pref = getSharedPreferences("user", Context.MODE_PRIVATE);
                                pref.edit().putString("uid", myuser.uid).apply();
                            }
                        }

                    }
                });


    }


}
