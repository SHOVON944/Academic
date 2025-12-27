package datacamp;

public class Example2 {
    void display(){
        System.out.println("Invoked.");
    }
    void invokedDisplay(){
        this.display();
    }
    public static void main(String[] args) {
        Example2 obj2 = new Example2();
        obj2.invokedDisplay();
    }
}
