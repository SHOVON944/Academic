package ConditionalStatement;
import java.util.Scanner;

public class TwentyFive {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input the number: ");
        int n = scan.nextInt();

        // for upper half dimond......
        for (int i = 1; i <= n; i++) {
            // for space...
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // for decreasing number...
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }
            // for increasing number.......
            for (int j = 2; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }

        // for lower hal dimond....
        for (int i = n - 1; i >= 1; i--) {
            // for spaces....
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // for decreasing numbers...
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }
            // for increasing numbers...
            for (int j = 2; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }

        scan.close();
    }
}