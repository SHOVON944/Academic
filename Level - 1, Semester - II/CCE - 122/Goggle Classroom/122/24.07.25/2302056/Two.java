class Rectangle {
    double length;
    double width;

    Rectangle() {
        length = 1.0;
        width = 1.0;
    }

    Rectangle(double l, double w) {
        length = l;
        width = w;
    }

    double calculateArea() {
        return length * width;
    }
}

class Two {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle(4.5, 2.0);

        System.out.println("Area 1: " + r1.calculateArea());
        System.out.println("Area 2: " + r2.calculateArea());
    }
}