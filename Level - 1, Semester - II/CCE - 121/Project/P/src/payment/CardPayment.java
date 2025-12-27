package payment;

import utils.ColorPrint;

public class CardPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        ColorPrint.printGreen("Payment of ৳" + String.format("%.2f", amount) + " successful via Card!");
    }

    @Override
    public String getName() {
        return "Card";
    }
}
