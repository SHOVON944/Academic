package ConditionalStatement;
import java.util.Scanner;

public class ThirtyOne{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        double a = scan.nextDouble();
        System.out.print("Enter the 2nd number: ");
        double b = scan.nextDouble();
        System.out.print("Enter the 3rd number: ");
        double c = scan.nextDouble();

        if((a<b)  &&  (b<c)) System.out.println("Increasing order.");
        else if((a>b)  &&  (b>c)) System.out.println("Decreasing order.");
        else System.out.println("Neither all are equal or different.");

        scan.close();
    }
}
