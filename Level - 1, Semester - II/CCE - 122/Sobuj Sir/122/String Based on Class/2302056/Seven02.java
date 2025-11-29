// using sort

import java.util.Arrays;
import java.util.Scanner;

public class Seven02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String str1 = scan.nextLine().toLowerCase();

        System.out.print("Enter second string: ");
        String str2 = scan.nextLine().toLowerCase();

        if (str1.length() != str2.length()) {
            System.out.println("Not an anagram");
            scan.close();
            return;
        }

        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        Arrays.sort(arr1);
        Arrays.sort(arr2);

        if (Arrays.equals(arr1, arr2)) {
            System.out.println("Strings are anagram");
        } else {
            System.out.println("Not an anagram");
        }

        scan.close();
    }
}
