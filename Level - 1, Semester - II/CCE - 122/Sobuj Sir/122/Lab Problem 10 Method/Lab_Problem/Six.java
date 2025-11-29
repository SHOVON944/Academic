package Lab_Problem;
import java.util.Scanner;

public class Six {

    public static void sumDigit(int a) {
        int digitSum = 0;
        while(a>0){
            int mod = a%10;
            digitSum += mod;
            a /= 10;
        }
        System.out.println("The sum of the digit is: " + digitSum);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the digit: ");
        int a = scan.nextInt();

        sumDigit(a);

        scan.close();
    }
}
