package RecursiveMethods;
import java.util.Scanner;

public class Six {

    public static String revCall(String str){
        if(str.isEmpty()) return "";
        return revCall(str.substring(1)) + str.charAt(0);  // str.substring(1) -> 1[0 base index] no index theke ses porjonto part carry kora
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String s = scan.nextLine();
        String ans = revCall(s);
        System.out.println("The reverse string of " + s + " is: " + ans);
        scan.close();
    }
}
