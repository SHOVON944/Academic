package class_assignment;
import java.util.Scanner;

public class P_06 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter digit(0-9) : ");
        int digit = scan.nextByte();
        
        if(digit==0) System.out.println("Zero");
        if(digit==1) System.out.println("One");
        if(digit==2) System.out.println("Two");
        if(digit==3) System.out.println("Three");
        if(digit==4) System.out.println("Four");
        if(digit==5) System.out.println("Five");
        if(digit==6) System.out.println("Six");
        if(digit==7) System.out.println("Seven");
        if(digit==8) System.out.println("Eight");
        if(digit==9) System.out.println("Nine");
    }
}
