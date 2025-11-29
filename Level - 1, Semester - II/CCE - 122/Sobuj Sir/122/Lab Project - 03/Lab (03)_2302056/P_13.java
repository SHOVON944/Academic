package class_assignment;
import java.util.Scanner;

public class P_13 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scan.nextInt();
        int fib[] = new int[n];
        
        fib[0] = 0;
        fib[1] = 1;
        
        for (int i = 2; i < n; i++){
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        
        for (int i = 0; i < n; i++) {
            System.out.print(fib[i] + " ");
        }
    }
}
