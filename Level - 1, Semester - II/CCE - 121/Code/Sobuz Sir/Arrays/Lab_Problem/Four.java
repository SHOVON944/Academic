package Lab_Problem;
import java.util.Scanner;


public class Four {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The size of number array: ");
        double[] arr = new double[scan.nextInt()];
        System.out.print("Enter the numberic Elements: ");
        double sum = 0;
        for(int i=0; i<arr.length; i++){
            arr[i] = scan.nextInt();
            sum += arr[i];
        }
        System.out.println("Avarage of the array element is: " + (sum/arr.length));

        scan.close();
    }
}
