package factory;

import io.Input;
import io.Output;

/**
 * Abstract Factory interface.
 * Each concrete factory produces a matching pair of Input and Output objects
 * for a specific UI technology (Console, GUI, Web).
 */
public interface InterfaceFactory {
    Input  createInput();
    Output createOutput();
}
