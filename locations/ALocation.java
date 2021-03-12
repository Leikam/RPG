package RPG.locations;

public abstract class ALocation {

    public abstract Enum<Locations> getLocationType();

    public void enter() {}

    public void exit() {}

    public enum Locations {
        SHOP,
        WOOD
    }

}
