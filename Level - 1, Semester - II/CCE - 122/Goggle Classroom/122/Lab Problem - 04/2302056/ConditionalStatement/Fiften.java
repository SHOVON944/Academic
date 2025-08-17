package ConditionalStatement;
import java.util.Scanner;

public class Fiften {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input number: ");
        int n = scan.nextInt();
        int sum = 0;
        for(int i=1; i<=n; i++){
            if(i%2!=0){
                System.out.println(i);
                sum += i;
            }
        }
        System.out.println("The Sum of odd Natural Number upto " + n + "terms is: " + sum);

        scan.close();
    }
}
