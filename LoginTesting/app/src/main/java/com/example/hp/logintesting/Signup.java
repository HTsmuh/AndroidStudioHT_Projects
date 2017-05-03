package com.example.hp.logintesting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Signup extends AppCompatActivity {
    EditText firstname;
    EditText lastname;
    EditText useremail;
    Spinner usergender;
    EditText userpassword;
    EditText usercontact;
    TextView gotoSignin;
    Button userregister;
    ProgressDialog progressDialog;
    DatabaseReference databaseReference;
    User user;
    private FirebaseAuth firebaseAuth;

    String email;
    String password;
    //data
    String name ;
    String lastName;
    String gender;
    String contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firstname= (EditText) findViewById(R.id.userfname);
        lastname= (EditText) findViewById(R.id.userlname);
        useremail= (EditText) findViewById(R.id.useremail);
        userpassword= (EditText) findViewById(R.id.userpassword);
        usergender= (Spinner) findViewById(R.id.usergender);
        usercontact= (EditText) findViewById(R.id.usercontact);
        gotoSignin= (TextView) findViewById(R.id.usersignin);
        userregister= (Button) findViewById(R.id.usersignup);

        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, Login.class));
        }
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    }
    public void gotosignin(View v){
        startActivity(new Intent(Signup.this, Login.class));
        finish();
    }
    public void register(View v){
        email = useremail.getText().toString().trim();
        password = userpassword.getText().toString().trim();
        //data
        name = firstname.getText().toString().trim();
        lastName = lastname.getText().toString().trim();
        gender = usergender.getSelectedItem().toString().trim();
        contact=usercontact.getText().toString().trim();
        //checking if email and passwords are empty

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(lastName)) {
            Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_LONG).show();
            return;
        } else if (gender.equals("Select Your Gender")) {
            Toast.makeText(this, "Please Select Your Gender", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(contact)) {
            Toast.makeText(this, "Please Enter Phone Number", Toast.LENGTH_LONG).show();
            return;
        } else {
            user = new User(name, lastName, email,password, gender,contact );
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                databaseReference.child(firebaseUser.getUid()).setValue(user);
            }
        }
            progressDialog.setMessage("Registering Please Wait...");
            progressDialog.show();
            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {

                                //display some message here
                                Toast.makeText(Signup.this, "Successfully registered", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(Signup.this, Login.class));
                                finish();
                            } else {
                                //display some message here
                                Toast.makeText(Signup.this, "Registration Error", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }
    }
