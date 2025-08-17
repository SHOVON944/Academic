package RecursiveMethods;
import java.util.Scanner;

public class Fiften {

    public static boolean sortCall(int[] arr, int index){
        if(arr.length-1 == index) return true;
        if(arr[index] > arr[index + 1]) return false;
        return sortCall(arr, index + 1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of array: ");
        int[] num = new int[scan.nextInt()];
        System.out.print("Enter the elements: ");
        for(int i=0; i<num.length; i++) num[i] = scan.nextInt();

        boolean result = sortCall(num, 0);
        if(result){
            System.out.println("The array is sorted.");
        } else{
            System.out.println("The array is not sorted.");
        }

        scan.close();
    }
}
