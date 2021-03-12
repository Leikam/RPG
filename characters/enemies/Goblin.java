package RPG.characters.enemies;

import RPG.characters.Monster;

public class Goblin extends Monster {

    /*
    * HEALTH: 10
    * STR: 2
    * DEX: 15
    *
    * GOLD: 5
    * EXP: 100
    * */

    public Goblin() {
        super("Гоблин");
        setMaxHealth(10);
        setHealth(15);
        setDexterity(15);
        setStrength(2);
        setGold(40);
        setExp(100);
    }

}
