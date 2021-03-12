package RPG.utils;

import java.util.Scanner;

public class CommandCLI {

    public static final Scanner SCANNER = new Scanner(System.in);

    public static String readLine() {
        return SCANNER.nextLine().trim();
    }

    public static int readInt() {
        return SCANNER.nextInt();
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static String printBooleanVerbal(boolean isCritical) {
        return isCritical ? "успех!" : "неудача!";
    }
}
