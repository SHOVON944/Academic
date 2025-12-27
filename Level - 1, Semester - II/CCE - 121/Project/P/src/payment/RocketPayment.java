package payment;

import utils.ColorPrint;

public class RocketPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        ColorPrint.printGreen("Payment of ৳" + String.format("%.2f", amount) + " successful via Rocket!");
    }

    @Override
    public String getName() {
        return "Rocket";
    }
}
