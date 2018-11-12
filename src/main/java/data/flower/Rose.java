package data.flower;

import data.base.Color;
import data.base.Country;
import data.base.Price;
import data.base.Scent;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Rose extends Flower {
    @Getter
    private boolean hasSpikes;

    private Rose() {}
    public Rose(Color color, float stemLength, Price price, boolean hasSpikes, Scent scent, Country country) {
        super(color, stemLength, price, scent, country);
        this.hasSpikes = hasSpikes;
    }

    public void setRandomAttributes() {
        super.setRandomAttributes();
        this.hasSpikes = Math.random() > 0.5;
    }

    public static Rose getRandom() {
        Rose rose = new Rose();
        rose.setRandomAttributes();
        return rose;
    }

    @Override
    public String toString() {
        String sup = super.toString();
        return String.format("%s%s)", sup.substring(0, sup.length() - 1), hasSpikes ? ", with spikes" : "");
    }
}
