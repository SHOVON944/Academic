import java.util.Scanner;

public class P_13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter 1st angle: ");
        int angle1 = scanner.nextInt();
        System.out.print("Enter 2nd angle: ");
        int angle2 = scanner.nextInt();
        System.out.print("Enter 3rd angle: ");
        int angle3 = scanner.nextInt();

        if ((angle1>0 && angle2>0 && angle3>0) && (angle1 + angle2 + angle3 == 180)) {
            System.out.println("The angles form a valid triangle.");
        } else {
            System.out.println("The angles do NOT form a valid triangle.");
        }
    }
}
