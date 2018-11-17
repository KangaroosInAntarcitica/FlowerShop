package data.cart;

import data.base.Price;
import data.set.Bouquet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<Bouquet> bouquetList;

    public Cart() {
        this.bouquetList = new ArrayList<>();
    }

    public boolean add(Bouquet bouquet) {
        if (!bouquetList.contains(bouquet))
            return bouquetList.add(bouquet);
        return false;
    }

    public boolean remove(Bouquet bouquet) {
        return bouquetList.remove(bouquet);
    }

    public List<Bouquet> getBouquetList() {
        return Collections.unmodifiableList(bouquetList);
    }

    public Price getPrice() {
        Price price = new Price();

        for (Bouquet bouquet: bouquetList) {
            price = price.add(bouquet.getPrice());
        }

        return price;
    }

    public String toString() {
        return String.format("Cart [bouquetsSize: %d, price: %s]",
                bouquetList.size(), getPrice());
    }

    public String toFullString() {
        StringBuilder builder = new StringBuilder();
        builder.append(toString());

        builder.append("\n{\n");
        for (Bouquet bouquet: bouquetList) {
            builder.append("\t");
            builder.append(bouquet);
            builder.append("\n");
        }
        builder.append("}");

        return builder.toString();
    }
}
