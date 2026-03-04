package adapter;

import io.GUIInput;
import io.Input;

/**
 * Adapter: bridges the int-based GUIInput to the String-based Input interface.
 */
public class AdapterGUIInput implements Input {

    private final GUIInput adaptee = new GUIInput();

    @Override
    public String read() {
        return String.valueOf(adaptee.read());
    }
}
