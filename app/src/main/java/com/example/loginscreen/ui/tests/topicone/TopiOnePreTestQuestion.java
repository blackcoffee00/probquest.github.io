package com.example.loginscreen.ui.tests.topicone;

public class TopiOnePreTestQuestion {
    public String questions[] = {
            "In rolling a die, what is the probability of getting a 4?",
            "A coin is tossed. What is the probability of getting a head?",
            "If a card is drawn from a well – shuffled deck of cards, what is the probability that it is a king of diamonds?",
            "A card is selected at random from an ordinary deck of playing cards. What is the probability that the card is a red queen?",
            "A box contains five red, four white, and six green balls. If a ball is selected at random, what is the probability that it is a green?",
            "A spinner, numbered from 1 – 15, is spun once. What is the probability of spinning an odd number?",
            "A spinner, numbered from 1 – 15, is spun once. What is the probability of the spinner landing on a number less than 4?",
            "If a letter is to be selected from the word ENRICHMENT, find the probability that the letter is a vowel?",
            "In a box, there are 30 balls numbered from 1 to 30. If a ball is drawn from the box, what is the probability of getting a number divisible by 3?",
            "A roulette wheel has 10 red numbers, 8 black numbers, and 12 green numbers. What is the probability that the ball will land on a red number on one spin?"
    };
    public String choices[][] = {
            {"4/6","1/4","1/6","1/2"},
            {"1/2","1","1/3","1/6"},
            {"1/4","4/13","1/13","1/52"},
            {"1/13","1/26","13/4","1/6"},
            {"2/7","1/15","2/5","1/6"},
            {"2/5","8/15","1/15","3/5"},
            {"4/15","1/5","1/4","3/14"},
            {"3/10","2/5","1/2","1/5"},
            {"3/14","2/15","3/10","1/3"},
            {"3/14","2/15","3/10","1/3"}
    };
    public String correctAnswer[] = {
            "1/6", "1/2", "1/52", "1/26", "2/5", "8/15", "1/5", "3/10", "1/3", "1/3"
    };
    public String solutions[] = {
            "Event = getting a 4 in a die\nNote: There is only one 4 in a die, thus\nn(E) = 1\nSample space = {1, 2, 3, 4, 5, 6}\nn(S) = 6",
            "Event = getting a head in a coin\nn(E) = 1\nSample space = {Head, Tail}\nn(S) = 2",
            "Event = draw a king of diamond\nNote: There is only one king of diamonds, thus,\nn(E) = 1\nSample space =\n{Diamonds: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King},\n{Hearts: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King},\n{Clubs: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King},\n{Spade: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King}\nn(S) = 52",
            "Event = drawing a red queen\nThere are two red queen in a deck of cards, that is, a queen of diamonds and a queen of hearts. Thus,\nn(E) = 2\nSample space =\n{Diamonds: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King},\n{Hearts: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King},\n{Clubs: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King},\n{Spade: Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King}\nn(S) = 52",
            "Event = drawing a green ball\nThere are six green balls. Thus,\nn(E) = 6\nSample space = {five red balls, four white balls, and six green balls}\nn(S) = 15",
            "Event = spinning odd number\nThere are eight odd numbers in a spinner such as 1, 3, 5, 7, 9, 11, 13, and 15. Thus,\nn(E) = 8\nSample space = {five red balls, four white balls, and six green balls}\nn(S) = 15",
            "Event = landing on a number less than 4\nThere are three numbers less than four, thus\nn(E) = 3\nSample space = {five red balls, four white balls, six green balls}\nn(S) = 15",
            "Event = selecting a vowel from the word ENRICHMENT\nThere are three vowels, thus\nn(E) = 3\nSample space = {E, N, R, I, C, H, M, E, N, T}\nn(S) = 10",
            "Event = drawing a ball numbered divisible by 3\nThere are ten balls, thus\nn(E) = 10\nSample space = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}\nn(S) = 30",
            "Event = the ball landing on a red number\nThere are ten red numbers, thus\nn(E) = 10\nSample space = {10(red), 8(black), 12(green)}\nn(S) = 30"
    };
    public String solAnswers[] = {
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{1}{6}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{1}{2}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{1}{52}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{1}{26}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{6}{15} = \\frac{2}{5}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{8}{15}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{3}{15} = \\frac{1}{5}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{3}{10}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{10}{30} = \\frac{1}{3}",
            "\\text{Probability of Event } = \\frac{\\text{number of event}}{\\text{number of sample spcae}}\\\\P(E) = \\frac{n(\\text{event})}{n(\\text{sample space})} = \\frac{10}{30} = \\frac{1}{3}"
    };
    public String getQuestion(int a) {
        String question = questions[a];
        return question;
    }
    public String getChoice1(int a) {
        String choice = choices[a][0];
        return choice;
    }
    public String getChoice2(int a) {
        String choice = choices[a][1];
        return choice;
    }
    public String getChoice3(int a) {
        String choice = choices[a][2];
        return choice;
    }
    public String getChoice4(int a) {
        String choice = choices[a][3];
        return choice;
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
