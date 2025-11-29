package RecursiveMethods;
import java.util.Scanner;

public class Eleven {

    public static void permutaionsCall(String str, String result){
        if(str.isEmpty()){
            System.out.println(result);
            return;
        }

        for(int i=0; i<str.length(); i++){
            char ch = str.charAt(i);
            String remaining = str.substring(0, i) + str.substring(i+1);
            permutaionsCall(remaining, result + ch);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String s = scan.nextLine();
        System.out.println("All Permutaions: \n");
        permutaionsCall(s, "");
        scan.close();
    }
}
