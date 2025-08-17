import java.util.Scanner;

public class Two {

    public static boolean backendCall(String s){
        int left = 0;
        int right = s.length() - 1;

        while(left<right){
            if(s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the string (Case sensitive): ");
        String s = scan.nextLine();

        boolean check = backendCall(s);
        System.out.println("The String same backend: " + check);

        scan.close();
    }
}
