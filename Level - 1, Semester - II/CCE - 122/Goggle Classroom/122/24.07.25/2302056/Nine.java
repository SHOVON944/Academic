class Box {
    double length;
    double width;
    double height;

    Box(double l, double w, double h) {
        length = l;
        width = w;
        height = h;
    }

    double calculateVolume() {
        return length * width * height;
    }

    boolean isCube() {
        return length == width && width == height;
    }
}

class Main {
    public static void main(String[] args) {
        Box b1 = new Box(3.0, 3.0, 3.0);
        Box b2 = new Box(2.5, 3.0, 4.0);

        System.out.println("Volume of Box 1: " + b1.calculateVolume());
        System.out.println("Is Box 1 a cube? " + b1.isCube());

        System.out.println("Volume of Box 2: " + b2.calculateVolume());
        System.out.println("Is Box 2 a cube? " + b2.isCube());
    }
}