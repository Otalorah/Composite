package component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeComponent extends CharacterComponent {

    private List<CharacterComponent> children;

    public CompositeComponent(String name) {
        super(name);
        this.children = new ArrayList<>();
    }

    public void addComponent(CharacterComponent component) {
        children.add(component);
        component.setParent(this);
    }

    public void removeComponent(CharacterComponent component) {
        children.remove(component);
        component.setParent(null);
    }

    public List<CharacterComponent> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public Map<String, Integer> getStats() {
        Map<String, Integer> total = new HashMap<>();
        for (CharacterComponent child : children) {
            child.getStats().forEach((key, value) ->
                total.merge(key, value, Integer::sum));
        }
        return total;
    }

    @Override
    public CompositeComponent clone() {
        CompositeComponent copy = new CompositeComponent(getName());
        for (CharacterComponent child : children) {
            copy.addComponent(child.clone());
        }
        return copy;
    }

    @Override
    public String toString() {
        return "CompositeComponent{name='" + getName() + "', children=" + children + "}";
    }
}
