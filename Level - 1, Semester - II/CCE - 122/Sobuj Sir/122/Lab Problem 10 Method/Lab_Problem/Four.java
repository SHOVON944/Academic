package Lab_Problem;
import java.util.Scanner;

public class Four {

    public static void vowel(String s) {
        int vCount = 0;
        for(char ch : s.toLowerCase().toCharArray()){
            if(ch=='a'  ||  ch=='e'  || ch=='i'  ||  ch=='o'  ||  ch=='u') vCount++;
        }
        System.out.println("The num of vowel present in the String is: " + vCount);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String s = scan.nextLine();
        vowel(s);

        scan.close();
    }
}
