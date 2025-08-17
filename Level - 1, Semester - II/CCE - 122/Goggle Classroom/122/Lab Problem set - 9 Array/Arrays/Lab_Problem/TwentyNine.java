package Lab_Problem;
import java.util.Scanner;

public class TwentyNine {

    public static void bubbleSortCall(int[] arr, int n){
        for(int i=0; i<n-1; i++){
            for(int j=0; j<n-i-1; j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        bubbleSortCall(arr, n);

        int sum = 0;
        for(int i :  arr){
            sum += i;
        }
        int avgExcept = (sum - arr[n-1] - arr[0]) / (n - 2);
        System.out.println("Sum of the array elements except largest and smallest value: " + avgExcept);

        scan.close();
    }
}
