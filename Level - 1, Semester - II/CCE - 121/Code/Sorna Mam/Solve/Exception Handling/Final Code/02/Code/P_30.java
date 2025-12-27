/*
Car Rental System 
 Cars can be rented if available. 
 Throw: 
o CarNotAvailableException if requested car is already rented. 
o InvalidRentalPeriodException if rental days ≤ 0. 
 Ensure that returned cars update availability correctly.
*/

import java.util.Scanner;

class CarNotAvailableException extends Exception {
    CarNotAvailableException(String message) {
        super(message);
    }
}

class InvalidRentalPeriodException extends Exception {
    InvalidRentalPeriodException(String message) {
        super(message);
    }
}

class Car {
    String model;
    boolean isRented;

    Car(String model) {
        this.model = model;
        this.isRented = false;
    }

    public void rentCar(int days) throws CarNotAvailableException, InvalidRentalPeriodException {
        if (days <= 0) throw new InvalidRentalPeriodException("Rental period must be > 0 days.");
        if (isRented) throw new CarNotAvailableException(model + " is already rented.");
        isRented = true;
        System.out.println(model + " rented for " + days + " days.");
    }

    public void returnCar() {
        if (isRented) {
            isRented = false;
            System.out.println(model + " has been returned and is now available.");
        } else {
            System.out.println(model + " was not rented.");
        }
    }

    public void showStatus() {
        System.out.println(model + " - " + (isRented ? "Rented" : "Available"));
    }
}

public class P_30 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Car car1 = new Car("Toyota Corolla");
        Car car2 = new Car("Honda Civic");

        while (true) {
            System.out.println("\n--- Car Status ---");
            car1.showStatus();
            car2.showStatus();

            System.out.println("\nOptions: rent / return / exit");
            System.out.print("Enter your choice: ");
            String choice = scan.nextLine();

            if (choice.equalsIgnoreCase("exit")) break;

            System.out.print("Enter car model: ");
            String model = scan.nextLine();

            if (choice.equalsIgnoreCase("rent")) {
                System.out.print("Enter rental days: ");
                int days;
                try {
                    days = Integer.parseInt(scan.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number. Try again.");
                    continue;
                }

                try {
                    if (model.equalsIgnoreCase("Toyota Corolla")) {
                        car1.rentCar(days);
                    } else if (model.equalsIgnoreCase("Honda Civic")) {
                        car2.rentCar(days);
                    } else {
                        System.out.println("Car not found.");
                    }
                } catch (CarNotAvailableException | InvalidRentalPeriodException e) {
                    System.out.println("Cannot rent: " + e.getMessage());
                }

            } else if (choice.equalsIgnoreCase("return")) {
                if (model.equalsIgnoreCase("Toyota Corolla")) {
                    car1.returnCar();
                } else if (model.equalsIgnoreCase("Honda Civic")) {
                    car2.returnCar();
                } else {
                    System.out.println("Car not found.");
                }

            } else {
                System.out.println("Invalid option.");
            }
        }

        System.out.println("Thank you for using Car Rental System!");
        scan.close();
    }
}
