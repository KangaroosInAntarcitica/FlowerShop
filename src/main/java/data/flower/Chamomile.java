package data.flower;

import data.base.Color;
import data.base.Country;
import data.base.Price;
import data.base.Scent;


public class Chamomile extends Flower {
    private Chamomile(){};
    public Chamomile(Color color, float stemLength, Price price, Scent scent, Country country) {
        super(color, stemLength, price, scent, country);
    }

    public static Chamomile getRandom() {
        Chamomile chamomile = new Chamomile();
        chamomile.setRandomAttributes();
        return chamomile;
    }
}