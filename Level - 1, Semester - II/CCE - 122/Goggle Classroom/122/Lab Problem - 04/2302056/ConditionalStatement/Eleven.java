package ConditionalStatement;

import java.util.Scanner;

public class Eleven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input number: ");
        int n = scan.nextInt();
        int sum = 0;
        for(int i=1; i<=n; i++){
            System.out.println(i);
            sum += i;
        }
        System.out.println("The Sum of Natural Number upto " + n + " terms: " + sum);

        scan.close();
    }
}
