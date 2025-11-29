class Animal{
    void move(){
        System.out.println("Animal is moving");
    }
}

class Cheetah extends Animal{
    void move(){
        System.out.println("Running");
    }
}

public class six {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Cheetah cheetah = new Cheetah();

        animal.move();
        cheetah.move();
    }
}