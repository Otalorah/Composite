package io;

/**
 * Target interface for the Adapter pattern (output side).
 * All factory-created outputs must conform to this contract.
 */
public interface Output {
    /** Display a String value to the user. */
    void show(String data);
}
