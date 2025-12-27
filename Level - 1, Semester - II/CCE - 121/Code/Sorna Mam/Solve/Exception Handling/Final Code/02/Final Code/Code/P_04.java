/*
Multiple Exception Handling 
Write a program that takes two numbers from the user and divides them. Handle both 
ArithmeticException (division by zero) and InputMismatchException (if the 
user enters non-numeric values).
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class P_04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter first number: ");
            int num1 = sc.nextInt();
            System.out.print("Enter second number: ");
            int num2 = sc.nextInt();
            int result = num1 / num2;
            System.out.println("Result = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed!");
        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input! Please enter numeric values only.");
        } finally {
            sc.close();
        }
    }
}
