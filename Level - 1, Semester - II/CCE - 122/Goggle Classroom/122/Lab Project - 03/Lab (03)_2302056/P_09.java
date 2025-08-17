package class_assignment;
import java.util.Scanner;

public class P_09 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int x = scan.nextInt();
        
        switch(x){
            case 1:
                System.out.println("You selected Billing. Please wait while we connect you to billing department...");
                break;
            case 2:
                System.out.println("You selected Technical Support. Please wait while we connect you to technical support...");
                break;
            case 3:
                System.out.println("You selected Speak to Agent. Please wait while we connect you to an available agent...");
                break;
            case 4:
                System.out.println("Thank you for calling. Goodbye!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}
