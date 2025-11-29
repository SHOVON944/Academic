package Lab_Problem;
import java.util.Scanner;

public class Three {

    public static void middle(String s) {
        int size = s.length();
        if(size%2==0){
            System.out.println("The middle character is: " + s.charAt(size/2 - 1) + " " + s.charAt(size/2));
        } else{
            System.out.println("The middle character is: " + s.charAt(size/2));
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the String: ");
        String s = scan.nextLine();
        middle(s);

        scan.close();
    }
}
