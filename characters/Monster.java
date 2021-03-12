package RPG.characters;

import RPG.Battle;

public class Monster extends Bot implements IWarrior {

    public Monster(String name) {
        super(name);
    }

    @Override
    public void attack(ACharacter character) {
        Battle.dealDamage(this, character);
    }

}
