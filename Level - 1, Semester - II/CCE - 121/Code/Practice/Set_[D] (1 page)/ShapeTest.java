/*
Write a Java program to create a base class Shape with methods draw() and calculateArea().
Create two subclasses Circle and Cylinder.
Override the draw() method in each subclass to draw the respective shape.
In addition, override the calculateArea() method in the Cylinder subclass to calculate and return the total surface area of the cylinder.
*/


class Shape1 {

    void draw() {
        System.out.println("Drawing a generic shape.");
    }

    double calculateArea() {
        System.out.println("Calculating area of a generic shape.");
        return 0;
    }
}

class Circle extends Shape1 {
    double radius;

    Circle(double radius) {
        this.radius = radius;
    }


    void draw() {                   // eita ki sout dia dia ki shape akabo !
        System.out.println("Drawing a Circle.");
    }
}

class Cylinder extends Shape1 {
    double radius;
    double height;

    Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    void draw() {                   // eita ki sout dia dia ki shape akabo !
        System.out.println("Drawing a Cylinder.");
    }

    double calculateArea() {
        double area = 2 * Math.PI * radius * (radius + height);     // 2 * PI * r * (r + h)
        return area;
    }
}

public class ShapeTest {
    public static void main(String[] args) {
        Shape1 s1 = new Circle(5);
        Shape1 s2 = new Cylinder(4, 10);

        s1.draw();
        System.out.println("Area (default Shape method): " + s1.calculateArea());

        s2.draw();
        System.out.println("Total Surface Area of Cylinder: " + s2.calculateArea());
    }
}
