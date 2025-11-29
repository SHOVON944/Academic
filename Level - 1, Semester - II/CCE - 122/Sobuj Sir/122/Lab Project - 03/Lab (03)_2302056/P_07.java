package class_assignment;
import java.util.Scanner;

public class P_07 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your age : ");
        int age = scan.nextByte();
        if(age>18  ||  age==18) System.out.println("You are eligible for voting.");
        else System.out.println("You are not eligible for voting.");
    }
}
