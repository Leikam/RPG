package RPG.utils;

import java.util.Random;

public class Randomizer {

    public static int CRITICAL_BOUND = 20;

    public static CriticalCheck isCriticalHit() {
        return isCriticalHit(CRITICAL_BOUND);
    }

    public static CriticalCheck isCriticalHit(int bound) {
        final int criticalDice = new Random().nextInt(CRITICAL_BOUND + 1);
        return new CriticalCheck(criticalDice >= bound, bound, criticalDice);
    }

    static public class CriticalCheck {
        public boolean success;
        public int bound;
        public int dice;

        public CriticalCheck(boolean success, int bound, int dice) {
            this.success = success;
            this.bound = bound;
            this.dice = dice;
        }
    }

}
