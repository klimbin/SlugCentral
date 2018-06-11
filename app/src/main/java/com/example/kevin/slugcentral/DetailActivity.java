package com.example.kevin.slugcentral;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DetailActivity extends AppCompatActivity {

    public JSONObject jo = null;
    public JSONArray ja = null;
    public String name, num, instructor, location, daysAndTime, status, seats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String caller = i.getStringExtra("caller");

        TextView cName = (TextView)findViewById(R.id.cName);
        TextView cNum = (TextView)findViewById(R.id.cNum);
        TextView cInstructor = (TextView)findViewById(R.id.cInstructor);
        TextView classroom = (TextView)findViewById(R.id.classroom);
        TextView daysTimes = (TextView)findViewById(R.id.daysTimes);
        TextView cStatus = (TextView)findViewById(R.id.cStatus);
        TextView cSeats = (TextView)findViewById(R.id.cSeats);

        Button add = findViewById(R.id.addButton);

        Button remove = findViewById(R.id.removeButton);

        // Read the file

        try {
            File f = new File(getFilesDir(), "file.ser");
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream o = new ObjectInputStream(fi);
            // Notice here that we are de-serializing a String object (instead of
            // a JSONObject object) and passing the String to the JSONObject's
            // constructor. That's because String is serializable and
            // JSONObject is not. To convert a JSONObject back to a String, simply
            // call the JSONObject's toString method.
            String j = null;
            try {
                j = (String) o.readObject();
            }
            catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
            try {
                jo = new JSONObject(j);
                ja = jo.getJSONArray("data");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
            // Here, initialize a new JSONObject
            jo = new JSONObject();
            ja = new JSONArray();
            try {
                jo.put("data", ja);
            }
            catch (JSONException j) {
                j.printStackTrace();
            }
        }


        name = i.getStringExtra("name");
        if(caller.equals("Search")) {
            remove.setEnabled(false);
            remove.setVisibility(View.INVISIBLE);
            num = i.getStringExtra("num");
            instructor = i.getStringExtra("instructor");
            location = i.getStringExtra("location");
            daysAndTime = i.getStringExtra("daysAndTime");
            status = i.getStringExtra("status");
            seats = i.getStringExtra("seats");
        }
        else {
            add.setEnabled(false);
            add.setVisibility(View.INVISIBLE);

            try {
                for (int j = 0; j < ja.length(); j++) {
                    JSONObject temp = ja.getJSONObject(j);
                    if (temp.getString("name").equals(name)) {
                        num = temp.getString("num");
                        instructor = temp.getString("instructor");
                        location = temp.getString("location");
                        daysAndTime = temp.getString("daysAndTime");
                        status = temp.getString("status");
                        seats = temp.getString("seats");
                        break;
                    }
                }
            }
            catch(JSONException e) {
                e.printStackTrace();
            }
        }

        cName.setText(name);
        cNum.setText(num);
        cInstructor.setText(instructor);
        classroom.setText(location);
        daysTimes.setText(daysAndTime);
        cStatus.setText(status);
        cSeats.setText(seats);


        add.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                JSONObject temp = new JSONObject();
                try {
                    for(int j = 0; j < ja.length(); j++) {
                        if(ja.getJSONObject(j).getString("name").equals(name)) {
                            Toast.makeText(DetailActivity.this, "This class has already been added",
                                    Toast.LENGTH_LONG).show();
                            //pop the activity off the stack
                            Intent i = new Intent(DetailActivity.this, ScheduleActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);

                        }
                    }
                    temp.put("name", name);
                    temp.put("num", num);
                    temp.put("instructor", instructor);
                    temp.put("location", location);
                    temp.put("daysAndTime", daysAndTime);
                    temp.put("status", status);
                    temp.put("seats", seats);
                }
                catch (JSONException j) {
                    j.printStackTrace();
                }

                ja.put(temp);

                // write the file
                try {
                    File f = new File(getFilesDir(), "file.ser");
                    FileOutputStream fo = new FileOutputStream(f);
                    ObjectOutputStream o = new ObjectOutputStream(fo);
                    String j = jo.toString();
                    o.writeObject(j);
                    o.close();
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }


                //pop the activity off the stack
                Intent i = new Intent(DetailActivity.this, ScheduleActivity.class);
//                i.putExtra("position", position);
//                i.putExtra("Action", "Add");
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        remove.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                try {
                    for (int i = 0; i < ja.length(); i++) {
                        if (ja.getJSONObject(i).getString("name").equals(name)) {
                            ja.remove(i);
                            break;
                        }
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                // write the file
                try {
                    File f = new File(getFilesDir(), "file.ser");
                    FileOutputStream fo = new FileOutputStream(f);
                    ObjectOutputStream o = new ObjectOutputStream(fo);
                    String j = jo.toString();
                    o.writeObject(j);
                    o.close();
                    fo.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                //pop the activity off the stack
                Intent i = new Intent(DetailActivity.this, ScheduleActivity.class);
//                i.putExtra("position", position);
//                i.putExtra("Action", "Remove");
//                i.putExtra("Name", name);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }
}
