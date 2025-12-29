import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// strategy 1: sort left column into array; sort right column into array; calculate difference for every row; sum up differences
// O(n*log(n) + n)
// strategy 2:? 

public class dec1 {
    public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec1-input.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            ArrayList<Integer> left = new ArrayList<>();
            ArrayList<Integer> right = new ArrayList<>();
            int sum = 0;
            int diff = 0;
            // read all integers into the respective lists            
            while (scan.hasNext()) {
                left.add(scan.nextInt());
                right.add(scan.nextInt());
            }
            // sort both lists individually
            Collections.sort(left);
            Collections.sort(right);
            //sanity check
            if (left.size() != right.size()) { System.out.println("oh oh size doesnt match");}
            // compare values for both lists - take the absolute value of the distance!, add all of them up
            for (int i=0; i<left.size(); i++) {
                diff = Math.abs(left.get(i) - right.get(i));
                sum += diff;
            }
            System.out.println("The result is "+sum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}