/*
Write a Java program to create a class called Animal with a method called makeSound(). 
Create a subclass called Cat that overrides the makeSound() method to meow. 
Expected Output: 
Some generic animal sound 
Meow  
*/

class Animal{
    void makeSound(){
        System.out.println("Some generic animal sound");
    }
}
class Cat extends Animal{
    void makeSound(){
        System.out.println("Meow");
    }
}

public class One {
    public static void main(String[] args) {
        Animal ani = new Animal();
        ani.makeSound();

        Cat c = new Cat();
        c.makeSound();
    }
}
