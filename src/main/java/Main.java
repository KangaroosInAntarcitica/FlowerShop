import data.ConsoleInterface;
import data.Storage;
import data.cart.Cart;
import data.payment.CardPayment;
import data.payment.CashPayment;
import data.payment.PaymentStrategy;

public class Main {
    private static Storage storage = Storage.getRandom();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new ConsoleInterface(storage, cart, listener);
        consoleInterface.startSellProcess();
    }

    private static ConsoleInterface.ConsoleInterfaceListener listener = new ConsoleInterface.ConsoleInterfaceListener() {
        @Override
        public boolean onPayment(PaymentStrategy strategy) {
            strategy.startPayment();
            strategy.processPayment(cart);
            strategy.printTicket(cart);
            return true;
        }

        @Override
        public PaymentStrategy[] getPaymentStrategies() {
            return new PaymentStrategy[]{new CardPayment(), new CashPayment()};
        }
    };


}
