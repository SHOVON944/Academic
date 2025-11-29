import java.util.Scanner;

public class Ten {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = scan.nextLine();
        String[] store = s.split(" ");
        StringBuilder result = new StringBuilder();

        for(String word : store){
            if(store.length>0){
                String makeWord = word.substring(0, 1).toUpperCase() + word.substring(1 );
                result.append(makeWord).append(" ");
            }
        }
        System.out.println("Capitalized sentence: " + result.toString().trim());    // .trim() -> removing first and last unneccessary white space

        scan.close();
    }
}
