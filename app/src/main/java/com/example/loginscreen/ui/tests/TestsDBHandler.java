package com.example.loginscreen.ui.tests;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestsDBHandler extends SQLiteOpenHelper {
    Context context;
    private static final String DB_NAME = "user_score.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "Test_Scores";
    private static final String ID = "id";
    private static final String SCORE = "score";
    private static final String TIME = "time";
    private static final String createTableQuery =
            "CREATE TABLE " + TABLE_NAME + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + SCORE + " INTEGER, "
                    + TIME + " INTEGER)";

    public TestsDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void storeTestScore(TestsModelClass testsModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SCORE, testsModelClass.getScore());
        contentValues.put(TIME, testsModelClass.getTime());

        long checkQuery = db.insert(TABLE_NAME, null, contentValues);

        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor getScoreTime() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);

        return cursor;
    }

    public void updateScore(TestsModelClass testsModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SCORE, testsModelClass.getScore());
        contentValues.put(TIME, testsModelClass.getTime());

        db.update(TABLE_NAME, contentValues, ID + " = ?", new String[]{String.valueOf(testsModelClass.getId())});
    }
}
