import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// DP: go from E to S 
// part solution is always from . to the next point 

public class dec20 {
    // Directions: left, right, up, down
    public static int[] dx = {0, 0, -1, 1};  // Left-right (horizontal)
    public static int[] dy = {-1, 1, 0, 0};  // Up-down (vertical)
    public static String[] directions = {"r", "l", "u", "d"};  // Directions for recursive calls
    public static void main(String[] args) {

        File in = new File("dec20-sample.txt");
        Scanner scan;

        ArrayList<ArrayList<String>> values = new ArrayList<>();

        try {
            scan = new Scanner(in);
            // initialize variables
            int x_e = 0, y_e = 0; // Coordinates of end
            int x_s = 0, y_s = 0; // Coordinates of start
            boolean foundE = false, foundS = false;

            // Process each line from input
            while (scan.hasNext()) {
                String line = scan.nextLine();
                ArrayList<String> currline = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    currline.add(String.valueOf(ch));

                    if (ch == 'E') { // Check directly against character
                        foundE = true;
                        y_e = i; // Column index of 'E'
                    }
                    else if (ch == 'S') {
                        foundS = true;
                        y_s = i;
                    }
                }
                // If 'E' is not found in the current line, increment the row index
                
                if (!foundE) x_e++;
                if (!foundS) x_s++;

                values.add(currline);
            }
            // Print all characters
            for (ArrayList<String> row : values) {
                for (String ch : row) {
                    System.out.print(ch + " "); // Print each character
                }
                System.out.println(); // New line after each row
            }
            System.out.println("the end is at position " + x_e + ", " + y_e);
            System.out.println("the start is at position " + x_s + ", " + y_s);


            // now the actual code
            int rows = values.size(); // how many rows
            int cols = values.get(0).size(); // how many cols
            int[][] path = new int[rows][cols];
            ArrayList<Integer> cheatedPaths = new ArrayList<Integer>();
            System.out.println("Max memory: " + (Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " MB");
            System.out.println("Free memory: " + (Runtime.getRuntime().freeMemory() / (1024 * 1024)) + " MB");
            System.out.println("Total memory: " + (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " MB");

            // non gpt version - same code
            // int result = Math.min(Math.min(findPath(x_e, y_e-1, values, path, 0, "r"), findPath(x_e-1, y_e, values, path, 0, "d")), Math.min(findPath(x_e+1, y_e, values, path, 0,"u"), findPath(x_e, y_e+1, values, path, 0,"l")));
            // System.out.println("the result is "+ result);
            // int baseline = path[x_s][y_s];
            // System.out.println("the fastest path to start takes "+baseline);

            int result = Math.min(Math.min(findPathGPT(x_e, y_e-1, values, path, 0, "r",cheatedPaths), findPathGPT(x_e-1, y_e, values, path, 0, "d",cheatedPaths)), Math.min(findPathGPT(x_e+1, y_e, values, path, 0,"u",cheatedPaths), findPathGPT(x_e, y_e+1, values, path, 0,"l",cheatedPaths)));
            System.out.println("the result is "+ result);
            int baseline = path[x_s][y_s];
            System.out.println("the fastest path to start takes "+baseline);
            System.out.println("time at end "+ path[x_e][y_e]);

            // now let's add some cheating, compare the cheated route with the baseline
            // boolean cheated = false;
            // int result_cheat = Math.min(Math.min(findPathGPTCheat(x_e, y_e-1, values, path, 0, "r", cheated,cheatedPaths), findPathGPTCheat(x_e-1, y_e, values, path, 0, "d", cheated,cheatedPaths)), Math.min(findPathGPTCheat(x_e+1, y_e, values, path, 0,"u", cheated,cheatedPaths), findPathGPTCheat(x_e, y_e+1, values, path, 0,"l", cheated,cheatedPaths)));
            // int count = 0;
            // for (int x : cheatedPaths) { 
            //     if (baseline-x > 100) {
            //         System.out.println("cheated path with time "+x+" and saved "+(baseline-x)+ " seconds");
            //         count++;
            //     }
            // }
            // System.out.println("count: "+count);
            int a,b;
            int value;
            for (int i=0; i<path.length; i++) {
                for (int j=0; j<path[0].length; j++) {
                    value = path[i][j];
                    if (value != Integer.MAX_VALUE && value !=0) {
                        // System.out.println(path[i][j]);
                        b = j-2;
                        // if it took us longer to reach point a,b than taking a shortcut from where we're at
                        if ( b < 0) {
                            continue;
                        }
                        if (path[i][b] > value+2) {
                            cheatedPaths.add(path[i][b] - (value+2));
                        }
                        b = j+2; 
                        if (b>=path[0].length) continue;
                        if (path[i][b] > value+2) {
                            cheatedPaths.add(path[i][b] - (value+2));
                        }

                        a = i-2; 
                        if ( a < 0) {
                            continue;
                        }
                        if (path[a][j] > value+2) {
                            cheatedPaths.add(path[a][j] - (value+2));
                        }
                        a = j+2; 
                        if (b>=path[0].length) continue;
                        if (path[a][j] > value+2) {
                            cheatedPaths.add(path[a][j] - (value+2));
                        }
                    }
                    for (int x : cheatedPaths) {
                        System.out.println("saved seconds: "+x);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // findPath returns the number of picoseconds it takes to reach the start
    public static int findPath(int x, int y, ArrayList<ArrayList<String>> values, int[][] path, int currSeconds, String where) {
        // currSeconds is a variable that says from this position, it takes that many seconds to reach the end
        // by definitions, currSeconds at the end should be zero
        // currSeconds is just a temporary variable - but we will store the permanent one in the path array

        // where says from where you're coming 

        // x is the row, y is the column
        int rows = values.size(); // how many rows
        int cols = values.get(0).size(); // how many cols
        int result=Integer.MAX_VALUE;

        // we made a move, so add 1 seconds to the seconds we used so far
        currSeconds++;
        System.out.println("currSeconds: "+currSeconds);

        if ( x < 0 || y < 0 || x > rows || y > cols) {
            // out of bounds
            System.out.println("out of bounds");
            return Integer.MAX_VALUE;
            
        } else if (path[x][y] !=0) {
            // base condition: we already found the path from the position to the start, just return how many seconds it takes
            if (currSeconds < path[x][y]) {
                // however, the path we are currently on is faster, so we update path
                path[x][y] = currSeconds;
            }
            System.out.println("already found a path before, returned "+path[x][y]);
            currSeconds = path[x][y];
            return path[x][y];

        } else if (values.get(x).get(y).equals("S")){
            // we're at the "end", that is, we backwards reached the start
            path[x][y] = currSeconds;
            System.out.println("found start with "+currSeconds + " seconds");
            return path[x][y];
            
        } else if (values.get(x).get(y).equals("E")){
            // we're at the "end", that is, we backwards reached the start
            System.out.println("found end with "+currSeconds + " seconds, gonna reset to 0");
            path[x][y] = 0;
            currSeconds = path[x][y];
            return path[x][y];
            
        } else if (values.get(x).get(y).equals("#")) {
            // we reached a wall; for now, this path is finished - return the max integer so this path doesn't get picked
            path[x][y] = Integer.MAX_VALUE;
            System.out.println("we are at a # ");
            currSeconds = path[x][y];
            return path[x][y];

        } else if (values.get(x).get(y).equals(".")) {
            System.out.println("we are are at a . and will call up the function for values around "+ x+" ,"+y);
            // check which direction we came from and then appropriately map out whats the fastest path coming from our neighbors
            if (where.equals("r")) {  // "r" for right, so we came from left
                // We can move up, down, or left (but not right)
                result = myMin(findPath(x-1, y, values, path, currSeconds, "d"),  // Move up, coming from down
                    findPath(x+1, y, values, path, currSeconds, "u"),  // Move down, coming from up
                    findPath(x, y-1, values, path, currSeconds, "r")   // Move left, coming from right
                );
            } else if (where.equals("l")) {  // "l" for left, so we came from right
                // We can move up, down, or right (but not left)
                result = myMin(
                    findPath(x-1, y, values, path, currSeconds, "d"),  // Move up, coming from down
                    findPath(x+1, y, values, path, currSeconds, "u"),  // Move down, coming from up
                    findPath(x, y+1, values, path, currSeconds, "l")   // Move right, coming from left
                );
            } else if (where.equals("u")) {  // "u" for up, so we came from below
                // We can move left, right, or down (but not up)
                result = myMin(
                    findPath(x, y-1, values, path, currSeconds, "r"),  // Move left, coming from right
                    findPath(x, y+1, values, path, currSeconds, "l"),  // Move right, coming from left
                    findPath(x+1, y, values, path, currSeconds, "u")   // Move down, coming from up
                );
            } else if (where.equals("d")) {  // "d" for down, so we came from above
                // We can move left, right, or up (but not down)
                result = myMin(
                    findPath(x, y-1, values, path, currSeconds, "r"),  // Move left, coming from right
                    findPath(x, y+1, values, path, currSeconds, "l"),  // Move right, coming from left
                    findPath(x-1, y, values, path, currSeconds, "d")   // Move up, coming from down
                );
            }
            result++; // have to increase the result from our neighbors to move to our current position
            System.out.println("we are at a . and returned "+result + " x,y: "+x+y);
            path[x][y] = Math.min(currSeconds, result); 
            currSeconds = path[x][y];
            return result;
        } else { 
            System.out.println("Hmmmm found a condition that isn't covered at a value of " +values.get(x).get(y));
            return Integer.MAX_VALUE;
        }

    }

    public static int findPathGPT(int x, int y, ArrayList<ArrayList<String>> values, int[][] path, int currSeconds, String where, ArrayList<Integer> cheatedPaths) {
        // Check for out of bounds

        int rows = values.size();
        int cols = values.get(0).size();
    
        if (x < 0 || y < 0 || x >= rows || y >= cols) {
            return Integer.MAX_VALUE;  // Out of bounds
        }
    
        // If already visited with better or equal time, return the result
        if (path[x][y] != 0 && path[x][y] <= currSeconds) {
            return path[x][y];
        } 
        // new code
        if (path[x][y] != 0 && path[x][y]>currSeconds) {
            path[x][y] = currSeconds;
            return path[x][y];
        }
    
        // Add 1 second for current position
        currSeconds++;

        // Handle specific grid values (start "S", end "E", wall "#")
        String cell = values.get(x).get(y);
        if (cell.equals("S") || cell.equals("E")) {
            path[x][y] = currSeconds;
            if (cell.equals("S")) {
                System.out.println("we are at the start with time "+currSeconds);
                cheatedPaths.add(currSeconds);
            }
            return currSeconds;  // Start or End cell, return current time
        }
        if (cell.equals("#")) {
            path[x][y] = Integer.MAX_VALUE;  // Wall, no valid path
            return Integer.MAX_VALUE;
        }
    
        
        // Initialize result to the maximum possible value
        int result = Integer.MAX_VALUE;
        // Loop through directions and exclude the one from which we came
        for (int i = 0; i < 4; i++) {
            if ((where.equals("r") && i == 1) || (where.equals("l") && i == 0) ||
                (where.equals("u") && i == 3) || (where.equals("d") && i == 2)) {
                continue;  // Skip the opposite direction
            }
            result = Math.min(result, findPathGPT(x + dx[i], y + dy[i], values, path, currSeconds, directions[i],cheatedPaths));
        }
    
        // Update the path with the minimum time found
        path[x][y] = Math.min(path[x][y] == 0 ? Integer.MAX_VALUE : path[x][y], result);
        return path[x][y];
    }

    public static int findPathGPTCheat(int x, int y, ArrayList<ArrayList<String>> values, int[][] path, int currSeconds, String where, boolean cheated, ArrayList<Integer> cheatedPaths) {
        // we already cheated, so just return how many seconds it normally takes to run
        // System.out.println("entering the function at pos "+x+", "+y);
        if (cheated) {
            // System.out.println("already cheated");
            return findPathGPT(x, y, values, path, currSeconds, where,cheatedPaths);
        }
        // Check for out of bounds
        int rows = values.size();
        int cols = values.get(0).size();
    
        if (x < 0 || y < 0 || x >= rows || y >= cols) {
            return Integer.MAX_VALUE;  // Out of bounds
        }
    
        // If already visited with better or equal time, return the result
        if (path[x][y] != 0 && path[x][y] <= currSeconds) {
            return path[x][y];
        }
    
        // Add 1 second for current position
        currSeconds++;

        // Handle specific grid values (start "S", end "E", wall "#")
        String cell = values.get(x).get(y);
        if (cell.equals("S") || cell.equals("E")) {
            path[x][y] = currSeconds;
            
            if (cell.equals("S")) { 
                cheatedPaths.add(currSeconds);
                System.out.println("we are at the start with time "+currSeconds);
            }
            return currSeconds;  // Start or End cell, return current time
        }
        
        // NOW as if we are at a .
        // set cheated to true if we are at a # for now - since we are cheating
        cheated = cell.equals("#") ? true : false;
        
        // Initialize result to the maximum possible value
        int result = Integer.MAX_VALUE;
        
        
        // Loop through directions and exclude the one from which we came
        for (int i = 0; i < 4; i++) {
            if ((where.equals("r") && i == 1) || (where.equals("l") && i == 0) ||
            (where.equals("u") && i == 3) || (where.equals("d") && i == 2)) {
                continue;  // Skip the opposite direction
            }
            result = Math.min(result, findPathGPTCheat(x + dx[i], y + dy[i], values, path, currSeconds, directions[i],cheated,cheatedPaths));
        }
        // result is the new path we found by exploring our neighbors
        
        // Update the path with the minimum time found
        path[x][y] = Math.min(path[x][y] == 0 ? Integer.MAX_VALUE : path[x][y], result); // first part of min is either max value (if we haven't found a path yet) or else the path we found previously
        
        // path[x][y] is the cheated value in the case of #
        
        // TODO: check this bit
        if (cell.equals("#")) {
            int result_nocheat = Integer.MAX_VALUE;
            // or, what is the other option? we don't cheat and cheat at a later stage 
            // so how do we factor in that choice? 
            // simply call up findPathGPTCheat again and ??

            // option we don't cheat - have to call up all directions again
            
            // can only call it from the direction we are coming from 
            for (int i = 0; i < 4; i++) {
                if ((where.equals("r") && i == 1) || (where.equals("l") && i == 0) ||
                (where.equals("u") && i == 3) || (where.equals("d") && i == 2)) {
                    result_nocheat = findPathGPTCheat(x+dx[i], y+dy[i], values, path, currSeconds, directions[i], false, cheatedPaths);  // go exactly in the opposite direction
                }
            }
            
            // result_nocheat is the new path we found by going back to where we came from
    
            // Update the path with the minimum time found
            path[x][y] = Math.min(path[x][y] == 0 ? Integer.MAX_VALUE : path[x][y], result_nocheat); // first part of min is either max value (if we haven't found a path yet) or else the path we found previously
    
            // if we didn't take the cheated path, set cheated to false and update path[x][y] to be max value (as we keep it as #)
            if (path[x][y] == result_nocheat) {
                cheated = false;
                path[x][y] = Integer.MAX_VALUE;
            }
            
        }
        return result; // or return path[x][y]??
    }
    
    public static int myMin(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }
}