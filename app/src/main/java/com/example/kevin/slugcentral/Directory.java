package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Directory extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final Context context = this;
    private Intent j;
    private FirebaseAuth mAuth;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,SearchActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onResume(){
        super.onResume();
        Button buttonSchedule = findViewById(R.id.buttonSchedule);
        Button buttonGE = findViewById(R.id.buttonGE);
        Button buttonMap = findViewById(R.id.buttonMap);
        mAuth = FirebaseAuth.getInstance();
        //get an intent from somewhere
        try {
            j = getIntent();
        }
        catch(Exception e){
        }
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //check if there is a login
        //if there isnt a login
        if(currentUser == null){
            //enable sign in button
            navigationView.getMenu().getItem(0).setVisible(true);
            //disable export
            navigationView.getMenu().getItem(1).setVisible(false);
            //disable sign out
            navigationView.getMenu().getItem(3).setVisible(false);
        }
        else{
            //disable sign in button
            navigationView.getMenu().getItem(0).setVisible(false);
            //enable export
            navigationView.getMenu().getItem(1).setVisible(true);
            //enable sign out
            navigationView.getMenu().getItem(3).setVisible(true);
        }
        buttonSchedule.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //pop the activity off the stack
                Intent i = new Intent(context, ScheduleActivity.class);
                startActivity(i);
            }
        });
        buttonGE.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //pop the activity off the stack
                Intent i = new Intent(context, GEActivity.class);
                startActivity(i);
            }
        });

        //TODO: have drop down menu so user can choose academic term
        buttonMap.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //pop the activity off the stack
                Intent i = new Intent(context, MapsActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.directory, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        final Context context = this;
        Intent i;
        if (id == R.id.nav_signIn) {
            i = new Intent(context,LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else if (id == R.id.nav_export) {

        }  else if (id == R.id.nav_settings) {
            i = new Intent(context,SettingsActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            //UPDATE LATER TO FIGURE OUT WHAT TO SEND
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Send Via"));
        } else if (id == R.id.nav_sendFeedBack) {
            i = new Intent(context,FeedbackActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else if (id == R.id.nav_signOut) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(Directory.this, "Signed Out",
                    Toast.LENGTH_SHORT).show();
            //enable sign in button
            navigationView.getMenu().getItem(0).setVisible(true);
            //disable export
            navigationView.getMenu().getItem(1).setVisible(false);
            //disable sign out
            navigationView.getMenu().getItem(3).setVisible(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
