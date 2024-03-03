package com.example.loginscreen.ui.tests;

public class TestsModelClass {
    private Integer testCode;
    private String score, time;

    public TestsModelClass(String score, String time) {
        this.testCode = testCode;
        this.score = score;
        this.time = time;
    }

    public TestsModelClass(Integer testCode, String score, String time) {
        this.testCode = testCode;
        this.score = score;
        this.time = time;
    }

    public Integer getTestCode() {
        return testCode;
    }

    public void setTestCode(Integer testCode) {
        this.testCode = testCode;
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
