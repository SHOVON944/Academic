/*
Write a Java program to sort an array of given integers using the Bubble Sorting (Ascending and Descending) algorithm.
Input needs to be taken from the keyboard.

Original Array: [7, -5, 3, 2, 1, 0, 45]
Sorted Array: [-5, 0, 1, 2, 3, 7, 45]
*/

import java.util.Scanner;

public class BubbleSortExample {
    private int[] arr;

    {
        System.out.println("Instance Initializer Block Executed!");
    }

    public BubbleSortExample(int[] inputArray) {
        arr = inputArray;
        System.out.println("Constructor Called! Array Initialized.");
    }

    //  Sort Ascending
    public void sortAscending() {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) { // Ascending condition
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("\nArray Sorted in Ascending Order:");
        display();
    }

    //  Sort Descending
    public void sortDescending() {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) { // Descending condition
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Array Sorted in Descending Order:");
        display();
    }

    public void display() {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int size = sc.nextInt();

        int[] numbers = new int[size];
        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            numbers[i] = sc.nextInt();
        }

        // Object make hole -> IIB colbe -> tarpor Constructor
        BubbleSortExample sorter = new BubbleSortExample(numbers);

        sorter.sortAscending();
        sorter.sortDescending();

        sc.close();
    }
}
