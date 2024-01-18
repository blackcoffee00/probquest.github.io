package com.example.loginscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.loginscreen.ui.profile.ProfileModelClass;

import java.io.ByteArrayOutputStream;

import kotlin.annotation.Target;

public class DBHandler extends SQLiteOpenHelper {
    Context context;
    private static final String DB_NAME = "user_profile.db";
    private static final int DB_VERSION = 1;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteImage;

    private static final String createTableQuery =
            "Create table UserProfile(name TEXT " +
                    ",section TEXT " +
                    ",image BLOB)";

    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void storeData(ProfileModelClass profileModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bitmapImage = profileModelClass.getImage();

        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byteImage = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", profileModelClass.getName());
        contentValues.put("section", profileModelClass.getSection());
        contentValues.put("image", byteImage);

        long checkQuery = db.insert("UserProfile", null, contentValues);

        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getUser() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from UserProfile", null);
        return cursor;
    }

}
