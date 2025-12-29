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
 
 public class dec3 {
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
                 
             }
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
         
     }
     public static int commonPrefix(String s1, String s2) {
            // find the common prefix of the two strings 
            // if there is no common prefix, return 0 
            // if there is a common prefix, return the length of the common prefix  
            int i = 0;
            while (i < s1.length() && i < s2.length()) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    break;
                }
                i++;
            } 
            return i;
     }


    }