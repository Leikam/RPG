package RPG.characters;

public abstract class ACharacter implements ICharacter {

    private String name;
    private int maxHealth = 20;
    private int health = maxHealth;
    private int strength = 1;
    private int dexterity = 1;
    private int gold = 0;
    private int exp = 0;

    public ACharacter(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getGold() {
        return gold;
    }

    @Override
    public int getExperience() {
        return exp;
    }

    @Override
    public int getMaxHealth() {
        return maxHealth;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public int getDexterity() {
        return dexterity;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    @Override
    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public void setExp(int exp) {
        this.exp = exp;
    }

    public void takeDamage(int damage) {
        this.setHealth(Math.max(this.health - damage, 0));
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public String toString() {
        return "ACharacter{" +
               "name='" + name + '\'' +
               ", maxHealth=" + maxHealth +
               ", health=" + health +
               ", strength=" + strength +
               ", dexterity=" + dexterity +
               ", gold=" + gold +
               ", exp=" + exp +
               '}';
    }
}
