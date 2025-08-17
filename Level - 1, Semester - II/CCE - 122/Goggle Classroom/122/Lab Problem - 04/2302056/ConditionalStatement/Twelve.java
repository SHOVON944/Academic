package ConditionalStatement;

import java.util.Scanner;

public class Twelve {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input element: ");
        int[] arr = new int[5];
        for(int i=0; i<5; i++) arr[i] = scan.nextInt();
        int sum = 0;
        for(int i=0; i<5; i++){
            System.out.println(arr[i]);
            sum += arr[i];
        }
        System.out.println("The sum of 5 no is : " + sum);
        System.out.println("The avarage is: " + (sum/5));

        scan.close();
    }
}
