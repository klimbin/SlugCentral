package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

//need android:theme="@style/AppTheme.NoActionBar" to get rid of label on one activity
public class MainActivity extends AppCompatActivity {

    public static ArrayList<Course> allData = new ArrayList<Course>();
    String tURL = "https://56ad94a8.ngrok.io/api/v1.0/courses/all/2000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa


        try {
            //https://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
            String jsonString = new Scanner(new URL(tURL).openStream(), "UTF-8").useDelimiter("\\A").next();

            JSONArray jsonarray = new JSONArray(jsonString);

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);

                String classID = jsonobject.getString("class_id");
                String dayTimes = jsonobject.getString("date_time");
                String className = jsonobject.getString("class_name");
                String location = jsonobject.getString("location");
                String instructor = jsonobject.getString("instructor");
                String enrolled = jsonobject.getString("enrolled");
                String status = jsonobject.getString("status");

                Course temp = new Course(classID, dayTimes, className, instructor, location, enrolled, status);

                allData.add(temp);
            }
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }

    }
    public void onResume(){
        super.onResume();
        final Context context = this;
        new CountDownTimer(1000, 10000){

            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                Intent i = new Intent(context, Directory.class);
                startActivity(i);
            }
        }.start();
    }
}

