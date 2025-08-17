package ConditionalStatement;
import java.util.Scanner;

public class TwentyThree {
        public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input number: ");
        int n = scan.nextInt();
        for(int i=0; i<n; i++){
            for(int k=0; k<i; k++){
                System.out.print(" ");
            }
            for(int j=n; j>i; j--){
                System.out.print("*");
            }
            System.out.println();
        }

        scan.close();
    }
}
