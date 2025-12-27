class OrderProcess{
    String version = "1.5.2";

    boolean process(){
        System.out.println("Process Order version: " + version);
        return true;
    }
}

public class Def {
    public static void main(String[] args) {
        OrderProcess obj = new OrderProcess();
        System.out.println("Version: " + obj.version);
        boolean result = obj.process();
        System.out.println("Order Process is: " + result);
    }
}
