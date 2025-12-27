class Vehicle {
    String model = "Generic";
    String year = "2020";
    String fuelType = "Petrol";
}

class Truck extends Vehicle {
    double getEfficiency() {
        return 5;
    }
}

class Car extends Vehicle {
    double getEfficiency() {
        return 15;
    }
}

public class nine {
    public static void main(String[] args) {
        Truck t = new Truck();
        System.out.println(t.getEfficiency());

        Car c = new Car();
        System.out.println(c.getEfficiency());
    }
}
