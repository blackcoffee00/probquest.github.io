package com.example.loginscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.loginscreen.ui.games.GameModelClass;
import com.example.loginscreen.ui.profile.ProfileModelClass;
import com.example.loginscreen.ui.tests.TestsModelClass;

import java.io.ByteArrayOutputStream;

public class DBHandler extends SQLiteOpenHelper {
    Context context;
    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    // Test Score Table
    private static final String TABLE1_NAME = "TestsScores";
    private static final String ID = "id";
    private static final String TEST_CODE = "test_code";
    private static final String SCORE = "score";
    private static final String TIME = "time";
    private static final String testsTable =
            "CREATE TABLE " + TABLE1_NAME + "("
                    + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TEST_CODE + " INTEGER, "
                    + SCORE + " INTEGER, "
                    + TIME + " INTEGER)";

    // Profile Table
    private static final String TABLE2_NAME = "UserProfile";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteImage;
    private static final String profileTable =
            "Create table " + TABLE2_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",fname TEXT " +
                    ",lname TEXT " +
                    ",section TEXT " +
                    ",image BLOB)";

    // Game1 Table
    private static final String TABLE3_NAME = "RollDiceScore";
    private static final String rollDiceScoreTable =
            "Create table " + TABLE3_NAME + "(id INTEGER" +
                    ",gameScore INTEGER)";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(testsTable);
        db.execSQL(profileTable);
        db.execSQL(rollDiceScoreTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE3_NAME);
        onCreate(db);
    }

    // For Tests Scores
    public void storeTestScore(TestsModelClass testsModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TEST_CODE, testsModelClass.getTestCode());
        contentValues.put(SCORE, testsModelClass.getScore());
        contentValues.put(TIME, testsModelClass.getTime());

        long checkQuery = db.insert(TABLE1_NAME, null, contentValues);

        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor getScoreTime() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE1_NAME, null);

        return cursor;
    }
    public Boolean checkCode(Integer testCode) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE1_NAME + " where " + TEST_CODE + " = ?", new String[]{String.valueOf(testCode)});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // For User Profile
    public void storeData(ProfileModelClass profileModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bitmapImage = profileModelClass.getImage();

        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byteImage = byteArrayOutputStream.toByteArray();

        ContentValues contentValues = new ContentValues();
        contentValues.put("fname", profileModelClass.getFname());
        contentValues.put("lname", profileModelClass.getLname());
        contentValues.put("section", profileModelClass.getSection());
        contentValues.put("image", byteImage);

        long checkQuery = db.insert(TABLE2_NAME, null, contentValues);

        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectArgs = { String.valueOf(id) };
        Cursor cursor = db.rawQuery("SELECT * FROM UserProfile WHERE id = ? LIMIT 1", selectArgs);

        return cursor;
    }
    public Boolean checkId(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE2_NAME + " where id = ?", new String[]{String.valueOf(id)});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updateProfile(ProfileModelClass profileModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE2_NAME + " where id = ?", new String[]{String.valueOf(profileModelClass.getId())});

        if (cursor.getCount() > 0) {
            Bitmap bitmapImage = profileModelClass.getImage();
            byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byteImage = byteArrayOutputStream.toByteArray();

            ContentValues contentValues = new ContentValues();
            contentValues.put("fname", profileModelClass.getFname());
            contentValues.put("lname", profileModelClass.getLname());
            contentValues.put("section", profileModelClass.getSection());
            contentValues.put("image", byteImage);

            long result = db.update(TABLE2_NAME, contentValues, " id = ?", new String[]{String.valueOf(profileModelClass.getId())});

            db.close();

            return result != -1;
        } else {
            db.close();
            return false;
        }
    }

    // For Roll Dice Game
    public void storeGameScore(GameModelClass gameModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("gameScore", gameModelClass.getGameScore());

        long checkQuery = db.insert(TABLE3_NAME, null, contentValues);

        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean checkGameId(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE3_NAME + " where id = ?", new String[]{String.valueOf(id)});
        boolean hasData = cursor.getCount() > 0;
        cursor.close();
        return hasData;
    }

    public Cursor getGameScore() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE3_NAME, null);

        return cursor;
    }

    public Boolean updateGameScore(GameModelClass gameModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE3_NAME + " where id = ?", new String[]{String.valueOf(gameModelClass.getId())});

        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("gameScore", gameModelClass.getGameScore());

            long result = db.update(TABLE3_NAME, contentValues, " id = ?", new String[]{String.valueOf(gameModelClass.getId())});

            db.close();
            return result != -1;
        } else {
            db.close();
            return false;
        }
    }
}
