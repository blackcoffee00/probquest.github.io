package com.example.loginscreen.ui.tests;

public class T2TestResultModelClass {
    private String t2TestQuestion;
    private int t2Mark, t2Btnbg1, t2Btnbg2;

    public T2TestResultModelClass(String t2TestQuestion, int t2Mark, int t2Btnbg1, int t2Btnbg2) {
        this.t2TestQuestion = t2TestQuestion;
        this.t2Mark = t2Mark;
        this.t2Btnbg1 = t2Btnbg1;
        this.t2Btnbg2 = t2Btnbg2;
    }

    public T2TestResultModelClass() {
        this("", 0, 0, 0);
    }

    public String getT2TestQuestion() {
        return t2TestQuestion;
    }

    public void setT2TestQuestion(String t2TestQuestion) {
        this.t2TestQuestion = t2TestQuestion;
    }

    public void setT2Mark(int t2Mark) {
        this.t2Mark = t2Mark;
    }

    public int getT2Mark() {
        return t2Mark;
    }

    public void setT2Btnbg1(int t2Btnbg1) {
        this.t2Btnbg1 = t2Btnbg1;
    }

    public int getT2Btnbg1() {
        return t2Btnbg1;
    }

    public void setT2Btnbg2(int t2Btnbg2) {
        this.t2Btnbg2 = t2Btnbg2;
    }

    public int getT2Btnbg2() {
        return t2Btnbg2;
    }
}
