import java.util.Scanner;

public class P_08 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the money: ");
        int money = scan.nextInt();
        int m500, m200, m100;

        if(money%100!=0){
            System.out.println("Cannot dispense: Amount must be a multiple of 100.");
        } else{
            m500 = money/500;
            money %= 500;

            m200 = money/200;
            money %= 200;

            m100 = money/100;
            money %= 100;

            if(money == 0){

                System.out.println("₹500 notes: " + m500);
                System.out.println("₹200 notes: " + m200);
                System.out.println("₹100 notes: " + m100);
            } else{
                System.out.println("Cannot dispense using ₹500, ₹200, ₹100 notes only.");
            }
        }
    }
}
