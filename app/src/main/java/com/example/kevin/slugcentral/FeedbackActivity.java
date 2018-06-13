package com.example.kevin.slugcentral;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        final Context context = this;
        Button send = findViewById(R.id.send_feedback);
        EditText feedback = findViewById(R.id.feedback_text);

        send.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(FeedbackActivity.this,"Thanks for the feedback <3",Toast.LENGTH_LONG).show();
                //pop the activity off the stack
                Intent i = new Intent(context, Directory.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }
}
