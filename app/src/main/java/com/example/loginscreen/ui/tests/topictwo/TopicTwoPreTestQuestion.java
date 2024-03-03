package com.example.loginscreen.ui.tests.topictwo;

public class TopicTwoPreTestQuestion {
    public String questions[] = {
            "A coin is thrown 3 times.",
            "Drawing a spade, replacing the card, then drawing a club.",
            "Drawing a red queen from an ordinary deck of cards.",
            "Picking two balls in succession without replacement.",
            "Getting a 3 when a die is tossed.",
            "Rolling an even number on a die, then tossing a head on a coin.",
            "Drawing a jack from a deck of cards.",
            "Getting an 8 on a spin.",
            "Getting head on a coin toss.",
            "Rolling a 3 on a die and getting heads on a coin toss."
    };
    public String correctAnswer[] = {
            "S", "C", "S", "C", "C", "C", "S", "S", "S", "C"
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
}
