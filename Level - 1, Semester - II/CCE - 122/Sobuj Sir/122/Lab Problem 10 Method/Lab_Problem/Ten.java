package Lab_Problem;

import java.util.Scanner;

public class Ten {

    public static boolean leapYrCall(int x){
        if((x%400==0)  ||  ((x%4==0) &&  (x%100!=0))) return true;
        else return false;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the year: ");
        int yr = scan.nextInt();
        boolean check = leapYrCall(yr);
        System.out.println("The year " + yr + " is leapyear? :" + check);

        scan.close();
    }
}
