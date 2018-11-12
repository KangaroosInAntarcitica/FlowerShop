package data.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Color {
    RED("red"),
    GREEN("green"),
    BLUE("blue"),
    BLACK("black"),
    WHITE("white");
    private String name;

    public String toString() {
        return name;
    }

    public static Color getRandom() {
        switch ((int) (Math.random() * 5)) {
            case 0: return Color.RED;
            case 1: return Color.GREEN;
            case 2: return Color.BLUE;
            case 3: return Color.BLACK;
            case 4: return Color.WHITE;
        }

        return null;
    }
}
