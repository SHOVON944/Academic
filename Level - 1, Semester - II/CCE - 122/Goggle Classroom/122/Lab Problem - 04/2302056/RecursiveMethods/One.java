package RecursiveMethods;
import java.util.Scanner;

public class One {

    public static int factorialCall(int a){
        if(a==0) return 1;
        return a * factorialCall(a-1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int a = scan.nextInt();
        if(a<0){
             System.out.println("Please input positive value.");
        } else{
            int ans = factorialCall(a);
            System.out.println("The factorial of " + a + " is: " + ans);
        }
        scan.close();
    }
}
