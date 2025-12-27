package payment;

import utils.Delay;

public class PaymentSpinner {

    public static void showSpinner() {
        String[] spinner = {"|", "/", "-", "\\"};
        for (int i = 0; i < 10; i++) {
            System.out.print("\r" + spinner[i % spinner.length] + " Processing...");
            Delay.waitMillis(200);
        }
        System.out.println("\rDone!            ");
    }
}
