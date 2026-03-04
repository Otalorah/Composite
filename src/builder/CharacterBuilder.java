package builder;

import component.CharacterComponent;
import model.Character;

public interface CharacterBuilder {

    void reset();

    CharacterBuilder setBasicInfo(String name, String characterClass);

    CharacterBuilder setLevel(int level);

    CharacterBuilder setBaseStats(int health, int mana, int attack, int defense, int speed);

    CharacterBuilder addEquipment(CharacterComponent component);

    CharacterBuilder addSkill(CharacterComponent skill);

    Character build();
}
