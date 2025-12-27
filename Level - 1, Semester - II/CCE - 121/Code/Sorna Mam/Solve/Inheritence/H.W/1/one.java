class Animal{
    void makeSound(){
        System.out.println("Some generic animal sound");
    }
}

class Cat extends Animal{
    void makeSound() {
        System.out.println("Meow");
    }
}

public class one {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Cat myCat = new Cat();

        myAnimal.makeSound();
        myCat.makeSound();
    }
}