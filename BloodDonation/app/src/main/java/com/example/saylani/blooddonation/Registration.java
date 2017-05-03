package com.example.saylani.blooddonation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity implements View.OnClickListener {
    EditText firstname;
    EditText lastname;
    EditText useremail;
    Spinner bloodgroup;
    EditText userpassword;
    TextView gotoSignin;
    Button userregister;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    User user;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firstname= (EditText) findViewById(R.id.userfname);
        lastname= (EditText) findViewById(R.id.userlname);

        progressDialog=new ProgressDialog(this);

        useremail= (EditText) findViewById(R.id.useremail);
        userpassword= (EditText) findViewById(R.id.userpassword);

        bloodgroup= (Spinner) findViewById(R.id.userbloodgroup);

        gotoSignin= (TextView) findViewById(R.id.usersignin);
        userregister= (Button) findViewById(R.id.usersignup);

        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, MainActivity.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        gotoSignin.setOnClickListener(this);
        userregister.setOnClickListener(this);

    }

    private void registerUser() {

        String email = useremail.getText().toString().trim();
        String password = userpassword.getText().toString().trim();
        //data
        String name = firstname.getText().toString().trim();
        String lastName = lastname.getText().toString().trim();
        String bloodGroup = bloodgroup.getSelectedItem().toString().trim();

        //checking if email and passwords are empty

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_LONG).show();
            return;
        }else
        if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Please Enter last Name", Toast.LENGTH_LONG).show();
            return;
        }else
        if (bloodGroup.equals("Select Blood group")) {
            Toast.makeText(this, "Please Select Blood group", Toast.LENGTH_LONG).show();
            return;
        }else
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter email", Toast.LENGTH_LONG).show();
            return;
        }else
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter password", Toast.LENGTH_LONG).show();
            return;
        }else{

            user = new User(name, lastName, email, bloodGroup, password);
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            databaseReference.child(firebaseUser.getUid()).setValue(user);
        }
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(Registration.this,"Successfully registered",Toast.LENGTH_LONG).show();
                        }else{
                            //display some message here
                            Toast.makeText(Registration.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view==gotoSignin){
            startActivity(new Intent(this, MainActivity.class));
        }if(view==userregister){
            registerUser();
        }
    }
}
