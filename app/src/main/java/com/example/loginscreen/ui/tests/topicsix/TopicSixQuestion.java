package com.example.loginscreen.ui.tests.topicsix;

public class TopicSixQuestion {
    public String questions1[] = {
            "A box contains 5 yellow marbles and 7 red marbles. What is the probability of drawing 2 yellow marbles and 1 red marble in succession without replacement?",
            "Two cards are selected at random from a standard deck. What is the probability that the first card is an 8 and the second card is a queen if the first card is not replaced before the second is selected?",
            "There are 10 marbles in the box, 3 are blue and 7 are green. If we randomly selected two marbles from this box, what is the probability of drawing a green marble and then a blue marble without replacement?",
            "A class is composed of 12 boys and and 13 girls. If two presenters to a poem recital are to be chosen in succession, what is the probability that the first is a boy and the second is a girl?",
            "There are five glasses of orange juice and four glasses of red wine on the counter. John Paul drinks two of them at random. What is the probability that he drank two glasses of orange juice?",
            "From a standard deck of cards, what is the probability of choosing a diamond, then a heart, then a black card without replacement?",
            "A box contains 5 red marbles and 7 green marbles. Find the probability of drawing 2 red marbles without replacement?",
            "Mrs. Cruz has to select two students from 35 girls and 15 boys to be part of the club. What is the probability that both students are girls?",
            "A box contains 10 gray caps and 18 black caps. A cap is taken, not replaced, and then a second cap is taken. What is the probability of taking out a gray cap followed by a black cap?",
            "There are 5 red socks and 11 pink socks in a drawer. If one sock is taken out without looking and then a second is taken out, what is the probability that they both will be pink?"
    };
    public String choices1[][] = {
            {"5/12","7/33","6/25","1/3"},
            {"8/241","23/41","9/43","4/663"},
            {"7/30","9/53","12/25","2/7"},
            {"5/121","11/73","13/25","12/13"},
            {"5/18","6/155","18/331","7/142"},
            {"812/1833","134/237","2197/66300","19/1561"},
            {"8/163","5/33","124/673","11/261"},
            {"138/189","29/155","25/621","17/195"},
            {"14/245","13/65","5/21","8/21"},
            {"11/24","35/61","8/423","21/362"}
    };
    public String correctAnswer1[] = {
            "7/33",
            "4/663",
            "7/30",
            "13/25",
            "5/18",
            "2197/66300",
            "5/33",
            "17/195",
            "5/21",
            "11/24"
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
            "A box contains 6 white marbles and 9 black marbles. What is the probability of drawing 2 white marbles and 1 black marble in succession without replacement?",
            "Two cards are selected at random from a standard deck. What is the probability that the first card is a jack and the second card is an ace if the first card is not replaced before the second is selected?",
            "There are 15 marbles in the box, 5 are white and 10 are violet. If we randomly selected two marbles from this box, what is the probability of drawing a white marble and then a violet marble without replacement?",
            "A class is composed of 16 boys and 24 girls. If two presenters to a poem recital are to be chosen in succession, what is the probability that the first is a boy and the second is a girl?",
            "There are four glasses of orange juice and six glasses of cola on the counter. Mr. John Paul drinks two of them at random. What is the probability that he drank two glasses of cola?",
            "From a standard deck of cards, what is the probability of choosing a spade, then a heart, then a red card without replacement?",
            "A box contains 8 orange marbles and 12 white marbles. Find the probability of drawing 2 white marbles without replacement?",
            "Mrs. Cruz has to select two students from 30 girls and 12 boys to be part of the club. What is the probability that both students are boys?",
            "A box contains 9 green caps and 15 brown. A cap is taken, not replaced, and then a second cap is taken. What is the probability of taking out a green cap followed by a brown cap?",
            "There are 6 white socks and 18 blue socks in a drawer. If one sock is taken out without looking and then a second is taken out, what is the probability that they both will be blue?"
    };
    public String choices2[][] = {
            {"37/234","7/41","16/235","9/91"},
            {"34/79","4/663","18/31","16/231"},
            {"4/79","6/49","10/21","12/361"},
            {"324/379","48/95","16/31","27/48"},
            {"34/79","5/8","15/46","1/3"},
            {"169/5304","7/34","130/313","181/921"},
            {"133/168","27/39","33/95","14/63"},
            {"42/97","127/333","28/101","22/287"},
            {"45/184","181/433","8/105","5/143"},
            {"2/45","62/83","14/139","21/92"}
    };
    public String correctAnswer2[] = {
            "9/91",
            "4/663",
            "10/21",
            "48/95",
            "1/3",
            "169/5304",
            "33/95",
            "22/287",
            "45/184",
            "21/92"
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
