package Lab_Problem;
import java.util.Scanner;


public class Two {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The size of number array: ");
        int[] arr = new int[scan.nextInt()];
        System.out.print("Enter the numberic Elements: ");
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            arr[i] = scan.nextInt();
            sum += arr[i];
        }
        System.out.println("Sum of the array element is: " + sum);

        scan.close();
    }
}
