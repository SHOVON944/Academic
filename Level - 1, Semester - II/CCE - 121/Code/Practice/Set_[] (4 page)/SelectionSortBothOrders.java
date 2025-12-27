/*
Write a Java program to sort an array of given integers using the Selection Sort Algorithm.
Original Array:
[7, -5, 3, 2, 1, 0, 45]
Sorted Array:
[-5, 0, 1, 2, 3, 7, 45]
*/

import java.util.Scanner;

public class SelectionSortBothOrders {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements in the array: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Print original array
        System.out.print("Original Array: [");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("]");

        //  ascending order
        int[] asc = arr.clone();  // Copy array for ascending sort
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (asc[j] < asc[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = asc[minIndex];
            asc[minIndex] = asc[i];
            asc[i] = temp;
        }

        // Print ascending array
        System.out.print("Sorted Array (Ascending): [");
        for (int i = 0; i < n; i++) {
            System.out.print(asc[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("]");

        //  descending order
        int[] desc = arr.clone();  // Copy array for descending sort
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (desc[j] > desc[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = desc[maxIndex];
            desc[maxIndex] = desc[i];
            desc[i] = temp;
        }

        System.out.print("Sorted Array (Descending): [");
        for (int i = 0; i < n; i++) {
            System.out.print(desc[i]);
            if (i < n - 1) System.out.print(", ");
        }
        System.out.println("]");

        sc.close();
    }
}



/*

import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // (Ascending / Descending)
        System.out.print("Sort in Ascending or Descending order? (A/D): ");
        char choice = sc.next().charAt(0);

        // Selection Sort Logic
        for (int i = 0; i < n - 1; i++) {
            int index = i;

            for (int j = i + 1; j < n; j++) {
                if (choice == 'A' || choice == 'a') {
                    if (arr[j] < arr[index]) {
                        index = j;
                    }
                } else if (choice == 'D' || choice == 'd') {
                    if (arr[j] > arr[index]) {
                        index = j;
                    }
                }
            }

            // swap
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        System.out.print("Sorted array: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
        sc.close();
    }
}


*/