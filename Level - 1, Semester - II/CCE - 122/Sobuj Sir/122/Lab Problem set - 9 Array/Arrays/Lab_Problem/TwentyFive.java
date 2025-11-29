package Lab_Problem;
import java.util.Scanner;

public class TwentyFive {
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
        System.out.print("Enter the 2nd array elements: ");
        for(int i=0; i<n2; i++) arr2[i] = scan.nextInt();

        System.out.print("Enter the size of the 3nd array: ");
        int n3 = scan.nextInt();
        int[] arr3 = new int[n3];
        System.out.print("Enter the 3rd array elements: ");
        for(int i=0; i<n3; i++) arr3[i] = scan.nextInt();

        int i = 0, j = 0, k = 0;
        while( (i<arr1.length) && (j<arr2.length) && (k<arr3.length) ){
            if((arr1[i]==arr2[j]) && (arr2[j]==arr3[k])){
                System.out.print(arr1[i] + " ");
                i++;
                j++;
                k++;
            } else if(arr1[i]<arr2[j]){
                i++;
            } else if(arr2[j]<arr3[k]){
                j++;
            } else{
                k++;
            }
        }

        scan.close();
    }
}
