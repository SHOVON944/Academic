/*
Write an inheritance hierarchy for classes Quadrilateral, Trapezoid, Parallelogram, Rectangle and 
Square. Use Quadrilateral as the superclass of the hierarchy. Create and use a Point class to 
represent the points in each shape. Make the hierarchy as deep (i.e., as many levels) as 
possible. Specify the instance variables and methods for each class. The private instance 
variables of Quadrilateral should be the x-y coordinate pairs for the four endpoints of the 
Quadrilateral. Write a program that instantiates objects of your classes and outputs each 
object’s area (except Quadrilateral). 
If input via constructor:  
new Trapezoid(new Point(0, 0), new Point(6, 0), new Point(4, 4), new Point(2, 4)); 
Expected Output: 
Trapezoid Area: 24.00 
Parallelogram Area: 20.00 
Rectangle Area: 12.00 
Square Area: 4.00 
*/

class Point {
    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}

// ---------------- Quadrilateral ----------------
class Quadrilateral {
    private Point p1, p2, p3, p4;

    public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    public Point getP4() {
        return p4;
    }

    // দুটি পয়েন্টের মধ্যে দূরত্ব বের করা
    protected double distance(Point a, Point b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }
}

// ---------------- Trapezoid ----------------
class Trapezoid extends Quadrilateral {
    public Trapezoid(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }

    // Trapezoid area = (a + b) / 2 × h
    public double area() {
        double base1 = distance(getP1(), getP2());
        double base2 = distance(getP3(), getP4());
        double height = Math.abs(getP1().getY() - getP3().getY());
        return (base1 + base2) * height / 2.0;
    }
}

// ---------------- Parallelogram ----------------
class Parallelogram extends Trapezoid {
    public Parallelogram(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }

    // Parallelogram area = base × height
    public double area() {
        double base = distance(getP1(), getP2());
        double height = Math.abs(getP1().getY() - getP3().getY());
        return base * height;
    }
}

// ---------------- Rectangle ----------------
class Rectangle extends Parallelogram {
    public Rectangle(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }

    // Rectangle area = length × width
    public double area() {
        double length = distance(getP1(), getP2());
        double width = distance(getP2(), getP3());
        return length * width;
    }
}

// ---------------- Square ----------------
class Square extends Rectangle {
    public Square(Point p1, Point p2, Point p3, Point p4) {
        super(p1, p2, p3, p4);
    }

    // Square area = side × side
    public double area() {
        double side = distance(getP1(), getP2());
        return side * side;
    }
}

// ---------------- Main Class ----------------
public class QuadrilateralHierarchy {
    public static void main(String[] args) {
        // Constructor-based initialization (যেভাবে প্রশ্নে দেওয়া)
        Trapezoid t = new Trapezoid(
            new Point(0, 0), new Point(6, 0),
            new Point(4, 4), new Point(2, 4)
        );

        Parallelogram p = new Parallelogram(
            new Point(0, 0), new Point(5, 0),
            new Point(4, 4), new Point(1, 4)
        );

        Rectangle r = new Rectangle(
            new Point(0, 0), new Point(4, 0),
            new Point(4, 3), new Point(0, 3)
        );

        Square s = new Square(
            new Point(0, 0), new Point(2, 0),
            new Point(2, 2), new Point(0, 2)
        );

        // Output as expected
        System.out.printf("Trapezoid Area: %.2f\n", t.area());
        System.out.printf("Parallelogram Area: %.2f\n", p.area());
        System.out.printf("Rectangle Area: %.2f\n", r.area());
        System.out.printf("Square Area: %.2f\n", s.area());
    }
}
