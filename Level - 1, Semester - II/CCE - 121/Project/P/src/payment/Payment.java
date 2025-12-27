package payment;

import cart.Cart;
import cart.CartItem;
import utils.ColorPrint;
import utils.Animation;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Payment {
    private Cart cart;
    private Scanner sc;
    private List<PaymentMethod> paymentMethods;

    public Payment(Cart cart) {
        this.cart = cart;
        this.sc = new Scanner(System.in);
        this.paymentMethods = new ArrayList<>();
        paymentMethods.add(new BkashPayment());
        paymentMethods.add(new RocketPayment());
        paymentMethods.add(new CardPayment());
        paymentMethods.add(new BankPayment());
    }

    public void makePayment() {
        cart.showCart();

        double total = 0;
        for (CartItem item : cart.getItems()) {
            total += item.getPrice();
        }

        // Random Gift System
        handleRandomGift();

        // Random VAT
        Random random = new Random();
        double vat = (random.nextInt(10) + 1) / 100.0; // VAT between 1% and 10%
        double totalWithVat = total + (total * vat);

        ColorPrint.printCyan("\nSubtotal: ৳" + String.format("%.2f", total));
        ColorPrint.printCyan("VAT (" + String.format("%.0f%%", vat * 100) + "): ৳" + String.format("%.2f", total * vat));
        ColorPrint.printCyan("Total Amount: ৳" + String.format("%.2f", totalWithVat));

        ColorPrint.printYellow("\nSelect Payment Method:");
        for (int i = 0; i < paymentMethods.size(); i++) {
            ColorPrint.printYellow((i + 1) + ". " + paymentMethods.get(i).getName());
        }

        try {
            System.out.print("\nEnter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice < 1 || choice > paymentMethods.size()) {
                ColorPrint.printRed("Invalid payment method. Defaulting to bKash.");
                choice = 1;
            }

            PaymentMethod selectedPaymentMethod = paymentMethods.get(choice - 1);

            ColorPrint.printCyan("\nProcessing Payment via " + selectedPaymentMethod.getName() + "...");
            Animation.spinner(2);

            selectedPaymentMethod.processPayment(totalWithVat);

        } catch (InputMismatchException e) {
            ColorPrint.printRed("Invalid input. Please enter a number.");
            sc.nextLine(); // clear the invalid input
        }
    }

    private void handleRandomGift() {
        int fastFoodItems = 0;
        int greenBazarItems = 0;
        int digitalMartItems = 0;

        for (CartItem item : cart.getItems()) {
            switch (item.getShopName()) {
                case "Fast Food":
                    fastFoodItems++;
                    break;
                case "Green Bazar":
                    greenBazarItems++;
                    break;
                case "Digital Mart":
                    digitalMartItems++;
                    break;
            }
        }

        Random random = new Random();
        if (fastFoodItems >= 2 && random.nextBoolean()) {
            ColorPrint.printGreen("🎁 Congratulations! You got a free Juice!");
        }
        if (greenBazarItems >= 3 && random.nextBoolean()) {
            ColorPrint.printGreen("🎁 Congratulations! You got a free Chocolate Bar!");
        }
        if (digitalMartItems >= 1 && random.nextBoolean()) {
            ColorPrint.printGreen("🎁 Congratulations! You got a free Headphone Case!");
        }
    }
}