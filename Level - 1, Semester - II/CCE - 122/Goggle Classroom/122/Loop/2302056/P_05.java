import java.util.Scanner;

public class P_05 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Cost Price(CP): ");
        int cp = scan.nextInt();
        System.out.print("Enter the Selling Price(CP): ");
        int sp = scan.nextInt();

        if(cp<sp) System.out.println("Profit");
        else System.out.println("Loss");
    }
}
