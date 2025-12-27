/*
Array Index Out of Bound 
Create an array of 5 elements. Ask the user to input an index and print the element at that 
index. Handle ArrayIndexOutOfBoundsException. 
*/


import java.util.Scanner;

public class P_02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[5];
        System.out.print("Enter the 5 elements: ");
        for(int i=0; i<5; i++) arr[i] = scan.nextInt();

        System.out.print("Enter an index (0-4): ");
        int index = scan.nextInt();

        try{
            System.out.println("Element at index " + index + " = " + arr[index]);
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Error: Invalid index! Please enter a value between 0 and 4.");
        }

        scan.close();
    }
}
