package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginscreen.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private EditText textStudentId, textPassword;
    private TextView addUsersRedirect;
    private Button loginButton;
    private ActivityLoginBinding binding;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textStudentId = findViewById(R.id.textStudentId);
        textPassword = findViewById(R.id.textPassword);
        addUsersRedirect = findViewById(R.id.addUsersRedirect);
        loginButton = findViewById(R.id.loginButton);

        //dbHandler = new DBHandler(LoginActivity.this);
/**
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String enteredStudentId = binding.textStudentId.getText().toString();
                String enteredPassword = binding.textPassword.getText().toString();

                 if (enteredStudentId.equals("") || enteredPassword.equals("")) {
                    Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                 } else {
                    Boolean checkCredentials = dbHandler.checkStudentIdPassword(enteredStudentId, enteredPassword);

                     if (checkCredentials == true) {
                         String userName = dbHandler.getNameStudentId();

                         Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                         startActivity(intent);
                         finish();
                     } else {
                        Toast.makeText(LoginActivity.this, "Incorrect student ID and password. Please try again.", Toast.LENGTH_SHORT).show();
                     }
                 }
            }
        });

        binding.addUsersRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, AddUsersActivity.class);
                startActivity(intent);
            }
        });
*/
    }
}