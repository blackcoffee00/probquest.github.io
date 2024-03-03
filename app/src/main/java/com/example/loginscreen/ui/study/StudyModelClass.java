package com.example.loginscreen.ui.study;

public class StudyModelClass {
    private Integer topicsId, timeSec;

    public StudyModelClass(Integer topicsId, Integer timeSec) {
        this.topicsId = topicsId;
        this.timeSec = timeSec;
    }

    public Integer getTopicsId() {
        return topicsId;
    }

    public void setTopicsId(Integer topicsId) {
        this.topicsId = topicsId;
    }

    public Integer getTimeSec() {
        return timeSec;
    }

    public void setTimeSec(Integer timeSec) {
        this.timeSec = timeSec;
    }
}
