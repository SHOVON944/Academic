package payment;

import utils.ColorPrint;

public class BkashPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        ColorPrint.printGreen("Payment of ৳" + String.format("%.2f", amount) + " successful via bKash!");
    }

    @Override
    public String getName() {
        return "bKash";
    }
}
