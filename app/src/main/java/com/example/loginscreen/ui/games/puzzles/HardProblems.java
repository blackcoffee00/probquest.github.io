package com.example.loginscreen.ui.games.puzzles;

public class HardProblems {
    public String problems[] = {
            "Ricardo has the spinner pictured here and a bag of marbles filled with 2 red marbles, 3 green marbles, and 3 blue marbles. What is the probability that Ricardo spins red on the spinner and picks a red marble out of the bag?",
            "There are 6 red marbles, 5 green marbles, and 4 yellow marbles in a bag. If Joe picks 2 marbles one after the other without replacement, then what is the probability that both are red in color?",
            "There are 2 violet balls and 4 pink balls in a bag. If two balls are drawn one after the other, then what is the probability of getting violet first and pink next, if the first ball drawn is replaced?",
            "There are 5 red roses, 3 yellow roses, and 8 white roses in a tray. If Stephanie picked 2 roses one after the other without replacing, then what is the probability of picking a white rose first and a red rose next?",
            "A basket contains 5 purple pencils and 9 brown pencils. If two pencils are picked at random one after the other without replacement, then what is the probability that both the pencils are purple?",
            "What is the probability of rolling an even number on the first roll of a number cube and rolling an odd number on the second roll?",
            "Two cards are drawn from a deck of 52 cards. What is the probability that the first card drawn is a king and the second card drawn is a heart, given that the first card is replaced?",
            "A box contains all the letters of the word U N D E R S T A N D. What is the probability of selecting an 'N' first and a 'D' next, if the first letter is replaced?",
            "There are 10 pens and 15 pencils in a box. If a student selects two of them at random, then what is the probability of selecting a pen and then a pencil?",
            "Using a standard deck of cards (which has 26 red cards and 26 black cards, with 13 cards of every suit), what is the probability of selecting a red card, and then after replacing the card, selecting a heart card?"
    };

    public String imageName[] = {
            "", "", "", "", "", "", "deck_of_cards", "", "", ""
    };

    public String correctAnswer[] = {
            "1/16", "1/7", "2/9", "1/6", "10/91", "1/4", "1/52", "1/25", "1/4", "1/8"
    };

    public String getProblem(int a) {
        String problem = problems[a];
        return problem;
    }

    public String getImageName(int a) {
        String image = imageName[a];
        return image;
    }

    public String getCorrectAnswer(int a) {
        String answer = correctAnswer[a];
        return answer;
    }
}
