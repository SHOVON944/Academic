import java.util.Scanner;

class PositiveNumberException extends Exception {
    public PositiveNumberException(String message) {
        super(message);
    }
}

public class CheckNonPositiveNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers one by one (enter 0 to stop):");

        try {
            while (true) {
                int num = scanner.nextInt();
                if (num == 0) break; // 0 dile user input bad
                if (num > 0) {
                    throw new PositiveNumberException("Error: The input contains a positive number: " + num);
                }
            }
            System.out.println("The input contains only non-positive numbers.");
        } catch (PositiveNumberException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter integers only.");
        } finally {
            scanner.close();
        }
    }
}
