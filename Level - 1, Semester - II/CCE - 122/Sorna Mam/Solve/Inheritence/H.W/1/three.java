class Shape{
    double getArea(){
        System.out.println("Calculating area in Shape class");
    }
}

class Rectangle extends Shape{
    double length = 5;
    double width = 3;

    double getArea(){
        return length * width;
    }
}

public class three {
    public static void main(String[] args) {
        Shape shape = new Shape();
        Rectangle rect = new Rectangle();

        System.out.println("Generic Shape Area: " + shape.getArea());
        System.out.println("Rectangle Area: " + rect.getArea());
    }
}