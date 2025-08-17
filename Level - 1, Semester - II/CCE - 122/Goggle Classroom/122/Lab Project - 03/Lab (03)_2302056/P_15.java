package class_assignment;

import java.util.Scanner;

public class P_15 {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int a = scan.nextInt();
        int rev = a;
        int sum = 0;
        while(a>0){
            int mod = a%10;
            sum += Math.pow(mod, 3);
            a /= 10;
        }
        if(rev == sum) System.out.println("This is a Armstrong Number.");
        else System.out.println("This is not a Armstrong Number.");
    }
}
