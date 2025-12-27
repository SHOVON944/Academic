/*
Nested Try-Catch 
Implement a program where: 
o The outer try handles an array index error. 
o The inner try handles division by zero. 
*/

import java.util.Scanner;

public class P_06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numbers = { 10, 20, 30, 40, 50 };

        try {
            // Outer try block
            System.out.print("Enter array index (0-4): ");
            int index = sc.nextInt();
            System.out.println("Selected number: " + numbers[index]);
            try {
                // Inner try block
                System.out.print("Enter a divisor: ");
                int divisor = sc.nextInt();
                int result = numbers[index] / divisor;
                System.out.println("Result = " + result);
            } catch (ArithmeticException e) {
                System.out.println("Error: Division by zero is not allowed!");
            }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Invalid array index! Please enter a value between 0 and 4.");
        } finally {
            sc.close();
        }
    }
}
