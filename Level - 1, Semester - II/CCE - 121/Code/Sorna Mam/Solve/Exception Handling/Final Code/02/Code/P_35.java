/*
 Taxi Ride-Hailing App 
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

class TaxiService {
    private int availableDrivers;
    private Set<String> serviceableDestinations;

    TaxiService(int availableDrivers, Set<String> destinations) {
        this.availableDrivers = availableDrivers;
        this.serviceableDestinations = destinations;
    }

    void requestRide(String customerName, String destination)
            throws NoDriverAvailableException, InvalidDestinationException {

        if (!serviceableDestinations.contains(destination.toLowerCase())) {
            throw new InvalidDestinationException("Destination '" + destination + "' is not serviceable.");
        }

        if (availableDrivers <= 0) {
            throw new NoDriverAvailableException("No drivers are available right now.");
        }

        availableDrivers--;
        System.out.println(customerName + " -> Ride confirmed to " + destination + ". Driver assigned.");
    }
}

public class P_35 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example data
        Set<String> destinations = new HashSet<>(Arrays.asList("airport", "downtown", "mall", "station"));
        TaxiService taxiService = new TaxiService(2, destinations); // 2 drivers available

        System.out.print("Enter number of ride requests: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.println("\nRide Request " + (i + 1) + ":");
            System.out.print("Enter customer name: ");
            String name = sc.nextLine();

            System.out.print("Enter destination: ");
            String destination = sc.nextLine();

            try {
                taxiService.requestRide(name, destination);
            } catch (NoDriverAvailableException | InvalidDestinationException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
