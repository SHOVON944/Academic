package RecursiveMethods;
import java.util.Scanner;

public class Three {

    public static int fibonacciCall(int a){
        if(a<=0) return 0;
        if(a==1) return 0;
        if(a==2) return 1;
        return fibonacciCall(a-1)+fibonacciCall(a-2);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number(Postive value): ");
        int a = scan.nextInt();
        int ans = fibonacciCall(a);
        System.out.println("The fibonacci of " + a + " is: " + ans);
        scan.close();
    }
}
