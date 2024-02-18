package com.example.loginscreen.ui.games.puzzles;

public class EasyProblems {
    public String problems[] = {
            "A bag contains 2 red, 3 green, and 2 blue gumballs. What is the probability of selecting a green one?",
            "If you flip a coin, what is the probability it will land on heads?",
            "There are red, yellow, and green lollipops in a bag. What is the probability of selecting a blue one?",
            "If you rolled a 6-sided die, what is the probability of rolling an even number?",
            "What is the probability of selecting the diamond suit from a deck of playing cards?",
            "What is the probability of paper losing to scissors?",
            "If you roll two fair six-sided dice, what is the probability that at least one die shows a 3?",
            "If you flip three fair coins, what is the probability that you'll get two tails and one head in any order?",
            "How many face cards are in a standard deck of cards?",
            "If you roll one die, what is the probability of getting an odd number or a 4?"
    };

    public String imageName[] = {
            "gumballs", "flip_a_coin", "lollipops", "roll_a_die", "deck_of_cards", "paper_scissors", "roll_two_dice", "flip_three_coins", "face_cards", "roll_a_die"
    };

    public String correctAnswer[] = {
            "3/7", "1/2", "0", "1/2", "1/4", "1", "11/36", "3/8", "12", "2/3"
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

    public String getProblem(int a) {
        String problem = problems[a];
        return problem;
    }

    public String getImageName(int a) {
        String image = imageName[a];
        return image;
    }

    public String getSolution(int a) {
        String solution = solutions[a];
        return solution;
    }

    public String getCorrectAnswer(int a) {
        String answer = correctAnswer[a];
        return answer;
    }
}
