package RPG.characters;

import RPG.Battle;

public class Hero extends ACharacter implements IWarrior {

    private int level = 1;

    public Hero(String name) {
        super(name);
        setMaxHealth(20);
        setHealth(20);
        setDexterity(25);
        setStrength(4);
        setGold(0);
    }

    @Override
    public void attack(ACharacter character) {
        Battle.battleMove(this, character);
    }

    @Override
    public void setExp(int exp) {
        super.setExp(exp);

        if (getExperience() > getExperience() * level) {
            level++;
            updateLevel();
        }
    }

    private void updateLevel() {
        System.out.printf("\n\n === Новый уровень! (%d) === \n\n", level);
        setMaxHealth(getMaxHealth() + 2);
        setDexterity(getDexterity() + 5);
        setStrength(getStrength() + 1);
    }

    public void loot(Monster enemy) {
        final int exp = enemy.getExperience();
        final int gold = enemy.getGold();
        setExp(getExperience() + exp);
        setGold(getGold() + gold);
        System.out.printf("\nГерой получает %d опыта и %d золота", exp, gold);
        System.out.println("\nНаш герой: " + this.toString());
    }
}
