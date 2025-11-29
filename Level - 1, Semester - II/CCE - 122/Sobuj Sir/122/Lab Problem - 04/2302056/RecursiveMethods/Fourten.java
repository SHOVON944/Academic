package RecursiveMethods;
import java.util.Scanner;

public class Fourten {

    public static int sumDCall(int a){
        if(a==0) return 0;
        return a%10 + sumDCall(a/10);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Digit: ");
        int a = scan.nextInt();

        int sumD = sumDCall(a);
        System.out.println("The sum of the digit is:  " + sumD);

        scan.close();
    }
}
