// using frequency arrays
import java.util.Scanner;

public class Seven01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String str1 = scan.nextLine().toLowerCase();    // direct convert to lower case

        System.out.print("Enter second string: ");
        String str2 = scan.nextLine().toLowerCase();

        if (str1.length() != str2.length()) {
            System.out.println("Not an anagram");
            scan.close();
            return;
        }

        int[] freq = new int[256];

        for (char ch : str1.toCharArray()) {
            freq[ch]++;
        }

        for (char ch : str2.toCharArray()) {
            freq[ch]--;
        }

        for (int count : freq) {
            if (count != 0) {
                System.out.println("Not an anagram");
                scan.close();
                return;
            }
        }

        System.out.println("Strings are anagram");
        scan.close();
    }
}
