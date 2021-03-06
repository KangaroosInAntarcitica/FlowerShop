package data.base;

import lombok.Getter;
import lombok.Setter;

public class Price {
    @Getter
    @Setter
    private int value;
    @Getter
    private Currency currency;

    public Price(int value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Price(int value) {
        this(value, Currency.USD);
    }

    public Price() {
        this(0);
    }

    public Price add(Price price) {
        if (!currency.equals(price.currency))
            throw new Error("Cannot add price of different currencies");

        return new Price(value + price.value, currency);
    }

    public Price multiply(double value) {
        return new Price((int) (this.value *= value), currency);
    }

    public String toString() {
        return String.format("%.2f%s", (float) value / 100, currency);
    }

    public enum Currency {
        USD("$"),
        EURO("€"),
        UAH("UAH");

        private String name;

        private Currency(String name) {
            this.name = name;
        }

        public String toString() {
            return this.name;
        }
    }
}
