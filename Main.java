
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
    static int arr[] = new int[4];
    public static void main(String[] args) {
        System.out.print("Input 4 numbers, this marks the start of the Sujiko puzzle: ");
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

        // Create the solver object
        SujikoSolver solver = null;

        try {
            solver = new SujikoSolver(arr);
        } catch (IllegalArgumentException e) {
            System.out.println();
            return;
        }
        
        // Solve the puzzle
        try {
            solver.solvePuzzle();
        } catch (Exception e) {
            System.out.println("solver object is null");
        }

        // Print solution
        try {
            solver.printSolution();
        } catch (PuzzleNotSolvedException e) {
            System.out.println(e);
            return;
        }
        
        // Marks the end of the program.
        markEnd();
    
    }

    /**
     * Functions prints the last words of the program.
     */
    private static void markEnd() {
        System.out.println();
        System.out.println("END");
    }

}