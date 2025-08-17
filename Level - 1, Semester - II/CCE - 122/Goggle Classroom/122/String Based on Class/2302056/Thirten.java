import java.util.Scanner;
import java.util.Stack;

public class Thirten {

    public static boolean isBalancedCall(String s){
        Stack<Character> checkerStore = new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch == '('   ||   ch == '{'   ||   ch == '['){
                checkerStore.push(ch);
            } else if(ch == ')'   ||   ch == '}'   ||   ch == ']'){
                if(checkerStore.isEmpty()) return false;
                char top = checkerStore.pop();  // pop() -> store last character of checkStore variable
                if((ch == ')'   &&   top != '(')  ||
                   (ch == ')'   &&   top != '(')  ||
                    (ch == ')'   &&   top != '(')) {
                        return false;
                    }
            }
        }
        return checkerStore.isEmpty();
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter parentheses string: ");
        String s = scan.nextLine();

        if(isBalancedCall(s)){
            System.out.println("Balanced.");
        } else{
            System.out.println("Not Balanced.");
        }

        scan.close();
    }
}
