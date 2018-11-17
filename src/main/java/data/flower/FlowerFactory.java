package data.flower;

public class FlowerFactory {
    private FlowerFactory(){};

    public static int getRandomFlowerType() {
        return (int) (Math.random() * 3);
    }

    public static Flower getRandom() {
        return getRandom(getRandomFlowerType());
    }

    public static Flower getRandom(int flowerType) {
        switch(flowerType) {
            case 0: return Tulip.getRandom();
            case 1: return Rose.getRandom();
            case 2: return Chamomile.getRandom();
        }
        return null;
    }
}
