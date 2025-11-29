import java.util.Scanner;

public class Palindrome_pattern {
    public static void main(String[] args) {
        // print palindrome number pattern 1 to n
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number : ");
        int n = scanner.nextInt();
        int nnm = 1;

        for (int i = 1; i <= n; i++) {
            int nsp = n - i;
            for (int j = 1; j <= nsp; j++) {
                System.out.print("  ");
            }
            nsp--;

            int a = i;
            for (int k = 1; k <= nnm; k++) {
                System.out.print(a + " ");
                a--;
            }
            nnm++;

            int b = 2;
            for (int p = 2; p <= i; p++) {
                System.out.print(b + " ");
                b++;
            }
            System.out.println();
        }
    }
}