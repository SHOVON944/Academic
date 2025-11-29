import java.util.Scanner;

public class Zig_zag_pattern {
    public static void main(String[] args) {

        // print a zig zag pattern
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n = scanner.nextInt();

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= n; j++) {
                if (((i + j) % 4 == 0) || (i == 2 && j % 4 == 0)) {
                    System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}