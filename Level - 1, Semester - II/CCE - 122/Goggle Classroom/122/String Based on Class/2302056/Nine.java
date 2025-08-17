import java.util.Scanner;

public class Nine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the string(0 to  9): ");
        String s = scan.nextLine();

        int start = 0;
        boolean isNegative = false;
        if(s.charAt(0) == '-'){
            isNegative = true;
            start = 1;
        }
        int result = 0;
        for(int i=start; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch<'0' || ch>'9'){
                System.out.println("Invalid input");
                scan.close();
                return;
            }
            int digit = ch - '0';
            result = result * 10  + digit;
        }
        if(isNegative){
            result = -result;
        }
        System.out.println("The numeric string to integer is: " + result);

        scan.close();
    }
}
