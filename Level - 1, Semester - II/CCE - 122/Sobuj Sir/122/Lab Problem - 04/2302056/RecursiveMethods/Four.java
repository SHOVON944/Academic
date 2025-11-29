package RecursiveMethods;
import java.util.Scanner;

public class Four {

    public static String revCall(String str){
        if(str.isEmpty()) return "";
        return revCall(str.substring(1)) + str.charAt(0);  // str.substring(1) -> 2 no index theke ses porjonto part carry kora
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String s = scan.nextLine();
        String check = s;
        String ans = revCall(s);

        if(check.equals(ans)) System.out.println("The string is palindrome string.");
        else System.out.println("The string is not a palindrome string.");


        scan.close();
    }
}
