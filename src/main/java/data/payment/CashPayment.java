package data.payment;

import data.cart.Cart;
import data.set.Bouquet;

public class CashPayment implements PaymentStrategy {
    @Override
    public void startPayment() {
        System.out.println("Start payment by cash");
    }

    @Override
    public void processPayment(Cart cart) {
        System.out.format("Shop assistant took %s from customer.\n", cart.getPrice());
    }

    @Override
    public void printTicket(Cart cart) {
        System.out.println("== == Receipt == ==");
        System.out.println("Flower count: " + cart.getBouquetList().size());
        for (Bouquet bouquet: cart.getBouquetList()) {
            System.out.println(bouquet);
        }
        System.out.println("Total price: " + cart.getPrice());
        System.out.println("== ==   End   == ==");

    }
}
