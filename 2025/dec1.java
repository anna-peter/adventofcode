import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec1 {
    public static void main(String[] args) {

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

                if (line.charAt(0) == 'R') {
                    curr += Integer.parseInt(line.substring(1));
                    // include equality so landing exactly on 100 (i.e. 0 after mod) is counted here
                    while (curr >= 100) {
                        System.out.println("passed 0 at "+line);
                        count++;
                        curr -= 100;
                        System.out.println("; new curr is "+ curr+"; count is "+ count);
                    }
                } else {
                    curr -= Integer.parseInt(line.substring(1));
                    // include equality so landing exactly on 0 is counted inside the loop
                    while (curr <= 0) {
                        System.out.print("passed 0 at "+line);
                        count++;
                        curr += 100;
                        System.out.println("; new curr is "+ curr+"; count is "+ count);
                    }
                }

                curr = curr % 100;
                System.out.println("count is "+ count+ " at line "+line);
            }
            System.out.println("final count is "+ count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}