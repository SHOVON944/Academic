package mainapp;

import cart.Cart;
import cart.CartItem;
import cart.Item;
import utils.ColorPrint;
import utils.Delay;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public abstract class Shop<T extends Item> {
    protected Cart cart;
    protected Scanner sc;

    public Shop(Cart cart) {
        this.cart = cart;
        this.sc = new Scanner(System.in);
    }

    protected abstract String getShopName();
    protected abstract List<T> getMenuItems();
    protected abstract String getShopColor();

    public void showMenu() {
        List<T> items = getMenuItems();
        boolean shopping = true;

        while (shopping) {
            printMenuHeader();

            for (int i = 0; i < items.size(); i++) {
                Item item = items.get(i);
                System.out.printf("%2d. %-30s ৳%-5.2f\n", i + 1, item.getName(), item.getPrice());
                if ((i + 1) % 4 == 0) System.out.println();
            }

            System.out.print("\nEnter item number to add to cart (0 to exit shop): ");
            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 0) {
                    shopping = false;
                    continue;
                }

                if (choice < 1 || choice > items.size()) {
                    ColorPrint.printRed("Invalid choice! Please try again.");
                } else {
                    T selected = items.get(choice - 1);
                    cart.addItem(new CartItem(selected.getName(), selected.getPrice(), getShopName()));
                    ColorPrint.printGreen(selected.getName() + " added to cart!");
                    Delay.waitMillis(500);
                }
            } catch (InputMismatchException e) {
                ColorPrint.printRed("Invalid input. Please enter a number.");
                sc.nextLine(); // clear the invalid input
            }
        }
    }

    private void printMenuHeader() {
        String color = getShopColor();
        String shopName = getShopName();
        String header = "\n=== " + shopName + " Menu ===";

        switch (color) {
            case "blue":
                ColorPrint.printBlue(header);
                break;
            case "yellow":
                ColorPrint.printYellow(header);
                break;
            case "cyan":
                ColorPrint.printCyan(header);
                break;
            case "green":
                ColorPrint.printGreen(header);
                break;
            default:
                System.out.println(header);
                break;
        }
    }
}
