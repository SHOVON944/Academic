/*
Nested Try-Catch 
Implement a program where: 
o The outer try handles an array index error. 
o The inner try handles division by zero. 
*/

import java.util.Scanner;

public class P_06 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[5];
        System.out.print("Enter the 5 elements: ");
        for(int i=0; i<5; i++) arr[i] = scan.nextInt();
        System.out.print("Enter the Numerator: ");
        int a = scan.nextInt();
        System.out.print("Enter the Denominator: ");
        int b = scan.nextInt();

        // outer try-catch block
        try{
            // inner try-catch block
            try{
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                double div = (double)a/ b;
                System.out.println("Division is: " + div);
            } catch(ArithmeticException e){
                System.out.println("Error: Cannot divide by zero!");
            }
            System.out.println("Enter an index (0-4): ");
            int index = scan.nextInt();
            System.out.println("Element at index " + index + " = " + arr[index]);
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error: Invalid index! Please enter a value between 0 and 4.");
        } finally{
            scan.close();
        }
    }
}
