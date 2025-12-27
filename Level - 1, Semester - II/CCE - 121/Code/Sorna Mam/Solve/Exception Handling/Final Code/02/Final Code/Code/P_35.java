/*
35. Taxi Ride-Hailing App 
 A customer requests a ride. 
 Throw: 
o NoDriverAvailableException if no drivers are free. 
o InvalidDestinationException if the destination is not serviceable.
*/

import java.util.*;

class NoDriverAvailableException extends Exception {
    NoDriverAvailableException(String message) {
        super(message);
    }
}

class InvalidDestinationException extends Exception {
    InvalidDestinationException(String message) {
        super(message);
    }
}

public class P_35 {

    static void requestRide(String destination, boolean driverAvailable)
            throws NoDriverAvailableException, InvalidDestinationException {

        List<String> validDestinations = Arrays.asList("Dhaka", "Khulna", "Barisal", "Patuakhali", "Jessore");

        if (!validDestinations.contains(destination))
            throw new InvalidDestinationException("❌ Destination not serviceable: " + destination);

        if (!driverAvailable)
            throw new NoDriverAvailableException("No drivers available right now!");

        System.out.println("Ride confirmed to " + destination + "! Your driver is on the way.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter your destination: ");
            String destination = sc.nextLine();

            System.out.print("Is any driver available? (true/false): ");
            boolean driverAvailable = sc.nextBoolean();

            requestRide(destination, driverAvailable);
        } 
        catch (InvalidDestinationException e) {
            System.out.println(e.getMessage());
        } 
        catch (NoDriverAvailableException e) {
            System.out.println(e.getMessage());
        } 
        finally {
            System.out.println("Ride request process completed.");
        }

        sc.close();
    }
}
