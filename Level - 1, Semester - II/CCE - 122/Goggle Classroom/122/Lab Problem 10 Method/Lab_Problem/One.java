package Lab_Problem;
import java.util.Scanner;

public class One {

    public static void small(double a, double b, double c) {
        if(a>b){
            if(a>c){
                System.out.println("Smalest number between three number is: " + a);
            } else{
                System.out.println("Smalest number between three number is: " + c);
            }
        } else{
            if(b>c){
                System.out.println("Smalest number between three number is: " + b);
            } else{
                System.out.println("Smalest number between three number is: " + c);
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        double a = scan.nextDouble();
        System.out.print("Enter the 2nd number: ");
        double b = scan.nextDouble();
        System.out.print("Enter the 3rs number: ");
        double c = scan.nextDouble();

        small(a, b, c);

        scan.close();
    }
}
