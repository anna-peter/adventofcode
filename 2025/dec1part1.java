import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec1part1 {
    public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec1-sampleInput.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            int count = 0;
            int curr = 50;

            // go through every line
            while (scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);
                
                
                // R1000 - 100 = 900 

                curr = curr % 100;
                if (curr==0) {
                    count++;
                }
                System.out.println(curr);
                
                // go through every character

                for (int i = 0; i < line.length(); i++) {
                    // char c = line.charAt(i);
                    // System.out.println(c);
                    
                }
                System.out.println("final count is "+ count);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}