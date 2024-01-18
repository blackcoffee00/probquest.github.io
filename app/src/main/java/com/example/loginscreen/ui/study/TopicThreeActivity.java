package com.example.loginscreen.ui.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.loginscreen.R;

public class TopicThreeActivity extends AppCompatActivity {

    private ImageView topicThreeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_three);

        topicThreeBack = findViewById(R.id.topicThreeBack);

        topicThreeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}