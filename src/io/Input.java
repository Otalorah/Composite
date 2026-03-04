package io;

/**
 * Target interface for the Adapter pattern (input side).
 * All factory-created inputs must conform to this contract.
 */
public interface Input {
    /** Read a value from the user and return it as a String. */
    String read();
}
