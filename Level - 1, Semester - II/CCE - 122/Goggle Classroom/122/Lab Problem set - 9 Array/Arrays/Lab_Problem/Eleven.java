package Lab_Problem;
import java.util.Scanner;

public class Eleven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        System.out.print("The reverse array is: ");
        for(int i=n-1; i>=0; i--) System.out.print(arr[i] + " ");

        scan.close();
    }
}
