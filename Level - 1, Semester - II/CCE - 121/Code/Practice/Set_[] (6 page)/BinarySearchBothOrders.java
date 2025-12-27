/*
Write a Java program to find a specified element in a given array of elements using Binary Search.
Input needs to be taken from the keyboard. Don't use any built-in method for this searching.

Input: 1, 5, 6, 7, 8, 11
Output: 11 is at index 5
*/


import java.util.Scanner;

public class BinarySearchBothOrders {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements in the array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Is the array ascending or descending? (A/D): ");
        char order = sc.next().toUpperCase().charAt(0);

        System.out.print("Enter element to search: ");
        int target = sc.nextInt();

        int index = -1;

        if (order == 'A') {
            index = binarySearchAsc(arr, target);
        } else if (order == 'D') {
            index = binarySearchDesc(arr, target);
        } else {
            System.out.println("⚠️ Invalid order! Enter A for ascending or D for descending.");
            sc.close();
            return;
        }

        if (index != -1) {
            System.out.println(target + " is at index " + index);
        } else {
            System.out.println(target + " not found in the array.");
        }

        sc.close();
    }

    // Binary search for ascending order
    public static int binarySearchAsc(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // Binary search for descending order
    public static int binarySearchDesc(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) { // reversed comparison
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
