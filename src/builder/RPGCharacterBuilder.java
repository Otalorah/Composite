package builder;

import component.CharacterComponent;
import model.BaseStats;
import model.Character;

import java.util.ArrayList;
import java.util.List;

public class RPGCharacterBuilder implements CharacterBuilder {

    private String name;
    private String characterClass;
    private BaseStats baseStats;
    private int level;
    private List<CharacterComponent> equipmentList;
    private List<CharacterComponent> skills;

    public RPGCharacterBuilder() {
        reset();
    }

    @Override
    public void reset() {
        this.name = "Unknown";
        this.characterClass = "None";
        this.baseStats = new BaseStats(100, 50, 10, 10, 10);
        this.level = 1;
        this.equipmentList = new ArrayList<>();
        this.skills = new ArrayList<>();
    }

    @Override
    public CharacterBuilder setBasicInfo(String name, String characterClass) {
        this.name = name;
        this.characterClass = characterClass;
        return this;
    }

    @Override
    public CharacterBuilder setLevel(int level) {
        this.level = level;
        return this;
    }

    @Override
    public CharacterBuilder setBaseStats(int health, int mana, int attack, int defense, int speed) {
        this.baseStats = new BaseStats(health, mana, attack, defense, speed);
        return this;
    }

    @Override
    public CharacterBuilder addEquipment(CharacterComponent component) {
        this.equipmentList.add(component);
        return this;
    }

    @Override
    public CharacterBuilder addSkill(CharacterComponent skill) {
        this.skills.add(skill);
        return this;
    }

    @Override
    public Character build() {
        Character character = new Character(name, characterClass, baseStats, level);
        for (CharacterComponent eq : equipmentList) {
            character.addEquipment(eq);
        }
        for (CharacterComponent sk : skills) {
            character.addSkill(sk);
        }
        reset();
        return character;
    }
}
