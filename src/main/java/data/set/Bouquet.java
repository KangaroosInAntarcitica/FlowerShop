package data.set;

import data.base.Color;
import data.flower.Chamomile;
import data.flower.Flower;
import data.flower.Rose;
import data.flower.Tulip;

import java.util.LinkedList;
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
            bouquet.add(getRandomFlower());
        }

        return bouquet;
    }

    public String toString() {
        return String.format("Bouquet [size: %d, price: %s]", this.size(), getPrice());
    }
}
