package Lab_Problem;
import java.util.Scanner;

public class Nineten {

    public static boolean checkMedian(int a, int b, int c) {
        if ((b <= a && a <= c) || (c <= a && a <= b)) return true;
        if ((a <= b && b <= c) || (c <= b && b <= a)) return true;
        if ((a <= c && c <= b) || (b <= c && c <= a)) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a, b, c: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        
        boolean check = checkMedian(a, b, c);
        System.out.println("Among the three input numbers, any one of them is the middle value of the remaining two numbers: " + check);
        
        scan.close();
    }
}