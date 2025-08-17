package Lab_Problem;
import java.util.Scanner;

public class TwentyFour {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the size of the 1st array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the 1st array elements(1,2,....n[Missing a number beteen 1 to n]): ");
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        int a = n + 1;
        int expectSum = (a * (a + 1)) / 2;      //  (n * (n + 1)) / 2
        int actualSum = 0;
        for(int i : arr){
            actualSum += i;
        }
        int missingNum = expectSum - actualSum;
        System.out.println("The missing number is: " + missingNum);

        scan.close();
    }
}
