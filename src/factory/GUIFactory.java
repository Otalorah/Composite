package factory;

import adapter.AdapterGUIInput;
import adapter.AdapterGUIOutput;
import io.Input;
import io.Output;

/**
 * Concrete factory that creates Swing GUI-based I/O.
 */
public class GUIFactory implements InterfaceFactory {

    @Override
    public Input createInput() {
        return new AdapterGUIInput();
    }

    @Override
    public Output createOutput() {
        return new AdapterGUIOutput();
    }
}
