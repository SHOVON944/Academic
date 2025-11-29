package Lab_Problem;
import java.util.Scanner;

public class TwentyThree {

    public static boolean allVowelCall(String s){
        for(char ch : s.toCharArray()){
            ch = Character.toLowerCase(ch);
            if(ch!='a'  &&  ch!='e'  &&  ch!='i'  &&  ch!='o'  &&  ch!='u') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input an String: ");
        String word = sc.nextLine();
        boolean check = allVowelCall(word);
        System.out.println("All the character of the string are vower! : " + check);

        sc.close();
    }
}
