package Lab_Problem;
import java.util.Scanner;


public class Nine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The size of number array: ");
        int n = scan.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter the numberic Elements: ");
        for(int i=0; i<arr.length; i++) arr[i] = scan.nextInt();
        System.out.print("Enter the insert vaule: ");
        int in = scan.nextInt();
        System.out.print("Enter the insert vaule position(Avoid zero based index): ");
        int position = scan.nextInt();

        int index = 0;
        int[] insert = new int[n+1];
        for(int i=0; i<arr.length; i++){
            if(i+1 == position){
                insert[index] = in;
                index++;
            }
            insert[index] = arr[i];
            index++;
        }
        System.out.println();
        System.out.print("New array is(with inserted value): ");
        for(int i: insert){
            System.out.print(i + " ");
        }


        scan.close();
    }
}
