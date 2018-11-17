package data;

import data.cart.Cart;
import data.payment.PaymentStrategy;
import data.set.Basket;
import data.set.Bouquet;
import data.set.FlowerList;

import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {
    public interface ConsoleInterfaceListener {
        PaymentStrategy[] getPaymentStrategies();
        boolean onPayment(PaymentStrategy strategy);
    }

    private Scanner scanner;
    private Storage storage;
    private Cart cart;
    private ConsoleInterfaceListener listener;

    public ConsoleInterface(Storage storage, Cart cart, ConsoleInterfaceListener listener) {
        scanner = new Scanner(System.in);
        this.storage = storage;
        this.cart = cart;
        this.listener = listener;
    }

    private int getInt(int min, int max) {
        Integer result = null;
        do {
            if (result != null && (result < min || result > max))
                System.out.format("Error. Number should be between %d and %d. Your: %d.\n", min, max, result);
            else if (result != null)
                break;
            result = getInt();
        } while(true);

        return result;
    }

    private int getInt() {
        while (!scanner.hasNextInt()){
            System.out.format("Error. Text \"%s\" is not an integer. Try again.\n", scanner.next());
        }
        return scanner.nextInt();
    }

    public void startSellProcess() {
        System.out.println("Hello. Welcome to our flower shop.");
        System.out.println("Do you want to buy some flowers?");

        int command;
        do {
            System.out.println("Please select command: \n\t1\tPrint baskets \n\t2\tPrint bouquets \n\t3\tView bouquet \n\t4\tCreate bouquet \n\t5\tAdd bouquet to cart \n\t6\tView Cart \n\t0\tExit");
            command = getInt(0, 6);

            if (command == 1) {
                printBasketsInfo(storage.getBaskets());
            }
            else if (command == 2) {
                printBouquetsInfo(storage.getBouquets());
            }
            else if (command == 3) {
                int bouquetIndex = chooseFromList(storage.getBouquets());
                if (bouquetIndex != 0) editBouquet(storage.getBouquets().get(bouquetIndex - 1));
            }
            else if (command == 4) {
                Bouquet bouquet = new Bouquet();
                storage.bouquets.add(bouquet);
                editBouquet(bouquet);
            }
            else if (command == 5) {
                int bouquetIndex = chooseFromList(storage.getBouquets());
                if (bouquetIndex != 0) addToCart(storage.getBouquets().get(bouquetIndex - 1));
            }
            else if (command == 6) {
                editCart();
            }
        } while (command != 0);
    }

    private <T extends FlowerList> int chooseFromList(List<T> list) {
        if (list.size() == 0) {
            System.out.format("Error: This %s is empty.", list.getClass().getSimpleName());
            return 0;
        }

        System.out.format("Please specify %s number (0 - quit):\n", list.get(0).getClass().getSimpleName());
        return getInt(0, list.size());
    }

    private void editBouquet(Bouquet bouquet) {
        BouquetModifier modifier = new BouquetModifier(bouquet);

        int command;
        do {
            boolean inCart = cart.getBouquetList().contains(bouquet);

            System.out.format("This is your bouquet: \n%s\n", bouquet.toFullString());
            System.out.format("Please select action: \n\t1\tAdd flowers \n\t2\t%s \n\t0\tExit\n",
                    inCart ? "Remove from cart" : "Add to cart");

            command = getInt(0, 2);

            if (command == 1) {
                System.out.println("Find a bucket, where to take flowers from.");
                int choice = chooseFromList(storage.getBaskets());

                if (choice != 0) {
                    Basket basket = storage.getBaskets().get(choice - 1);
                    System.out.println("Please specify the number of flowers to add:");
                    modifier.addFlowers(basket, getInt(0, basket.size()));
                }
            }
            if (command == 2) {
                if (inCart){
                    removeFromCart(bouquet);
                } else {
                    addToCart(bouquet);
                }
            }

        } while (command != 0);
    }

    private void editCart() {
        int command;
        do {
            System.out.format("This is your cart: \n%s\n", cart.toFullString());
            System.out.println("Please select action: \n\t1\tRemove bouquet \n\t2\tView bouquet \n\t3\tClear cart \n\t4\tProceed to payment \n\t0\tExit");

            command = getInt(0, 4);

            if (command == 1) {
                System.out.println("Choose the bouquet you want to remove.");
                int choice = chooseFromList(cart.getBouquetList());

                if (choice != 0) {
                    Bouquet bouquet = cart.getBouquetList().get(choice - 1);
                    if (cart.remove(bouquet))
                        storage.getBouquets().add(bouquet);
                }
            }
            if (command == 2) {
                int bouquetIndex = chooseFromList(cart.getBouquetList());
                if (bouquetIndex != 0) editBouquet(cart.getBouquetList().get(bouquetIndex - 1));
            }
            if (command == 3) {
                clearCart();
            }
            if (command == 4) {
                buy();
                command = 0;
            }
        } while (command != 0);
    }

    private boolean addToCart(Bouquet bouquet) {
        boolean result = cart.add(bouquet);
        storage.getBouquets().remove(bouquet);
        return result;
    }

    private boolean removeFromCart(Bouquet bouquet) {
        boolean result = cart.remove(bouquet);
        if (result)
            storage.getBouquets().add(bouquet);
        return result;
    }

    private boolean buy() {
        PaymentStrategy[] strategies = listener.getPaymentStrategies();

        System.out.println("Choose the way to pay: ");
        for (int i = 0; i < strategies.length; i++) {
            System.out.format("\t%d\t%s\n", i + 1, strategies[i].getClass().getSimpleName());
        }
        System.out.print("\t0\tExit\n");
        int paymentType = getInt(0, strategies.length);

        if (paymentType != 0) {
            boolean result = listener.onPayment(strategies[paymentType - 1]);
            if (result) deleteCart();
            return result;
        }

        return false;
    }

    private void deleteCart() {
        List<Bouquet> bouquetList = cart.getBouquetList();
        int size = bouquetList.size();
        for (int i = 0; i < size; i++) {
            cart.remove(bouquetList.get(0));
        }
    }

    private void clearCart() {
        List<Bouquet> bouquetList = cart.getBouquetList();
        int size = bouquetList.size();
        for (int i = 0; i < size; i++) {
            Bouquet bouquet = bouquetList.get(0);
            if (cart.remove(bouquet))
                storage.getBouquets().add(bouquet);
        }

    }

    private <T extends FlowerList> void printFlowerListInfo (List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%d. %s\n", i + 1, list.get(i).toString());
        }
    }

    private void printBasketsInfo(List<Basket> list) {
        System.out.println("All the baskets: ");
        printFlowerListInfo(list);
    }

    private void printBouquetsInfo(List<Bouquet> list) {
        System.out.println("All the bouquets: ");
        printFlowerListInfo(list);
    }
}
