package data.flower;

import data.base.Color;
import data.base.Country;
import data.base.Price;
import data.base.Scent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Flower {
    protected Color color;
    protected float stemLength;
    protected Price price;
    protected Scent scent;
    protected Country country;

    protected Flower(){};

    private static float minStemLength = 10;
    private static float maxStemLength = 30;
    private static int minPrice = 5_00;
    private static int maxPrice = 100_00;

    protected void setRandomAttributes() {
        this.color = Color.getRandom();
        this.stemLength = (float) (minStemLength + Math.random() * (maxStemLength - minStemLength));
        this.price = new Price((int) (minPrice + Math.pow(Math.random(), 2) * (maxPrice - minPrice)));
        this.scent = Scent.getRandom();
        this.country = Country.getRandom();
    }

    public String toString() {
        return String.format("%s (%s, %.2fcm, %s, smells %s, from %s)",
                getClass().getSimpleName(), color, stemLength, price, scent, country);
    }
}
