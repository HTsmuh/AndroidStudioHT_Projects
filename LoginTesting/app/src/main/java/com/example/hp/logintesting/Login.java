package com.example.hp.logintesting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity{
    TextView signupnew;
    EditText signinuseremail;
    EditText signinuserpassword;
    Button buttonsignin;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    String TAG = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupnew = (TextView) findViewById(R.id.newuser);
        buttonsignin = (Button) findViewById(R.id.signinbtn);
        progressDialog = new ProgressDialog(this);
        signinuseremail = (EditText) findViewById(R.id.signinemial);
        signinuserpassword = (EditText) findViewById(R.id.signinpassword);
        firebaseAuth=FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    public void goHome(){
        Intent i = new Intent(this, Home.class);
        startActivity(i);
        finish();
    }
    public void registration(View v){
        Intent i = new Intent(this, Signup.class);
        startActivity(i);
        finish();
    }
    public void login(View v){

        //getting email and password from edit texts
        String uemail = signinuseremail.getText().toString().trim();
        String upassword = signinuserpassword.getText().toString().trim();
        Log.d("email", uemail);
        Log.d("password", upassword);

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(uemail)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(upassword)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Signing in Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(uemail, upassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        Log.d("test", "signInWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Log.w("test", "signInWithEmail:failed", task.getException());
                            Toast.makeText(getBaseContext(), "Sign in failed.. Check email or password",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            finish();
                            goHome();

                        }
                    }
                });
    }
}
