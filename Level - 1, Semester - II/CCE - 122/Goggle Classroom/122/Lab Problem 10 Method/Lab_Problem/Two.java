package Lab_Problem;
import java.util.Scanner;

public class Two {

    public static void avg(double a, double b, double c) {
        System.out.println("The three number avarage is: " + (a+b+c)/3);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        double a = scan.nextDouble();
        System.out.print("Enter the 2nd number: ");
        double b = scan.nextDouble();
        System.out.print("Enter the 3rs number: ");
        double c = scan.nextDouble();

        avg(a, b, c);

        scan.close();
    }
}
