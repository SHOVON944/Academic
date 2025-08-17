package Lab_Problem;
import java.util.Scanner;

public class Thirten {

    public static double calculateAreaCall(double a, double b, double c){
        double s = (a+b+c)/2;
        double area = Math.sqrt(s * (s-a) * (s-b)  * (s-c));
        return area;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the side a: ");
        double a = scan.nextDouble();
        System.out.print("Enter the side b: ");
        double b = scan.nextDouble();
        System.out.print("Enter the side c: ");
        double c = scan.nextDouble();
        double calculate = calculateAreaCall(a, b, c);
        System.out.println("The area of triangle is: " + calculate);

        scan.close();
    }
}
