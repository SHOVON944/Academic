class Vehicle{
    void drive(){
        System.out.println("Driving vehicle");
    }
}

class Car extends Vehicle{
    void drive(){
        System.out.println("Repairing a car");
    }
}

public class two {
    public static void main(String[] args) {
        Vehicle myVehicle = new Vehicle();
        Car myCar = new Car();

        myVehicle.drive();
        myCar.drive();
    }
}