/*
 * Solution to Advent of Code 2022 in Java 
 * The challenge is about elves playing rock, paper scissors. 
 * Your total score is the sum of your scores for each round. The score for a single round is the score for the shape you selected 
 * (1 for Rock, 2 for Paper, and 3 for Scissors) plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).
 * Author: Anna Peter 
 */

 // Part 2: X - lose, Y - draw, Z - win 
 // first char = what opponent has; second char = desired outcome 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec2 {
    public static void main(String[] args) {

        // read input and declare scanner
        File in = new File("dec2-input");
        Scanner scan;

        try {
            scan = new Scanner(in);
            int score = 0;

            // generic code to read the input characters 
            while (scan.hasNext()) {
                String line = scan.nextLine();
                char opp = line.charAt(0);
                char me = line.charAt(2);
                
                //check the bonus of the different cards and change name for the compare task after
                if (me=='X') {
                    // lose +0
                    score+=0;
                    score += evaluate(opp, 'l');
                } else if (me=='Y') {
                    // draw +3
                    score+=3;
                    score += evaluate(opp,'d');
                } else {
                    // win +6
                    score +=6;
                    score += evaluate(opp, 'w');
                }
                // System.out.printf("Opponent has %s and I have %s, resulting in final score %d \n", opp, me, score);
                
            }
            System.out.println(score);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    // given the desired outcome and the opponent's choice, figures out what I should choose and returns the corresponding score
    public static int evaluate(char opp, char outcome) {
        if (opp=='A' && outcome == 'd' || opp=='B' && outcome=='l' || opp=='C' && outcome=='w') {
            // me is rock 
            return 1;
        } else if (opp=='B' && outcome == 'd' || opp=='C' && outcome=='l' || opp=='A' && outcome=='w') {
            // me is paper
            return 2; 
        }
        return 3;
    }

    // A for Rock, B for Paper, and C for Scissors
    // X for Rock, Y for Paper, and Z for Scissors
    // 1 for Rock, 2 for Paper, and 3 for Scissors
    
    // compares the two choices and adds the score for win/lose/draw
    public static int compare(char opp, char me) {
        int result = 0;

        if (opp==me) {
            result = 3;
        } else if ((opp=='A' && me=='B') || (opp=='B' && me=='C') || (opp=='C' && me=='A')) {
            result = 6;
        } else {
            result = 0; 
        }
        // System.out.printf(" Comparison gave me %d. ", result);
        return result; 
    }
}

/*
 * Part 1: 
 * import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec2 {
    public static void main(String[] args) {

        File in = new File("dec2-input");
        Scanner scan;


        try {
            scan = new Scanner(in);
            int score = 0;

            while (scan.hasNext()) {
                String line = scan.nextLine();
                char opp = line.charAt(0);
                char me = line.charAt(2);
                
                //check the bonus of the different cards and change name for the compare task after
                if (me=='X') {
                    score+=1;
                    me = 'A';
                } else if (me=='Y') {
                    score+=2;
                    me = 'B';
                } else {
                    score +=3;
                    me = 'C';
                }
                score += compare(opp,me);
                System.out.printf("Opponent has %s and I have %s, resulting in final score %d \n", opp, me, score);
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    public static int compare(char opp, char me) {
        int result = 0;

        if (opp==me) {
            
            result = 3;
        } else if ((opp=='A' && me=='B') || (opp=='B' && me=='C') || (opp=='C' && me=='A')) {
            result = 6;
        } else {
            result = 0; 
        }
        System.out.printf(" Comparison gave me %d. ", result);
        return result; 
    }
}

 * 
 */
