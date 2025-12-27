/*
29. Online Food Delivery System 
 placeOrder(String foodItem, int quantity) throws: 
o OutOfStockException if the item is unavailable. 
o InvalidQuantityException if quantity ≤ 0. 
 Simulate multiple restaurants, each with its own menu.
*/


import java.util.Scanner;

class OutOfStockException extends Exception {
    OutOfStockException(String message) {
        super(message);
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}

class Restaurant {
    String name;
    int pizzaStock, burgerStock;

    Restaurant(String name, int pizzaStock, int burgerStock) {
        this.name = name;
        this.pizzaStock = pizzaStock;
        this.burgerStock = burgerStock;
    }

    public void placeOrder(String foodItem, int quantity)
            throws OutOfStockException, InvalidQuantityException {

        if (quantity <= 0)
            throw new InvalidQuantityException("Quantity must be > 0.");

        if (foodItem.equalsIgnoreCase("Pizza")) {
            if (quantity > pizzaStock)
                throw new OutOfStockException("Only " + pizzaStock + " Pizza(s) available.");
            pizzaStock -= quantity;
        } else if (foodItem.equalsIgnoreCase("Burger")) {
            if (quantity > burgerStock)
                throw new OutOfStockException("Only " + burgerStock + " Burger(s) available.");
            burgerStock -= quantity;
        }

        else {
            throw new OutOfStockException(foodItem + " is not available.");
        }
    }

    public void showMenu() {
        System.out.println("Menu of " + name + ":");
        System.out.println("Pizza - " + pizzaStock + " available");
        System.out.println("Burger - " + burgerStock + " available");
        System.out.println();
    }
}

public class P_29 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // exam e user theke nibo quantity,,, r Resturent er name barai dibo...
        Restaurant r1 = new Restaurant("Pizza Palace", 5, 3);
        Restaurant r2 = new Restaurant("Burger Hub", 4, 6);

        while (true) {
            System.out.println("\n--- Available Restaurants ---");
            r1.showMenu();
            r2.showMenu();

            System.out.print("Enter restaurant name (or type 'exit' to quit): ");
            String resName = scan.nextLine();
            if (resName.equalsIgnoreCase("exit"))
                break;

            System.out.print("Enter food item: ");
            String foodItem = scan.nextLine();

            System.out.print("Enter quantity: ");
            int quantity;
            try {
                quantity = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
                continue;
            }

            try {
                if (resName.equalsIgnoreCase("Pizza Palace")) {
                    r1.placeOrder(foodItem, quantity);
                    System.out.println("Order placed successfully at Pizza Palace!");
                } else if (resName.equalsIgnoreCase("Burger Hub")) {
                    r2.placeOrder(foodItem, quantity);
                    System.out.println("Order placed successfully at Burger Hub!");
                } else {
                    System.out.println("Restaurant not found. Try again.");
                }
            } catch (InvalidQuantityException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (OutOfStockException e) {
                System.out.println("Order failed: " + e.getMessage());
            }
        }

        System.out.println("Thank you for using Online Food Delivery App!");
        scan.close();
    }
}
