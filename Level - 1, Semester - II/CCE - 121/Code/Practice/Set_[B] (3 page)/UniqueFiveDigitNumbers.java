/*
Write a Java program to create and display unique five-digit numbers using 1, 2, 3, 4, and 5.
Also, count how many five-digit unique numbers are there.
*/


public class UniqueFiveDigitNumbers {
    public static void main(String[] args) {
        int count = 0;

        System.out.println("All unique five-digit numbers using 1, 2, 3, 4, and 5:");

        for (int a = 1; a <= 5; a++) {
            for (int b = 1; b <= 5; b++) {
                for (int c = 1; c <= 5; c++) {
                    for (int d = 1; d <= 5; d++) {
                        for (int e = 1; e <= 5; e++) {
                            if (a != b && a != c && a != d && a != e &&
                                b != c && b != d && b != e &&
                                c != d && c != e &&
                                d != e) {

                                int number = a * 10000 + b * 1000 + c * 100 + d * 10 + e;
                                System.out.println(number);
                                count++;
                            }
                        }
                    }
                }
            }
        }

        System.out.println("\nTotal unique numbers: " + count);
    }
}
