package factory;

import adapter.AdapterConsoleInput;
import adapter.AdapterConsoleOutput;
import io.Input;
import io.Output;

/**
 * Concrete factory that creates console-based I/O.
 */
public class ConsoleFactory implements InterfaceFactory {

    @Override
    public Input createInput() {
        return new AdapterConsoleInput();
    }

    @Override
    public Output createOutput() {
        return new AdapterConsoleOutput();
    }
}
