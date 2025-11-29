import java.util.Scanner;

public class Four {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the main string: ");
        String mainStr = scan.nextLine();
        System.out.print("Enter the substring: ");
        String subStr = scan.nextLine();

        int checkLoop = mainStr.length() - subStr.length() + 1; // eita dia loop flow korano hobe...eikhaner j value ta asbe seitukui check korlei hocce..karon tar beshi loop ghuraile sub string er length theke kom word check kora hobe 
        boolean found = false;
        for(int i=0; i<checkLoop; i++){     // main string er word check korbe
            int j;
            for(j=0; j<subStr.length(); j++){
                if(mainStr.charAt(i+j) != subStr.charAt(j)){    // main string er (i+j) check hocce karon ager j chararcter bad jasse seta i...r j hocce current char
                    break;
                }
            }
            if(j == subStr.length()){   // checking
                found = true;
            }
        }
       if (found) {
            System.out.println("Substring exists!");
        } else {
            System.out.println("Substring does not exist.");
        }

        scan.close();
    }
}
