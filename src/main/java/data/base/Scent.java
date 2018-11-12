package data.base;

public enum Scent {
    PLEASANT("pleasant"),
    UNPLEASANT("unpleasant"),
    SWEET("sweet"),
    AWFUL("awful"),
    NONE("none");

    private String description;

    private Scent(String description) {
        this.description = description;
    }

    public String toString() {
        return this.description;
    }

    public static Scent getRandom() {
        switch((int) (Math.random() * 5)) {
            case 0: return PLEASANT;
            case 1: return UNPLEASANT;
            case 2: return SWEET;
            case 3: return AWFUL;
            case 4: return NONE;
        }
        return null;
    }
}
