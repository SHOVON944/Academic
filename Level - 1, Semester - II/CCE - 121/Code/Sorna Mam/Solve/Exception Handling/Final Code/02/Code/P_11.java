/*
 Command-Line Argument Validation 
Write a program that accepts two numbers from command-line arguments and prints their 
sum. Handle exceptions if the user: 
 Provides fewer arguments 
 Provides invalid (non-numeric) arguments
*/

/* run code:
    open terminal
    javac P_11.java
    java P_11 10 20     -> sum = 30
    java P_11 10        -> Through Error
    java P_11 10 20 30  -> sum = 30

*/
public class P_11{
    public static void main(String[] args) {
        try{
            if(args.length<2){
                throw new IllegalArgumentException("Please provide two numbers as arguments.");
            }

            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int sum = a + b;
            System.out.println("Sum is: " + sum);
        } catch(NumberFormatException e){
            System.out.println("Invalid input: Please enter only numeric values.");
        } catch(IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}