package Lab_Problem;
import java.util.Scanner;

public class TwentyTwo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the array elements: ");
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        System.out.print("Enter the sum to find: ");
        int target = scan.nextInt();
        boolean found = false;
        for(int i=0; i<n-1; i++){
            for(int j=i+1; j<n; j++){
                if(arr[i] + arr[j] == target){
                    System.out.println(arr[i] + " + " + arr[j] + " = " + target);
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("There are no pair value which sum is equal to the target(sum) value.");
        }

        scan.close();
    }
}
