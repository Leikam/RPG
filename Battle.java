package RPG;

import java.util.Random;

import RPG.characters.ACharacter;
import RPG.characters.Hero;
import RPG.characters.Monster;
import RPG.characters.enemies.Goblin;
import RPG.characters.enemies.Skeleton;
import RPG.utils.CommandCLI;
import RPG.utils.LoggerUtils;
import RPG.utils.Randomizer;

public class Battle {

    public static int calculateHit(ACharacter character) {
        int strength = character.getStrength();
        int dexterity = character.getDexterity();
        final Random random = new Random();
        int damage = strength;

        final int hitRandom = random.nextInt(101);
        final boolean isSuccessHit = dexterity * 3 > hitRandom;
        final Randomizer.CriticalCheck criticalCheck = Randomizer.isCriticalHit();
        final boolean isCritical = criticalCheck.success;

        if (isSuccessHit || isCritical) {
            if (isCritical) {
                damage *= 2;
            }
        } else {
            damage = 0;
        }

        LoggerUtils.logDistinctByActor(
            character,
            "\nАтака (%s)!" +
            "\nКритический удар: (%d/%d) – " + CommandCLI.printBooleanVerbal(isCritical),
            character.getName(),
            criticalCheck.dice, criticalCheck.bound
        );

        LoggerUtils.logDistinctByActor(
            character,
            "\nРасчет удара: %d (%d * 3) > %d (из 100) – " + CommandCLI.printBooleanVerbal(isSuccessHit),
            dexterity * 3, dexterity, hitRandom
        );

        return damage;
    }

    public static void duel() {
        new Thread(() -> {
            System.out.println("⚔ ⚔ ⚔ Начинается битва! ⚔ ⚔ ⚔");
            final Hero hero = Game.HERO;

            final Random random = new Random();
            Monster enemy = random.nextBoolean() ? new Goblin() : new Skeleton();

            while (enemy.isAlive() && hero.isAlive()) {
                try {
                    hero.attack(enemy);
                    Thread.sleep(1000);
                    enemy.attack(hero);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (enemy.isAlive()) {
                System.out.println("Герой проиграл ✝ ..Игра окончена");
                Controller.startGameMenu();
            } else {
                System.out.println("Герой победил!");
                hero.loot(enemy);
            }

            Controller.startLocationMenu(
                unused -> {
                    System.out.println("Продолжить бой");
                    Battle.duel();
                }
            );
        }).start();
    }

    public static void dealDamage(ACharacter actor, ACharacter subject) {
        final int damage = Battle.calculateHit(actor);

        LoggerUtils.logDistinctByActor(
            actor,
            "\n%s нанёс %d урона %s" + (damage > 0 ? "" : " – промах!"),
            actor.getName(), damage, subject.getName()
        );

        subject.takeDamage(damage);

        LoggerUtils.logDistinctByActor(
            actor,
            "\nУ %s осталось %d здоровья\n",
            subject.getName(), subject.getHealth()
        );
    }
}
