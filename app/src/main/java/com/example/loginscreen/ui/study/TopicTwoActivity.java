package com.example.loginscreen.ui.study;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.loginscreen.R;

public class TopicTwoActivity extends AppCompatActivity {

    private ImageView topicTwoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_two);

        topicTwoBack = findViewById(R.id.topicTwoBack);

        topicTwoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}