package com.example.loginscreen.ui.tests;

import android.content.Intent;

public class TestsModelClass {
    private Integer id;
    private String score, time;

    public TestsModelClass(String score, String time) {
        this.id = id;
        this.score = score;
        this.time = time;
    }

    public TestsModelClass(Integer id, String score, String time) {
        this.id = id;
        this.score = score;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
