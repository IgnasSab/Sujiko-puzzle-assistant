

import Custom_Exceptions.PuzzleNotSolvedException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class acts as an interface between the user and the actual solving program.
 * 
 */
public class SujikoSolver {
    
    private final int maxNumber = 9;
    private final int gridSize = 3;
    private Set<Integer> visited = new HashSet<>();
    private int arr[];
    private int[][] sujikoGrid = new int[gridSize][gridSize];
    private boolean solved = false;
    private boolean unsolvable = false;
    /**
     * This constructor accepts the parameters required to solve the puzzle.
     * 
     * 
     * @param arr 
     * 
     * @pre @code{arr.size() == 4}
     * @throws IllegalArgumentException if @pre is violated.
     */
    SujikoSolver(int[] arr) throws IllegalArgumentException {
        
        if (arr.length != 4) {
            throw new IllegalArgumentException("Invalid array size");
        }

        this.arr = Arrays.copyOf(arr, arr.length);
        
    }

    /**
     * Prints the solution in an eye-pleasing manner.
     * 
     * @throws PuzzleNotSolvedException if the puzzle is not yet solved, but the
     * function is called
     */
    public void printSolution() throws PuzzleNotSolvedException {
        if (solved == false && unsolvable == true) {
            throw new PuzzleNotSolvedException("Puzzle is unsolvable");
        } else if (solved == false && unsolvable == false) {
            throw new PuzzleNotSolvedException("Puzzle it not yet solved");
        }

        // Print it in the terminal
        System.out.println("+---+---+---+");
        for (int i = 0; i < gridSize; i++) {
            System.out.print("|");
            for (int j = 0; j < gridSize; j++) {
                System.out.print(" " + sujikoGrid[i][j] + " |");
            }
            System.out.println();
            System.out.println("+---+---+---+");     
        }

    }

    /**
     * This function solves the sujiko puzzle given the parameters.
     * 
     * @post @code{solved == true && unsolvable == false} if the puzzle was solved or
     * @code{solved = false && unsolvable == true} if the puzzle is unsolvable
     */
    public void solvePuzzle() {

        if (solve(0, 0)) {
            solved = true;
        } else {
            unsolvable = true;
        }

    }

    /**
     * Recursively calls itself to solve the puzzle.
     * 
     * @param x the x coordinate to be filled
     * @param y the y coordinate to be filled
     * 
     * @return true if you can solve the puzzle, false otherwise
     */
    private boolean solve(int x, int y) {   

        // The end of puzzle was reached in the last iteration
        if (x > 2) {
            return true;
        }
        int x_new = x;
        int y_new = y;
        if (y == 2) {
            x_new++;
            y_new = 0;
        } else {
            y_new++;
        }

        for (int i = 1; i <= maxNumber; i++) {

            // Check if the combination of x, y, i is valid
            if (!isValid(x, y, i)) {
                continue;
            }
            
            fixDependenciesGrid(x, y, i, -1);
            sujikoGrid[x][y] = i;
            visited.add(i);

            // Change the coordinate so they don't overflow.


            if (solve(x_new, y_new)) {
                return true;
            } else {
                fixDependenciesGrid(x, y, i, 1);
                sujikoGrid[x][y] = 0;
                visited.remove(i);
            }

        }

        return false;

    }

    /**
     * Checks if such a combination is valid.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     * @param i the candidate for that coordinate
     * 
     * @return true if that combination is valid and false otherwise.
     */
    private boolean isValid(int x, int y, int i) {
        if (visited.contains(i)) {
            return false;
        } else if ((x == 0 && y == 0 && i <= arr[0])
                || (x == 1 && y == 0 && i <= arr[0] && i <= arr[2])
                || (x == 2 && y == 0 && i <= arr[2])
                || (x == 0 && y == 1 && i <= arr[0] && i <= arr[1])
                || (x == 1 && y == 1 && i == arr[0] && i <= arr[1] && i <= arr[2] && i <= arr[3])
                || (x == 2 && y == 1 && i == arr[2] && i <= arr[3])
                || (x == 0 && y == 2 && i <= arr[1])
                || (x == 1 && y == 2 && i == arr[1] && i <= arr[3])
                || (x == 2 && y == 2 && i == arr[3])) {
            return true;
        }
        return false;
    }

    /**
     * This functions fixes from all 4 dependencies the number contained in @code{x, y}.
     * 
     * @param x coordinate
     * @param y coordinate
     * @param add is 1 if we want to add and -1 if we want to subtract
     */
    private void fixDependenciesGrid(int x, int y, int i, int add) {
        if (x == 0 && y == 0) {
            arr[0] += add * i;
        } else if (x == 1 && y == 0) {
            arr[0] += add * i;
            arr[2] += add * i;
        } else if (x == 2 && y == 0) {
            arr[2] += add * i;
        } else if (x == 0 && y == 1) {
            arr[0] += add * i;
            arr[1] += add * i;
        } else if (x == 1 && y == 1) {
            arr[0] += add * i;
            arr[1] += add * i;
            arr[2] += add * i;
            arr[3] += add * i;
        } else if (x == 2 && y == 1) {
            arr[2] += add * i;
            arr[3] += add * i;
        } else if (x == 0 && y == 2) {
            arr[1] += add * i;
        } else if (x == 1 && y == 2) {
            arr[1] += add * i;
            arr[3] += add * i;
        } else if (x == 2 && y == 2) {
            arr[3] += add * i;
        }    
    }
    

}
