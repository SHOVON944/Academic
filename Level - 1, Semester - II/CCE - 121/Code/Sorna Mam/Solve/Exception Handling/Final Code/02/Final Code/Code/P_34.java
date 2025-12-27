/*
34. Airline Luggage Handling 
 Each passenger has a luggage weight limit. 
 Throw: 
o OverweightLuggageException if weight > limit. 
o InvalidLuggageException if weight ≤ 0.
*/

import java.util.*;

class OverweightLuggageException extends Exception {
    OverweightLuggageException(String message) {
        super(message);
    }
}

class InvalidLuggageException extends Exception {
    InvalidLuggageException(String message) {
        super(message);
    }
}

public class P_34 {

    static void checkLuggage(double weight, double limit)
            throws OverweightLuggageException, InvalidLuggageException {
        
        if (weight <= 0)
            throw new InvalidLuggageException("Invalid luggage weight!");
        else if (weight > limit)
            throw new OverweightLuggageException("Luggage overweight! Allowed: " + limit + " kg");
        else
            System.out.println("Luggage accepted. Have a safe flight!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter luggage weight (kg): ");
            double weight = sc.nextDouble();

            System.out.print("Enter luggage weight limit (kg): ");
            double limit = sc.nextDouble();

            checkLuggage(weight, limit);
        } 
        catch (InvalidLuggageException e) {
            System.out.println(e.getMessage());
        } 
        catch (OverweightLuggageException e) {
            System.out.println(e.getMessage());
        } 
        finally {
            System.out.println("Luggage check process completed.");
        }

        sc.close();
    }
}
