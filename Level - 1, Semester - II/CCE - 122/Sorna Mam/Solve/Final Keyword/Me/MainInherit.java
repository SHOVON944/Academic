class Bike  {
    final void run() { System.out.println("running...");   }
}
public class MainInherit extends Bike  {
    public static void main(String args[])  {
        new MainInherit().run();
    }
}    