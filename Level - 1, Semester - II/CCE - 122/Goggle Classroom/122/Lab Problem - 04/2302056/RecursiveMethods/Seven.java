package RecursiveMethods;
import java.util.Scanner;

public class Seven {

    public static int gcdCall(int a, int b){
        if(b==0) return a;
        return gcdCall(b, a%b);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number 1: ");
        int a = scan.nextInt();
        System.out.print("Enter the number 2: ");
        int b = scan.nextInt();
        int ans = gcdCall(a, b);
        System.out.println("The GCD of " + a + " and " + b + " is: " + ans);

        scan.close();
    }
}
