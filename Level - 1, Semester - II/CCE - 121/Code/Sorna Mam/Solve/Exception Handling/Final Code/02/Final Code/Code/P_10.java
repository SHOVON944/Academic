/*
 Student Result Processing 
 Input marks of students in 3 subjects. 
 If any mark is negative or greater than 100, throw a custom exception 
InvalidMarksException. 
 Otherwise, calculate the average and display the result.
*/

import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

public class P_10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter marks for Subject 1: ");
            double m1 = sc.nextDouble();
            System.out.print("Enter marks for Subject 2: ");
            double m2 = sc.nextDouble();
            System.out.print("Enter marks for Subject 3: ");
            double m3 = sc.nextDouble();

            if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
                throw new InvalidMarksException("Marks must be between 0 and 100!");
            }

            double average = (m1 + m2 + m3) / 3.0;

            System.out.println("\n--- Student Result ---");
            System.out.println("Average Marks: " + average);

            if (average >= 80)
                System.out.println("Grade: A+");
            else if (average >= 70)
                System.out.println("Grade: A");
            else if (average >= 60)
                System.out.println("Grade: B");
            else if (average >= 50)
                System.out.println("Grade: C");
            else if (average >= 40)
                System.out.println("Grade: D");
            else
                System.out.println("Grade: F");
        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input! Please enter numeric marks.");
        } finally {
            sc.close();
            System.out.println("\nResult processing completed.");
        }
    }
}
