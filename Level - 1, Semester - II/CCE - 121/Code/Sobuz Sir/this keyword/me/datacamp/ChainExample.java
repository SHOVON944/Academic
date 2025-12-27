package datacamp;
public class ChainExample {
    int value;

    ChainExample setValue(int value) {
        this.value = value;
        return this;
    }

    void display() {
        System.out.println("Value: " + value);
    }

    public static void main(String[] args) {
        ChainExample obj = new ChainExample();
        obj.setValue(100).display();
    }
}