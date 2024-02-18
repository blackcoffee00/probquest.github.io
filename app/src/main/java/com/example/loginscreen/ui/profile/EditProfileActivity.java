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

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.NavigationActivity;
import com.example.loginscreen.R;

import java.io.IOException;

public class EditProfileActivity extends AppCompatActivity {

    private EditText textFName, textLName, textSection;
    private ImageView userImage;
    private ImageView arrowBackButton;
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

        textFName = findViewById(R.id.textFName);
        textLName = findViewById(R.id.textLName);
        textSection = findViewById(R.id.textSection);
        userImage = findViewById(R.id.userImage);
        uploadImage = findViewById(R.id.uploadImage);
        arrowBackButton = findViewById(R.id.arrowBackButton);
        saveButton = findViewById(R.id.saveButton);

        Cursor cursor = dbHandler.getUser(1);

        while (cursor.moveToNext()) {
            textFName.setText("" + cursor.getString(1));
            textLName.setText("" + cursor.getString(2));
            textSection.setText("" + cursor.getString(3));
            byte[] imageByte = cursor.getBlob(4);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            userImage.setImageBitmap(bitmap);
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
        if (!textFName.getText().toString().isEmpty() &&
                !textSection.getText().toString().isEmpty() &&
                userImage.getDrawable() != null && bitmapImage !=null) {

            Boolean checkId = dbHandler.checkId(1);
            if (checkId == true) {
                String fname = textFName.getText().toString();
                String lname = textLName.getText().toString();
                String section = textSection.getText().toString();
                Bitmap image = bitmapImage;

                Boolean updateProfile = dbHandler.updateProfile(new ProfileModelClass(1, fname, lname, section, image));

                if (updateProfile == true) {
                    Toast.makeText(EditProfileActivity.this, "Profile Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "Profile is not updated", Toast.LENGTH_SHORT).show();
                }

            } else {
                dbHandler.storeData(new ProfileModelClass(1, textFName.getText().toString(), textLName.getText().toString(), textSection.getText().toString(), bitmapImage));
                Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
            }

            Intent intent = new Intent(EditProfileActivity.this, NavigationActivity.class);
            intent.putExtra("loadToProfileFragment", R.id.navigation_profile);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(EditProfileActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
        }
    }
}
