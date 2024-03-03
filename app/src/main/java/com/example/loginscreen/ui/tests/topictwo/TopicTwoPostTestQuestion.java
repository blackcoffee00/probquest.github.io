package com.example.loginscreen.ui.tests.topictwo;

public class TopicTwoPostTestQuestion {
    public String questions[] = {
            "Drawing a king of diamonds from a deck of cards.",
            "Drawing a face card from an ordinary deck of cards.",
            "Rolling an odd number on a die, then tossing a tail on a coin.",
            "A coin is thrown 2 times.",
            "Getting a 7 on a spin.",
            "Picking two balls in succession.",
            "Getting at least two heads when three coins are tossed.",
            "Drawing a heart, replacing the card, then drawing a jack of clubs.",
            "Rolling a 3 on a die and getting heads on a coin toss",
            "Getting a number divisible by 2 when a die is tossed."
    };
    public String correctAnswer[] = {
            "S", "S", "C", "C", "S", "S", "C", "C", "C", "S"
    };
    public String solutions[] = {
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public String solAnswers[] = {

            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };
    public String getQuestion(int a) {
        String question = questions[a];
        return question;
    }
    public String getCorrectAnswer(int a) {
        String answer = correctAnswer[a];
        return answer;
    }
    public String getSolution(int a) {
        String solution = solutions[a];
        return solution;
    }
    public String getSolAnswer(int a) {
        String solAnswer = solAnswers[a];
        return solAnswer;
    }
}
