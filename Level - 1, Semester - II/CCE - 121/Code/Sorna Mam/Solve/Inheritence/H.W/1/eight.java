class Shape{
    double getPerimeter(){
        System.out.println("Calculating perimeter in Shape class");
        System.out.println("Generic Shape Perimeter: 0.0");
        return 0;
    }

    double getArea(){
        System.out.println("Calculating area in Shape class");
        System.out.println("Generic Shape Area: 0.0");
        return 0;
    }
}

class Circle extends Shape{
    double radius = 5;

    double getPerimeter(){
        double perimeter = 2 * Math.PI * radius;
        System.out.println("Circle Perimeter: " + perimeter);
        return perimeter;
    }

    double getArea(){
        double area = Math.PI * radius * radius;
        System.out.println("Circle Area: " + area);
        return area;
    }
}

public class eight {
    public static void main(String[] args) {
        Shape shape = new Shape();
        Circle circle = new Circle();

        shape.getPerimeter();
        shape.getArea();

        circle.getPerimeter();
        circle.getArea();
    }
}