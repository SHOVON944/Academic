package RecursiveMethods;
import java.util.Scanner;

public class Thirten {

    public static int productsCall(int[] arr, int index, int prdct){
        if(arr.length==index) return 1;
        return arr[index] * productsCall(arr, index+1, prdct);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of array: ");
        int[] num = new int[scan.nextInt()];
        System.out.print("Enter the elements: ");
        for(int i=0; i<num.length; i++) num[i] = scan.nextInt();

        int products = productsCall(num, 0,  1);
        System.out.println("The products of all numbers present in the array:  " + products);

        scan.close();
    }
}
