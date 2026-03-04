package component;

import java.util.HashMap;
import java.util.Map;

public class Skill extends CharacterComponent {

   private int damage;
   private int manaCost;
   private String skillType;

   public Skill(String name, int damage, int manaCost, String skillType) {
      super(name);
      this.damage = damage;
      this.manaCost = manaCost;
      this.skillType = skillType;
   }

   public int getDamage() {
      return damage;
   }

   public int getManaCost() {
      return manaCost;
   }

   public String getSkillType() {
      return skillType;
   }

   @Override
   public Map<String, Integer> getStats() {
      Map<String, Integer> stats = new HashMap<>();
      stats.put("damage", damage);
      stats.put("manaCost", manaCost);
      return stats;
   }

   public String rate() {
      if (damage >= 80)
         return "S";
      if (damage >= 60)
         return "A";
      if (damage >= 40)
         return "B";
      if (damage >= 20)
         return "C";
      return "D";
   }

   @Override
   public Skill clone() {
      return new Skill(getName(), damage, manaCost, skillType);
   }

   @Override
   public String toString() {
      return "Skill{name='" + getName() + "', damage=" + damage +
            ", manaCost=" + manaCost + ", skillType='" + skillType +
            "', rate=" + rate() + "}";
   }
}
