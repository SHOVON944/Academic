package Lab_Problem;
import java.util.Scanner;

public class Tweleve {

    public static void matrixGenerateCall(int x){
        for(int i=1;  i<=x; i++){
            for(int j=1; j<=x; j++){
                System.out.print((int)(Math.random()*2) + " ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n = scan.nextInt();
        matrixGenerateCall(n);

        scan.close();
    }
}
