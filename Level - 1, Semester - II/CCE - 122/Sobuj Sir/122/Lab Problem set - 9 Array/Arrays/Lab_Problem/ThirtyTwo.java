package Lab_Problem;
import java.util.Scanner;

public class ThirtyTwo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        boolean found65 = false;
        boolean found77 = false;
        boolean found = false;
        for(int i : arr){
            if(i == 65) found65 = true;
            if(i == 77) found77 = true;
            if( found65 && found77 ) {
                found = true;
                break;
            }
        }

        if(found){
            System.out.println("There are 65 and 77 value present in the array.");
        } else{
            System.out.println("There are no 65 and 77 value present in the array.");
        }

        scan.close();
    }
}
