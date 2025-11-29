package Lab_Problem;
import java.util.Scanner;


public class Seven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The size of number array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the numberic Elements: ");
        for(int i=0; i<arr.length; i++) arr[i] = scan.nextInt();
        System.out.print("Enter the remove value: ");
        int remove = scan.nextInt();
        int[] store = new int[n-1];
        int index = 0;
        for(int i=0; i<arr.length; i++){
            if(arr[i]==remove){
                continue;
            }
            store[index] = arr[i];
            index++;
        }
        System.out.println("The new array without remove value is:  ");
        for(int i=0;  i<n-1;  i++) System.out.print(store[i] + " ");

        scan.close();
    }
}
