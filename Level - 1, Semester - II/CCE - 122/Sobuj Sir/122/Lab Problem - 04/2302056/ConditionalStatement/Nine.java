package ConditionalStatement;

import java.util.Scanner;

public class Nine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Inpur a year: ");
        int y = scan.nextInt();
        if((y%400==0)   ||   ((y%4==0) && (y%100!=0))){
            System.out.println("It is a leap year.");
        } else{
            System.out.println("It is not a leap year.");
        }

        scan.close();
    }
}
