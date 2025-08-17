import java.util.Scanner;

public class P_14 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter first number: ");
        double num1 = input.nextDouble();
        System.out.print("Enter operator (+, -, *, /): ");
        char operator = input.next().charAt(0);
        double result;
        System.out.print("Enter second number: ");
        double num2 = input.nextDouble();

        switch (operator) {
            case '+':
                System.out.println("Result: " + (num1+num2));
                break;

            case '-':
                System.out.println("Result: " + (num1-num2));
                break;

            case '*':
                System.out.println("Result: " + (num1*num2));
                break;

            case '/':
                if (num2 != 0) {
                    System.out.println("Result: " + (num1/num2));
                    break;
                } else {
                    System.out.println("Error: Division by zero!");
                }
                break;

            default:
                System.out.println("Invalid operator!");
        }
    }
}
