package adapter;

import io.Input;
import io.WebInput;

/**
 * Adapter: bridges the int-based WebInput to the String-based Input interface.
 */
public class AdapterWebInput implements Input {

    private final WebInput adaptee = new WebInput();

    @Override
    public String read() {
        return String.valueOf(adaptee.read());
    }
}
