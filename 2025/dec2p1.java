import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class dec2p1 {
     public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec2-input.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            long start = -1;
            long end = -1;
            ArrayList<Long> invalidIDs = new ArrayList<>();

            // go through every line
            while (scan.hasNext()) {
                String line = scan.next();
                String[] items = line.trim().split(",");

                for (String item : items) {
                    String[] parts = item.split("-");
                    start = Long.parseLong(parts[0].trim());
                    end = Long.parseLong(parts[1].trim());
                    // System.out.println("start "+start+"; end "+end);
                    while (start<=end) {
                        if (isInvalid(Long.toString(start))) {
                            invalidIDs.add(start);
                        }
                        start++;
                    }

                }

            }
            long sum = 0;
            for (Long value : invalidIDs) {
                System.out.println(value);
                sum += value;
            }
            System.out.println("final sum is "+sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // sample();
    }

    public static void sample() {
        String s = "1010";
        int mid = s.length() /2;
        String p1 = s.substring(0, mid);
        String p2 = s.substring(mid);
        System.out.println("p1: "+p1+ "; p2: "+p2);
    }
    public static Boolean isInvalid(String s) {
        if (s.length()%2 !=0) {
            return false;
        }
        int mid = s.length()/2;
        String p1 = s.substring(0, mid);
        String p2 = s.substring(mid);
        if (p1.equals(p2)) {
            System.out.println("p1: "+p1+ "; p2: "+p2);
            System.out.println(" they are equal \n");
            return true;
        }
        return false;
    }
}
