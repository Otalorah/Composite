package adapter;

import io.ConsoleOutput;
import io.Output;

/**
 * Adapter: bridges the int-based ConsoleOutput to the String-based Output interface.
 */
public class AdapterConsoleOutput implements Output {

    private final ConsoleOutput adaptee = new ConsoleOutput();

    @Override
    public void show(String data) {
        try {
            adaptee.show(Integer.parseInt(data.trim()));
        } catch (NumberFormatException e) {
            // Fallback: print the string directly when the value is not a plain integer
            System.out.println("[Console] Output: " + data);
        }
    }
}
