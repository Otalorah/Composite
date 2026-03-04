package builder;

import component.Equipment;
import component.Skill;
import model.Character;

import java.util.Map;

public class CharacterDirector {

    private CharacterBuilder builder;

    public CharacterDirector(CharacterBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(CharacterBuilder builder) {
        this.builder = builder;
    }

    /**
     * Creates a balanced mage character.
     */
    public Character createCharacter(String name, String characterClass) {
        return builder
                .setBasicInfo(name, characterClass)
                .setLevel(1)
                .setBaseStats(100, 80, 15, 10, 12)
                .addSkill(new Skill("Basic Attack", 20, 0, "physical"))
                .build();
    }

    /**
     * Creates a heavy tank character with high defense.
     */
    public Character createTank(String name) {
        Equipment armor = new Equipment("Iron Plate", Map.of("defense", 30, "health", 50), "chest");
        Equipment shield = new Equipment("Tower Shield", Map.of("defense", 20), "offhand");
        Skill taunt = new Skill("Taunt", 5, 10, "utility");

        return builder
                .setBasicInfo(name, "Tank")
                .setLevel(1)
                .setBaseStats(250, 30, 10, 40, 8)
                .addEquipment(armor)
                .addEquipment(shield)
                .addSkill(taunt)
                .build();
    }

    /**
     * Creates a rogue character focused on speed and damage.
     */
    public Character createRogue(String name) {
        Equipment daggers = new Equipment("Dual Daggers", Map.of("attack", 25, "speed", 15), "weapon");
        Skill backstab = new Skill("Backstab", 60, 20, "physical");
        Skill evasion  = new Skill("Evasion",  0,  15, "defensive");

        return builder
                .setBasicInfo(name, "Rogue")
                .setLevel(1)
                .setBaseStats(120, 60, 30, 12, 35)
                .addEquipment(daggers)
                .addSkill(backstab)
                .addSkill(evasion)
                .build();
    }
}
