// using bollean array
import java.util.Scanner;

public class Six02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scan.nextLine();
        
        boolean[] seen = new boolean[256]; // ASCII char support
        StringBuilder result = new StringBuilder();
        
        for (char ch : input.toCharArray()) {
            if (!seen[ch]) {
                result.append(ch);
                seen[ch] = true;
            }
        }
        
        System.out.println("After removing duplicates: " + result.toString());
        scan.close();
    }
}
