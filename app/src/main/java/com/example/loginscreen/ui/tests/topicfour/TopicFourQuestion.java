package com.example.loginscreen.ui.tests.topicfour;

public class TopicFourQuestion {
    public String questions1[] = {
            "A card is chosen from a well – shuffled deck of 52 cards. What is the probability that the card will be a spade or a jack?",
            "A card is selected at random an ordinary deck of playing cards. What is the probability that the card is either a face card or a jack of hearts?",
            "A die is rolled. What is the probability of rolling a 4 or an even number?",
            "A single number cube is rolled. What is the probability of rolling an even number or a prime number?",
            "A spinner number 1-20 is spun. Each number is likely to be spun. What is the probability of spinning a 10 or a number at least 16?",
            "A bag contains 15 chips numbered 1 to 15. If a chip is drawn randomly from the bag, what is the probability that the number is 12 or divisible by 3?",
            "Of 240 students, 176 are on the honor roll, 48 are members of the varsity team, and 36 are in the honor roll and are also members of the varsity team. What is the probability that a randomly selected student is on the honor roll or is a member of the varsity team?",
            "Find the probability of turning up an even number or a number greater than 2 when rolling a die?",
            "A bag contains cards numbered from 1 to 14. One card drawn at random. What is the probability of selecting a prime number or a multiple of 3?",
            "One card is randomly selected from a deck of cards. What is the probability that it will be a diamond or a red queen?"
    };
    public String choices1[][] = {
            {"1/26","4/13","1/52","13/52"},
            {"13/52","4/13","1/52","3/13"},
            {"1/2","2/3","1/6","1/36"},
            {"1/6","1/3","5/6","2/5"},
            {"1/2","3/10","1/4","1/20"},
            {"1/3","4/5","1/5","7/15"},
            {"1/120","29/120","81/120","109/120"},
            {"1/3","2/3","5/6","3/5"},
            {"5/7","2/7","9/14","11/14"},
            {"7/26","5/26","4/13","5/13"}
    };
    public String correctAnswer1[] = {
            "4/13", "3/13", "1/2", "5/6", "3/10", "1/3", "109/120", "2/3", "9/14", "7/26"
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
            "A card is chosen from a well – shuffled deck of 52 cards. What is the probability that the card will be a diamond or a King?",
            "A card is drawn at random from a well-shuffled deck of 52 cards. Find the probability that the card drawn is a black or a face card?",
            "A card is selected at random an ordinary deck of playing cards. What is the probability that the card is either a red card or an ace card?",
            "A single number cube is rolled. What is the probability of rolling an odd number or a prime number?",
            "A spinner number 1-20 is spun. Each number is likely to be spun. What is the probability of spinning a 10 or a number at least 6?",
            "A bag contains 25 chips numbered 1 to 25. If a chip is drawn randomly from the bag, what is the probability that the number is divisible by 3 or divisible by 5?",
            "Of 50 students, 10 are on the honor roll, 15 are members of the varsity team, and 5 are in the honor roll and are also members of the varsity team. What is the probability that a randomly selected student is on the honor roll or is a member of the varsity team?",
            "If a die is rolled, what is the probability of obtaining an even number or a number greater than four?",
            "A bag contains cards numbered from 1 to 14. One card drawn at random. What is the probability of selecting a prime number or a divisible by 3?",
            "In a math class of 40 students, 25 are boys and 15 are girls. On a unit test, 10 boys and 5 girls made a perfect score. If a student is chosen at random from the class, what is the probability of choosing a girl or a student with a perfect score?"
    };
    public String choices2[][] = {
            {"7/13","3/26","4/13","5/13"},
            {"9/26","8/13","7/13","5/13"},
            {"3/5","1/4","9/52","7/13"},
            {"5/6","1/2","2/3","1/3"},
            {"3/10","13/20","3/4","11/20"},
            {"1/5","3/25","8/9","2/3"},
            {"7/50","4/15","3/10","2/5"},
            {"7/26","5/26","4/13","2/3"},
            {"7/26","5/26","9/14","5/13"},
            {"3/40","5/8","7/10","11/12"}
    };
    public String correctAnswer2[] = {
            "4/13", "8/13", "7/13", "1/2", "3/4", "1/5", "2/5", "2/3", "9/14", "5/8"
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
