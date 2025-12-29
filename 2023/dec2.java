import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec2 {
    public static void main(String[] args) {
        System.out.println("helloo");
        File in = new File("dec2-test.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables

            int sum = 0;
            int gameID = 0;
            String color = "";
            boolean includeLine = true;
            int num = 0;

            scan.next();
            while (scan.hasNext()) {
                // include the line until it doesn't fit
                includeLine = true;
                String nextWord = scan.next();
                System.out.println("next: " + nextWord);
                while (!nextWord.equals("Game")) {
                    gameID = Integer.parseInt(nextWord.substring(0, 1));
                    num = scan.nextInt();
                    color = scan.next();
                    System.out.println("gameID: " + gameID + " number: " + num + " color: " + color);
                    includeLine = doesFit(color, num);
                    nextWord = color;
                }
            }
            // System.out.println("the sum is: " + sum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean doesFit(String color, int num) {
        if (color.equals("red")) {
            return num < 13;
        } else if (color.equals("green")) {
            return num < 14;
        } else if (color.equals("blue")) {
            return num < 15;
        }
        System.out.println("didn't find a matching color ERROR ");
        return false;
    }

}
