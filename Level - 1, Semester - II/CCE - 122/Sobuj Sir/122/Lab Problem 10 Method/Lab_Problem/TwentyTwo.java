package Lab_Problem;
import java.util.Scanner;

public class TwentyTwo {

    public static boolean allDigitEvenCall(int a){
        while(a>0){
            int mod = a%10;
            if(mod%2!=0) return false;
            a /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input an integer(positive/negative): ");
        int number = sc.nextInt();
        boolean check = allDigitEvenCall(number);
        System.out.println("Every digit of the entered integer is even! : " + check);

        sc.close();
    }
}
