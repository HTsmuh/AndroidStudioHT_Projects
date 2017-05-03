package com.asadullah.blooddonationsystem.Screens;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.asadullah.blooddonationsystem.Drawer.Dashboard;
import com.asadullah.blooddonationsystem.Drawer.MyRequest;
import com.asadullah.blooddonationsystem.Drawer.Notification;
import com.asadullah.blooddonationsystem.Drawer.PostRequest;
import com.asadullah.blooddonationsystem.Drawer.Settings;
import com.asadullah.blooddonationsystem.Feeds;
import com.asadullah.blooddonationsystem.FeedsAdapter;
import com.asadullah.blooddonationsystem.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView rvFeeds;
    RecyclerView.LayoutManager layoutManager;
    FeedsAdapter adapter;
    ArrayList<Feeds> feeds;

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialize();
    }

    public void initialize(){
        setupToolbar();
        mAuth = FirebaseAuth.getInstance();
        rvFeeds = (RecyclerView)findViewById(R.id.rvFeeds);
        layoutManager = new LinearLayoutManager(this);

        feeds = new ArrayList<>();

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        String[] drawerItem = new String[5];

        drawerItem[0] = "Home";
        drawerItem[1] = "My Request";
        drawerItem[2] = "Post Requirement";
        drawerItem[3] = "Notification";
        drawerItem[4] = "Settings";

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new Dashboard();
                break;
            case 1:
                fragment = new MyRequest();
                break;
            case 2:
                fragment = new PostRequest();
                break;
            case 3:
                fragment = new Notification();
                break;
            case 4:
                fragment = new Settings();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.signOut();
    }
}
