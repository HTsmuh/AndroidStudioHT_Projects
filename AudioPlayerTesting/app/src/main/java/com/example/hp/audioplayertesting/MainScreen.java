package com.example.hp.audioplayertesting;

import android.app.Activity;
import android.os.Bundle;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainScreen extends Activity {
    StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        mStorageRef = FirebaseStorage.getInstance().getReference();

    }
}
