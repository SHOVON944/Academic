package ConditionalStatement;
import java.util.Scanner;

public class TwentyNine{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st number: ");
        int a = scan.nextInt();
        int count = 0;
        if(a==0){
            count = 1;
        } else{
            while(a>0){
                a /= 10;
                count++;
            }
        }
        System.out.println("Number of digits in the number: " + count);

        scan.close();
    }
}
