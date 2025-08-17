// using string builder

import java.util.Scanner;

public class Five01 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the 1st string: ");
        String first = scan.nextLine();
        System.out.print("Enter the 2nd string: ");
        String second = scan.nextLine();

        StringBuilder sb = new StringBuilder();
        sb.append(first);
        sb.append(" ");
        sb.append(second);

        String s = sb.toString();   // convert to string
        System.out.println("Combined String is: " + s);

        scan.close();
    }
}
