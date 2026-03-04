package component;

import java.util.HashMap;
import java.util.Map;

public class EquipmentSet extends CompositeComponent {

   private Map<String, Integer> setBonus;
   private int requiredPieces;

   public EquipmentSet(String name, Map<String, Integer> setBonus, int requiredPieces) {
      super(name);
      this.setBonus = new HashMap<>(setBonus);
      this.requiredPieces = requiredPieces;
   }

   public boolean isComplete() {
      return getChildren().size() >= requiredPieces;
   }

   @Override
   public Map<String, Integer> getStats() {
      Map<String, Integer> total = super.getStats();
      if (isComplete()) {
         setBonus.forEach((key, value) -> total.merge(key, value, Integer::sum));
      }
      return total;
   }

   @Override
   public EquipmentSet clone() {
      EquipmentSet copy = new EquipmentSet(getName(), new HashMap<>(setBonus), requiredPieces);
      for (var child : getChildren()) {
         copy.addComponent(child.clone());
      }
      return copy;
   }

   @Override
   public String toString() {
      return "EquipmentSet{name='" + getName() + "', isComplete=" + isComplete() +
            ", pieces=" + getChildren().size() + "/" + requiredPieces + "}";
   }
}
