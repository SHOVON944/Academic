package mainapp;

import cart.Cart;
import FastFood.FastFoodShop;
import GreenBazar.GreenBazarShop;
import DigitalMart.DigitalShop;
import payment.Payment;
import utils.ColorPrint;
import utils.Animation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cart cart = new Cart();
        boolean running = true;

        Animation.printWithAnimation("============================== WELCOME TO DIGITAL MART ==============================", 10);

        while (running) {
            ColorPrint.printCyan("\n==============================\n");
            ColorPrint.printYellow("1. Fast Food Shop 🍔");
            ColorPrint.printYellow("2. Green Bazar 🥦");
            ColorPrint.printYellow("3. Digital Mart 💻");
            ColorPrint.printCyan("\n------------------------------");
            ColorPrint.printRed("0. Exit Digital Mart");
            System.out.print("\nSelect your shop: ");

            try {
                int shopChoice = sc.nextInt();
                sc.nextLine();

                switch (shopChoice) {
                    case 1:
                        new FastFoodShop(cart).showMenu();
                        break;
                    case 2:
                        new GreenBazarShop(cart).showMenu();
                        break;
                    case 3:
                        new DigitalShop(cart).showMenu();
                        break;
                    case 0:
                        running = false;
                        continue; // Skip checkout prompt on exit
                    default:
                        ColorPrint.printRed("Invalid choice! Please try again.");
                }

                // Ask if want to checkout only if there are items in the cart
                if (!cart.getItems().isEmpty()) {
                    System.out.print("\nDo you want to proceed to checkout? (y/n): ");
                    String checkout = sc.nextLine();
                    if (checkout.equalsIgnoreCase("y")) {
                        Payment payment = new Payment(cart);
                        payment.makePayment();
                        cart.clearCart();
                        System.out.print("\nDo you want to continue shopping? (y/n): ");
                        String continueShopping = sc.nextLine();
                        if (!continueShopping.equalsIgnoreCase("y")) {
                            running = false;
                        }
                    }
                }
            } catch (InputMismatchException e) {
                ColorPrint.printRed("Invalid input. Please enter a number.");
                sc.nextLine(); // clear the invalid input
            }
        }

        ColorPrint.printGreen("\nThank you for shopping at Digital Mart 💚");
    }
}
