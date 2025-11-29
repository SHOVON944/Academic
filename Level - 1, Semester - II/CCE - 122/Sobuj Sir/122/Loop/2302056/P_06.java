import java.util.Scanner;

public class P_06 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st length: ");
        int a = scan.nextInt();
        System.out.print("Enter the 2nd length: ");
        int b = scan.nextInt();
        System.out.print("Enter the 3rd length: ");
        int c = scan.nextInt();

        if((a+b>c) && (b+c>a) && (a+c>b)){
            if(a==b && b==c) System.out.println("This is a Equilateral triangle.");
            else if(a==b || b==c || a==c) System.out.println("This is a Isosceles triangle");
            else System.out.println("This is a Scalene triangle.");
        } else{
            System.out.println("Invalid input. Any two length sum must be greater than other number.");
        }
    }
}
