package data.set;

import data.flower.Flower;
import data.flower.FlowerFactory;

import java.util.List;

public class Bouquet extends FlowerList {

    public Bouquet() { super(); }
    public Bouquet(List<Flower> initialFlowers) { super(initialFlowers); }

    private static int minSize = 4;
    private static int maxSize = 20;
    public static Bouquet getRandom() {
        int size = (int) (minSize + Math.random() * (maxSize - minSize));

        Bouquet bouquet = new Bouquet();
        for (int i = 0; i < size; i++) {
            bouquet.add(FlowerFactory.getRandom());
        }

        return bouquet;
    }

    public String toString() {
        return String.format("Bouquet [size: %d, price: %s]", this.size(), getPrice());
    }
}
