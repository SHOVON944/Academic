package RecursiveMethods;
import java.util.Scanner;

public class Five {

    public static int poweCall(int b, int expnt){
        if(expnt==0) return 1;
        return b * poweCall(b, expnt-1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the base: ");
        int b = scan.nextInt();
        System.out.print("Enter the exponent: ");
        int e = scan.nextInt();
        int ans = poweCall(b, e);
        System.out.println(b + " raised to the power of " + e + " is: " + ans);
        scan.close();
    }
}
