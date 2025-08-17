import java.util.Scanner;

public class Eight {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the sentence: ");
        String sentence = scan.nextLine().toLowerCase();    

        System.out.print("Enter word to count: ");
        String word = scan.nextLine().toLowerCase();

        String[] reserve = sentence.split(" ");

        int count = 0;
        for(String s : reserve){
            if(s.equals(word)){
                count++;
            }
        }
        System.out.println("The word '" + word + "' present in the String " + count + " times.");

        scan.close();
    }
}
