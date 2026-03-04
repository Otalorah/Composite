package component;

import prototype.Prototype;
import java.util.Map;

public abstract class CharacterComponent implements Prototype<CharacterComponent> {

    private String name;
    private CharacterComponent parent;

    public CharacterComponent(String name) {
        this.name = name;
        this.parent = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CharacterComponent getParent() {
        return parent;
    }

    public void setParent(CharacterComponent parent) {
        this.parent = parent;
    }

    public abstract Map<String, Integer> getStats();

    @Override
    public abstract CharacterComponent clone();
}
