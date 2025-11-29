package ConditionalStatement;
import java.util.Scanner;

public class Four {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        double a = scan.nextDouble();
        if(a>=0){
            if(a<1) System.out.println("Positive small number.");
            else if(a>1000000) System.out.println("Positive large number.");
            else System.out.println("Positive number.");
        } else{
            if(Math.abs(a)<1) System.out.println("Negative small number.");
            else if(Math.abs(a)>1000000) System.out.println("Negative large number.");
            else System.out.println("Negative number.");
        }

        scan.close();
    }
}
