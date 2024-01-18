package com.example.loginscreen.ui.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

import java.io.IOException;

public class ProfileFragment extends Fragment {

    private TextView profileName, profileSection, profileEditProfile;
    private ImageView profileImage;
    DBHandler dbHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileName = root.findViewById(R.id.profileName);
        profileSection = root.findViewById(R.id.profileSection);
        profileImage = root.findViewById(R.id.profileImage);
        profileEditProfile = root.findViewById(R.id.profileEditProfile);

        dbHandler = new DBHandler(requireContext());
        Cursor cursor = dbHandler.getUser();

        if (cursor.getCount() == 0) {
            Toast.makeText(requireContext(), "No Profile Details", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                profileName.setText("" + cursor.getString(0));
                profileSection.setText("" + cursor.getString(1));
                byte[] imageByte = cursor.getBlob(2);

                Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                profileImage.setImageBitmap(bitmap);
            }
        }

        profileEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}