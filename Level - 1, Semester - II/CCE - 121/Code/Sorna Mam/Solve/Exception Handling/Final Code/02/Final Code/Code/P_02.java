/*
Array Index Out of Bound 
Create an array of 5 elements. Ask the user to input an index and print the element at that 
index. Handle ArrayIndexOutOfBoundsException.
*/

import java.util.Scanner;

public class P_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] numbers = { 10, 20, 30, 40, 50 };

        try {
            System.out.print("Enter an index (0 to 4): ");
            int index = sc.nextInt();
            System.out.println("Element at index " + index + " = " + numbers[index]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index out of range! Please enter a value between 0 and 4.");
        } finally {
            sc.close();
        }
    }
}
