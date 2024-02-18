package com.example.loginscreen.ui.games.puzzles;

public class MediumProblems {
    public String problems[] = {
            "You're at a clothing store that dyes your clothes while you wait. You get to pick from 4 pieces of clothing (shirt, pants, socks, or hat) and 3 colors (purple, blue, or orange). If you randomly pick the piece of clothing and the color, what is the probability that you'll end up with an orange hat?",
            "James lives in San Francisco and works in Mountain View. In the morning, he has 3 transportation options (bus, cab, or train) to work, and in the evening he has the same 3 choices for his trip home. If James randomly chooses his ride in the morning and in the evening, what is the probability that he'll take the same mode of transportation twice?",
            "If you draw one card from a standard deck, what is the probability of drawing a 5 or a diamond?",
            "If you roll one die, what is the probability of getting an even number or a multiple of 3?",
            "A bag contains 9 green marbles, 5 yellow marbles, and 6 red marbles. You choose two marbles. What is the probability of selecting a green followed by a red marble?",
            "Of 240 students, 176 are on the honor roll, 48 are members of the varsity team, and 36 are in the honor roll and are also members of the varsity team. What is the probability that a randomly selected student is on the honor roll or is a member of the varsity team?",
            "In rolling a fair die, what is the probability of getting an even or divisible by 3?",
            "One card is drawn at random from a 52-deck of cards. Find the probability that it is a diamond or a King.",
            "There are 14 tiles with one letter from the word MATHEMATICIANS on each. You are now going to choose a letter, put it back, and then choose another letter. Find the probability of choosing the letter A, and then A again.",
            "A bag contains 8 blue marbles, 7 green marbles, and 5 yellow marbles. Suppose you drew one marble from the bag above, replaced it and then drew a second marble. What is the probability of drawing a green, and then a blue marble."
    };

    public String imageName[] = {
            "clothing", "transportation", "deck_of_cards", "roll_a_die", "marbles_1", "students", "roll_a_die", "deck_of_cards", "mathematics", "marbles_2"
    };

    public String correctAnswer[] = {
            "1/12", "1/3", "4/13", "5/6", "27/190", "47/60", "2/3", "4/13", "1/49", "7/50"
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
