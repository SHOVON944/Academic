/*
Finally Block Example 
Write a program to demonstrate how the finally block executes regardless of exceptions. 
Example: file closing or resource cleanup. 
*/

public class P_07 {
    public static void main(String[] args) {
        try {
            System.out.println("Inside try block.");
            int result = 10 / 0;
            System.out.println("This line will not execute.");
        } catch (ArithmeticException e) {
            System.out.println("Exception caught: " + e.getMessage());
        } finally {
            System.out.println("Finally block executed: Resources closed or cleaned up.");
        }

        System.out.println("Program continues after try-catch-finally.");
    }
}
