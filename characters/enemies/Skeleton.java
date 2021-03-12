package RPG.characters.enemies;

import RPG.characters.Monster;

public class Skeleton extends Monster {

    /*
    * HEALTH: 15
    * STR: 3
    * DEX: 10
    *
    * GOLD: 15
    * EXP: 200
    * */

    public Skeleton() {
        super("Скелет");
        setMaxHealth(15);
        setHealth(20);
        setDexterity(10);
        setStrength(3);
        setGold(75);
        setExp(250);
    }

}
