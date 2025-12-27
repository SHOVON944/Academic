/*
Number Format Exception 
Read a string from the user and convert it into an integer. Handle the 
NumberFormatException if the input is not a valid number.
*/

import java.util.Scanner;

public class P_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter a number: ");
            String input = sc.nextLine();
            int number = Integer.parseInt(input);

            System.out.println("You entered: " + number);
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid number format! Please enter digits only.");
        } finally {
            sc.close();
        }
    }
}
