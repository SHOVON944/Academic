package Lab_Problem;
import java.util.Scanner;

public class Fourten {

    public static double calculateAreaPentagonCall(int n, double a){
        double area  = (n * Math.pow(a, 2) / (4 * (Math.tan(Math.PI/n))));   // n.a2 / 4tan(pi/n)
        return  area;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of side: ");
        int n = scan.nextInt();
        System.out.print("Enter the side length: ");
        double a = scan.nextDouble();
        double calculate = calculateAreaPentagonCall(n, a);
        System.out.println("The area of Pentagon is: " + calculate);

        scan.close();
    }
}
