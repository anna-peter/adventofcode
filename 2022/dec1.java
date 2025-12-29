import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class dec1 {
    public static void main(String[] args) {

        System.out.println("helloo");
        File in = new File("dec1-input");
        Scanner scan;
        int maxCalories = 0;
        int currentCal = 0;

        try {
            scan = new Scanner(in);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                System.out.println(line);
                if (line=="") {
                    maxCalories = Math.max(currentCal,maxCalories);
                    currentCal = 0;
                }
                else { 
                    currentCal += Integer.parseInt(line);
                }
            }
            System.out.println(maxCalories);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }
    
}
