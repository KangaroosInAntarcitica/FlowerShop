package data;

import data.flower.Flower;
import data.set.Basket;
import data.set.Bouquet;

import java.util.List;

public class BouquetModifier {
    private static Bouquet bouquet;

    public BouquetModifier(Bouquet bouquet) {
        this.bouquet = bouquet;
    }

    public void addFlowers(Basket basket, int number) {
        List<Flower> flowers = basket.getFlowers();

        if (flowers.size() < number)
            throw new IndexOutOfBoundsException(String.format("There aren't enough flowers in cart (%d)", flowers.size()));

        for (int i = 0; i < number; i++) {
            Flower flower = basket.getFlowers().get(0);
            basket.remove(flower);
            bouquet.add(flower);
        }
    }
}
