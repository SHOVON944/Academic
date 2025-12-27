/*
Finally Block Example 
Write a program to demonstrate how the finally block executes regardless of exceptions. 
Example: file closing or resource cleanup. 
*/

import java.util.Scanner;

public class P_07 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Numerator: ");
        int a = scan.nextInt();
        System.out.print("Enter the Denominator: ");
        int b = scan.nextInt();
        try{
            if (b == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            double div = (double)a / b;
            System.out.println("Division is: " + div);
        } catch(ArithmeticException e){
            System.out.println("Error: Cannot divide by zero!");
        } finally{
            System.out.println("Finally block executed: Cleaning up resources...");
            scan.close();
        }

        scan.close();
    }
}
