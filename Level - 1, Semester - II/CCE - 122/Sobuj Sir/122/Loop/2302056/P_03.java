import java.util.Scanner;

public class P_03 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the Number(1-100): ");
        int number = scan.nextInt();
        int check = number/10;
        switch (check){
            case 10:
            case 9:
            case 8:
                System.out.println("Grade A");
                break;
            case 7:
                System.out.println("Grade B");
                break;
            case 6:
                System.out.println("Grade C");
                break;
            case 5:
                System.out.println("Grade D");
                break;
            default:
                System.out.println("Grade F");
        }
    }
}
