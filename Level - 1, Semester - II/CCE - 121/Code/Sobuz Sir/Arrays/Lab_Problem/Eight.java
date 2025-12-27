package Lab_Problem;
import java.util.Scanner;


public class Eight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The size of number array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the numberic Elements: ");
        for(int i=0; i<arr.length; i++) arr[i] = scan.nextInt();
        int[] copy = new int[n];
        for(int i=0; i<arr.length; i++){
            copy[i] = arr[i];
            }
            System.out.print("Original value is : ");
            for(int i: arr){
                System.out.print(i + " ");
            }
            System.out.println();
            System.out.print("Copy value is     : ");
            for(int i: copy){
                System.out.print(i + " ");
            }


        scan.close();
    }
}
