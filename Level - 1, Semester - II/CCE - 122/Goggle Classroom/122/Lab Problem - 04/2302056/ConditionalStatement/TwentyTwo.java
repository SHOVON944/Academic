package ConditionalStatement;
import java.util.Scanner;

public class TwentyTwo {
    
    public static int factorial(int x) {
        int fact = 1;
        for (int i = 2; i <= x; i++) {
            fact = fact * i;
        }
        return fact;
    }
    
    public static int mathformula(int n, int r) {
        int nc = factorial(n) / (factorial(r) * factorial(n - r));
        return nc;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        int a = n;
        
        for (int i = 0; i <= n; i++) {
            for (int k = 1; k <= a; k++) {
                System.out.print(" ");
            }
            a--;

            for (int j = 0; j <= i; j++) {
                int ncr = mathformula(i, j);
                System.out.print(ncr + " ");
            }
            System.out.println();
        }
        
        scanner.close();
    }
}