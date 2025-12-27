/*
Custom Exception – Age Validation 
Create a custom exception InvalidAgeException. Throw this exception if a user enters 
age less than 18 when applying for a driving license.
*/

import java.util.Scanner;

class InvalidAgeException extends Exception{
    InvalidAgeException(String message){
        super(message);
    }
}

public class P_05 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter age: ");
        int age = scan.nextInt();

        try{
            if(age<18){
                throw new InvalidAgeException("Age must be at least 18 to apply for a driving license.");
            } else{
                System.out.println("Eligible for driving license.");
            }
        } catch(InvalidAgeException e){
            System.out.println("Error: " + e.getMessage());
        } finally{
            scan.close();
        }
    }
}
