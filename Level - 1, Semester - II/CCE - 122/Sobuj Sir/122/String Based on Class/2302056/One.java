import java.util.Scanner;

public class One {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = scan.nextLine();

        char[] charA = s.toCharArray(); // Converting the string to a character array
        int size = charA.length;
        for(int i=size-1; i>=0; i--){
            System.out.print(charA[i]);
        }

        scan.close();
    }
}
