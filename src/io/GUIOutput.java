package io;

import javax.swing.JOptionPane;

/**
 * Concrete GUI (Swing) output.
 * Works with raw int values — adapted to the Output interface by AdapterGUIOutput.
 */
public class GUIOutput {

    /** Show the given integer inside a Swing dialog. */
    public void show(int data) {
        JOptionPane.showMessageDialog(null,
                "Result: " + data,
                "GUI Output",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
