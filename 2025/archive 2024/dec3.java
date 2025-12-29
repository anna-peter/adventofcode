import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

/*
 * Step 1: extract all actual mul(x,y) operations - maybe do a regex? 
 * Step 2: multiply and sum up
 */

public class dec3 {
    public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec3.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            String regex = "mul\\((\\d+),(\\d+)\\)";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher;
            int sum = 0;
            int x, y;

            // go through every line
            while (scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);
                // match the line against our regex pattern
                matcher = pattern.matcher(line);
                while (matcher.find()) {
                    x = Integer.parseInt(matcher.group(1));
                    y = Integer.parseInt(matcher.group(2));
                    System.out.println("Extracted x (as int): " + x);
                    System.out.println("Extracted y (as int): " + y);
                    sum += x*y;
                }
            System.out.println("Sum is "+sum);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}