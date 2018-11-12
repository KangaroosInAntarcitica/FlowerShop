package data;

import data.flower.Flower;
import data.set.Basket;
import data.set.Bouquet;
import data.set.FlowerList;

import java.util.List;
import java.util.Scanner;

public class ConsoleHelper {
    public interface ConsoleListener {
        void onBouquetSelected(Bouquet bouquet);
        boolean onPayment(int type);
        void onError();
    }

    private Scanner scanner;
    private Storage storage;
    private ConsoleListener listener;

    public ConsoleHelper(Storage storage, ConsoleListener listener) {
        scanner = new Scanner(System.in);
        this.storage = storage;
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
            System.out.println("Please select command: \n\t1\tPrint baskets \n\t2\tPrint bouquets \n\t3\tView bouquet \n\t4\tCreate bouquet \n\t5\tBuy bouquet \n\t0\tExit");
            command = getInt(0, 5);

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
                if (bouquetIndex != 0) buyBouquet(storage.getBouquets().get(bouquetIndex - 1));
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

        int command = 0;
        do {
            System.out.format("This is your bouquet: \n%s\n", bouquet.toFullString());
            System.out.println("Please select action: \n\t1\tAdd flowers \n\t2\tBuy bouquet \n\t0\tExit");

            command = getInt();

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
                if (buyBouquet(bouquet)) return;
            }

        } while (command != 0);
    }

    private boolean buyBouquet(Bouquet bouquet) {
        System.out.println("Choose the way to pay: \n\t1\tCash \n\t2\tCard \n\t0\tExit");
        int paymentType = getInt(0, 2);

        if (paymentType != 0) {
            listener.onBouquetSelected(bouquet);
             boolean result = listener.onPayment(paymentType - 1);
             if (result) {
                 storage.bouquets.remove(bouquet);
             }
             return result;
        }

        return false;
    }

    private <T extends FlowerList> void printFlowerListInfo (List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.format("%d. %s\n", i + 1, list.get(i).toFullString());
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
