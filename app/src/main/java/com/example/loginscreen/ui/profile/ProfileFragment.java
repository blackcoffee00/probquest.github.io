package com.example.loginscreen.ui.profile;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.loginscreen.DBHandler;
import com.example.loginscreen.R;

public class ProfileFragment extends Fragment {

    private TextView profileFName, profileLName, profileSection;
    private ImageView profileImage;
    private LinearLayout profileEditProfile;
    DBHandler dbHandler;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profileFName = root.findViewById(R.id.profileFName);
        profileLName = root.findViewById(R.id.profileLName);
        profileSection = root.findViewById(R.id.profileSection);
        profileImage = root.findViewById(R.id.profileImage);
        profileEditProfile = root.findViewById(R.id.profileEditProfile);

        dbHandler = new DBHandler(requireContext());
        Cursor cursor = dbHandler.getUser(1);

        while (cursor.moveToNext()) {
            profileFName.setText("" + cursor.getString(1));
            profileLName.setText("" + cursor.getString(2));
            profileSection.setText("" + cursor.getString(3));
            byte[] imageByte = cursor.getBlob(4);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
            profileImage.setImageBitmap(bitmap);
        }
        cursor.close();

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