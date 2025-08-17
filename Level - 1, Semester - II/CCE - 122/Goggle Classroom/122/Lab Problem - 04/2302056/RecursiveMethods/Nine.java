package RecursiveMethods;
import java.util.Scanner;

public class Nine {

    public static int sumOddCall(int[] arr, int index){

        if(arr.length==index) return 0;
        if(arr[index]%2!=0){
            return 1 + sumOddCall(arr, index+1);
        } else{
            return sumOddCall(arr, index+1);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of array: ");
        int[] num = new int[scan.nextInt()];
        System.out.print("Enter the elements: ");
        for(int i=0; i<num.length; i++) num[i] = scan.nextInt();

        int count = sumOddCall(num, 0);
        System.out.println("The number of odd numbers present in the array:  " + count);

        scan.close();
    }
}
