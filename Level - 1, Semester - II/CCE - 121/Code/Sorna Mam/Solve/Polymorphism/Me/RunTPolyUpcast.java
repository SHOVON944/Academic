class Bike{
    void run(){
        System.out.println("Running");
    }
}

class Splender extends Bike{
    void run(){
        System.out.println("Running safely with 60Km/h");
    }
}

public class RunTPolyUpcast {
    public static void main(String[] args) {
        Bike b = new Splender();    // upcasting
        b.run();
    }
}
