public class Rectangle {
    int length;
    int width;

    public Rectangle() {
        this(0);
    }

    public Rectangle(int side) {
        this(side, side);
    }

    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
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