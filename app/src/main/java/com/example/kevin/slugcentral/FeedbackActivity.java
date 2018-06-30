package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

public class FeedbackActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        final Context context = this;
        Button send = findViewById(R.id.send_feedback);
        EditText feedback = findViewById(R.id.feedback_text);

        send.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(FeedbackActivity.this,"Thanks for the feedback <3",Toast.LENGTH_LONG).show();
                //pop the activity off the stack
                Bundle bundle = new Bundle();
                bundle.putString("value", "feedback");
                mFirebaseAnalytics.logEvent("event", bundle);
                Intent i = new Intent(context, Directory.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }
}
