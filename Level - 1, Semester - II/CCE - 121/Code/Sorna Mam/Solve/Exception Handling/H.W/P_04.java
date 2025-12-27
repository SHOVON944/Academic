/*
Write a Java program to create a method that takes an integer as a parameter and throws 
an exception if the number is odd. 
Sample Output: 
The number 7 is odd!  
The number 8 is even. 
*/

import java.util.Scanner;

public class P_04 {
        static void checkNumber(int num) throws Exception {
        if (num % 2 != 0) {
            throw new Exception("The number " + num + " is odd!");
        } else {
            System.out.println("The number " + num + " is even.");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int number = scan.nextInt();

        try {
            checkNumber(number);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        scan.close();
    }
}
