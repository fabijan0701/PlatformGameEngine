package io.output;

/**
 * Default game output stream
 */
public class GameOutput {

    private static final String ANSI_RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    private static final String ANSI_UP = String.format("\033[%dA", 1);
    private static final String ANSI_CLEAR = "\033[2K";

    public GameOutput() {}

    public void println(String message) {
        System.out.println(message + ANSI_RESET);
    }

    /**
     * Prints the message, but rewrites the previous one.
     */
    public void println_r(String message) {
        System.out.printf("\r%s", message);
    }

    public void printInColor(String message, String AsciiColor) {
        System.out.println(AsciiColor + message + ANSI_RESET);
    }

    public void printWarning(String message) {
        System.out.println(YELLOW + "WARNING: " + message + ANSI_RESET);
    }

    public void printError(String error) {
        System.out.println(RED + "ERROR: "+ error);
    }

    public void printInfo(String message) {
        System.out.println(BLUE + "INFO: " + message);
    }
}
