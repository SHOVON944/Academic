import java.util.Scanner;

class Operation{
    int square(int n){
        return n*n;
    }
}

class Circle{
    Operation op;   // aggregation (HAS-A Relationship)
    double pi = Math.PI;
    double area(int radius){
        op = new Operation();
        int rsquare = op.square(radius);
        return pi*rsquare;
    }
}

public class Example01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the radius of the circle: ");
        int r = scan.nextInt();
        Circle c = new Circle();
        double result = c.area(r);
        System.out.println(result);
        scan.close();
    }
}
