package data.cart;

import data.base.Price;

public class DiscountCart extends Cart {
    private Cart cart;
    private double discount;

    public DiscountCart(Cart cart, double discount) {
        this.cart = cart;

        if (discount < 0 || discount > 1) {
            throw new Error("Wrong discount value");
        }
        this.discount = discount;
    }

    public String toString() {
        String sup = super.toString();
        return String.format("%s, discount: %.1f%%]",
               sup.substring(0, sup.length() - 1), discount * 100);
    }

    public Price getPrice() {
        return cart.getPrice().multiply(discount);
    }
}
