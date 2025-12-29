import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec11p1 {
    public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec1-input.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables

            // go through every line
            while (scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);

                // go through every character

                for (int i = 0; i < line.length(); i++) {

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}