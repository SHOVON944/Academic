import java.util.Scanner;

public class Three {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the string (Case sensitive): ");
        String s = scan.nextLine();

        s = s.toLowerCase();
        int countVowel = 0;
        int countConsonant = 0;
        for(char ch : s.toCharArray()){ // converting the string to character array
            if( (ch=='a')  ||  (ch=='e')  ||  (ch=='i')  ||  (ch=='o')  ||  (ch=='u') ){
                countVowel++;
            } else{
                countConsonant++;
            }
        }
        System.out.println("Vowel present in the String: " + countVowel);
        System.out.println("Consonant present in the String: " + countConsonant);

        scan.close();
    }
}
