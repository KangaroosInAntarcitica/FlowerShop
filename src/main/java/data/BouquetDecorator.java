package data;

import data.base.Price;
import data.flower.Flower;
import data.set.Bouquet;

import java.util.LinkedList;
import java.util.List;

public class BouquetDecorator extends Bouquet {
    private Bouquet previousBouquet;


    public BouquetDecorator(Bouquet previousBouquet, List<Flower> flowers) {
        super(flowers);
        this.previousBouquet = previousBouquet;
    }

    @Override
    public List<Flower> getFlowers() {
        List<Flower> allFlowers = new LinkedList<>();
        allFlowers.addAll(super.getFlowers());
        allFlowers.addAll(previousBouquet.getFlowers());

        return allFlowers;
    }

    @Override
    public int size() {
        return super.size() + previousBouquet.size();
    }

    @Override
    public Price getPrice() {
        Price price = new Price(super.getPrice().getValue());
        price = price.add(previousBouquet.getPrice());
        return price;
    }
}
