package ConditionalStatement;
import java.util.Scanner;

public class Three {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        int a = scan.nextInt();
        System.out.print("Enter the 2nd number: ");
        int b = scan.nextInt();
        System.out.print("Enter the 3rd number: ");
        int c = scan.nextInt();

        if(a>b){
            if(a>c){
                System.out.println("The greatest: " + a);
            } else{
                System.out.println("The greatest: " + c);
            }
        } else{
            if(b>c){
                System.out.println("The greatest: " + b);
            } else{
                System.out.println("The greatest: " + c);
            }
        }

        scan.close();
    }
}
