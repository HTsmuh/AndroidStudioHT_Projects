package com.example.hp.digitalquran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Home extends AppCompatActivity {
      ListView itemList;
    ArrayAdapter<String> listAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ListView itemList = (ListView)findViewById(R.id.listView);

        DbBackend dbBackend = new DbBackend(Home.this);
        String[] terms = dbBackend.dictionaryWords();

        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, terms);

        itemList.setAdapter(listAdapter);
    }
}
