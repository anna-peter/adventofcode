import java.util.Scanner;
import java.util.Random;

public class testpilot {
    // main class
    public static void main(String[] args) {
        // say hello world
        System.out.println("Hello World!");
        // ask for a magic number
        System.out.println("Please enter a magic number: ");
        // read the magic number
        Scanner scan = new Scanner(System.in);
        int magic = scan.nextInt();
        // create a random integer between 100 and 200 
        Random rand = new Random();
        int random = rand.nextInt(100) + 100;
        // print the random number
        System.out.println(random);
    }
}
