/*
We have to calculate the area of a rectangle, a square and a circle. Create an abstract class ‘Shape’ with three abstract methods namely ‘RectangleArea’ taking two parameters, ‘SquareArea’ and ‘CircleArea’ taking one parameter each. The parameters of ‘RectangleArea’ are its length and breadth, that of ‘SquareArea’ is its side and that of ‘CircleArea’ is its radius. Now create another class ‘Area’ containing all the three methods ‘RectangleArea’, ‘SquareArea’ and ‘CircleArea’ for printing the area of rectangle, square and circle respectively. Create an object of class ‘Area’ and call all the three methods. Repeat the process for 4 rectangles, 4 squares and 5 circles.
*/


import java.util.Scanner;

// Abstract class
// Abstract class extend korte hoi...
abstract class Shape {
    abstract void RectangleArea(double length, double breadth);
    abstract void SquareArea(double side);
    abstract void CircleArea(double radius);
}

// Class Area extends 
class Area extends Shape {
    void RectangleArea(double length, double breadth) {
        double area = length * breadth;
        System.out.println("Area of Rectangle = " + area);
    }

    void SquareArea(double side) {
        double area = side * side;
        System.out.println("Area of Square = " + area);
    }

    void CircleArea(double radius) {
        double area = Math.PI * radius * radius;
        System.out.println("Area of Circle = " + area);
    }
}

public class ShapeAreaDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Area a = new Area();

        System.out.println("=== Calculate Area of 4 Rectangles ===");
        for (int i = 1; i <= 4; i++) {
            System.out.print("Enter length of rectangle " + i + ": ");
            double length = sc.nextDouble();
            System.out.print("Enter breadth of rectangle " + i + ": ");
            double breadth = sc.nextDouble();
            a.RectangleArea(length, breadth);
        }

        System.out.println("\n=== Calculate Area of 4 Squares ===");
        for (int i = 1; i <= 4; i++) {
            System.out.print("Enter side of square " + i + ": ");
            double side = sc.nextDouble();
            a.SquareArea(side);
        }

        System.out.println("\n=== Calculate Area of 5 Circles ===");
        for (int i = 1; i <= 5; i++) {
            System.out.print("Enter radius of circle " + i + ": ");
            double radius = sc.nextDouble();
            a.CircleArea(radius);
        }

        sc.close();
    }
}
