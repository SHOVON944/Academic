public class Rectangle {
    int length;
    int width;

    public Rectangle() {
        this(0, 0);
        System.out.println("No-arg constructor called");
    }

    public Rectangle(int side) {
        this(side, side);
        System.out.println("Single-arg constructor called");
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
        System.out.println("Two-arg constructor called");
    }

    public void display() {
        System.out.println("Length: " + length + ", Width: " + width);
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        r1.display();

        Rectangle r2 = new Rectangle(5);
        r2.display();

        Rectangle r3 = new Rectangle(4, 7);
        r3.display();
    }
}