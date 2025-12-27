/*
Airline Luggage Handling 
 Each passenger has a luggage weight limit. 
 Throw: 
o OverweightLuggageException if weight > limit. 
o InvalidLuggageException if weight ≤ 0. 
*/


import java.util.Scanner;

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

class Passenger {
    String name;
    double luggageWeight;
    double weightLimit;

    Passenger(String name, double luggageWeight, double weightLimit) {
        this.name = name;
        this.luggageWeight = luggageWeight;
        this.weightLimit = weightLimit;
    }

    void checkLuggage() throws OverweightLuggageException, InvalidLuggageException {
        if (luggageWeight <= 0) {
            throw new InvalidLuggageException("Invalid luggage weight!");
        } else if (luggageWeight > weightLimit) {
            throw new OverweightLuggageException("Luggage overweight! Limit: " + weightLimit + " kg");
        } else {
            System.out.println(name + " → Luggage accepted (" + luggageWeight + " kg)");
        }
    }
}

public class P_34 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of passengers: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        Passenger[] passengers = new Passenger[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nPassenger " + (i + 1) + ":");
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter luggage weight (kg): ");
            double weight = sc.nextDouble();

            System.out.print("Enter luggage weight limit (kg): ");
            double limit = sc.nextDouble();
            sc.nextLine(); // consume newline

            passengers[i] = new Passenger(name, weight, limit);
        }

        System.out.println("\n--- Luggage Check Results ---");
        for (Passenger p : passengers) {
            try {
                p.checkLuggage();
            } catch (OverweightLuggageException | InvalidLuggageException e) {
                System.out.println(p.name + " → " + e.getMessage());
            }
        }

        sc.close();
    }
}
