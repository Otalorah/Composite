package io;

import java.util.Scanner;

/**
 * Singleton wrapper around {@code System.in}.
 * <p>
 * Having a single shared {@link Scanner} for the whole application prevents the
 * well-known problem where two separate {@code Scanner} instances wrapping the
 * same underlying stream interfere with each other (and closing one closes the
 * stream for everyone).
 */
public final class ConsoleScanner {

    private static final Scanner INSTANCE = new Scanner(System.in);

    private ConsoleScanner() {}

    /** Returns the application-wide {@code System.in} scanner. Never close it. */
    public static Scanner get() {
        return INSTANCE;
    }
}
