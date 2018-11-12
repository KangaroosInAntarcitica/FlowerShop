package data.payment;

import data.flower.Flower;
import data.set.FlowerList;

import java.util.List;

public class CardPayment implements PaymentStrategy {
    @Override
    public void startPayment() {
        System.out.println("Start payment by card");
    }

    @Override
    public void processPayment(FlowerList flowers) {
        System.out.println("Connecting to payment server...");
        System.out.format("Received %s from customer.\n", flowers.getPrice());
    }

    @Override
    public void printTicket(FlowerList flowers) {
        System.out.println("Your online receipt: ");
        System.out.println("Flower count: " + flowers.size());
        for (Flower flower: flowers.getFlowers()) {
            System.out.printf("%s: %s\n", flower.getClass().getSimpleName(), flower.getPrice());
        }
        System.out.println("Total price: " + flowers.getPrice());
    }
}
