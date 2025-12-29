import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class dec2 {
    public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec2.txt");
        Scanner scan;

        try {
            scan = new Scanner(in);
            // initialize variables
            int x = -1;
            int y = -1;
            boolean safe = true;
            int reports = 0;
            boolean firstiteration = true;
            boolean isincreasing = true;

            // go through every line
            while (scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);
                firstiteration = true;
                String[] tokens = line.split("\\s+");
                // go through every character
                x = Integer.parseInt(tokens[0]);
                for (int i = 1; i < tokens.length; i++) {
                    y = Integer.parseInt(tokens[i]);
                    // System.out.println("x is "+ x+" and y is "+y);
                    if ((x<y && isincreasing) || (x<y && firstiteration)) {
                        if (firstiteration) { 
                            isincreasing=true; 
                            firstiteration = false;
                        } 
                        if (x == y-1 || x == y-2 || x == y-3) {
                            safe = true;
                            System.out.println("safe on increasing to "+ y);
                        } else {
                            safe = false;
                            System.out.println("unsafe on increasing");
                            break;
                        }
                    } else if ((x>y && !isincreasing) || (x>y && firstiteration)) {
                        if (firstiteration) {
                            isincreasing = false;
                            firstiteration = false;
                        }
                        if (y == x-1 || y == x-2 || y == x-3) {
                            safe = true;
                            System.out.println("safe on decreasing");

                        } else {
                            safe = false;
                            System.out.println("unsafe on decreasing");
                            break;
                        }
                    } else {
                        safe = false;
                        System.out.println("unsafe with x "+ x+" and y "+ y);
                        break;
                    }
                    x = y;
                }

                if (safe) { 
                    reports++; 
                    System.out.println("safe reports increased to "+reports);
                }
            }
            System.out.println("Safe reports "+ reports);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}