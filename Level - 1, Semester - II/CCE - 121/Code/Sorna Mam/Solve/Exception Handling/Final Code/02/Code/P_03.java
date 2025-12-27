/*
Number Format Exception 
Read a string from the user and convert it into an integer. 
Handle the NumberFormatException if the input is not a valid number.
*/

import java.util.Scanner;

public class P_03 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a number: ");
        String input = scan.nextLine();

        try{
            int num = Integer.parseInt(input);  // string -> integer
            System.out.println("The integer value is: " + num);
        } catch(NumberFormatException e){
            System.out.println("Error: '" + input + "' is not a valid number!");
        }

        scan.close();
    }
}
