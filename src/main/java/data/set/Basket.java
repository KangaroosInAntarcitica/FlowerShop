package data.set;

import data.base.Color;
import data.flower.Chamomile;
import data.flower.Flower;
import data.flower.Rose;
import data.flower.Tulip;

import java.util.LinkedList;
import java.util.List;

// Store flowers of the same type
public class Basket extends FlowerList {

    public Basket() { super(); }
    public Basket(List<Flower> flowers) { super(flowers); }

    public boolean add(Flower flower) {
        if (size() > 0 && !flowers.get(0).getClass().equals(flower.getClass()))
            return false;
        return super.add(flower);
    }

    private static int minSize = 10;
    private static int maxSize = 40;
    public static Basket getRandom() {
        int flowerType = (int) (Math.random() * 3);
        int size = (int) (minSize + Math.random() * (maxSize - minSize));

        Basket basket = new Basket();
        for (int i = 0; i < size; i++) {
            basket.add(getRandomFlower(flowerType));
        }

        return basket;
    }

    @Override
    public String toString() {
        return String.format("Basket [flowers: %s, size: %d, price: %s]",
                size() > 0 ? flowers.get(0).getClass().getSimpleName() : "nothing", size(), getPrice());
    }
}
