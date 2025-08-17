package Lab_Problem;
import java.util.Scanner;


// as same as 12 no...full same
public class Thirten {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = scan.nextInt();

        boolean found = false;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(arr[i] == arr[j]){
                    System.out.println("The duplicate value is: " + arr[i]);
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("There are no duplicate value in this array.");
        }

        scan.close();
    }
}
