package Lab_Problem;
import java.util.Scanner;

public class Fourten {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the size of the 1st String: ");
        int n1 = scan.nextInt();
        scan.nextLine();    // clear buffer
        String[] s1 = new String[n1];
        System.out.print("Enter the 1st String elements: ");
        for(int i=0; i<n1; i++) s1[i] = scan.nextLine();

        System.out.print("Enter the size of the 2nd String: ");
        int n2 = scan.nextInt();
        scan.nextLine();    // clear buffer
        String[] s2 = new String[n2];
        System.out.print("Enter the 1st String elements: ");
        for(int i=0; i<n2; i++) s2[i] = scan.nextLine();

        boolean found = false;
        for(int i=0; i<n1; i++){
            for(int j=0; j<n2; j++){
                if(s1[i].equals(s2[j])){
                    System.out.println("The duplicate value is between two arrays(String value): " + s1[i]);
                    found = true;
                    break;
                }
            }
        }
        if(!found){
            System.out.println("There are no common value between two arrays(String value).");
        }

        scan.close();
    }
}
