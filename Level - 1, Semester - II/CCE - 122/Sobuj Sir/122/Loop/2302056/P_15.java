import java.util.Scanner;

public class P_15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an alphabet: ");
        char ch = scanner.next().charAt(0);

        if((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
            char lowerCh = Character.toLowerCase(ch);
            if(lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u') {
                System.out.println(ch + " is a Vowel.");
            } else{
                System.out.println(ch + " is a Consonant.");
            }
        } else{
            System.out.println("Invalid input!");
        }
    }
}
