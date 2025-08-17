package ConditionalStatement;
import java.util.Scanner;

public class One {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int a = scan.nextInt();
        if(a>=0) System.out.println("The number is positive.");
        else System.out.println("The number is negative.");

        scan.close();
    }
}
