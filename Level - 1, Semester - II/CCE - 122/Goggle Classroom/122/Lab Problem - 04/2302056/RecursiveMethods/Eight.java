package RecursiveMethods;
import java.util.Scanner;

public class Eight {

    public static int countCall(int[] arr, int index, int target){

        if(arr.length==index) return 0;
        if(arr[index]==target){
            return 1 + countCall(arr, index+1, target);
        } else{
            return countCall(arr, index+1, target);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of array: ");
        int[] num = new int[scan.nextInt()];
        System.out.print("Enter the elements: ");
        for(int i=0; i<num.length; i++) num[i] = scan.nextInt();
        System.out.println("Enter the target value: ");
        int target = scan.nextInt();

        int count = countCall(num, 0, target);
        System.out.println("The number " + target + " appears " + count + " times.");

        scan.close();
    }
}
