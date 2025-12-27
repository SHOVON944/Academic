/*
Write a Java program to display the following character rhombus structure.

Input the number: 7

Expected Output:

      A
     ABA
    ABCBA
   ABCDCBA
  ABCDEDCBA
 ABCDEFEDCBA
ABCDEFGFEDCBA
 ABCDEFEDCBA
  ABCDEDCBA
   ABCDCBA
    ABCBA
     ABA
      A
*/

import java.util.Scanner;

public class Alphabate_Inc_Dec_Rhombus_Piramid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt();

        // Upper half
        for (int i = 1; i <= rows; i++) {
            //  spaces
            for (int j = i; j < rows; j++) {
                System.out.print(" ");
            }

            //  increasing numbers
            for (int k = 1; k <= i; k++) {
                System.out.print((char) ('A'+ k - 1));
            }

            //  decreasing numbers
            for (int k = i - 1; k >= 1; k--) {
                System.out.print((char) ('A'+ k - 1));
            }

            System.out.println();
        }

        // Lower half
        for (int i = rows - 1; i >= 1; i--) {
            //  spaces
            for (int j = i; j < rows; j++) {
                System.out.print(" ");
            }

            //  increasing numbers
            for (int k = 1; k <= i; k++) {
                System.out.print((char) ('A'+ k - 1));
            }

            //  decreasing numbers
            for (int k = i - 1; k >= 1; k--) {
                System.out.print((char) ('A'+ k - 1));
            }

            System.out.println();
        }

        sc.close();
    }
}
