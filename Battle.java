package RPG;

import java.util.Random;
import java.util.function.Supplier;

import RPG.characters.ACharacter;
import RPG.characters.Hero;
import RPG.characters.Monster;
import RPG.characters.enemies.Goblin;
import RPG.characters.enemies.Skeleton;
import RPG.characters.npc.Merchant;
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

        LoggerUtils.logDistinct(
            character instanceof Hero,
            "\nАтака (%s)!" +
            "\nКритический удар: (%d/%d) – " + CommandCLI.printBooleanVerbal(isCritical),
            character.getName(),
            criticalCheck.dice, criticalCheck.bound
        );

        LoggerUtils.logDistinct(
            character instanceof Hero,
            "\nРасчет удара: %d (%d * 3) > %d (из 100) – " + CommandCLI.printBooleanVerbal(isSuccessHit),
            dexterity * 3, dexterity, hitRandom
        );

        return damage;
    }

    public static void fight() {
        new Thread(() -> {
            System.out.println("⚔ ⚔ ⚔ Начинается битва! ⚔ ⚔ ⚔");
            final Hero hero = Game.HERO;

            final Random random = new Random();
            Monster enemy = random.nextBoolean() ? new Goblin() : new Skeleton();

            while (true) {
                hero.attack(enemy);

                if (enemy.isAlive()) {
                    enemy.attack(hero);

                    if (!hero.isAlive()) {
                        System.out.print("Герой проиграл ✝ ..Игра окончена");
                        /* @todo: начать новую игру; */
                        System.exit(0);
                        break;
                    }
                } else {
                    System.out.printf("\nГерой победил!");
                    hero.loot(enemy);
                    break;
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Controller.startLocationMenu(
                unused -> {
                    System.out.println("Продолжить бой");
                    Battle.fight();
                }
            );
        }).start();
    }

    public static void battleMove(ACharacter actor, ACharacter subject) {
        final int damage = Battle.calculateHit(actor);

        LoggerUtils.logDistinct(
            actor instanceof Hero,
            "\n%s нанёс %d урона %s",
            actor.getName(), damage, subject.getName()
        );

        subject.takeDamage(damage);

        LoggerUtils.logDistinct(
            actor instanceof Hero,
            "\nУ %s осталось %d здоровья\n",
            subject.getName(), subject.getHealth()
        );
    }
}
