package data.flower;

import data.base.Color;
import data.base.Country;
import data.base.Price;
import data.base.Scent;
import lombok.AllArgsConstructor;

public class Tulip extends Flower {
    private Tulip(){}
    public Tulip(Color color, float stemLength, Price price, Scent scent, Country country) {
        super(color, stemLength, price, scent, country);
    }

    public static Tulip getRandom() {
        Tulip tulip = new Tulip();
        tulip.setRandomAttributes();
        return tulip;
    }

}
