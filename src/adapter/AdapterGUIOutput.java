package adapter;

import io.GUIOutput;
import io.Output;

import javax.swing.JOptionPane;

/**
 * Adapter: bridges the int-based GUIOutput to the String-based Output interface.
 */
public class AdapterGUIOutput implements Output {

    private final GUIOutput adaptee = new GUIOutput();

    @Override
    public void show(String data) {
        try {
            adaptee.show(Integer.parseInt(data.trim()));
        } catch (NumberFormatException e) {
            // Fallback: show the string inside a dialog when not a plain integer
            JOptionPane.showMessageDialog(null, "Result: " + data, "GUI Output",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
