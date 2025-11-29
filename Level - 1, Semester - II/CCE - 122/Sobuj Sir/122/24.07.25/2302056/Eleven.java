class Triangle {
    double a, b, c;

    Triangle(double x, double y, double z) {
        a = x;
        b = y;
        c = z;
    }

    boolean isValid() {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    double calculateArea() {
        if (!isValid()) {
            System.out.println("Invalid triangle. Cannot calculate area.");
            return 0;
        }
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}

class Main {
    public static void main(String[] args) {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = new Triangle(1, 2, 3);

        if (t1.isValid()) {
            System.out.println("Area of Triangle 1: " + t1.calculateArea());
        } else {
            System.out.println("Triangle 1 is invalid.");
        }

        if (t2.isValid()) {
            System.out.println("Area of Triangle 2: " + t2.calculateArea());
        } else {
            System.out.println("Triangle 2 is invalid.");
        }
    }
}