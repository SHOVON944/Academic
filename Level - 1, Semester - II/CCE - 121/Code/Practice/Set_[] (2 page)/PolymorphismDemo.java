/*
Write a console-based program to implement polymorphism using inheritance. Consider the example of Shape as a base class with a method show(). Then create two child classes — Circle and Rectangle — which inherit the base class Shape and override its method show(). Add one more method named getInfo() in the base class.
This method should display the class name in which it is implemented. Do not override this method in the child classes. When you call the method getInfo() using a child object, it should still display the name of the base class — which implies that the method has been directly inherited and was not overridden.
*/

import java.util.Scanner;

// Base class
class Shape4 {
    public void show() {
        System.out.println("This is a generic shape.");
    }

    public void getInfo() {
        System.out.println("This method is implemented in the base class: Shape");
    }
}

class Circle4 extends Shape4 {
    public void show() {
        System.out.println("This is a Circle.");
    }
}

class Rectangle extends Shape4 {
    public void show() {
        System.out.println("This is a Rectangle.");
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose shape: 1 for Circle, 2 for Rectangle");
        int choice = sc.nextInt();

        Shape4 shape; // Polymorphic reference

        if (choice == 1) {
            shape = new Circle4();
        } else if (choice == 2) {
            shape = new Rectangle();
        } else {
            shape = new Shape4();
        }

        // Call overridden method
        shape.show();

        // Call inherited method (not overridden)
        shape.getInfo();

        sc.close();
    }
}
