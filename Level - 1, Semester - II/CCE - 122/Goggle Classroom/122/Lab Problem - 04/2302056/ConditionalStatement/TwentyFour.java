package ConditionalStatement;
import java.util.Scanner;

public class TwentyFour {
        public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input number: ");
        int n = scan.nextInt();
        int space = n-1;
        int a = 1;
        for(int i=1; i<=n; i++){
            for(int j=1; j<=space; j++){
                System.out.print(" ");
            }
            for(int k=1; k<=a; k++){
                System.out.print("@");
            }
            System.out.println();
            space--;
            a++;
        }

        scan.close();
    }
}
