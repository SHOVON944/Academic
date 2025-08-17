package Lab_Problem;
import java.util.Scanner;

public class ThirtyFour {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        boolean checking = true;
        for(int i=0; i<n-1; i++){
            if(arr[i+1] != arr[i] + 1){
                checking = false;
                break;
            }
        }
        if(checking){
            System.out.println("The array elements is consecutive.");
        } else{
            System.out.println("The array elements is not consecutive.");
        }

        scan.close();
    }
}
