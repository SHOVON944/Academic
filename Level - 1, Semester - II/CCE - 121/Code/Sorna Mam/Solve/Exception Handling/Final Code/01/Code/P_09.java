/*
Write a Java program to create a method that takes a string as input and throws an 
exception if the string does not contain vowels. 
Sample Output: 
The string 'rhythm' does not contain any vowels.  
The string 'hello' contains vowels.
*/

import java.util.Scanner;

class NoContainVowelsException extends Exception{
    NoContainVowelsException(String message){
        super(message);
    }
}

public class P_09 {

    public static void checkContainVowels(String str) throws NoContainVowelsException{
        String lowerStr = str.toLowerCase();
        if( !( (lowerStr.contains("a")) || (lowerStr.contains("e")) || (lowerStr.contains("i")) || (lowerStr.contains("o")) || (lowerStr.contains("u")) ) ){
            throw new NoContainVowelsException("The string '" + str + "' does not contain any vowels.");
        } else{
            System.out.println("The string '" + str + "' contains vowels.");
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String str = scan.nextLine();
        try{
            checkContainVowels(str);
        } catch(NoContainVowelsException e){
            System.out.println(e.getMessage());
        }

        scan.close();
    }
}
