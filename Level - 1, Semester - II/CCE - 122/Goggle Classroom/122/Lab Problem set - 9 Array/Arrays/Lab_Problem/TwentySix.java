package Lab_Problem;
import java.util.Scanner;

public class TwentySix {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n1 = scan.nextInt();
        int[] arr1 = new int[n1];
        System.out.print("Enter the 1st array elements: ");
        for(int i=0; i<n1; i++) arr1[i] = scan.nextInt();

        int  index = 0;
        for(int i : arr1){
            if(i  != 0){
                arr1[index] = i;
                index++;
            }
        }
        while(index<n1){
            arr1[index] = 0;
            index++;
        }

        for(int i : arr1){
            System.out.print(i + " ");
        }

        scan.close();
    }
}
