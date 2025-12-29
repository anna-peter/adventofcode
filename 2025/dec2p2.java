import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class dec2p2 {
     public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec2-input.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            long start = -1;
            long end = -1;
            String start_str = "";
            ArrayList<Long> invalidIDs = new ArrayList<>();

            // go through every line
            while (scan.hasNext()) {
                String line = scan.next();
                String[] items = line.trim().split(",");

                for (String item : items) {
                    String[] parts = item.split("-");
                    start_str = parts[0].trim();
                    start = Long.parseLong(start_str);
                    end = Long.parseLong(parts[1].trim());
                    // System.out.println("start "+start+"; end "+end);
                    while (start<=end) {
                        start_str = Long.toString(start); //fix: moved up
                        for (int j=0;j<start_str.length()/2; j++) {
                            String compare_to = start_str.substring(0, j+1);
                            String rest = start_str.substring(j+1);
                            // System.out.println("comparing "+compare_to+ " and "+rest);
                            if (isInvalidGeneral(compare_to, rest)) {
                                invalidIDs.add(start);
                                break; //fix
                            }
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
    }
    // returns true if it is a repeating pattern
    public static Boolean isInvalidGeneral(String compare_to, String rest) {
        int compare_len = compare_to.length();
        int rest_len = rest.length();
        if (rest_len % compare_len !=0) {
            // cannot be a repeating pattern
            return false;
        }

        for (int i=0; i<rest_len;i+=compare_len) {
            if (!rest.regionMatches(i, compare_to, 0, compare_len)) {
                return false;
            }
        }

        return true;
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
