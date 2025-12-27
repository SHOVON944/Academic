/*
Write a Java program to display Pascal’s triangle in normal order and also in reverse order.
Test Data
Input number of rows: 5

Expected Output:
Input number of rows: 5
     1 
    1 1 
   1 2 1 
  1 3 3 1 
 1 4 6 4 1 

 1 4 6 4 1
  1 3 3 1
   1 2 1
    1 1
     1
*/

import java.util.Scanner;

public class PascalTriangleBoth {

    static int factorial(int x) {
        int fact = 1;
        for (int i = 2; i <= x; i++)
            fact *= i;
        return fact;
    }

    static int nCr(int n, int r) {

        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int n = sc.nextInt();

        System.out.println("\n--- Pascal's Triangle (Normal Order) ---");
        for (int i = 0; i < n; i++) {
            for (int k = 1; k < n - i; k++)
                System.out.print(" ");
            for (int j = 0; j <= i; j++)
                System.out.print(nCr(i, j) + " ");
            System.out.println();
        }

        System.out.println("\n--- Pascal's Triangle (Reverse Order) ---");
        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k < n - i; k++)
                System.out.print(" ");
            for (int j = 0; j <= i; j++)
                System.out.print(nCr(i, j) + " ");
            System.out.println();
        }

        sc.close();
    }
}
