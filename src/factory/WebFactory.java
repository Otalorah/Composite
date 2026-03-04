package factory;

import adapter.AdapterWebInput;
import adapter.AdapterWebOutput;
import io.Input;
import io.Output;

/**
 * Concrete factory that creates Web (HTTP) based I/O.
 * Input listens on http://localhost:8080/?value=<number>
 * Output serves the result at  http://localhost:8081/
 */
public class WebFactory implements InterfaceFactory {

    @Override
    public Input createInput() {
        return new AdapterWebInput();
    }

    @Override
    public Output createOutput() {
        return new AdapterWebOutput();
    }
}
