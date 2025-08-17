package Lab_Problem;
import java.util.Scanner;

public class Ten {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();
        int maximum = arr[0];
        int minimum = arr[0];
        for(int i=0; i<n; i++){
            if(maximum<arr[i]) maximum = arr[i];
            if(minimum>arr[i]) minimum = arr[i];
        }
        System.out.println("The maximum number present in the array: " + maximum);
        System.out.println("The minimum number present in the array: " + minimum);

        scan.close();
    }
}
