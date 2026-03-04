package adapter;

import io.ConsoleInput;
import io.Input;

/**
 * Adapter: bridges the int-based ConsoleInput to the String-based Input interface.
 */
public class AdapterConsoleInput implements Input {

    private final ConsoleInput adaptee = new ConsoleInput();

    @Override
    public String read() {
        return String.valueOf(adaptee.read());
    }
}
