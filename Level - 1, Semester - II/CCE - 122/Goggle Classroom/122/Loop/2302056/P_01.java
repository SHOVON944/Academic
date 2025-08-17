import java.util.Scanner;

public class P_01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the a number: ");
        int a = scan.nextInt();
        System.out.print("Enter the b number: ");
        int b = scan.nextInt();
        System.out.print("Enter the c number: ");
        int c = scan.nextInt();

        if(a>b){
            if(a>c){
                System.out.println("The largest number is : " + a);
            } else{
                System.out.println("The largest number is : " + c);
            }
        } else{
            if(b>c){
                System.out.println("The largest number is : " + b);
            } else{
                System.out.println("The largest number is : " + c);
            }
        }
    }
}
