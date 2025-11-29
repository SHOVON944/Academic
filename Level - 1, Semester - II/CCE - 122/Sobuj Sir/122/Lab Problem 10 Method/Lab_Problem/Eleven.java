package Lab_Problem;

import java.util.Scanner;

public class Eleven {

    public static boolean validPassCall(String s){
        if(s.length()<10){
            return false;
        }

        int digitCount = 0;
        for(char ch : s.toCharArray()){
            if(!Character.isLetterOrDigit(ch)){
                return false;
            }
            if(Character.isDigit(ch)){
                digitCount++;
            }
        }
        if(digitCount<2){
            return false;
        }

        return true;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the password: ");
        String pass = scan.nextLine();
        boolean check = validPassCall(pass);
        System.out.println("The password " + pass + " is valid? :" + check);

        scan.close();
    }
}
