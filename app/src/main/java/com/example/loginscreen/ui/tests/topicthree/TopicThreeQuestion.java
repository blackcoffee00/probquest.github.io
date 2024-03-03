package com.example.loginscreen.ui.tests.topicthree;

public class TopicThreeQuestion {
    public String questions1[] = {
            "A card is chosen from a well-shuffled deck of 52 cards. What is the probability that the card will be a king or a queen?",
            "If one card is drawn from an ordinary deck of cards, what is the probability of getting a diamond or a heart?",
            "A card is selected at random an ordinary deck of playing cards. What is the probability that the card is either a face card or an ace of hearts?",
            "A spinner number 1-10 is spun. Each number is likely to be spun. What is the probability of spinning a 3 or a number at least 6?",
            "Ramcea puts 50 marbles in a box in which 16 are red, 14 are blue, and 20 are yellow. If Ramcea picks one marble at random, what is the probability that he selects a red marble or a blue marble?",
            "A bag contains 20 chips numbered 1 to 20. If a chip is drawn randomly from the bag, what is the probability that it is a 7 or a number at most 5?",
            "What is the probability of a dice showing a 2 or 5?",
            "A single number cube is rolled. What is the probability of rolling a 2 or greater than 5?",
            "A die is rolled. What is the probability of rolling a 3 or a number divisible by 2?",
            "Each of the numbers from 1 to 30 is written on a card and placed in a bag. If card is drawn at random, what is the probability that the number is a multiple of 10 or a prime number?"
    };
    public String choices1[][] = {
            {"1/26","2/13","1/52","1/8"},
            {"2/15","1/13","1/2","1/26"},
            {"1/5","1/4","1/26","1/52"},
            {"1/2","3/10","1/10","3/5"},
            {"2/5","3/5","17/25","16/25"},
            {"1/4","7/20","3/10","1/10"},
            {"1/3","1/6","5/6","1/4"},
            {"1/2","1/5","1/3","1/3"},
            {"1/3","2/3","1/6","1/2"},
            {"1/30","1/3","6/15","3/10"}
    };
    public String correctAnswer1[] = {
            "1/26", "1/2", "1/4", "3/5", "3/5", "3/10", "1/3", "1/3", "2/3", "6/15"
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
    public String questions2[] = {
            "A card is chosen from a well-shuffled deck of 52 cards. What is the probability that the card will be a jack or a queen?",
            "If one card is drawn from an ordinary deck of cards, what is the probability of getting a club or a spade?",
            "A card is selected at random an ordinary deck of playing cards. What is the probability that the card is either a number card or a jack of diamonds?",
            "A spinner number 1-10 is spun. Each number is likely to be spun. What is the probability of spinning a 2 or a number at least 4?",
            "Ramcea puts 40 marbles in a box in which 8 are red, 10 are blue, and 22 are yellow. If Ramcea picks one marble at random, what is the probability that she selects a blue marble or a yellow marble?",
            "A bag contains 20 chips numbered 1 to 20. If a chip is drawn randomly from the bag, what is the probability that it is a 10 or a number at most 4?",
            "What is the probability of a dice showing a 4 or 6?",
            "A single number cube is rolled. What is the probability of rolling a 3 or greater than 4?",
            "A die is rolled. What is the probability of rolling a 4 or a number divisible by 3?",
            "Each of the numbers from 5 to 20 is written on a card and placed in a bag. If card is drawn at random, what is the probability that the number is a multiple of 4 or a prime number?"
    };
    public String choices2[][] = {
            {"4/13","1/26","1/8","1/52"},
            {"2/15","1/13","1/26","1/2"},
            {"41/52","1/4","1/13","1/52"},
            {"1/2","3/10","4/5","2/5"},
            {"1/20","3/5","1/10","11/20"},
            {"1/4","7/20","3/10","1/10"},
            {"1/6","2/3","2/5","1/4"},
            {"1/2","3/4","2/3","1/3"},
            {"1/3","2/3","1/6","1/2"},
            {"1/20","11/16","1/10","4/11"}
    };
    public String correctAnswer2[] = {
            "1/26", "1/2", "41/52", "4/5", "11/20", "3/10", "2/3", "1/2", "1/2", "11/16"
    };
    public String getPreTestQuestion(int a) {
        String question = questions1[a];
        return question;
    }
    public String getPreTestChoice1(int a) {
        String choice = choices1[a][0];
        return choice;
    }
    public String getPreTestChoice2(int a) {
        String choice = choices1[a][1];
        return choice;
    }
    public String getPreTestChoice3(int a) {
        String choice = choices1[a][2];
        return choice;
    }
    public String getPreTestChoice4(int a) {
        String choice = choices1[a][3];
        return choice;
    }
    public String getPreTestCorrectAnswer(int a) {
        String answer = correctAnswer1[a];
        return answer;
    }
    public String getPreTestSolution(int a) {
        String solution = solutions[a];
        return solution;
    }
    public String getPreTestSolAnswer(int a) {
        String solAnswer = solAnswers[a];
        return solAnswer;
    }
    public String getPostTestQuestion(int a) {
        String question = questions2[a];
        return question;
    }
    public String getPostTestChoice1(int a) {
        String choice = choices2[a][0];
        return choice;
    }
    public String getPostTestChoice2(int a) {
        String choice = choices2[a][1];
        return choice;
    }
    public String getPostTestChoice3(int a) {
        String choice = choices2[a][2];
        return choice;
    }
    public String getPostTestChoice4(int a) {
        String choice = choices2[a][3];
        return choice;
    }
    public String getPostTestCorrectAnswer(int a) {
        String answer = correctAnswer2[a];
        return answer;
    }
}
