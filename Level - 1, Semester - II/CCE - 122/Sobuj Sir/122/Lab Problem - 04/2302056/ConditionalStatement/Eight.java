package ConditionalStatement;
import java.util.Scanner;

public class Eight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input alphabate: ");
        String s = scan.nextLine();
        if(s.length()>1) System.out.println("Error, not a single character.");
        char ch = s.charAt(0);
        if(!Character.isLetter(ch)) System.out.println("It is not a character, please input valid character.");

        ch = Character.toLowerCase(ch);
        if(ch=='a'  ||  ch=='e'  ||  ch=='i'  ||  ch=='o'  ||  ch=='u'){
            System.out.println("It is a Vower.");
        } else{
            System.out.println("It is a Constant.");
        }

        scan.close();
    }
}
