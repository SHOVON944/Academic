package Lab_Problem;
import java.util.Scanner;


public class Five {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The size of number array: ");
        int[] arr = new int[scan.nextInt()];
        System.out.print("Enter the numberic Elements: ");
        for(int i=0; i<arr.length; i++) arr[i] = scan.nextInt();
        System.out.print("Enter the specific value: ");
        int target = scan.nextInt();
        boolean found = false;
        for(int i : arr){
            if(i==target){
                found = true;
                break;
            }
        }
        if(found) System.out.println("Specific value found.");
        else System.out.println("Specific value not found.");

        scan.close();
    }
}
