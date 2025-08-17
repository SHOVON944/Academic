import java.util.Scanner;

public class P_02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the year: ");
        int ly = scan.nextInt();

        if(ly%400==0 || (ly%4==0 && ly%100!=0)){
            System.out.printf("%d is a Leap Year.",ly);
        } else{
            System.out.printf("%d is not a Leap Year.",ly);
        }
    }
}
