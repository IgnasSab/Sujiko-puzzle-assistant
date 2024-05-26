package Solver;

import Custom_Exceptions.PuzzleNotSolvedException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

/**
 * This class acts as an interface between the user and the actual solving program.
 * 
 */
public class SujikoSolver {
    
    private int arr[];
    private List<List<Integer>> sujikoGrid = new ArrayList<>();
    private boolean solved = false;
    private boolean unsolvable = false;
    /**
     * This constructor accepts the parameters required to solve the puzzle 
     * and initializes the grid.
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

        // Initialize the grid.
        for (int i = 0; i < 3; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                row.add(0);
            }
            sujikoGrid.add(row);
        }
        
    }

    /**
     * This function solves the sujiko puzzle given the parameters.
     * 
     * @post @code{solved == true && unsolvable == false} if the puzzle was solved or
     * @code{solved = false && unsolvable == true} if the puzzle is unsolvable
     */
    public void solvePuzzle() {

        

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
    }

}
