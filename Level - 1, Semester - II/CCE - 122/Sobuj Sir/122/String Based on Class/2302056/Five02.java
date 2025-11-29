
/* Using character array
    eitate 2 ta string er majhe space print hoina
*/
import java.util.Scanner;

public class Five02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Enter first string: ");
        String str1 = scan.nextLine();
        
        System.out.print("Enter second string: ");
        String str2 = scan.nextLine();
        
        char[] combined = new char[str1.length() + str2.length()];
        
        for (int i = 0; i < str1.length(); i++) {
            combined[i] = str1.charAt(i);
        }
        
        for (int i = 0; i < str2.length(); i++) {
            combined[str1.length() + i] = str2.charAt(i);
        }
        
        String result = new String(combined);
        System.out.println("Combined String: " + result);
        
        scan.close();
    }
}