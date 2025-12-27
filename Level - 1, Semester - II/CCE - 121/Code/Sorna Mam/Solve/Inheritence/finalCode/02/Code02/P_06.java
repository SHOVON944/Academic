/*
Create a class named 'Shape' with a method to print "This is This is shape". Then create 
two other classes named 'Rectangle', 'Circle' inheriting the Shape class, both having a 
method to print "This is rectangular shape" and "This is circular shape" respectively. 
Create a subclass 'Square' of 'Rectangle' having a method to print "Square is a rectangle". 
Now call the method of 'Shape' and 'Rectangle' class by the object of 'Square' class. 
Expected Output: 
This is shape 
This is rectangular shape 
*/

class Shpae06 {
    void displayShape() {
        System.out.println("This is shape.");
    }
}

class Rectengle06 extends Shpae06 {
    void displayRectengular() {
        System.out.println("This is Rectengular Shape.");
    }
}

class Circle06 extends Shpae06 {
    void displayCircle() {
        System.out.println("This is Circular Shape.");
    }
}

class Square06 extends Rectengle06 {
    void displaySquare() {
        System.out.println("Square is a Rectengular.");
    }
}

public class P_06 {
    public static void main(String[] args) {
        Square06 sqr06 = new Square06();
        sqr06.displayShape();
        sqr06.displayRectengular();
    }
}
