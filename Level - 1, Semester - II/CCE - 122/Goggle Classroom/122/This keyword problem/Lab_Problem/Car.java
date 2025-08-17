import java.util.Scanner;

class Car{
    String model;
    String clr;
    int speed;

    public Car setModel(String model){
        this.model = model;
        return this;
    }
    public Car setColor(String clr){
        this.clr = clr;
        return this;
    }
    public Car setSpeed(int speed){
        this.speed = speed;
        return this;
    }

    public void display(){
        System.out.println("\n------Details------");
        System.out.println("Model : " + model);
        System.out.println("Colour : " + clr);
        System.out.println("Speed : " + speed + "km/h");
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the car model: ");
        String model = scan.nextLine();
        System.out.println("Enter the car colour: ");
        String clr = scan.nextLine();
        System.out.println("Enter the car speed: ");
        int spd = scan.nextInt();

        Car obj = new Car()             // fluent interfaces
                        .setModel(model)
                        .setColor(clr)
                        .setSpeed(spd);

        obj.display();

        scan.close();
    }
}