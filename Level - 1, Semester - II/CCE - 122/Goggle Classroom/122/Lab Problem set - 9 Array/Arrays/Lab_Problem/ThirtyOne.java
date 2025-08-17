package Lab_Problem;
import java.util.Scanner;

public class ThirtyOne {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        int sum = 0;
        for(int i : arr){
            if(i == 10){
                sum += 10;
            }
        }
        boolean check = (sum == 30);
        System.out.println("Result: " + check);


        scan.close();
    }
}
