package com.example.loginscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.example.loginscreen.ui.profile.ProfileModelClass;
import com.example.loginscreen.ui.study.StudyModelClass;
import com.example.loginscreen.ui.tests.TestResultModelClass;
import com.example.loginscreen.ui.tests.TestsModelClass;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class DBHandler extends SQLiteOpenHelper {
    Context context;
    private static final String DB_NAME = "probquestdb.db";
    private static final int DB_VERSION = 1;

    // Test Score Table
    private static final String TABLE1_NAME = "TestsScores";
    private static final String TEST_CODE = "test_code";
    public static final String SCORE = "score";
    public static final String TIME = "time";
    private static final String testsTable =
            "CREATE TABLE " + TABLE1_NAME + "("
                    + TEST_CODE + " INTEGER PRIMARY KEY, "
                    + SCORE + " INTEGER, "
                    + TIME + " INTEGER)";

    // Profile Table
    private static final String TABLE2_NAME = "UserProfile";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] byteImage;
    private static final String profileTable =
            "Create table " + TABLE2_NAME + "(profileId INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",fname TEXT " +
                    ",lname TEXT " +
                    ",section TEXT " +
                    ",image BLOB)";

    //Topics TIme Table
    private static final String TABLE3_NAME = "StudyTime";
    private static final String TOPICS_ID = "topicsId";
    public static final String TIME_IN_SEC = "timeInSec";
    private static final String studyTimeTable =
            "CREATE TABLE " + TABLE3_NAME + "("
                    + TOPICS_ID + " INTEGER PRIMARY KEY, "
                    + TIME_IN_SEC + " INTEGER)";

    //Test Result Table
    private static final String TABLE4_NAME = "TestResults";
    private static final String RESULT_ID = "resultId";
    public static final String QUESTIONS = "questions";
    public static final String BTN1 = "btn1";
    public static final String BTN2 = "btn2";
    public static final String BTN3 = "btn3";
    public static final String BTN4 = "btn4";
    public static final String SOLUTION = "solution";
    public static final String ANSWER = "answer";
    public static final String SOL_TEXT = "solText";
    public static final String ANS_TEXT = "ansText";
    public static final String SOL_VIS = "solVis";
    public static final String ANS_VIS = "ansVis";
    public static final String MARK = "mark";
    public static final String BTN_BG1 = "btnbg1";
    public static final String BTN_BG2 = "btnbg2";
    public static final String BTN_BG3 = "btnbg3";
    public static final String BTN_BG4 = "btnbg4";
    private static final String testResultTable =
            "CREATE TABLE " + TABLE4_NAME + "("
                    + RESULT_ID + " INTEGER PRIMARY KEY, "
                    + QUESTIONS + " TEXT, "
                    + BTN1 + " TEXT, "
                    + BTN2 + " TEXT, "
                    + BTN3 + " TEXT, "
                    + BTN4 + " TEXT, "
                    + SOLUTION + " TEXT, "
                    + ANSWER + " TEXT, "
                    + SOL_TEXT + " INTEGER, "
                    + ANS_TEXT + " INTEGER, "
                    + SOL_VIS + " INTEGER, "
                    + ANS_VIS + " INTEGER, "
                    + MARK + " INTEGER, "
                    + BTN_BG1 + " INTEGER, "
                    + BTN_BG2 + " INTEGER, "
                    + BTN_BG3 + " INTEGER, "
                    + BTN_BG4 + " INTEGER)";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(testsTable);
        db.execSQL(profileTable);
        db.execSQL(studyTimeTable);
        db.execSQL(testResultTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE3_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE4_NAME);
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
    public Map<String, String> getScoreTime(int testCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Map<String, String> scoreAndTime = new HashMap<>();
        String[] columns = {SCORE, TIME};
        String selection = TEST_CODE + " = ?";
        String[] selectionArgs = {String.valueOf(testCode)};
        Cursor cursor = db.query(TABLE1_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int scoreIndex = cursor.getColumnIndex(SCORE);
            int timeIndex = cursor.getColumnIndex(TIME);
            String score = cursor.getString(scoreIndex);
            String time = cursor.getString(timeIndex);
            scoreAndTime.put(SCORE, String.valueOf(score));
            scoreAndTime.put(TIME, time);
        }
        return scoreAndTime;
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
    public Cursor getUser(int profileId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectArgs = { String.valueOf(profileId) };
        Cursor cursor = db.rawQuery("SELECT * FROM UserProfile WHERE profileId = ? LIMIT 1", selectArgs);

        return cursor;
    }
    public Boolean checkId(Integer profileId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE2_NAME + " where profileId = ?", new String[]{String.valueOf(profileId)});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updateProfile(ProfileModelClass profileModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE2_NAME + " where profileId = ?", new String[]{String.valueOf(profileModelClass.getProfileId())});

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

            long result = db.update(TABLE2_NAME, contentValues, " profileId = ?", new String[]{String.valueOf(profileModelClass.getProfileId())});

            db.close();

            return result != -1;
        } else {
            db.close();
            return false;
        }
    }

    //Topics TIme
    public void storeStudyTime(StudyModelClass studyModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TOPICS_ID, studyModelClass.getTopicsId());
        contentValues.put(TIME_IN_SEC, studyModelClass.getTimeSec());

        long checkQuery = db.insert(TABLE3_NAME, null, contentValues);

        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    public Map<String, String> getTopicsId(int topicsId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Map<String, String> timeInSec = new HashMap<>();
        String[] column = {TIME_IN_SEC};
        String selection = TOPICS_ID + " = ?";
        String[] selectionArgs = {String.valueOf(topicsId)};
        Cursor cursor = db.query(TABLE3_NAME, column, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int timeSecsIndex = cursor.getColumnIndex(TIME_IN_SEC);
            String timeSecs = cursor.getString(timeSecsIndex);
            timeInSec.put(TIME_IN_SEC, String.valueOf(timeSecs));
        }
        return timeInSec;
    }
    public Boolean checkTopicsId(int topicsId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE3_NAME + " where " + TOPICS_ID + " = ?", new String[]{String.valueOf(topicsId)});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean updateTime(StudyModelClass studyModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE3_NAME + " where "+ TOPICS_ID + " = ?", new String[]{String.valueOf(studyModelClass.getTopicsId())});

        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TIME_IN_SEC, studyModelClass.getTimeSec());
            long result = db.update(TABLE3_NAME, contentValues, TOPICS_ID + " = ?", new String[]{String.valueOf(studyModelClass.getTopicsId())});
            db.close();
            return result != -1;
        } else {
            db.close();
            return false;
        }
    }

    //Test Result
    public void storeTestResult(TestResultModelClass testResultModelClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(RESULT_ID, testResultModelClass.getResultId());
        contentValues.put(QUESTIONS, testResultModelClass.getTestQuestion());
        contentValues.put(BTN1, testResultModelClass.getBtn1());
        contentValues.put(BTN2, testResultModelClass.getBtn2());
        contentValues.put(BTN3, testResultModelClass.getBtn3());
        contentValues.put(BTN4, testResultModelClass.getBtn4());
        contentValues.put(SOLUTION, testResultModelClass.getTestSolution());
        contentValues.put(ANSWER, testResultModelClass.getTestAnswer());
        contentValues.put(SOL_TEXT, testResultModelClass.getSolText());
        contentValues.put(ANS_TEXT, testResultModelClass.getAnsText());
        contentValues.put(SOL_VIS, testResultModelClass.getSolVis());
        contentValues.put(ANS_VIS, testResultModelClass.getSolVis());
        contentValues.put(MARK, testResultModelClass.getMark());
        contentValues.put(BTN_BG1, testResultModelClass.getBtnbg1());
        contentValues.put(BTN_BG2, testResultModelClass.getBtnbg2());
        contentValues.put(BTN_BG3, testResultModelClass.getBtnbg3());
        contentValues.put(BTN_BG4, testResultModelClass.getBtnbg4());

        long checkQuery = db.insert(TABLE4_NAME, null, contentValues);

        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    public Map<String, String> getResultId(int resultId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Map<String, String> result = new HashMap<>();
        String[] column = {QUESTIONS, BTN1, BTN2, BTN3, BTN4, SOLUTION, ANSWER, SOL_TEXT, ANS_TEXT, SOL_VIS, ANS_VIS, MARK, BTN_BG1, BTN_BG2, BTN_BG3, BTN_BG4};
        String selection = RESULT_ID + " = ?";
        String[] selectionArgs = {String.valueOf(resultId)};
        Cursor cursor = db.query(TABLE4_NAME, column, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex(QUESTIONS);
            String question = cursor.getString(questionIndex);
            int btn1Index = cursor.getColumnIndex(BTN1);
            String btn1 = cursor.getString(btn1Index);
            int btn2Index = cursor.getColumnIndex(BTN2);
            String btn2 = cursor.getString(btn2Index);
            int btn3Index = cursor.getColumnIndex(BTN3);
            String btn3 = cursor.getString(btn3Index);
            int btn4Index = cursor.getColumnIndex(BTN4);
            String btn4 = cursor.getString(btn4Index);
            int solTextIndex = cursor.getColumnIndex(SOL_TEXT);
            String solText = cursor.getString(solTextIndex);
            int ansTextIndex = cursor.getColumnIndex(ANS_TEXT);
            String ansText = cursor.getString(ansTextIndex);
            int solVisIndex = cursor.getColumnIndex(SOL_VIS);
            String solVis = cursor.getString(solVisIndex);
            int ansVisIndex = cursor.getColumnIndex(ANS_VIS);
            String ansVis = cursor.getString(ansVisIndex);
            int markIndex = cursor.getColumnIndex(MARK);
            String mark = cursor.getString(markIndex);
            int btnbg1Index = cursor.getColumnIndex(BTN_BG1);
            String btnbg1 = cursor.getString(btnbg1Index);
            int btnbg2Index = cursor.getColumnIndex(BTN_BG2);
            String btnbg2 = cursor.getString(btnbg2Index);
            int btnbg3Index = cursor.getColumnIndex(BTN_BG3);
            String btnbg3 = cursor.getString(btnbg3Index);
            int btnbg4Index = cursor.getColumnIndex(BTN_BG4);
            String btnbg4 = cursor.getString(btnbg4Index);
            result.put(QUESTIONS, String.valueOf(question));
            result.put(BTN1, String.valueOf(btn1));
            result.put(BTN2, String.valueOf(btn2));
            result.put(BTN3, String.valueOf(btn3));
            result.put(BTN4, String.valueOf(btn4));
            result.put(SOL_TEXT, String.valueOf(solText));
            result.put(ANS_TEXT, String.valueOf(ansText));
            result.put(SOL_VIS, String.valueOf(solVis));
            result.put(ANS_VIS, String.valueOf(ansVis));
            result.put(MARK, String.valueOf(mark));
            result.put(BTN_BG1, String.valueOf(btnbg1));
            result.put(BTN_BG2, String.valueOf(btnbg2));
            result.put(BTN_BG3, String.valueOf(btnbg3));
            result.put(BTN_BG4, String.valueOf(btnbg4));
        }
        return result;
    }
}
