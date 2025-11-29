package ConditionalStatement;
import java.util.Scanner;

public class ThirtyTwo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        double a = scan.nextDouble();
        System.out.print("Enter the 2nd number: ");
        double b = scan.nextDouble();

        a = Math.round(a*100) / 100.0;
        b = Math.round(b*100) / 100.0;

        if(a==b) System.out.println("They are equal.");
        else System.out.println("They are differen");

        scan.close();
    }
}
