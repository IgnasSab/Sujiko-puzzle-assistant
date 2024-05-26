package Custom_Exceptions;

/**
 * Custom exception which will be thrown when the solution of the puzzle 
 * was needed, but the solution was not yet calculated or doesn't exist.
 */
public class PuzzleNotSolvedException extends Exception {

    public PuzzleNotSolvedException(String message) {
        super(message);
    }

}
