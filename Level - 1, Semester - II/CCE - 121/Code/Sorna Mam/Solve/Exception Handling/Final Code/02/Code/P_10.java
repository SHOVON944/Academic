/*
Student Result Processing 
 Input marks of students in 3 subjects. 
 If any mark is negative or greater than 100, throw a custom exception 
InvalidMarksException. 
 Otherwise, calculate the average and display the result. 
*/

import java.util.Scanner;


class InvalidMarksException extends Exception{
    InvalidMarksException(String message){
        super(message);
    }
}

public class P_10 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter 1st subject mark: ");
        int first = scan.nextInt();
        System.out.print("Enter 2nd subject mark: ");
        int second = scan.nextInt();
        System.out.print("Enter 3rd subject mark: ");
        int third = scan.nextInt();

        try{
            if( (first<0 || first>100)  ||  (second<0 || second>100)  ||  (third<0 || third>100) ){
                throw new InvalidMarksException("Invalid Marks input.");
            }
            double avrg = (first + second + third) / 3.0;
            System.out.println("3 subjects mark average is: " + avrg);
        } catch(InvalidMarksException e){
            System.out.println("Error. " + e.getMessage());
        } finally{
            scan.close();
        }
    }
}


/*
    -------------Better------------     
import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String msg) { super(msg); }
}

public class StudentResultProcessing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[] marks = new double[3];
        try {
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter mark for subject " + (i+1) + ": ");
                marks[i] = sc.nextDouble();
                if (marks[i] < 0 || marks[i] > 100) throw new InvalidMarksException("Marks must be between 0 and 100.");
            }
            double avg = (marks[0] + marks[1] + marks[2]) / 3.0;
            System.out.printf("Average = %.2f\n", avg);
        } catch (InvalidMarksException e) {
            System.out.println("InvalidMarksException: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

*/