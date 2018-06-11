package com.example.kevin.slugcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DetailActivity extends AppCompatActivity {

    public int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String num = i.getStringExtra("num");
        String instructor = i.getStringExtra("instructor");
        String location = i.getStringExtra("location");
        String daysAndTime = i.getStringExtra("daysAndTime");
        String status = i.getStringExtra("status");
        String seats = i.getStringExtra("seats");
        position = i.getIntExtra("position", -1);
        final String temp = name;
        String caller = i.getStringExtra("caller");

        TextView cName = (TextView)findViewById(R.id.cName);
        TextView cNum = (TextView)findViewById(R.id.cNum);
        TextView cInstructor = (TextView)findViewById(R.id.cInstructor);
        TextView classroom = (TextView)findViewById(R.id.classroom);
        TextView daysTimes = (TextView)findViewById(R.id.daysTimes);
        TextView cStatus = (TextView)findViewById(R.id.cStatus);
        TextView cSeats = (TextView)findViewById(R.id.cSeats);

        cName.setText(name);
        cNum.setText(num);
        cInstructor.setText(instructor);
        classroom.setText(location);
        daysTimes.setText(daysAndTime);
        cStatus.setText(status);
        cSeats.setText(seats);

        Button add = findViewById(R.id.addButton);


        Button remove = findViewById(R.id.removeButton);

        if(caller.equals("SearchActivity")) {
            remove.setEnabled(false);
            remove.setVisibility(View.INVISIBLE);
        }
        // TODO: if user already added this class to planner disable add button
//        else if(blah blah) {
//            add.setText("Cannot add class twice");
//            add.setEnabled(false);
//        }
        else {
            add.setEnabled(false);
            add.setVisibility(View.INVISIBLE);
        }


        add.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                // TODO: send data to schedule activity to add class to schedule

                //pop the activity off the stack
                Intent i = new Intent(DetailActivity.this, ScheduleActivity.class);
                i.putExtra("position", position);
                i.putExtra("Action", "Add");
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        remove.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                // TODO: send data to schedule activity to remove class from schedule


                //pop the activity off the stack
                Intent i = new Intent(DetailActivity.this, ScheduleActivity.class);
                i.putExtra("position", position);
                i.putExtra("Action", "Remove");
                i.putExtra("Name", temp);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
