package RPG.characters;

public interface ICharacter {

    String getName();

    int getMaxHealth();

    int getHealth();

    int getStrength();

    int getDexterity();

    int getGold();

    int getExperience();

    void setName(String name);

    void setMaxHealth(int maxHealth);

    void setHealth(int health);

    void setStrength(int strength);

    void setDexterity(int dexterity);

    void setGold(int gold);

    void setExp(int exp);
}
