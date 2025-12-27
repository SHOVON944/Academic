// package practiceJava;

class Vehicle {
    String make;
    String model;
    int year;
    String fueltype;

    Vehicle(String make, String model, int year, String fueltype) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.fueltype = fueltype;
    }

    public double fuelefficiency() {
        return 0;
    }

    public double distancetravel(double fuelused) {
        return fuelused * fuelefficiency();
    }

    public double maximumspeed() {
        return 0;
    }

    public void display() {
        System.out.println("Make :" + make + "model :" + model + "Year :" + year + "fueltype : " + fueltype);
    }
}

class Car extends Vehicle {
    public Car(String make, String model, int year, String fueltype) {
        super(make, model, year, fueltype);
    }

    public double fuelefficiency() {
        return 15;
    }

    public double maximumspeed() {
        return 180;
    }
}

class Truck extends Vehicle {
    public Truck(String make, String model, int year, String fueltype) {
        super(make, model, year, fueltype);
    }

    public double fuelefficiency() {
        return 8;
    }

    public double maximumspeed() {
        return 150;
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(String make, String model, int year, String fueltype) {
        super(make, model, year, fueltype);
    }

    public double fuelefficiency() {
        return 25;
    }

    public double maximumspeed() {
        return 200;
    }
}

public class Prblm_01 {
    public static void main(String[] args) {
        Car c = new Car("Toyoto", "H24", 2002, "Petrol");
        Truck t = new Truck("Volvo", "C54", 2013, "Dissel");
        Motorcycle m = new Motorcycle("Yemaha", "H76", 2010, "Petrol");
        c.display();
        System.out.println("Fuel efficiency: " + c.fuelefficiency() + "km");
        System.out.println("Distance Travel : " + c.distancetravel(10) + "km");
        System.out.println("Maximum speed :" + c.maximumspeed() + "km/h");
        t.display();
        System.out.println("Fuel efficiency: " + t.fuelefficiency() + "km");
        System.out.println("Distance Travel : " + t.distancetravel(10) + "km");
        System.out.println("Maximum speed :" + t.maximumspeed() + "km/h");
        m.display();
        System.out.println("Fuel efficiency: " + m.fuelefficiency() + "km");
        System.out.println("Distance Travel : " + m.distancetravel(10) + "km");
        System.out.println("Maximum speed :" + m.maximumspeed() + "km/h");

    }

}