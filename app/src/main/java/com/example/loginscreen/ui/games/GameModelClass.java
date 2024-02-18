package com.example.loginscreen.ui.games;

public class GameModelClass {
    private Integer id, gameScore;

    public GameModelClass(Integer id, Integer gameScore) {
        this.id = id;
        this.gameScore = gameScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGameScore() {
        return gameScore;
    }

    public void setGameScore(Integer gameScore) {
        this.gameScore = gameScore;
    }
}
