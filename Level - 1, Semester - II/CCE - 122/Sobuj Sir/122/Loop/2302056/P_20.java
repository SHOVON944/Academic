import java.util.Scanner;

public class P_20 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the a number: ");
        int a = scan.nextInt();
        System.out.print("Enter the b number: ");
        int b = scan.nextInt();
        System.out.print("Enter the c number: ");
        int c = scan.nextInt();

        int ans = (a>b)? ((a>c)? a : c) : ((b>c)? b : c);
        System.out.println("The largest number is: " + ans);
    }
}
