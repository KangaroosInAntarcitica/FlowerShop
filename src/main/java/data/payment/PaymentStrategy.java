package data.payment;

import data.cart.Cart;

public interface PaymentStrategy {
    void startPayment();
    void processPayment(Cart cart);
    void printTicket(Cart cart);
}
