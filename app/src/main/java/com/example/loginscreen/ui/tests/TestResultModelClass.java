package com.example.loginscreen.ui.tests;

public class TestResultModelClass {
    private String testQuestion, btn1, btn2, btn3, btn4, testSolution, testAnswer;
    private int resultId, solText, ansText, solVis, ansVis, mark, btnbg1, btnbg2, btnbg3, btnbg4;

    public TestResultModelClass(int resultId, String testQuestion, String btn1, String btn2, String btn3, String btn4, String testSolution, String testAnswer,
                                int solText, int ansText, int solVis, int ansVis, int mark, int btnbg1, int btnbg2, int btnbg3, int btnbg4) {
        this.resultId = resultId;
        this.testQuestion = testQuestion;
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.btn4 = btn4;
        this.testSolution = testSolution;
        this.testAnswer = testAnswer;
        this.solText = solText;
        this.ansText = ansText;
        this.solVis = solVis;
        this.ansVis = ansVis;
        this.mark = mark;
        this.btnbg1 = btnbg1;
        this.btnbg2 = btnbg2;
        this.btnbg3 =  btnbg3;
        this.btnbg4 = btnbg4;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(String testQuestion) {
        this.testQuestion = testQuestion;
    }

    public String getBtn1() {
        return btn1;
    }

    public void setBtn1(String btn1) {
        this.btn1 = btn1;
    }

    public String getBtn2() {
        return btn2;
    }

    public void setBtn2(String btn2) {
        this.btn2 = btn2;
    }

    public String getBtn3() {
        return btn3;
    }

    public void setBtn3(String btn3) {
        this.btn3 = btn3;
    }

    public String getBtn4() {
        return btn4;
    }

    public void setBtn4(String btn4) {
        this.btn4 = btn4;
    }

    public String getTestSolution() {
        return testSolution;
    }

    public void setTestSolution(String testSolution) {
        this.testSolution = testSolution;
    }

    public String getTestAnswer() {
        return testAnswer;
    }

    public void setTestAnswer(String testAnswer) {
        this.testAnswer = testAnswer;
    }

    public int getSolText() {
        return solText;
    }

    public void setSolText(int solText) {
        this.solText = solText;
    }

    public int getAnsText() {
        return ansText;
    }

    public void setAnsText(int ansText) {
        this.ansText = ansText;
    }

    public int getSolVis() {
        return solVis;
    }

    public void setSolVis(int solVis) {
        this.solVis = solVis;
    }

    public int getAnsVis() {
        return ansVis;
    }

    public void setAnsVis(int ansVis) {
        this.ansVis = ansVis;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getBtnbg1() {
        return btnbg1;
    }

    public void setBtnbg1(int btnbg1) {
        this.btnbg1 = btnbg1;
    }

    public int getBtnbg2() {
        return btnbg2;
    }

    public void setBtnbg2(int btnbg2) {
        this.btnbg2 = btnbg2;
    }

    public int getBtnbg3() {
        return btnbg3;
    }

    public void setBtnbg3(int btnbg3) {
        this.btnbg3 = btnbg3;
    }

    public int getBtnbg4() {
        return btnbg4;
    }

    public void setBtnbg4(int btnbg4) {
        this.btnbg4 = btnbg4;
    }
}
