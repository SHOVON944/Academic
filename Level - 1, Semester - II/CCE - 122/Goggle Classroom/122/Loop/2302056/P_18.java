import java.util.Scanner;
import java.util.regex.*;

public class P_18 {

    public static String checkPassword(String password) {
        int length = password.length();
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for(char c:password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        int score = 0;
        if (length >= 8) score++;
        if (length >= 12) score++;
        if (hasLower) score++;
        if (hasUpper) score++;
        if (hasDigit) score++;
        if (hasSpecial) score++;

        if (score<3) {
            return "Weak";
        } else if(score<5){
            return "Medium";
        } else{
            return "Strong";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String strength = checkPassword(password);
        System.out.println("Password strength: " + strength);
    }
}