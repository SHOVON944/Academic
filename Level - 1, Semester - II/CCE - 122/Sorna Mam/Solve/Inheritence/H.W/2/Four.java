class Rectangle {
    private int length;
    private int breadth;

    public Rectangle(int length, int breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public void printArea() {
        System.out.println("Area of Rectangle: " + (length * breadth));
    }

    public void printPerimeter() {
        System.out.println("Perimeter of Rectangle: " + (2 * (length + breadth)));
    }
}

class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }
}

public class Four {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(5, 3);
        Square sq = new Square(4);

        rect.printArea();
        rect.printPerimeter();
        
        sq.printArea();
        sq.printPerimeter();
    }
}