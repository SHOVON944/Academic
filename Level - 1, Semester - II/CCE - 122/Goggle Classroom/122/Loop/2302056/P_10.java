import java.util.Scanner;

public class P_10 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the amount: ");
        float amount = scan.nextFloat();
        float discount = 0;
        float finalAmount;

        if (amount > 5000) {
            discount = 0.20f * amount;
        } else if (amount > 1000) {
            discount = 0.10f * amount;
        }

        finalAmount = amount - discount;

        System.out.println("Discount: " + discount);
        System.out.println("Amount to pay after discount: " + finalAmount);
    }
}
