package RPG.utils;

import RPG.characters.ACharacter;
import RPG.characters.Hero;

public class LoggerUtils {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void logPositive(String info, Object... args) {
        System.out.printf((ANSI_BLUE + info + ANSI_RESET), args);
    }

    public static void logNegative(String error, Object... args) {
        System.out.printf((ANSI_RED + error + ANSI_RESET), args);
    }

    public static void logDistinct(boolean test, String message, Object... args) {
        if (test) {
            logPositive(message, args);
        } else {
            logNegative(message, args);
        }
    }

    public static void logDistinctByActor(ACharacter character, String message, Object... args) {
        logDistinct(character instanceof Hero, message, args);
    }
}