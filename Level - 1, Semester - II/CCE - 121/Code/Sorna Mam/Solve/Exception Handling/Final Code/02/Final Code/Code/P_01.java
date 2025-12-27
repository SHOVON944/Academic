/*
Divide by Zero Handling 
Write a Java program to take two integers as input and perform division. Handle the 
ArithmeticException if the denominator is zero. 
*/

import java.util.Scanner;

public class P_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter numerator: ");
            int numerator = sc.nextInt();

            System.out.print("Enter denominator: ");
            int denominator = sc.nextInt();
            int result = numerator / denominator;
            System.out.println("Result = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        } finally {
            sc.close();
        }
    }
}
