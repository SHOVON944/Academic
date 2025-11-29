package Lab_Problem;

import java.util.Scanner;

public class Eighten {

    public static void sortCall(int[] arr){
        int temp;
        if(arr[0]>arr[1]) { temp = arr[0]; arr[0] = arr[1]; arr[1] = temp; }
        if(arr[0]>arr[2]) { temp = arr[0]; arr[0] = arr[2]; arr[2] = temp; }
        if(arr[1]>arr[2]) { temp = arr[1]; arr[1] = arr[2]; arr[2] = temp; }
    }

    public static boolean checkCall(int[] arr){
        sortCall(arr);
        if((arr[0] - arr[1])  ==  (arr[1] - arr[2])) return true; 

        return false;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[3];
        System.out.println("Enter a, b, c: ");
        for(int i=0; i<3; i++) arr[i] = scan.nextInt();
        boolean check = checkCall(arr);
        System.out.println("The  three number is consecutive? : " + check);

        scan.close();
    }
}
