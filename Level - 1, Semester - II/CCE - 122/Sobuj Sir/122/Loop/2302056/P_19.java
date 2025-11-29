import java.util.Scanner;

public class P_19 {

    public static void checkVotingEligibility(int age) {
        if (age >= 18) {
            System.out.println("You are eligible to vote.");
        } else {
            System.out.println("You are not eligible to vote yet.");
        }
    }

    public static void checkDrivingEligibility(int age) {
        if (age >= 16) {
            System.out.println("You are eligible to drive.");
        } else {
            System.out.println("You are not eligible to drive yet.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your age: ");
        int age = scanner.nextInt();
        checkVotingEligibility(age);
        checkDrivingEligibility(age);
    }
}