/*
Write a Java program to create a method that takes an integer as a parameter and throws 
an exception if the number is odd. 
Sample Output: 
The number 7 is odd!  
The number 8 is even.
*/

import java.util.Scanner;

class OddNumberException extends Exception{
    OddNumberException(String message){
        super(message);
    }
}

public class P_04 {

    public static void checkNumber(int num) throws OddNumberException{
        if(num%2!=0){
            throw new OddNumberException("The number " + num + " is odd!");
        } else{
            System.out.println("The number " + num + " is even.");
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = scan.nextInt();
        try{
            checkNumber(num);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

        scan.close();
    }
}
