/*
Divide by Zero Handling 
Write a Java program to take two integers as input and perform division. Handle the 
ArithmeticException if the denominator is zero.
*/

import java.util.Scanner;

public class P_01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Numerator: ");
        int a = scan.nextInt();
        System.out.print("Enter the Denominator: ");
        int b = scan.nextInt();
        try{
            double div = (double)a / b;
            System.out.println("Division is: " + div);
        } catch(ArithmeticException e){
            System.out.println("Error: Cannot divide by zero!");
        }

        scan.close();
    }
}
