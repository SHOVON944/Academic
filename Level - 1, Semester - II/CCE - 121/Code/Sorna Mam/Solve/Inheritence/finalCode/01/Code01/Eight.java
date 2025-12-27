/*
Write a Java program to create a class called Shape with methods called getPerimeter() 
and getArea(). Create a subclass called Circle that overrides the getPerimeter() and 
getArea() methods to calculate the area and perimeter of a circle. Consider, Radius=5. 
Expected Output: 
Calculating perimeter in Shape class 
Generic Shape Perimeter: 0.0 
Calculating area in Shape class 
Generic Shape Area: 0.0 
Circle Perimeter: 31.41592653589793 
Circle Area: 78.53981633974483 
*/

import java.util.Scanner;

class Shape {
    double getPerimeter(){
        System.out.println("Calculating perimeter in Shape class");
        return 0.0;
    }
    double getArea() {
        System.out.println("Calculating area in Shape class");
        return 0.0;
    }
}
class Circle extends Shape{
    double radius;

    Circle(double radius){
        this.radius = radius;
    }
    double getPerimeter(){
        return 2 * Math.PI * radius;
    }
    double getArea(){
        return Math.PI * radius * radius;
    }
}

public class Eight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Shape sh = new Shape();
        System.out.println("Generic Shape Perimeter: " + sh.getPerimeter());
        System.out.println("Generic Shape Area: " + sh.getArea());

        System.out.print("Enter the radius: ");
        double r = scan.nextDouble();
        Circle crcl = new Circle(r);
        System.out.println("Circle Perimeter: " + crcl.getPerimeter());
        System.out.println("Circle Area: " + crcl.getArea());

        scan.close();
    }
}
