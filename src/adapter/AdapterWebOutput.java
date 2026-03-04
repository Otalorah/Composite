package adapter;

import io.Output;
import io.WebOutput;

/**
 * Adapter: bridges the int-based WebOutput to the String-based Output interface.
 */
public class AdapterWebOutput implements Output {

    private final WebOutput adaptee = new WebOutput();

    @Override
    public void show(String data) {
        try {
            adaptee.show(Integer.parseInt(data.trim()));
        } catch (NumberFormatException e) {
            // If the data is not a plain integer, hash its length as a numeric proxy
            adaptee.show(data.length());
        }
    }
}
