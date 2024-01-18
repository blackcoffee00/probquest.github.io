package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUsersActivity extends AppCompatActivity {

    private EditText editName, editID, editPassword, editSection;
    private Button addUser;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_users);

        editName = findViewById(R.id.editName);
        editID = findViewById(R.id.editID);
        editPassword = findViewById(R.id.editPassword);
        editSection = findViewById(R.id.editSection);
        addUser = findViewById(R.id.addUser);

        dbHandler = new DBHandler(AddUsersActivity.this);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String courseName = editName.getText().toString();
                String courseStudentID = editID.getText().toString();
                String coursePassword = editPassword.getText().toString();
                String courseSection = editSection.getText().toString();

                if (courseName.isEmpty() && courseStudentID.isEmpty() && coursePassword.isEmpty() && courseSection.isEmpty()) {
                    Toast.makeText(AddUsersActivity.this, "Please complete all the requirements", Toast.LENGTH_SHORT).show();
                    return;
                }

                //dbHandler.addUsers(courseName, courseStudentID, coursePassword, courseSection);

                Toast.makeText(AddUsersActivity.this, "User has been added.", Toast.LENGTH_SHORT).show();
                editName.setText("");
                editID.setText("");
                editPassword.setText("");
                editSection.setText("");
            }
        });
    }
}