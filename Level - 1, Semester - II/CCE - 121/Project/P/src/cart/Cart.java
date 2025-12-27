package cart;

import utils.ColorPrint;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(CartItem item) {
        items.add(item);
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void clearCart() {
        items.clear();
    }

    public void showCart() {
        Scanner sc = new Scanner(System.in);
        boolean modifying = true;

        while (modifying) {
            ColorPrint.printCyan("\n===== Cart Items =====");
            for (int i = 0; i < items.size(); i++) {
                CartItem item = items.get(i);
                System.out.printf("%2d. %-30s %s ৳%.2f\n", i + 1, item.getName(), item.getShopName(), item.getPrice());
            }

            System.out.print("\nDo you want to remove any item? (y/n): ");
            String rem = sc.nextLine();
            if (rem.equalsIgnoreCase("y")) {
                System.out.print("Enter item number to remove: ");
                int idx = sc.nextInt();
                sc.nextLine();
                if (idx >= 1 && idx <= items.size()) {
                    ColorPrint.printRed(items.get(idx - 1).getName() + " removed!");
                    items.remove(idx - 1);
                } else {
                    ColorPrint.printRed("Invalid item number!");
                }
            } else {
                modifying = false;
            }
        }
    }
}
