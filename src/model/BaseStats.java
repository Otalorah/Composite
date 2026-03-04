package model;

import prototype.Prototype;

public class BaseStats implements Prototype<BaseStats> {

   private int health;
   private int mana;
   private int attack;
   private int defense;
   private int speed;

   public BaseStats(int health, int mana, int attack, int defense, int speed) {
      this.health = health;
      this.mana = mana;
      this.attack = attack;
      this.defense = defense;
      this.speed = speed;
   }

   public int getHealth() {
      return health;
   }

   public int getMana() {
      return mana;
   }

   public int getAttack() {
      return attack;
   }

   public int getDefense() {
      return defense;
   }

   public int getSpeed() {
      return speed;
   }

   public void setHealth(int health) {
      this.health = health;
   }

   public void setMana(int mana) {
      this.mana = mana;
   }

   public void setAttack(int attack) {
      this.attack = attack;
   }

   public void setDefense(int defense) {
      this.defense = defense;
   }

   public void setSpeed(int speed) {
      this.speed = speed;
   }

   @Override
   public BaseStats clone() {
      return new BaseStats(health, mana, attack, defense, speed);
   }

   @Override
   public String toString() {
      return "BaseStats{health=" + health + ", mana=" + mana +
            ", attack=" + attack + ", defense=" + defense +
            ", speed=" + speed + "}";
   }
}
