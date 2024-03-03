package com.example.loginscreen.ui.tests.topicone;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class SharedPrefsHelper {
    private static final String PREFS_NAME = "T1PreViewResult";

    public static void saveLists(Context context, List<String> list1, List<String> list2, List<String> list3, List<String> list4, List<String> list5, List<String> list6, List<String> list7, List<Integer> list8, List<Integer> list9, List<Integer> list10, List<Integer> list11, int[] list12, int[] list13, int[] list14, int[] list15, int[] list16) {
        SharedPreferences.Editor editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        Gson gson = new Gson();
        editor.putString("list1", gson.toJson(list1));
        editor.putString("list2", gson.toJson(list2));
        editor.putString("list3", gson.toJson(list3));
        editor.putString("list4", gson.toJson(list4));
        editor.putString("list5", gson.toJson(list5));
        editor.putString("list6", gson.toJson(list6));
        editor.putString("list7", gson.toJson(list7));
        editor.putInt("list8", list8.get(0));
        editor.putInt("list9", list9.get(0));
        editor.putInt("list10", list10.get(0));
        editor.putInt("list11", list11.get(0));

        for (int i = 0; i < list12.length; i++) {
            editor.putInt("list12" + i, list12[i]);
        }
        for (int i = 0; i < list13.length; i++) {
            editor.putInt("list13" + i, list13[i]);
        }
        for (int i = 0; i < list14.length; i++) {
            editor.putInt("list14" + i, list14[i]);
        }
        for (int i = 0; i < list15.length; i++) {
            editor.putInt("list15" + i, list15[i]);
        }
        for (int i = 0; i < list16.length; i++) {
            editor.putInt("list16" + i, list16[i]);
        }

        editor.apply();
    }

    public static void getLists(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type1 = new TypeToken<List<String>>() {}.getType();
        Type type2 = new TypeToken<List<Integer>>() {}.getType();
        Type type3 = new TypeToken<int[]>() {}.getType();
        List<String> list1 = gson.fromJson(prefs.getString("list1", ""), type1);
        List<String> list2 = gson.fromJson(prefs.getString("list2", ""), type1);
        List<String> list3 = gson.fromJson(prefs.getString("list3", ""), type1);
        List<String> list4 = gson.fromJson(prefs.getString("list4", ""), type1);
        List<String> list5 = gson.fromJson(prefs.getString("list5", ""), type1);
        List<String> list6 = gson.fromJson(prefs.getString("list6", ""), type1);
        List<String> list7 = gson.fromJson(prefs.getString("list7", ""), type1);
        int list8 = prefs.getInt("list8", 0);
        int list9 = prefs.getInt("list9", 0);
        int list10 = prefs.getInt("list10", 0);
        int list11 = prefs.getInt("list11", 0);
        int[] list12 = gson.fromJson(prefs.getString("list12", ""), type3);
        int[] list13 = gson.fromJson(prefs.getString("list13", ""), type3);
        int[] list14 = gson.fromJson(prefs.getString("list14", ""), type3);
        int[] list15 = gson.fromJson(prefs.getString("list15", ""), type3);
        int[] list16 = gson.fromJson(prefs.getString("list16", ""), type3);
    }
}
