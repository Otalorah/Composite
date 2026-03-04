package io;

/**
 * Concrete console-based output.
 * Works with raw int values — adapted to the Output interface by AdapterConsoleOutput.
 */
public class ConsoleOutput {

    /** Print an integer to standard output. */
    public void show(int data) {
        System.out.println("[Console] Output: " + data);
    }
}
