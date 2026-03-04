package model;

import component.CharacterComponent;
import component.CompositeComponent;
import prototype.Prototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Character implements Prototype<Character> {

   private String name;
   private String characterClass;
   private BaseStats baseStats;
   private int level;
   private CompositeComponent equipment;
   private List<CharacterComponent> skills;

   public Character(String name, String characterClass, BaseStats baseStats, int level) {
      this.name = name;
      this.characterClass = characterClass;
      this.baseStats = baseStats;
      this.level = level;
      this.equipment = new CompositeComponent("Equipment");
      this.skills = new ArrayList<>();
   }

   public String getKey() {
      return name + "_" + characterClass + "_" + level;
   }

   public String getCharacterClass() {
      return characterClass;
   }

   public int getLevel() {
      return level;
   }

   public BaseStats getBaseStats() {
      return baseStats;
   }

   public CompositeComponent getEquipment() {
      return equipment;
   }

   public List<CharacterComponent> getSkills() {
      return new ArrayList<>(skills);
   }

   public void setState(String data) {
      // Parses a simple "key=value;..." string to update name/class/level
      String[] parts = data.split(";");
      for (String part : parts) {
         String[] kv = part.split("=");
         if (kv.length == 2) {
            switch (kv[0].trim().toLowerCase()) {
               case "name" -> this.name = kv[1].trim();
               case "class" -> this.characterClass = kv[1].trim();
               case "level" -> this.level = Integer.parseInt(kv[1].trim());
            }
         }
      }
   }

   public void setLevel(int level) {
      this.level = level;
   }

   public void addEquipment(CharacterComponent component) {
      equipment.addComponent(component);
   }

   public void addSkill(CharacterComponent component) {
      skills.add(component);
   }

   public Map<String, Integer> getTotalStats() {
      Map<String, Integer> total = new HashMap<>();
      total.put("health", baseStats.getHealth());
      total.put("mana", baseStats.getMana());
      total.put("attack", baseStats.getAttack());
      total.put("defense", baseStats.getDefense());
      total.put("speed", baseStats.getSpeed());

      equipment.getStats().forEach((key, value) -> total.merge(key, value, Integer::sum));

      return total;
   }

   @Override
   public Character clone() {
      Character copy = new Character(name, characterClass, baseStats.clone(), level);
      for (CharacterComponent child : equipment.getChildren()) {
         copy.addEquipment(child.clone());
      }
      for (CharacterComponent skill : skills) {
         copy.addSkill(skill.clone());
      }
      return copy;
   }

   @Override
   public String toString() {
      return "Character{name='" + name + "', class='" + characterClass +
            "', level=" + level + ", stats=" + getTotalStats() +
            ", skills=" + skills + "}";
   }
}
