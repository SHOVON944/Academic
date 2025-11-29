/*
Create a class with a method that prints "This is parent class" and its subclass with 
another method that prints "This is child class". Now, create an object for each of the 
class and call 
1 - method of parent class by object of parent class 
2 - method of child class by object of child class 
3 - method of parent class by object of child class 
Expected Output: 
This is parent class 
This is child class 
This is parent class 
*/


    class Parent{
        void parentMethod(){
            System.out.println("This is parent class.");
        }
    }
    class Child extends Parent{
        void childMethod(){
            System.out.println("This is child class.");
        }
    }

    public class P_01 {
        public static void main(String[] args) {
            Parent pp = new Parent();
            Child cc = new Child();
            Parent pc = new Child();

            pp.parentMethod();
            cc.childMethod();
            pc.parentMethod();
        }
    }
