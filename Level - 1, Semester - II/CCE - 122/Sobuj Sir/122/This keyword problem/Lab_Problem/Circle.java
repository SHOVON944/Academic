import java.util.Scanner;

public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public void display() {
        double area = this.calculateArea();
        System.out.println("Circle Area: " + area);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the radius of the circle: ");
        int r = scan.nextInt();
        Circle c1 = new Circle(r);
        c1.display();

        scan.close();
    }
}
