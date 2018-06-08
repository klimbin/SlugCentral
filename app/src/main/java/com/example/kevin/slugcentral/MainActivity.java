package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;

//need android:theme="@style/AppTheme.NoActionBar" to get rid of label on one activity
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
