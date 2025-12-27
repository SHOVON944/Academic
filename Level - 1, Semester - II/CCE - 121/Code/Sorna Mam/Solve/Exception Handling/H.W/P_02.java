/*
Create a program to ask the user for a real number and display its square root. Errors 
must be trapped using "try..catch". 
Sample Output: 
Enter a real number: 25  
The square root of 25.0 is 5.0 
Enter a real number: -9  
Cannot compute the square root of a negative number. 
Enter a real number: abc  
Error: Invalid input. Please enter a valid real number. 
*/

import java.util.Scanner;

public class P_02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a real number: ");

        try {
            double num = scan.nextDouble();

            if (num < 0) {
                System.out.println("Cannot compute the square root of a negative number.");
            } else {
                double result = Math.sqrt(num);
                System.out.println("The square root of " + num + " is " + result);
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter a valid real number.");
        }

        scan.close();
    }
}
