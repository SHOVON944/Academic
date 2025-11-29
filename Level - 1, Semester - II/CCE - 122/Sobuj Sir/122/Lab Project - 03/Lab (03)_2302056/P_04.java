package class_assignment;
import java.util.Scanner;

public class P_04 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter installment amoutnt : ");
        int installment = scan.nextInt();
        System.out.print("Enter year : ");
        int year = scan.nextInt();
        
        double amount = installment/year;
        System.out.println("Amount is : " + amount);
    }
}
