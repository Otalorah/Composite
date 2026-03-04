package io;

import java.util.Scanner;

/**
 * Concrete console-based input.
 * Works with raw int values — adapted to the Input interface by AdapterConsoleInput.
 * Uses the application-wide {@link ConsoleScanner} to avoid multiple Scanner
 * instances wrapping {@code System.in}.
 */
public class ConsoleInput {

    /** Read an integer from standard input. */
    public int read() {
        Scanner scanner = ConsoleScanner.get();
        System.out.print("[Console] Enter a number: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
