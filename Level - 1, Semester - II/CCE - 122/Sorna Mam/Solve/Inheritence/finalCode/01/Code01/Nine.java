/*
Write a Java program to create a vehicle class hierarchy. The base class should be 
Vehicle, with subclasses Truck, Car and Motorcycle. Each subclass should have 
properties such as make, model, year, and fuel type. Implement methods for calculating 
fuel efficiency, distance traveled, and maximum speed. 
Expected Output: 
=== Truck Info === 
Make: Scania, Model: R-Series, Year: 2021, Fuel: Diesel 
Fuel Efficiency: 6.0 km/l 
Distance for 40L: 240.0 km 
Max Speed: 110.0 km/h 
=== Car Info === 
Make: Honda, Model: Civic, Year: 2023, Fuel: Petrol 
Fuel Efficiency: 18.0 km/l 
Distance for 40L: 720.0 km 
Max Speed: 190.0 km/h 
=== Motorcycle Info === 
Make: Suzuki, Model: Gixxer, Year: 2022, Fuel: Petrol 
Fuel Efficiency: 40.0 km/l 
Distance for 10L: 400.0 km 
Max Speed: 150.0 km/h 
*/

import java.util.Scanner;

class Vehicle {
    String make;
    String model;
    int year;
    String fuelType;
    double fuelEfficiency;
    double maxSpeed;

    Vehicle(String make, String model, int year, String fuelType, double fuelEfficiency, double maxSpeed) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.fuelEfficiency = fuelEfficiency;
        this.maxSpeed = maxSpeed;
    }

    double getFuelEfficiency() {
        return fuelEfficiency;
    }

    double calculateDistance(double fuel) {
        return fuel * fuelEfficiency;
    }

    double getMaxSpeed() {
        return maxSpeed;
    }

    void display9() {
        System.out.println("Make: " + make + ", Model: " + model + ", Year: " + year + ", Fuel: " + fuelType);
    }
}

class Truck extends Vehicle {
    Truck(String make, String model, int year, String fuelType, double fuelEfficiency, double maxSpeed) {
        super(make, model, year, fuelType, fuelEfficiency, maxSpeed);
    }
}

class Car extends Vehicle {
    Car(String make, String model, int year, String fuelType, double fuelEfficiency, double maxSpeed) {
        super(make, model, year, fuelType, fuelEfficiency, maxSpeed);
    }
}

class Motorcycle extends Vehicle {
    Motorcycle(String make, String model, int year, String fuelType, double fuelEfficiency, double maxSpeed) {
        super(make, model, year, fuelType, fuelEfficiency, maxSpeed);
    }
}

public class Nine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Truck input
        System.out.println("Make: ");
        String tMake = scan.nextLine();
        System.out.println("Model: ");
        String tModel = scan.nextLine();
        System.out.println("Year: ");
        int tYear = scan.nextInt();
        System.out.println("Fuel Type: ");
        String tFuel = scan.nextLine();
        System.out.println("Fuel Efficiency: ");
        double tEfficiency = scan.nextDouble();
        System.out.println("Max Speed: ");
        double tMaxSpeed = scan.nextDouble();
        scan.nextLine(); // clear buffer

        Truck trck = new Truck(tMake, tModel, tYear, tFuel, tEfficiency, tMaxSpeed);

        // Car input
        System.out.println("Make: ");
        String cMake = scan.nextLine();
        System.out.println("Model: ");
        String cModel = scan.nextLine();
        System.out.println("Year: ");
        int cYear = scan.nextInt();
        System.out.println("Fuel Type: ");
        String cFuel = scan.nextLine();
        System.out.println("Fuel Efficiency: ");
        double cEfficiency = scan.nextDouble();
        System.out.println("Max Speed: ");
        double cMaxSpeed = scan.nextDouble();
        scan.nextLine(); // clear buffer

        Car car = new Car(cMake, cModel, cYear, cFuel, cEfficiency, cMaxSpeed);

        // Motorcycle input
        System.out.println("Make: ");
        String motoCMake = scan.nextLine();
        System.out.println("Model: ");
        String motoCModel = scan.nextLine();
        System.out.println("Year: ");
        int motoCYear = scan.nextInt();
        System.out.println("Fuel Type: ");
        String motoCFuel = scan.nextLine();
        System.out.println("Fuel Efficiency: ");
        double motoCEfficiency = scan.nextDouble();
        System.out.println("Max Speed: ");
        double motoCMaxSpeed = scan.nextDouble();
        scan.nextLine(); // clear buffer

        Motorcycle motCycl = new Motorcycle(motoCMake, motoCModel, motoCYear, motoCFuel, motoCEfficiency,
                motoCMaxSpeed);

        // Display Truck Info
        System.out.println("\n=== Truck Info ===");
        trck.display9();
        System.out.println("Fuel Efficiency: " + trck.getFuelEfficiency() + " km/l");
        System.out.println("Distance for 40L: " + trck.calculateDistance(40) + " km");
        System.out.println("Max Speed: " + trck.getMaxSpeed() + " km/h");

        // Display Car Info
        System.out.println("\n=== Car Info ===");
        car.display9();
        System.out.println("Fuel Efficiency: " + car.getFuelEfficiency() + " km/l");
        System.out.println("Distance for 40L: " + car.calculateDistance(40) + " km");
        System.out.println("Max Speed: " + car.getMaxSpeed() + " km/h");

        // Display Motorcycle Info
        System.out.println("\n=== Motorcycle Info ===");
        motCycl.display9();
        System.out.println("Fuel Efficiency: " + motCycl.getFuelEfficiency() + " km/l");
        System.out.println("Distance for 10L: " + motCycl.calculateDistance(10) + " km");
        System.out.println("Max Speed: " + motCycl.getMaxSpeed() + " km/h");

        scan.close();
    }
}