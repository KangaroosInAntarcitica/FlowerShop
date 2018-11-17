package data.payment;

import data.cart.Cart;
import data.set.Bouquet;

public class CardPayment implements PaymentStrategy {
    @Override
    public void startPayment() {
        System.out.println("Start payment by card");
    }

    @Override
    public void processPayment(Cart cart) {
        System.out.println("Connecting to payment server...");
        System.out.format("Received %s from customer.\n", cart.getPrice());
    }

    @Override
    public void printTicket(Cart cart) {
        System.out.println("Your online receipt: ");
        System.out.println("Bouquet count: " + cart.getBouquetList().size());
        for (Bouquet bouquet: cart.getBouquetList()) {
            System.out.println(bouquet);
        }
        System.out.println("Total price: " + cart.getPrice());
    }
}
