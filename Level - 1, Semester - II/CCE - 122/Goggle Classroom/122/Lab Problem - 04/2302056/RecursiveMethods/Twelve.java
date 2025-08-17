package RecursiveMethods;
import java.util.Scanner;

public class Twelve {

    public static int maxEleCall(int[] arr, int index, int max){

        if(arr.length==index) return max;
        if(max<arr[index]){
            return maxEleCall(arr, index+1, arr[index]);
        } else{
            return maxEleCall(arr, index+1, max);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of array: ");
        int[] num = new int[scan.nextInt()];
        System.out.print("Enter the elements: ");
        for(int i=0; i<num.length; i++) num[i] = scan.nextInt();

        int max = maxEleCall(num, 0,  num[0]);
        System.out.println("The maximum numbers present in the array:  " + max);

        scan.close();
    }
}
