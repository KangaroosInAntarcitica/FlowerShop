package data.set;

import data.base.Price;
import data.flower.Flower;
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
            price = price.add(flower.getPrice());
        }

        return price;
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
