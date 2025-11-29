package Lab_Problem;
import java.util.Scanner;

public class TwentyThree {
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

        boolean check = true;
        if(n1 != n2){
            check = false;
        } else{
            for(int i=0; i<n1; i++){
                if(arr1[i]!=arr2[i]){
                    check = false;
                    break;
                }
            }
        }

        if(check){
            System.out.println("Both array is equal.");
        } else{
            System.out.println("Array are not equal.");
        }

        scan.close();
    }
}
