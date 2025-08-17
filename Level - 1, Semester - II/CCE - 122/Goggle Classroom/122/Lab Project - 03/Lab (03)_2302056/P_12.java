package class_assignment;
import java.util.Scanner;

public class P_12 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter m : ");
        int m = scan.nextInt();
        System.out.print("Enter n : ");
        int n = scan.nextInt();
        
        int sum = 0;
        for(int i=m; i<=n; i++){
            if(i%2!=0) sum += i;
        }
        System.out.println("Sum of odd number between m to n is: " + sum);
        
    }
}
