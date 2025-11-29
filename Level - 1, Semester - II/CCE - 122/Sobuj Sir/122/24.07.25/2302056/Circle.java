public class Circle {
    double radius;

    Circle(double r) {
        radius = r;
    }

    double getArea() {
        return Math.PI * radius * radius;
    }

    double getCircumference() {
        return 2 * Math.PI * radius;
    }
}

class Five {
    public static void main(String[] args) {
        Circle c = new Circle(5.0);

        System.out.println("Area: " + c.getArea());
        System.out.println("Circumference: " + c.getCircumference());
    }
}
