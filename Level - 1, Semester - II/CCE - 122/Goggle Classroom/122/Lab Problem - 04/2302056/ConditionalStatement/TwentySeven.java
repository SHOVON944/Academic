package ConditionalStatement;

import java.util.Scanner;

public class TwentySeven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the number: ");
        int a = scan.nextInt();
        if(a>0) System.out.println("Positive number.");
        else if(a==0) System.out.println("Zero");
        else System.out.println("Negative number.");

        scan.close();
    }
}
