public class BasicPlus {
    private String color;

    // Instance Initializer Block
    {
        color = "Red"; // ডিফল্টভাবে সব Car-এর color Red সেট হবে
        System.out.println("Initializer Block executed");
    }

    public BasicPlus() {
        System.out.println("Constructor executed");
    }

    public static void main(String[] args) {
        BasicPlus myCar = new BasicPlus();
    }
}