import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec3p1 {
    public static void main(String[] args) {

        File in = new File("dec3-input.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            int max1 = 0;
            int max2 = 0;
            int c = 0;
            int c2 = 0;
            int joltage_sum = 0;
            int joltage = 0;

            // go through every line
            while (scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);
                max1 = line.charAt(0)-'0';
                max2 = line.charAt(1)-'0';
                // System.out.println("initialized max1 "+max1+ " and max2 "+max2);
                // go through every character

                for (int i = 2; i < line.length()-1; i++) {
                    c = line.charAt(i)-'0';
                    c2 = line.charAt(i+1)-'0';
                    if (max2>max1) {
                        max1 = max2;
                        max2 = c;
                    }

                    if (c>max1) {
                        max1 = c;
                        max2 = c2;
                        System.out.println("assigned a higher max "+max1+ " and max2 "+max2);
                    } else if (c>max2) {
                        max2 = c;
                        System.out.println("assigned a higher max2 "+ max2);
                    }
                }
                c2 = line.charAt(line.length()-1)-'0';
                if (c2> max2) {
                    max2 = c2;
                    System.out.println("assigned a higher max2 at end "+ max2);
                }
                joltage = Integer.parseInt(Integer.toString(max1) + Integer.toString(max2));
                System.out.println("joltage for line "+line+" is "+joltage);
                joltage_sum += joltage;
            }
            System.out.println("final joltage sum is "+joltage_sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}