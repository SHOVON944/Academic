/*
Now repeat the above example to print the area of 15 squares. Hint-Use array of objects 
Expected Output: 
Square 1 - Area of Square: 1 
Square 2 - Area of Square: 4 
Square 3 - Area of Square: 9 
Square 4 - Area of Square: 16 
Square 5 - Area of Square: 25 
Square 6 - Area of Square: 36 
Square 7 - Area of Square: 49 
Square 8 - Area of Square: 64 
Square 9 - Area of Square: 81 
Square 10 - Area of Square: 100 
Square 11 - Area of Square: 121 
Square 12 - Area of Square: 144 
Square 13 - Area of Square: 169 
Square 14 - Area of Square: 196 
Square 15 - Area of Square: 225
*/

import java.util.Scanner;


class Rectangle {
    int length;
    int breadth;


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


    public void printArea() {
        System.out.println("Area of Square: " + (length * length));
    }


    public void printPerimeter() {
        System.out.println("Perimeter of Square: " + (4 * length));
    }
}

public class P_05 {
    public static void main(String[] args) {
        // Part 1: Rectangle and Square objects
        Rectangle rect = new Rectangle(5, 3);
        Square sq = new Square(4);

        rect.printArea();
        rect.printPerimeter();
        sq.printArea();
        sq.printPerimeter();

        System.out.println("\n--- Areas of 15 Squares (1 to 15 sides) ---");

        // Part 2: Array of 15 Squares with fixed sides
        Square[] squares = new Square[15];
        for (int i = 0; i < 15; i++) {
            squares[i] = new Square(i + 1); // side = i+1
            System.out.println("Square " + (i + 1) + " - Area of Square: " + (squares[i].length * squares[i].length));
        }

        System.out.println("\n--- Enter sides of 15 Squares ---");
        Scanner sc = new Scanner(System.in);

        Square[] userSquares = new Square[15];
        for (int i = 0; i < 15; i++) {
            System.out.print("Enter side of Square " + (i + 1) + ": ");
            int side = sc.nextInt();
            userSquares[i] = new Square(side);
        }

        System.out.println("\n--- Areas of 15 User-given Squares ---");
        for (int i = 0; i < 15; i++) {
            System.out.println("Square " + (i + 1) + " - Area of Square: " + (userSquares[i].length * userSquares[i].length));
        }

        sc.close();
    }
}
