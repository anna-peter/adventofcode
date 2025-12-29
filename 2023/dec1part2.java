import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec1part2 {
    public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec1-input.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            int sum = 0;
            int finalInt = 0;
            char a = '0';
            char b = '0';
            // go through every line
            while (scan.hasNext()) {
                String line = scan.nextLine();
                // System.out.println(line);

                // go through every character

                boolean first = false;
                for (int i = 0; i < line.length(); i++) {
                    char current = line.charAt(i);
                    if (Character.isDigit(current)) {
                        if (first == false) {
                            a = current;
                            b = current;
                            first = true;
                        } else {
                            b = current;
                        }
                    }
                }
                System.out.println(a);
                System.out.println(b);
                String finalString = String.valueOf(a).concat(String.valueOf(b));
                finalInt = Integer.parseInt(finalString);
                sum += finalInt;
            }
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // function that, given a string, returns the positions where numbers (written out) can be found
    public static int number(String s) {
        

        return 0;
    }
}
