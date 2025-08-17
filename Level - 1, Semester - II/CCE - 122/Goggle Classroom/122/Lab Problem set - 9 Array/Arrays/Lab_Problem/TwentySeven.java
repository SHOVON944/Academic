package Lab_Problem;
import java.util.Scanner;

public class TwentySeven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n1 = scan.nextInt();
        int[] arr1 = new int[n1];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n1; i++) arr1[i] = scan.nextInt();

        for(int i : arr1){
            if(i%2==0){
                System.out.print(i + " ");
            }
        }
        System.out.println();

        for(int i : arr1){
            if(i%2!=0){
                System.out.print(i + " ");
            }
        }

        scan.close();
    }
}
