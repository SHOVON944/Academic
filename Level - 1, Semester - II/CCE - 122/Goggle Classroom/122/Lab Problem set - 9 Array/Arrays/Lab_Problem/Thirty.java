package Lab_Problem;
import java.util.Scanner;

public class Thirty {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        boolean found = false;
        for(int i : arr){
            if( (i == 0)  ||  (i == -1)){
                found = true;
                break;
            }
        }

        if(!found){
            System.out.println("There are no 0 or -1 value present in the array.");
        } else{
            System.out.println("There are 0 or -1 value present in the array.");
        }

        scan.close();
    }
}
