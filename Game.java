package RPG;

import RPG.characters.Hero;

public class Game {

    public static Hero HERO = null;
    public static Controller CONTROLLER = null;

    public static void main(String[] args) {
        CONTROLLER = new Controller();
        HERO = new Hero("Паладин Газебо");

        CONTROLLER.startMainMenu();
    }

}
