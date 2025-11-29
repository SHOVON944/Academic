package Lab_Problem;
import java.util.Scanner;

public class Twenty {

    public static int extractFirstDigitCall(int num) {
        if(num<0) num = -num;
        while (num >= 10) {
            num /= 10;
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Input an integer(positive/negative): ");
        int number = sc.nextInt();

        int firstDigit = extractFirstDigitCall(number);
        System.out.println("Extract the first digit from the said integer: " + firstDigit);

        sc.close();
    }
}
