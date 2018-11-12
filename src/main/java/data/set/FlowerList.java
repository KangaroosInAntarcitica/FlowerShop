package data.set;

import data.base.Price;
import data.flower.Chamomile;
import data.flower.Flower;
import data.flower.Rose;
import data.flower.Tulip;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@NoArgsConstructor
public abstract class FlowerList {
    protected List<Flower> flowers = new LinkedList<>();

    public FlowerList(List<Flower> flowers) {
        for (Flower flower: flowers) {
            add(flower);
        }
    }

    public boolean add(Flower flower) {
        if (contains(flower)) return false;
        return flowers.add(flower);
    }

    public boolean contains(Flower flower) {
        return flowers.contains(flower);
    }

    public boolean remove(Flower flower) {
        return flowers.remove(flower);
    }

    public List<Flower> getFlowers() {
        return Collections.unmodifiableList(flowers);
    }

    public int size() {
        return flowers.size();
    }

    public Price getPrice() {
        Price price = new Price();
        for (Flower flower : flowers) {
            price.add(flower.getPrice());
        }

        return price;
    }

    protected static Flower getRandomFlower(int flowerType) {
        switch(flowerType) {
            case 0: return Tulip.getRandom();
            case 1: return Rose.getRandom();
            case 2: return Chamomile.getRandom();
        }
        return null;
    }
    protected static Flower getRandomFlower() {
        return getRandomFlower((int) (Math.random() * 3));
    }

    public String toString() {
        return String.format("FlowerList [size: %d, price: %s]", size(), getPrice());
    }

    public String toFullString() {
        StringBuilder builder = new StringBuilder();
        builder.append(toString());
        builder.append("\n{\n");

        for (int i = 0; i < size(); i++) {
            builder.append("\t");
            builder.append(flowers.get(i));
            builder.append("\n");
        }

        builder.append("}");
        return builder.toString();
    }
}
