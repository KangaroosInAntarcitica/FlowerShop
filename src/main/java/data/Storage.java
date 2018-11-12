package data;

import data.set.Basket;
import data.set.Bouquet;

import java.util.LinkedList;
import java.util.List;

public class Storage {
    List<Basket> baskets;
    List<Bouquet> bouquets;

    public Storage() {
        baskets = new LinkedList<>();
        bouquets = new LinkedList<>();
    }

    public static Storage getRandom() {
        int basketsCount = (int) (1 + Math.random() * 10);
        int bouquetsCount = (int) (1 + Math.random() * 10);

        Storage storage = new Storage();
        for (int i = 0; i < basketsCount; i++) {
            storage.baskets.add(Basket.getRandom());
        }
        for (int i = 0; i < bouquetsCount; i++) {
            storage.bouquets.add(Bouquet.getRandom());
        }

        return storage;
    }

    public List<Basket> getBaskets() {
        return this.baskets;
    }

    public List<Bouquet> getBouquets() {
        return this.bouquets;
    }


    @Override
    public String toString() {
        return String.format("Storage {baskets: %s, bouquets: %s}", baskets, bouquets);
    }

    public String toFullString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Storage [basketsSize: %d, bouquetsSize: %d]", baskets.size(), bouquets.size()));

        builder.append("{\n");
        for (Basket basket: baskets) {
            builder.append(basket);
            builder.append("\n");
        }
        for (Bouquet bouquet: bouquets) {
            builder.append(bouquet);
            builder.append("\n");
        }
        builder.append("}");

        return builder.toString();
    }
}
