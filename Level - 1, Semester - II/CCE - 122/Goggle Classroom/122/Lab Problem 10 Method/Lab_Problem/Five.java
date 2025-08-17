package Lab_Problem;
import java.util.Scanner;

public class Five {

    public static void countWord(String s) {
        int wordCount = 0;
        for(char ch : s.toLowerCase().toCharArray()){
            if(ch==' ') wordCount++;
        }
        System.out.println("The num of character present in the String is: " + wordCount+1);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String s = scan.nextLine();
        countWord(s);

        scan.close();
    }
}
