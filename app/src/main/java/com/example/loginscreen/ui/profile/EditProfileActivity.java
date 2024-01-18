package com.example.loginscreen.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    private EditText textName, textSection;
    private ImageView userImage, arrowBackButton;
    private RelativeLayout uploadImage;
    private Button saveButton;
    private Uri uri;
    private Bitmap bitmapImage;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dbHandler = new DBHandler(this);

        textName = findViewById(R.id.textName);
        textSection = findViewById(R.id.textSection);
        userImage = findViewById(R.id.userImage);
        uploadImage = findViewById(R.id.uploadImage);
        arrowBackButton = findViewById(R.id.arrowBackButton);
        saveButton = findViewById(R.id.saveButton);

        Cursor cursor = dbHandler.getUser();

        if (cursor.getCount() == 0) {
        } else {
            while (cursor.moveToNext()) {
                textName.setText("" + cursor.getString(0));
                textSection.setText("" + cursor.getString(1));
            }
        }

        arrowBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            assert data != null;
                            uri = data.getData();
                            try {
                                bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            } catch (IOException e) {
                                Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            userImage.setImageBitmap(bitmapImage);
                        } else {
                            Toast.makeText(EditProfileActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    activityResultLauncher.launch(intent);
                } catch (Exception e) {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storeImage();
            }
        });
    }

    private void storeImage() {
        if (!textName.getText().toString().isEmpty() &&
                !textSection.getText().toString().isEmpty() &&
                userImage.getDrawable() != null && bitmapImage !=null) {

            dbHandler.storeData(new ProfileModelClass(textName.getText().toString(), textSection.getText()
                    .toString(), bitmapImage));

            Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            Toast.makeText(EditProfileActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }
}
