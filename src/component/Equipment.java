package component;

import java.util.HashMap;
import java.util.Map;

public class Equipment extends CharacterComponent {

    private Map<String, Integer> stats;
    private String slot;

    public Equipment(String name, Map<String, Integer> stats, String slot) {
        super(name);
        this.stats = new HashMap<>(stats);
        this.slot = slot;
    }

    public String getSlot() {
        return slot;
    }

    @Override
    public Map<String, Integer> getStats() {
        return new HashMap<>(stats);
    }

    @Override
    public Equipment clone() {
        return new Equipment(getName(), new HashMap<>(stats), slot);
    }

    @Override
    public String toString() {
        return "Equipment{name='" + getName() + "', slot='" + slot +
               "', stats=" + stats + "}";
    }
}
