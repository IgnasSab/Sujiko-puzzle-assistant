package Solver;

// Custom 
import Custom_Exceptions.PuzzleNotSolvedException;

// Library
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main method class.
 * 
 * Its all functionality is to provide the user with the ability to
 * select the 4 numbers required to solve the Sujiko puzzle and solve.
 * It also prints the solution afterall if the puzzle was solved.
 */
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Input 4 numbers, this marks the start of the Sujiko puzzle: ");
        int arr[] = {};
        for (int i = 0; i < 4; i++) {
            int input;
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e);
                break;
            }
            arr[i] = input;
        }

        SujikoSolver solver = new SujikoSolver(arr);
        
        // Solve the puzzle
        solver.solve();

        try {
            solver.printSolution();
        } catch (PuzzleNotSolvedException e) {
            System.out.println(e);
        }
        
        // Marks the end of the program.
        markEnd();
    
    }

    /**
     * Functions prints the last words of the program.
     */
    private static void markEnd() {
        System.out.println();
        System.out.println("THE END.");
    }

}