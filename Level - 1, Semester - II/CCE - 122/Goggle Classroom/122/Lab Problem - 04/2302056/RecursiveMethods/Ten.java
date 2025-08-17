package RecursiveMethods;
import java.util.Scanner;

public class Ten {

    public static int lengthCall(String str){
        if(str.isEmpty()) return 0;
        return 1 + lengthCall(str.substring(1));  // str.substring(1) -> 1[0 base indes] no index theke ses porjonto part carry kora
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String s = scan.nextLine();
        int ans = lengthCall(s);
        System.out.println("The string length is: " + ans);
        scan.close();
    }
}
