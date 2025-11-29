/*
Write a Java program to create a class called Shape with a method called getArea(). 
Create a subclass called Rectangle that overrides the getArea() method to calculate the 
area of a rectangle. 
Expected Output: 
Calculating area in Shape class 
Generic Shape Area: 0.0 
Rectangle Area: 15.0 
 */

import java.util.Scanner;

class Shape{
    double getArea(){
        System.out.println("Calculating area in Shape class");
        return 0.0;
    }
}
class Rectangle extends Shape{
    double getArea(double length,  double width){
        return length*width;
    }
}

public class Three{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the length of the triangle: ");
        double l = scan.nextDouble();
        System.out.print("Enter the width of the triangle: ");
        double w = scan.nextDouble();

        Shape s = new Shape();
        Rectangle r = new Rectangle();

        System.out.println("Generic Shape area: " + s.getArea());
        System.out.println("Rectangle area: " + r.getArea(l, w));

        scan.close();
    }
} 