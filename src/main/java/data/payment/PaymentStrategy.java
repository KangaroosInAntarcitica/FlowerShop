package data.payment;

import data.flower.Flower;
import data.set.FlowerList;

import java.util.List;

public interface PaymentStrategy {
    void startPayment();
    void processPayment(FlowerList flowers);
    void printTicket(FlowerList flowers);
}
