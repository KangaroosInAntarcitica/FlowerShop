package data.base;

public enum Country {
    UKRAINE("Ukraine"),
    ENGLAND("England"),
    CHINA("China");

    private String name;

    private Country(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public static Country getRandom() {
        switch((int) (Math.random() * 3)) {
            case 0: return UKRAINE;
            case 1: return ENGLAND;
            case 2: return CHINA;
        }
        return null;
    }
}
