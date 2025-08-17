package Lab_Problem;
import java.util.Scanner;

public class One {

    public static void sortNum(int[] num){
        int lngt = num.length;
        for(int i=0; i<lngt-1; i++){
            for(int j=0; j<lngt-i-1; j++){
                if(num[j]>num[j+1]){
                    int temp = num[j];
                    num[j] = num[j+1];
                    num[j+1] = temp;
                }
            }
        }
    }


    public static void sortWords(String[] s){
        int lngt = s.length;
        for(int i=0; i<lngt-1; i++){
            for(int j=0; j<lngt-i-1; j++){
                if( s[j].compareTo(s[j+1])>0){
                    String temp = s[j];
                    s[j] = s[j+1];
                    s[j+1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter The size of number array: ");
        int[] arr = new int[scan.nextInt()];
        System.out.print("Enter the numberic Elements: ");
        for(int i=0; i<arr.length; i++) arr[i] = scan.nextInt();
        sortNum(arr);
        System.out.print("Sorted numeric array is: ");
        for(int i : arr) System.out.print(i + " ");
        System.out.println();

        System.out.println("Enter the size of words array ");
        String[] words = new String[scan.nextInt()];
        scan.nextLine();            // consume newLine...must use 
        System.out.println("Enter the word Elements: ");
        for(int i=0; i<words.length; i++) words[i] = scan.nextLine();
        sortWords(words);
        System.out.print("Sorted Word array:");
        for(String s: words) System.out.print(s + " ");

        scan.close();
    }
}
