package Lab_Problem;

import java.util.Scanner;

public class Seventen {

    public static void count2Call(String s){
        int count = 0;
        for(char ch : s.toCharArray()){
            if(ch == '2') count++;
        }
        System.out.println("The number of digit that present in the integer which value is 2: " +  count);
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number:  ");
        String a = scan.nextLine();
        count2Call(a);

        scan.close();
    }
}
