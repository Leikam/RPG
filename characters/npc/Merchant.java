package RPG.characters.npc;

import java.util.Optional;

import RPG.Controller;
import RPG.Game;
import RPG.IMenu;
import RPG.characters.Bot;
import RPG.characters.Hero;

public class Merchant extends Bot {

    public Merchant() {
        super("Торговец");
    }

    public static void bargain() {
        final int choice = Controller.buildMenu(Optional.of("\nЧто брать будете?"), Menu.EXIT_MENU, Menu.POTION);
        switch (Menu.values()[choice]) {
            case POTION -> {
                System.out.println("Беру зелье");
                buyPotion(Game.HERO);
                Controller.startLocationMenu(unused -> {
                    bargain();
                });
            }
            case EXIT_MENU -> {
                Controller.startMainMenu();
            }
        }

    }

    private static void buyPotion(Hero hero) {
        if (hero.getGold() > 100) {
            hero.setGold(hero.getGold() - 100);
            hero.setHealth(Math.min(hero.getHealth() + 10, hero.getMaxHealth()));
            System.out.println(hero.toString());
        } else {
            System.out.printf("\nНедостаточно денег!");
        }
    }

    enum Menu implements IMenu {
        EXIT_MENU("0. Просто посмотреть зашел"),
        POTION("1. Зелье (100 золотых)"),
        ;

        private String menuLabel;

        Menu(String s) {
            menuLabel = s;
        }

        @Override
        public String getMenuItemLabel() {
            return menuLabel;
        }
    }

}
