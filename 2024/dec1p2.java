// figure out exactly how often each number from the left list appears in the right list. 
//Calculate a total similarity score by adding up each number in the left list after multiplying it by the number of times that number appears in the right list.
// 3   4
// 4   3
// 2   5
// 1   3
// 3   9
// 3   3
// sorted: 
// 1   3
// 2   3
// 3   3
// 3   4
// 3   5
// 4   9
// 10 9 
// 10 10 

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class dec1p2 {
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
            // j is the index counter for the right side 
            // if left(i) < right(j), j should stay at the same place and i advance, until the values match
            // if left(i) > right(j), 
            int i = 0; 
            // int save = right.get(j);
            for (int j=0; j<right.size(); j++) {
                // diff = Math.abs(left.get(i) - right.get(i));
                // catch up j and add up all the ones where it's equal
                while (left.get(i)==right.get(j) && j>i) {
                    sum += left.get(i);
                    // save = right.get(j);
                    i++;
                }
                // while (left.get(i)==save) {
                //     sum+=left.get(i);
                //     j++;
                // }
            }
            System.out.println("The result is "+sum);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
