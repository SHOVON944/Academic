// question e ektu change korci. Animal -> Animal6. Because, ei folder(package) e already Animal name e class ase.
/*
  Write a Java program to create a class called Animal with a method named move(). 
Create a subclass called Cheetah that overrides the move() method to run. 
Expected Output: 
Animal is moving 
Running 
 */

class Animal6{
    void move(){
        System.out.println("Animal is moving");
    }
}
class Cheetah extends Animal6{
    void move(){
        System.out.println("Running");
    }
}

public class Six {
    public static void main(String[] args) {
        Animal6 anml = new Animal6();
        Cheetah chth = new Cheetah();

        anml.move();
        chth.move();
    }
}
