import data.*;
import data.flower.Chamomile;
import data.flower.Rose;
import data.flower.Tulip;
import data.payment.CardPayment;
import data.payment.CashPayment;
import data.payment.PaymentStrategy;
import data.set.Basket;
import data.set.Bouquet;

public class Main {
    private static Bouquet currentBouquet;
    private static Storage storage = Storage.getRandom();

    public static void main(String[] args) {
        ConsoleHelper consoleHelper = new ConsoleHelper(storage, listener);
        consoleHelper.startSellProcess();
    }

    private static ConsoleHelper.ConsoleListener listener = new ConsoleHelper.ConsoleListener() {
        @Override
        public void onBouquetSelected(Bouquet bouquet) {
            currentBouquet = bouquet;
        }

        @Override
        public boolean onPayment(int type) {
            startPaymentStep(type);
            return true;
        }

        @Override
        public void onError() {
            System.out.println("Sorry, application can't process your request");
        }
    };

    private static void startPaymentStep(int type) {
        PaymentStrategy strategy = null;
        if (type == 0) {
            strategy = new CashPayment();
        } else if (type == 1) {
            strategy = new CardPayment();
        }

        strategy.startPayment();
        strategy.processPayment(currentBouquet);
        strategy.printTicket(currentBouquet);
    }
}
