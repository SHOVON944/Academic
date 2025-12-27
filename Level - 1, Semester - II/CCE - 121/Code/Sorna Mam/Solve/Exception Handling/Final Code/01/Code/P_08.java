/*
Write a Java program that reads a list of integers from the user and throws an exception if 
any numbers are duplicates. 
Sample Output: 
Enter integers (enter a non-integer to stop): 
Enter a number: 10 
Enter a number: 20 
Enter a number: 30 
Enter a number: 20 
Duplicate number found: 20 
*/

import java.util.ArrayList;
import java.util.Scanner;

class DuplicateNumberException extends Exception{
    DuplicateNumberException(String message){
        super(message);
    }
}

public class P_08 {

    public static void checkNumber(int num, ArrayList<Integer> list) throws DuplicateNumberException{
        if(list.contains(num)){
            throw new DuplicateNumberException("Duplicate Number found: " + num);
        } else{
            list.add(num);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList <Integer> list = new ArrayList<>();

        while(true){
            System.out.print("Enter a number(non-integer to stop): ");
            if(scan.hasNextInt()){
                int num = scan.nextInt();
                try{
                    checkNumber(num, list);
                } catch(DuplicateNumberException e){
                    System.out.println(e.getMessage());
                    break;  
                }
            } else{
                break;
            }
        }

        scan.close();
    }
}
