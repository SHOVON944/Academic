import java.util.Scanner;

public class P_11 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter weight (in kg): ");
        float weight = scan.nextFloat();
        System.out.print("Enter height (in meters): ");
        float height = scan.nextFloat();

        float bmi = (float) (weight / Math.pow(height, 2));

        System.out.println("Your BMI is: " + bmi);
        if (bmi < 18.5) {
            System.out.println("Category: Underweight");
        } else if (bmi >= 18.5 && bmi < 25) {
            System.out.println("Category: Normal");
        } else if (bmi >= 25 && bmi < 30) {
            System.out.println("Category: Overweight");
        } else {
            System.out.println("Category: Obese");
        }
    }
}
