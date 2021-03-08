package RPG;

import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import RPG.characters.npc.Merchant;
import RPG.utils.CommandCLI;

public class Controller {

    public static void startMainMenu() {
        final int choice = buildMenu(Optional.of("\nКуда пойдем?"), Menu.EXIT_MENU, Menu.SHOP, Menu.WOOD, Menu.INFO);
        switch (Menu.values()[choice]) {
            case WOOD -> {
                System.out.println("Идем в лес");
                Battle.fight();
            }
            case SHOP -> {
                System.out.println("Идем в лавку");
                Merchant.bargain();
            }
            case INFO -> {
                System.out.print(Game.HERO.toString());
                startMainMenu();
            }
            case EXIT_MENU -> System.out.println("выходим из игры");
        }
    }

    public static void startLocationMenu(Consumer<Void> onContinue) {
        final int choice = buildMenu(Optional.of("\nЧто дальше?"), LocationMenu.BACK, LocationMenu.CONTINUE);
        switch (LocationMenu.values()[choice]) {
            case BACK -> {
                System.out.println("Идем обратно в город");
                startMainMenu();
            }
            case CONTINUE -> {
                System.out.println("Продолжаем");
                onContinue.accept(null);
            }
        }
    }

    public static <E extends Enum<E> & IMenu> int buildMenu(Optional<String> greeting, E... menus) throws IllegalStateException {
        final TreeSet<E> menu = new TreeSet<>(List.of(menus));
        greeting.ifPresent(System.out::println);
        menu.stream()
            .sorted((o1, o2) -> {
                if (o1.ordinal() == 0) {
                    return 1;
                }
                if (o2.ordinal() == 0) {
                    return -1;
                }
                return o1.ordinal() - o2.ordinal();
            })
            .forEach(item -> System.out.println(item.getMenuItemLabel()));
        CommandCLI.print("\n");

        final int menuSize = menu.size() - 1;
        final int choice = CommandCLI.readInt();

        if (choice > menuSize) {
            throw new IllegalStateException("Неверный пункт меню");
        }

        return choice;
    }

    enum LocationMenu implements IMenu {
        BACK("0. Вернуться в город"),
        CONTINUE("1. Продолжить"),
        ;

        private String menuItem;

        LocationMenu(String s) {
            menuItem = s;
        }

        @Override
        public String getMenuItemLabel() {
            return menuItem;
        }
    }

    enum Menu implements IMenu {
        EXIT_MENU("0. На выход"),
        SHOP("1. К торговцу"),
        WOOD("2. В тёмный лес"),
        INFO("3. Открыть инвентарь"),
        ;

        private String menuItem;

        Menu(String s) {
            menuItem = s;
        }

        @Override
        public String getMenuItemLabel() {
            return menuItem;
        }
    }

}
