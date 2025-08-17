package class_assignment;
import java.util.Scanner;

public class P_11{
    
    public static void factorial(int n){
        int fact = 1;
        for(int i=1; i<=n; i++)fact *= i;
        
        System.out.println("The factorial is: " + fact);
}
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter n : ");
        int n = scan.nextInt();
        factorial(n);
    }
}
