package ConditionalStatement;
import java.util.Scanner;

public class Thirty{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        double a = scan.nextDouble();
        System.out.print("Enter the 2nd number: ");
        double b = scan.nextDouble();
        System.out.print("Enter the 3rd number: ");
        double c = scan.nextDouble();

        if((a==b)  &&  (b==c)) System.out.println("All number are equal.");
        else System.out.println("All number are different.");

        scan.close();
    }
}
