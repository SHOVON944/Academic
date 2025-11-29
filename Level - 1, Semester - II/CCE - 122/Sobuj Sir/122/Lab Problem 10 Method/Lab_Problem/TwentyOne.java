package Lab_Problem;
import java.util.Scanner;

public class TwentyOne {

    public static void factor3Call(int a){
        while((a%3==0)  &&  (a!=0)){
            System.out.print("3 * ");
            a /= 3;
        }
        System.out.println(a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input an integer(positive/negative): ");
        int number = sc.nextInt();
        factor3Call(number);

        sc.close();
    }
}
