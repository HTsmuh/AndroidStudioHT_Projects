package com.example.saylani.blooddonation;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {
    TextView signup;
    EditText signinuseremail;
    EditText signinuserpassword;
    Button buttonsignin;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = (TextView) findViewById(R.id.signupclick);
        buttonsignin = (Button) findViewById(R.id.signinbtn);
        progressDialog = new ProgressDialog(this);
        signinuseremail = (EditText) findViewById(R.id.signinemial);
        signinuserpassword = (EditText) findViewById(R.id.signinpassword);
        firebaseAuth=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Registration.class));
                finish();
            }
        });
        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("test", "onAuthStateChanged:signed_in:" + user.getUid());
                    goHome();

                } else {
                    // User is signed out
                    Log.d("test", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }
    public void goHome(){
        Intent i = new Intent(this, Menu.class);
        startActivity(i);
        finish();
    }
    private void loginUser() {

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
