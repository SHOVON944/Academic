package payment;

import utils.ColorPrint;

public class BankPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        ColorPrint.printGreen("Payment of ৳" + String.format("%.2f", amount) + " successful via Bank Transfer!");
    }

    @Override
    public String getName() {
        return "Bank Transfer";
    }
}
