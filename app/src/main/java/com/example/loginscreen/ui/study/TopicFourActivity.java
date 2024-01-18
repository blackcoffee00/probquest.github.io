package com.example.loginscreen.ui.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.loginscreen.R;

public class TopicFourActivity extends AppCompatActivity {

    private ImageView topicFourBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_four);

        topicFourBack = findViewById(R.id.topicFourBack);

        topicFourBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}