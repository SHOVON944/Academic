package RecursiveMethods;
import java.util.Scanner;

public class Two {

    public static int sumCall(int a){
        if(a==1) return 1;
        return a + sumCall(a-1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number(For positve value): ");
        int a = scan.nextInt();
        if(a<0){
             System.out.println("Please input positive value.");
        } else{
            int ans = sumCall(a);
            System.out.println("The sum of " + a + " is: " + ans);
        }
        scan.close();
    }
}
