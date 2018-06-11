package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

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
    private ProgressBar mProgress;
    public static ArrayList<Course> allData = new ArrayList<Course>();
    private Handler mHandler;
    String tURL = "https://0c333020.ngrok.io/api/v1.0/courses/all/2000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onResume(){
        super.onResume();
        final Context context = this;
        mProgress = (ProgressBar) findViewById(R.id.splash_screen_progress_bar);
        mHandler = new Handler();
        final int progress =(int )(Math.random() * 100 + 1);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                mProgress.setProgress(progress);
            }
        }, 1000);
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

        mHandler.postDelayed(new Runnable() {
            public void run() {
                mProgress.setProgress((int)(Math.random() * (100-progress) + 1) + progress);
            }
        }, 1000);
        mHandler.postDelayed(new Runnable() {
            public void run() {
                Intent i = new Intent(context,Directory.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        }, 3000);
    }

}

