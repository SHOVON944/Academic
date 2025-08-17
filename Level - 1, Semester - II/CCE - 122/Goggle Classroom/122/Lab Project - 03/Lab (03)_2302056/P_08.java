package class_assignment;
import java.util.Scanner;

public class P_08 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a : ");
        int a = scan.nextInt();
        System.out.print("Enter b : ");
        int b = scan.nextInt();
        System.out.print("Enter c : ");
        int c = scan.nextInt();
        
        if(a>b && a>c ) System.out.println("a is greater.");
        else if(b>a && b>c ) System.out.println("b is greater.");
        else System.out.println("c is greater.");
    }
}
