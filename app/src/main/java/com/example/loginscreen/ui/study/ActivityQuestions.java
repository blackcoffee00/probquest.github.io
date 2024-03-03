package com.example.loginscreen.ui.study;

public class ActivityQuestions {
    public String act1Questions[] = {
            "The probability of getting a 4 in rolling a standard die once.",
            "The probability of choosing a male student for SSG President.",
            "The probability of getting an even number or a multiple of 3 on a die in rolling a standard dice once.",
            "The probability of drawing a spade from a standard deck of cards. 5. The probability of drawing a heart and a red card from a standard deck of cards.",
            "The probability of drawing a heart and a red card from a standard deck of cards.",
            "A box contains 5 yellow marbles and 7 red marbles. What is the probability of drawing 2 yellow marbles and 1 red marble in succession without replacement?",
            "From a standard deck of cards, what is the probability of choosing a diamond, then a heart, then a black card without replacement?",
            "If you roll a six - sided die and flip a coin, what is the probability of rolling a five and getting head?",
            "Two cards are selected at random from a standard deck. What is the probability that the first card is an 8 and the second card is a queen if the first card is not replaced before the second is selected?",
            "In a box, there are 30 balls numbered from 1 to 30. If a ball is drawn from the box, what is the probability of getting a number divisible by 5?"
    };
    public String correctAnsCaps[] = {
            "S", "S", "C", "S", "C", "C", "C", "C", "C", "S"
    };
    public String correctAnsLow[] = {
            "s", "s", "c", "s", "c", "c", "c", "c", "c", "s"
    };
    public String getAct1Question(int a) {
        String question = act1Questions[a];
        return question;
    }
    public String getCorrectAnsCaps(int a) {
        String answer1 = correctAnsCaps[a];
        return answer1;
    }
    public String getCorrectAnsLow(int a) {
        String answer2 = correctAnsLow[a];
        return answer2;
    }

    public String[] act21Questions= {
            "A card is drawn from a standard deck of cards. What is the probability of drawing an ace or a face card?",
            "A die is rolled. What is the probability of rolling a 4 or a number greater than 3?",
            "Each of the numbers from 1 to 30 is written on a card and placed in a bag, and one is drawn at random. What is the probability that the number is a multiple of 3 or a multiple of 4?",
            "A card is drawn from a standard deck of cards. What is the probability of drawing a 6 or a king?",
            "Alma has a stack of 10 basketball cards, 7 volleyball cards, and 8 badminton cards. If she selects a card at random from the stack, what is the probability that it is a basketball card or a badminton card?",
            "In the math club, 5 of the 8 girls are seniors and 3 of the 10 boys are seniors. What is the probability of randomly selecting a boy or a senior to represent the school for a Regional math contest?",
            "A die is rolled. What is the probability of rolling a 2 or a 3?",
            "Eva has a stack of playing cards consisting of 10 diamonds, 9 hearts, 8 spades and 7 clubs. If she selects a card at random from this stack, what is the probability that it is a diamond or club?",
            "One tile with each letter of the alphabet is placed in the bag and one is drawn at random. What is the probability of selecting a consonant from the word event.",
            "A card is randomly selected from a deck of cards. What is the probability that the card will either be a heart or an ace?"
    };

    public String[] act21Answers = {
            "Mutually Exclusive", "Not Mutually Exclusive", "Not Mutually Exclusive", "Mutually Exclusive", "Mutually Exclusive", "Not Mutually Exclusive", "Mutually Exclusive", "Mutually Exclusive", "Mutually Exclusive", "Not Mutually Exclusive"
    };

    public String getAct21Question(int a) {
        String question = act21Questions[a];
        return question;
    }
    public String getAct21Answer(int a) {
        String answer = act21Answers[a];
        return answer;
    }

    public String[] act22Questions = {
            "If one card is drawn from an ordinary deck of cards, find the probability of getting a king or a queen or a jack.",
            "If one card is drawn from an ordinary deck of cards, find the probability of getting a club or a heart or a spade.",
            "If one card is drawn from an ordinary deck of cards, find the probability of getting a red card or a black queen.",
            "If one card is drawn from an ordinary deck of cards, find the probability of getting a face card or an ace.",
            "If one card is drawn from an ordinary deck of cards, find the probability of getting a number card or a queen of hearts.",
            "Two dice are rolled. Find the probability of getting a sum of 5 or 7 or 9.",
            "Two dice are rolled. Find the probability of getting doubles or sum of 11.",
            "Two dice are rolled. Find the probability of getting a sum less than 5 or greater than 9."
    };

    public String[] act22Answers1 = {
            "3/13", "3/4", "7/13", "4/13", "37/52", "7/18", "2/9", "1/3"
    };

    public String[] act22Answers2 = {
            "12/52", "39/52", "28/52", "16/52", "37/52", "14/36", "8/36", "12/36"
    };

    public String getAct22Question(int a) {
        String question = act22Questions[a];
        return question;
    }
    public String getAct22SimpAns(int a) {
        String answer = act22Answers1[a];
        return answer;
    }
    public String getAct22Ans(int a) {
        String answer = act22Answers2[a];
        return answer;
    }
}
