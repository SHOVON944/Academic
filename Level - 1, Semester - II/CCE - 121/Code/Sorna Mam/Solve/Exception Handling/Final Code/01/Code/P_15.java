/*
(Catching Exceptions Using Outer Scopes) Write a program showing that a method with 
its own try block does not have to catch every possible error generated within the try. 
Some exceptions can slip through to, and be handled in, other scopes.
*/


public class P_15 {

    public static void main(String[] args) {
        try {
            innerMethod();
        } catch (ArithmeticException e) {
            System.out.println("Caught in outer scope: " + e);
        }
        System.out.println("Program continues normally...");
    }

    static void innerMethod() {
        try {
            System.out.println("Inside innerMethod...");
            int[] arr = new int[3];
            arr[5] = 10; 
            int x = 10 / 0; 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught inside innerMethod: " + e);
            throw new ArithmeticException("Division by zero happened later!");
        }
    }
}
