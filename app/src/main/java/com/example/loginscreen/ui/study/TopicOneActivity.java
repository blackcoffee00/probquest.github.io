package com.example.loginscreen.ui.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.loginscreen.R;

public class TopicOneActivity extends AppCompatActivity {

    private ImageView topicOneBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_one);

        topicOneBack = findViewById(R.id.topicOneBack);

        topicOneBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}