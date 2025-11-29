/*
Create a class named 'Rectangle' with two data members 'length' and 'breadth' and two 
methods to print the area and perimeter of the rectangle respectively. Its constructor 
having parameters for length and breadth is used to initialize length and breadth of the 
rectangle. Let class 'Square' inherit the 'Rectangle' class with its constructor having a 
parameter for its side (suppose s) calling the constructor of its parent class as 'super(s,s)'. 
Print the area and perimeter of a rectangle and a square. 
If Input:  
Rectangle rect = new Rectangle(5, 3); 
Square sq = new Square(4); 
Expected Output: 
Area of Rectangle: 15 
Perimeter of Rectangle: 16 
Area of Square: 16 
Perimeter of Square: 16 
*/

import java.util.Scanner;

class Rectangle04{
    int length;
    int breadth;

    Rectangle04(int length, int breadth){
        this.length = length;
        this.breadth = breadth;
    }

    void printArea(){
        int area = length * breadth;
        System.out.println("Area of Rectengle: " + area);
    }
    void printPerimeter(){
        int perimeter = 2 * (length + breadth);     // 4*side , we also write 2*(side*side)
        System.out.println("Perimeter is: " + perimeter);
    }
}

class Square04 extends Rectangle04{
    Square04(int side){
        super(side, side);
    }
    void printArea(){
        int area = length * breadth;
        System.out.println("Area of Square: " + area);
    }
    void printPerimeter(){
        int perimeter = 2 * (length + breadth);
        System.out.println("Perimeter is: " + perimeter);
    }
}


public class P_04 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Rectenger length: ");
        int lR = scan.nextInt();
        System.out.print("Enter the Rectenger breadth: ");
        int bR = scan.nextInt();
        Rectangle04 objR = new Rectangle04(lR, bR);

        System.out.print("Enter the Square side: ");
        int sS = scan.nextInt();
        Square04 objS = new Square04(sS);

        objR.printArea();
        objR.printPerimeter();
        objS.printArea();
        objS.printPerimeter();

        scan.close();
    }
}



