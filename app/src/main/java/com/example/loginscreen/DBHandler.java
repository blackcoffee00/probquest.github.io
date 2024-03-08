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
import com.example.loginscreen.ui.tests.T2TestResultModelClass;
import com.example.loginscreen.ui.tests.TestResultModelClass;
import com.example.loginscreen.ui.tests.TestsModelClass;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    //Topic One Pre-Test Table
    public static final String TABLE4_NAME = "T1PreTestResult";
    private static final String t1PreTestResultTable = "CREATE TABLE " + TABLE4_NAME + "(t11Id INTEGER PRIMARY KEY AUTOINCREMENT, t11questions TEXT, t11btn1 TEXT, t11btn2 TEXT, t11btn3 TEXT, t11btn4 TEXT, t11solution TEXT, t11answer TEXT, t11mark INTEGER, t11btnbg1 INTEGER, t11btnbg2 INTEGER, t11btnbg3 INTEGER, t11btnbg4 INTEGER)";

    //Topic One Post-Test Table
    public static final String TABLE5_NAME = "T1PostTestResult";
    private static final String t1PostTestResultTable = "CREATE TABLE " + TABLE5_NAME + "(t12Id INTEGER PRIMARY KEY AUTOINCREMENT, t12questions TEXT, t12btn1 TEXT, t12btn2 TEXT, t12btn3 TEXT, t12btn4 TEXT, t12solution TEXT, t12answer TEXT, t12mark INTEGER, t12btnbg1 INTEGER, t12btnbg2 INTEGER, t12btnbg3 INTEGER, t12btnbg4 INTEGER)";

    //Topic Two Pre-Test Table
    public static final String TABLE6_NAME = "T2PreTestResult";
    private static final String t2PreTestResultTable = "CREATE TABLE " + TABLE6_NAME + "(t21Id INTEGER PRIMARY KEY AUTOINCREMENT, t21questions TEXT, t21mark INTEGER, t21btnbg1 INTEGER, t21btnbg2 INTEGER)";

    //Topic Two Post-Test Table
    public static final String TABLE7_NAME = "T2PostTestResult";
    private static final String t2PostTestResultTable = "CREATE TABLE " + TABLE7_NAME + "(t22Id INTEGER PRIMARY KEY AUTOINCREMENT, t22questions TEXT, t22mark INTEGER, t22btnbg1 INTEGER, t22btnbg2 INTEGER)";

    //Topic Three Pre-Test Table
    public static final String TABLE8_NAME = "T3PreTestResult";
    private static final String t3PreTestResultTable = "CREATE TABLE " + TABLE8_NAME + "(t31Id INTEGER PRIMARY KEY AUTOINCREMENT, t31questions TEXT, t31btn1 TEXT, t31btn2 TEXT, t31btn3 TEXT, t31btn4 TEXT, t31solution TEXT, t31answer TEXT, t31mark INTEGER, t31btnbg1 INTEGER, t31btnbg2 INTEGER, t31btnbg3 INTEGER, t31btnbg4 INTEGER)";

    //Topic Three Post-Test Table
    public static final String TABLE9_NAME = "T3PostTestResult";
    private static final String t3PostTestResultTable = "CREATE TABLE " + TABLE9_NAME + "(t32Id INTEGER PRIMARY KEY AUTOINCREMENT, t32questions TEXT, t32btn1 TEXT, t32btn2 TEXT, t32btn3 TEXT, t32btn4 TEXT, t32solution TEXT, t32answer TEXT, t32mark INTEGER, t32btnbg1 INTEGER, t32btnbg2 INTEGER, t32btnbg3 INTEGER, t32btnbg4 INTEGER)";

    //Topic Four Pre-Test Table
    public static final String TABLE10_NAME = "T4PreTestResult";
    private static final String t4PreTestResultTable = "CREATE TABLE " + TABLE10_NAME + "(t41Id INTEGER PRIMARY KEY AUTOINCREMENT, t41questions TEXT, t41btn1 TEXT, t41btn2 TEXT, t41btn3 TEXT, t41btn4 TEXT, t41solution TEXT, t41answer TEXT, t41mark INTEGER, t41btnbg1 INTEGER, t41btnbg2 INTEGER, t41btnbg3 INTEGER, t41btnbg4 INTEGER)";

    //Topic Four Post-Test Table
    public static final String TABLE11_NAME = "T4PostTestResult";
    private static final String t4PostTestResultTable = "CREATE TABLE " + TABLE11_NAME + "(t42Id INTEGER PRIMARY KEY AUTOINCREMENT, t42questions TEXT, t42btn1 TEXT, t42btn2 TEXT, t42btn3 TEXT, t42btn4 TEXT, t42solution TEXT, t42answer TEXT, t42mark INTEGER, t42btnbg1 INTEGER, t42btnbg2 INTEGER, t42btnbg3 INTEGER, t42btnbg4 INTEGER)";

    //Topic Five Pre-Test Table
    public static final String TABLE12_NAME = "T5PreTestResult";
    private static final String t5PreTestResultTable = "CREATE TABLE " + TABLE12_NAME + "(t51Id INTEGER PRIMARY KEY AUTOINCREMENT, t51questions TEXT, t51btn1 TEXT, t51btn2 TEXT, t51btn3 TEXT, t51btn4 TEXT, t51solution TEXT, t51answer TEXT, t51mark INTEGER, t51btnbg1 INTEGER, t51btnbg2 INTEGER, t51btnbg3 INTEGER, t51btnbg4 INTEGER)";

    //Topic Five Post-Test Table
    public static final String TABLE13_NAME = "T5PostTestResult";
    private static final String t5PostTestResultTable = "CREATE TABLE " + TABLE13_NAME + "(t52Id INTEGER PRIMARY KEY AUTOINCREMENT, t52questions TEXT, t52btn1 TEXT, t52btn2 TEXT, t52btn3 TEXT, t52btn4 TEXT, t52solution TEXT, t52answer TEXT, t52mark INTEGER, t52btnbg1 INTEGER, t52btnbg2 INTEGER, t52btnbg3 INTEGER, t52btnbg4 INTEGER)";

    //Topic Six Pre-Test Table
    public static final String TABLE14_NAME = "T6PreTestResult";
    private static final String t6PreTestResultTable = "CREATE TABLE " + TABLE14_NAME + "(t61Id INTEGER PRIMARY KEY AUTOINCREMENT, t61questions TEXT, t61btn1 TEXT, t61btn2 TEXT, t61btn3 TEXT, t61btn4 TEXT, t61solution TEXT, t61answer TEXT, t61mark INTEGER, t61btnbg1 INTEGER, t61btnbg2 INTEGER, t61btnbg3 INTEGER, t61btnbg4 INTEGER)";

    //Topic Six Post-Test Table
    public static final String TABLE15_NAME = "T6PostTestResult";
    private static final String t6PostTestResultTable = "CREATE TABLE " + TABLE15_NAME + "(t62Id INTEGER PRIMARY KEY AUTOINCREMENT, t62questions TEXT, t62btn1 TEXT, t62btn2 TEXT, t62btn3 TEXT, t62btn4 TEXT, t62solution TEXT, t62answer TEXT, t62mark INTEGER, t62btnbg1 INTEGER, t62btnbg2 INTEGER, t62btnbg3 INTEGER, t62btnbg4 INTEGER)";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(testsTable);
        db.execSQL(profileTable);
        db.execSQL(studyTimeTable);
        db.execSQL(t1PreTestResultTable);
        db.execSQL(t1PostTestResultTable);
        db.execSQL(t2PreTestResultTable);
        db.execSQL(t2PostTestResultTable);
        db.execSQL(t3PreTestResultTable);
        db.execSQL(t3PostTestResultTable);
        db.execSQL(t4PreTestResultTable);
        db.execSQL(t4PostTestResultTable);
        db.execSQL(t5PreTestResultTable);
        db.execSQL(t5PostTestResultTable);
        db.execSQL(t6PreTestResultTable);
        db.execSQL(t6PostTestResultTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE2_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE3_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE4_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE5_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE6_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE7_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE8_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE9_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE10_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE11_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE12_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE13_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE14_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE15_NAME);
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

    //T1 Pre-Test Result
    public void storeT11Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t11questions", testResult.getTestQuestion());
        contentValues.put("t11btn1", testResult.getBtn1());
        contentValues.put("t11btn2", testResult.getBtn2());
        contentValues.put("t11btn3", testResult.getBtn3());
        contentValues.put("t11btn4", testResult.getBtn4());
        contentValues.put("t11solution", testResult.getTestSolution());
        contentValues.put("t11answer", testResult.getTestAnswer());
        contentValues.put("t11mark", testResult.getMark());
        contentValues.put("t11btnbg1", testResult.getBtnbg1());
        contentValues.put("t11btnbg2", testResult.getBtnbg2());
        contentValues.put("t11btnbg3", testResult.getBtnbg3());
        contentValues.put("t11btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE4_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT11Result(int t11Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t11Id", "t11questions", "t11btn1", "t11btn2", "t11btn3", "t11btn4", "t11solution", "t11answer", "t11mark", "t11btnbg1", "t11btnbg2", "t11btnbg3", "t11btnbg4"};
        String selection = "t11Id = ?";
        String[] selectionArgs = {String.valueOf(t11Id)};

        Cursor cursor = db.query(TABLE4_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t11questions");
            int btn1Index = cursor.getColumnIndex("t11btn1");
            int btn2Index = cursor.getColumnIndex("t11btn2");
            int btn3Index = cursor.getColumnIndex("t11btn3");
            int btn4Index = cursor.getColumnIndex("t11btn4");
            int solutionIndex = cursor.getColumnIndex("t11solution");
            int answerIndex = cursor.getColumnIndex("t11answer");
            int markIndex = cursor.getColumnIndex("t11mark");
            int btnbg1Index = cursor.getColumnIndex("t11btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t11btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t11btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t11btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T1 Post-Test Result
    public void storeT12Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t12questions", testResult.getTestQuestion());
        contentValues.put("t12btn1", testResult.getBtn1());
        contentValues.put("t12btn2", testResult.getBtn2());
        contentValues.put("t12btn3", testResult.getBtn3());
        contentValues.put("t12btn4", testResult.getBtn4());
        contentValues.put("t12solution", testResult.getTestSolution());
        contentValues.put("t12answer", testResult.getTestAnswer());
        contentValues.put("t12mark", testResult.getMark());
        contentValues.put("t12btnbg1", testResult.getBtnbg1());
        contentValues.put("t12btnbg2", testResult.getBtnbg2());
        contentValues.put("t12btnbg3", testResult.getBtnbg3());
        contentValues.put("t12btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE5_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT12Result(int t12Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t12Id", "t12questions", "t12btn1", "t12btn2", "t12btn3", "t12btn4", "t12solution", "t12answer", "t12mark", "t12btnbg1", "t12btnbg2", "t12btnbg3", "t12btnbg4"};
        String selection = "t12Id = ?";
        String[] selectionArgs = {String.valueOf(t12Id)};

        Cursor cursor = db.query(TABLE5_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t12questions");
            int btn1Index = cursor.getColumnIndex("t12btn1");
            int btn2Index = cursor.getColumnIndex("t12btn2");
            int btn3Index = cursor.getColumnIndex("t12btn3");
            int btn4Index = cursor.getColumnIndex("t12btn4");
            int solutionIndex = cursor.getColumnIndex("t12solution");
            int answerIndex = cursor.getColumnIndex("t12answer");
            int markIndex = cursor.getColumnIndex("t12mark");
            int btnbg1Index = cursor.getColumnIndex("t12btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t12btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t12btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t12btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T2 Pre-Test Result
    public void storeT21Result(T2TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t21questions", testResult.getT2TestQuestion());
        contentValues.put("t21mark", testResult.getT2Mark());
        contentValues.put("t21btnbg1", testResult.getT2Btnbg1());
        contentValues.put("t21btnbg2", testResult.getT2Btnbg2());

        long checkQuery = db.insert(TABLE6_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public T2TestResultModelClass getT21Result(int t21Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        T2TestResultModelClass testResult = new T2TestResultModelClass();

        String[] columns = {"t21Id", "t21questions", "t21mark", "t21btnbg1", "t21btnbg2"};
        String selection = "t21Id = ?";
        String[] selectionArgs = {String.valueOf(t21Id)};

        Cursor cursor = db.query(TABLE6_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t21questions");
            int markIndex = cursor.getColumnIndex("t21mark");
            int btnbg1Index = cursor.getColumnIndex("t21btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t21btnbg2");
            testResult.setT2TestQuestion(cursor.getString(questionIndex));
            testResult.setT2Mark(cursor.getInt(markIndex));
            testResult.setT2Btnbg1(cursor.getInt(btnbg1Index));
            testResult.setT2Btnbg2(cursor.getInt(btnbg2Index));
            cursor.close();
        }
        return testResult;
    }

    //T2 Post-Test Result
    public void storeT22Result(T2TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t22questions", testResult.getT2TestQuestion());
        contentValues.put("t22mark", testResult.getT2Mark());
        contentValues.put("t22btnbg1", testResult.getT2Btnbg1());
        contentValues.put("t22btnbg2", testResult.getT2Btnbg2());

        long checkQuery = db.insert(TABLE7_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public T2TestResultModelClass getT22Result(int t22Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        T2TestResultModelClass testResult = new T2TestResultModelClass();

        String[] columns = {"t22Id", "t22questions", "t22mark", "t22btnbg1", "t22btnbg2"};
        String selection = "t22Id = ?";
        String[] selectionArgs = {String.valueOf(t22Id)};

        Cursor cursor = db.query(TABLE7_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t22questions");
            int markIndex = cursor.getColumnIndex("t22mark");
            int btnbg1Index = cursor.getColumnIndex("t22btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t22btnbg2");
            testResult.setT2TestQuestion(cursor.getString(questionIndex));
            testResult.setT2Mark(cursor.getInt(markIndex));
            testResult.setT2Btnbg1(cursor.getInt(btnbg1Index));
            testResult.setT2Btnbg2(cursor.getInt(btnbg2Index));
            cursor.close();
        }
        return testResult;
    }

    //T3 Pre-Test Result
    public void storeT31Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t31questions", testResult.getTestQuestion());
        contentValues.put("t31btn1", testResult.getBtn1());
        contentValues.put("t31btn2", testResult.getBtn2());
        contentValues.put("t31btn3", testResult.getBtn3());
        contentValues.put("t31btn4", testResult.getBtn4());
        contentValues.put("t31solution", testResult.getTestSolution());
        contentValues.put("t31answer", testResult.getTestAnswer());
        contentValues.put("t31mark", testResult.getMark());
        contentValues.put("t31btnbg1", testResult.getBtnbg1());
        contentValues.put("t31btnbg2", testResult.getBtnbg2());
        contentValues.put("t31btnbg3", testResult.getBtnbg3());
        contentValues.put("t31btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE8_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT31Result(int t31Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t31Id", "t31questions", "t31btn1", "t31btn2", "t31btn3", "t31btn4", "t31solution", "t31answer", "t31mark", "t31btnbg1", "t31btnbg2", "t31btnbg3", "t31btnbg4"};
        String selection = "t31Id = ?";
        String[] selectionArgs = {String.valueOf(t31Id)};

        Cursor cursor = db.query(TABLE8_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t31questions");
            int btn1Index = cursor.getColumnIndex("t31btn1");
            int btn2Index = cursor.getColumnIndex("t31btn2");
            int btn3Index = cursor.getColumnIndex("t31btn3");
            int btn4Index = cursor.getColumnIndex("t31btn4");
            int solutionIndex = cursor.getColumnIndex("t31solution");
            int answerIndex = cursor.getColumnIndex("t31answer");
            int markIndex = cursor.getColumnIndex("t31mark");
            int btnbg1Index = cursor.getColumnIndex("t31btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t31btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t31btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t31btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T3 Post-Test Result
    public void storeT32Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t32questions", testResult.getTestQuestion());
        contentValues.put("t32btn1", testResult.getBtn1());
        contentValues.put("t32btn2", testResult.getBtn2());
        contentValues.put("t32btn3", testResult.getBtn3());
        contentValues.put("t32btn4", testResult.getBtn4());
        contentValues.put("t32solution", testResult.getTestSolution());
        contentValues.put("t32answer", testResult.getTestAnswer());
        contentValues.put("t32mark", testResult.getMark());
        contentValues.put("t32btnbg1", testResult.getBtnbg1());
        contentValues.put("t32btnbg2", testResult.getBtnbg2());
        contentValues.put("t32btnbg3", testResult.getBtnbg3());
        contentValues.put("t32btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE9_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT32Result(int t32Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t32Id", "t32questions", "t32btn1", "t32btn2", "t32btn3", "t32btn4", "t32solution", "t32answer", "t32mark", "t32btnbg1", "t32btnbg2", "t32btnbg3", "t32btnbg4"};
        String selection = "t32Id = ?";
        String[] selectionArgs = {String.valueOf(t32Id)};

        Cursor cursor = db.query(TABLE9_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t32questions");
            int btn1Index = cursor.getColumnIndex("t32btn1");
            int btn2Index = cursor.getColumnIndex("t32btn2");
            int btn3Index = cursor.getColumnIndex("t32btn3");
            int btn4Index = cursor.getColumnIndex("t32btn4");
            int solutionIndex = cursor.getColumnIndex("t32solution");
            int answerIndex = cursor.getColumnIndex("t32answer");
            int markIndex = cursor.getColumnIndex("t32mark");
            int btnbg1Index = cursor.getColumnIndex("t32btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t32btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t32btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t32btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T4 Pre-Test Result
    public void storeT41Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t41questions", testResult.getTestQuestion());
        contentValues.put("t41btn1", testResult.getBtn1());
        contentValues.put("t41btn2", testResult.getBtn2());
        contentValues.put("t41btn3", testResult.getBtn3());
        contentValues.put("t41btn4", testResult.getBtn4());
        contentValues.put("t41solution", testResult.getTestSolution());
        contentValues.put("t41answer", testResult.getTestAnswer());
        contentValues.put("t41mark", testResult.getMark());
        contentValues.put("t41btnbg1", testResult.getBtnbg1());
        contentValues.put("t41btnbg2", testResult.getBtnbg2());
        contentValues.put("t41btnbg3", testResult.getBtnbg3());
        contentValues.put("t41btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE10_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT41Result(int t41Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t41Id", "t41questions", "t41btn1", "t41btn2", "t41btn3", "t41btn4", "t41solution", "t41answer", "t41mark", "t41btnbg1", "t41btnbg2", "t41btnbg3", "t41btnbg4"};
        String selection = "t41Id = ?";
        String[] selectionArgs = {String.valueOf(t41Id)};

        Cursor cursor = db.query(TABLE10_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t41questions");
            int btn1Index = cursor.getColumnIndex("t41btn1");
            int btn2Index = cursor.getColumnIndex("t41btn2");
            int btn3Index = cursor.getColumnIndex("t41btn3");
            int btn4Index = cursor.getColumnIndex("t41btn4");
            int solutionIndex = cursor.getColumnIndex("t41solution");
            int answerIndex = cursor.getColumnIndex("t41answer");
            int markIndex = cursor.getColumnIndex("t41mark");
            int btnbg1Index = cursor.getColumnIndex("t41btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t41btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t41btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t41btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T4 Post-Test Result
    public void storeT42Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t42questions", testResult.getTestQuestion());
        contentValues.put("t42btn1", testResult.getBtn1());
        contentValues.put("t42btn2", testResult.getBtn2());
        contentValues.put("t42btn3", testResult.getBtn3());
        contentValues.put("t42btn4", testResult.getBtn4());
        contentValues.put("t42solution", testResult.getTestSolution());
        contentValues.put("t42answer", testResult.getTestAnswer());
        contentValues.put("t42mark", testResult.getMark());
        contentValues.put("t42btnbg1", testResult.getBtnbg1());
        contentValues.put("t42btnbg2", testResult.getBtnbg2());
        contentValues.put("t42btnbg3", testResult.getBtnbg3());
        contentValues.put("t42btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE11_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT42Result(int t42Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t42Id", "t42questions", "t42btn1", "t42btn2", "t42btn3", "t42btn4", "t42solution", "t42answer", "t42mark", "t42btnbg1", "t42btnbg2", "t42btnbg3", "t42btnbg4"};
        String selection = "t42Id = ?";
        String[] selectionArgs = {String.valueOf(t42Id)};

        Cursor cursor = db.query(TABLE11_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t42questions");
            int btn1Index = cursor.getColumnIndex("t42btn1");
            int btn2Index = cursor.getColumnIndex("t42btn2");
            int btn3Index = cursor.getColumnIndex("t42btn3");
            int btn4Index = cursor.getColumnIndex("t42btn4");
            int solutionIndex = cursor.getColumnIndex("t42solution");
            int answerIndex = cursor.getColumnIndex("t42answer");
            int markIndex = cursor.getColumnIndex("t42mark");
            int btnbg1Index = cursor.getColumnIndex("t42btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t42btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t42btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t42btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T5 Pre-Test Result
    public void storeT51Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t51questions", testResult.getTestQuestion());
        contentValues.put("t51btn1", testResult.getBtn1());
        contentValues.put("t51btn2", testResult.getBtn2());
        contentValues.put("t51btn3", testResult.getBtn3());
        contentValues.put("t51btn4", testResult.getBtn4());
        contentValues.put("t51solution", testResult.getTestSolution());
        contentValues.put("t51answer", testResult.getTestAnswer());
        contentValues.put("t51mark", testResult.getMark());
        contentValues.put("t51btnbg1", testResult.getBtnbg1());
        contentValues.put("t51btnbg2", testResult.getBtnbg2());
        contentValues.put("t51btnbg3", testResult.getBtnbg3());
        contentValues.put("t51btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE12_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT51Result(int t51Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t51Id", "t51questions", "t51btn1", "t51btn2", "t51btn3", "t51btn4", "t51solution", "t51answer", "t51mark", "t51btnbg1", "t51btnbg2", "t51btnbg3", "t51btnbg4"};
        String selection = "t51Id = ?";
        String[] selectionArgs = {String.valueOf(t51Id)};

        Cursor cursor = db.query(TABLE12_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t51questions");
            int btn1Index = cursor.getColumnIndex("t51btn1");
            int btn2Index = cursor.getColumnIndex("t51btn2");
            int btn3Index = cursor.getColumnIndex("t51btn3");
            int btn4Index = cursor.getColumnIndex("t51btn4");
            int solutionIndex = cursor.getColumnIndex("t51solution");
            int answerIndex = cursor.getColumnIndex("t51answer");
            int markIndex = cursor.getColumnIndex("t51mark");
            int btnbg1Index = cursor.getColumnIndex("t51btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t51btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t51btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t51btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T5 Post-Test Result
    public void storeT52Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t52questions", testResult.getTestQuestion());
        contentValues.put("t52btn1", testResult.getBtn1());
        contentValues.put("t52btn2", testResult.getBtn2());
        contentValues.put("t52btn3", testResult.getBtn3());
        contentValues.put("t52btn4", testResult.getBtn4());
        contentValues.put("t52solution", testResult.getTestSolution());
        contentValues.put("t52answer", testResult.getTestAnswer());
        contentValues.put("t52mark", testResult.getMark());
        contentValues.put("t52btnbg1", testResult.getBtnbg1());
        contentValues.put("t52btnbg2", testResult.getBtnbg2());
        contentValues.put("t52btnbg3", testResult.getBtnbg3());
        contentValues.put("t52btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE13_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT52Result(int t52Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t52Id", "t52questions", "t52btn1", "t52btn2", "t52btn3", "t52btn4", "t52solution", "t52answer", "t52mark", "t52btnbg1", "t52btnbg2", "t52btnbg3", "t52btnbg4"};
        String selection = "t52Id = ?";
        String[] selectionArgs = {String.valueOf(t52Id)};

        Cursor cursor = db.query(TABLE13_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t52questions");
            int btn1Index = cursor.getColumnIndex("t52btn1");
            int btn2Index = cursor.getColumnIndex("t52btn2");
            int btn3Index = cursor.getColumnIndex("t52btn3");
            int btn4Index = cursor.getColumnIndex("t52btn4");
            int solutionIndex = cursor.getColumnIndex("t52solution");
            int answerIndex = cursor.getColumnIndex("t52answer");
            int markIndex = cursor.getColumnIndex("t52mark");
            int btnbg1Index = cursor.getColumnIndex("t52btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t52btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t52btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t52btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T6 Pre-Test Result
    public void storeT61Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t61questions", testResult.getTestQuestion());
        contentValues.put("t61btn1", testResult.getBtn1());
        contentValues.put("t61btn2", testResult.getBtn2());
        contentValues.put("t61btn3", testResult.getBtn3());
        contentValues.put("t61btn4", testResult.getBtn4());
        contentValues.put("t61solution", testResult.getTestSolution());
        contentValues.put("t61answer", testResult.getTestAnswer());
        contentValues.put("t61mark", testResult.getMark());
        contentValues.put("t61btnbg1", testResult.getBtnbg1());
        contentValues.put("t61btnbg2", testResult.getBtnbg2());
        contentValues.put("t61btnbg3", testResult.getBtnbg3());
        contentValues.put("t61btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE14_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT61Result(int t61Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t61Id", "t61questions", "t61btn1", "t61btn2", "t61btn3", "t61btn4", "t61solution", "t61answer", "t61mark", "t61btnbg1", "t61btnbg2", "t61btnbg3", "t61btnbg4"};
        String selection = "t61Id = ?";
        String[] selectionArgs = {String.valueOf(t61Id)};

        Cursor cursor = db.query(TABLE14_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t61questions");
            int btn1Index = cursor.getColumnIndex("t61btn1");
            int btn2Index = cursor.getColumnIndex("t61btn2");
            int btn3Index = cursor.getColumnIndex("t61btn3");
            int btn4Index = cursor.getColumnIndex("t61btn4");
            int solutionIndex = cursor.getColumnIndex("t61solution");
            int answerIndex = cursor.getColumnIndex("t61answer");
            int markIndex = cursor.getColumnIndex("t61mark");
            int btnbg1Index = cursor.getColumnIndex("t61btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t61btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t61btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t61btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }

    //T6 Post-Test Result
    public void storeT62Result(TestResultModelClass testResult) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("t62questions", testResult.getTestQuestion());
        contentValues.put("t62btn1", testResult.getBtn1());
        contentValues.put("t62btn2", testResult.getBtn2());
        contentValues.put("t62btn3", testResult.getBtn3());
        contentValues.put("t62btn4", testResult.getBtn4());
        contentValues.put("t62solution", testResult.getTestSolution());
        contentValues.put("t62answer", testResult.getTestAnswer());
        contentValues.put("t62mark", testResult.getMark());
        contentValues.put("t62btnbg1", testResult.getBtnbg1());
        contentValues.put("t62btnbg2", testResult.getBtnbg2());
        contentValues.put("t62btnbg3", testResult.getBtnbg3());
        contentValues.put("t62btnbg4", testResult.getBtnbg4());

        long checkQuery = db.insert(TABLE15_NAME, null, contentValues);
        if (checkQuery != -1) {
            db.close();
        } else {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public TestResultModelClass getT62Result(int t62Id) {
        SQLiteDatabase db = this.getReadableDatabase();
        TestResultModelClass testResult = new TestResultModelClass();

        String[] columns = {"t62Id", "t62questions", "t62btn1", "t62btn2", "t62btn3", "t62btn4", "t62solution", "t62answer", "t62mark", "t62btnbg1", "t62btnbg2", "t62btnbg3", "t62btnbg4"};
        String selection = "t62Id = ?";
        String[] selectionArgs = {String.valueOf(t62Id)};

        Cursor cursor = db.query(TABLE15_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            int questionIndex = cursor.getColumnIndex("t62questions");
            int btn1Index = cursor.getColumnIndex("t62btn1");
            int btn2Index = cursor.getColumnIndex("t62btn2");
            int btn3Index = cursor.getColumnIndex("t62btn3");
            int btn4Index = cursor.getColumnIndex("t62btn4");
            int solutionIndex = cursor.getColumnIndex("t62solution");
            int answerIndex = cursor.getColumnIndex("t62answer");
            int markIndex = cursor.getColumnIndex("t62mark");
            int btnbg1Index = cursor.getColumnIndex("t62btnbg1");
            int btnbg2Index = cursor.getColumnIndex("t62btnbg2");
            int btnbg3Index = cursor.getColumnIndex("t62btnbg3");
            int btnbg4Index = cursor.getColumnIndex("t62btnbg4");
            testResult.setTestQuestion(cursor.getString(questionIndex));
            testResult.setBtn1(cursor.getString(btn1Index));
            testResult.setBtn2(cursor.getString(btn2Index));
            testResult.setBtn3(cursor.getString(btn3Index));
            testResult.setBtn4(cursor.getString(btn4Index));
            testResult.setTestSolution(cursor.getString(solutionIndex));
            testResult.setTestAnswer(cursor.getString(answerIndex));
            testResult.setMark(cursor.getInt(markIndex));
            testResult.setBtnbg1(cursor.getInt(btnbg1Index));
            testResult.setBtnbg2(cursor.getInt(btnbg2Index));
            testResult.setBtnbg3(cursor.getInt(btnbg3Index));
            testResult.setBtnbg4(cursor.getInt(btnbg4Index));
            cursor.close();
        }
        return testResult;
    }
}
