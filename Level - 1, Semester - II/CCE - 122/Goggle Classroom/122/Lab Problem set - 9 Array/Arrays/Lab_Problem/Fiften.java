package Lab_Problem;
import java.util.Scanner;

public class Fiften {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the 1st array: ");
        int n1 = scan.nextInt();
        int[] arr1 = new int[n1];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n1; i++) arr1[i] = scan.nextInt();

        System.out.print("Enter the size of the 2nd array: ");
        int n2 = scan.nextInt();
        int[] arr2 = new int[n2];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n2; i++) arr2[i] = scan.nextInt();

        boolean found = false;
        for(int i=0; i<n1; i++){
            for(int j=0; j<n2; j++){
                if(arr1[i] == arr2[j]){
                    System.out.println("The duplicate value is: " + arr1[i]);
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("There are no common value between two arrays.");
        }

        scan.close();
    }
}
