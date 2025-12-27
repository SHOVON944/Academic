package Basic;

//Java Program to illustrate the use of Null Pointer Exception in Java  
public class Main {
    public static void main(String[] args) {
        System.out.println("\n");
        String str = null; // Initializing a String variable to null
        try {

            int length = str.length(); // Attempting to call a method on a null reference
            System.out.println("Length of the string: " + length);
            int div = 100 / 0;
        } catch (NullPointerException e) {
            System.out.println("Error: Null reference encountered.");
            // Additional error handling code can be added here
        }
        // catch(ArithmeticException eh){
        // System.out.println("Arithmat");
        // }
    }
}