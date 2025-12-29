import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec3p1_copy {
    public static void main(String[] args) {

        File in = new File("dec3-input.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            int joltage_sum = 0;
            
            // go through every line
            while (scan.hasNext()) {
                String line = scan.nextLine();
                // add each char in line as a separate int to an array
                int[] numbers = createArray(line);
                // find the max integer and its index
                int max = -1;
                int maxIndex = -1;
                for (int i=0; i<numbers.length-1; i++) {
                    if (numbers[i]>max) {
                        max = numbers[i];
                        maxIndex = i;
                    }
                }
                int max2 = -1;
                for (int i=maxIndex+1; i<numbers.length; i++) {
                    if (numbers[i]>max2) {
                        max2 = numbers[i];
                    }
                }
                int joltage = Integer.parseInt(Integer.toString(max) + Integer.toString(max2));
                System.out.println("joltage for line "+line+" is "+joltage);
                joltage_sum += joltage;
            }
            System.out.println("Total joltage is "+joltage_sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static int[] createArray(String line) {
        int[] numbers = new int[line.length()];
        for (int i=0; i<line.length(); i++) {
            numbers[i] = line.charAt(i)-'0';
        }
        return numbers;
    }
    
}
