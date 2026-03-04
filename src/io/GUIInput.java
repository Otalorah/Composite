package io;

import javax.swing.JOptionPane;

/**
 * Concrete GUI (Swing) input.
 * Works with raw int values — adapted to the Input interface by AdapterGUIInput.
 */
public class GUIInput {

    /** Show a Swing dialog and return the entered integer. */
    public int read() {
        String raw = JOptionPane.showInputDialog(null,
                "Enter a number:", "GUI Input", JOptionPane.QUESTION_MESSAGE);
        if (raw == null) {
            return 0; // user cancelled
        }
        try {
            return Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number, returning 0.");
            return 0;
        }
    }
}
