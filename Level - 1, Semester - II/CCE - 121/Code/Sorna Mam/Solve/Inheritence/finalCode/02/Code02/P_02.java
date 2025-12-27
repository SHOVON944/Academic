/*
In the above example, declare the method of the parent class as private and then repeat 
the first two operations (You will get error in the third). 
*/


class Parent2{
    private void parentMethod(){        // private methood inherite hoi na
        System.out.println("This is parent class.");
    }
}
class Child extends Parent2{
    void childMethod(){
        System.out.println("This is child class.");
    }
}

public class P_02 {
    public static void main(String[] args) {
        Parent2 pp = new Parent2();
        Child cc = new Child();

        pp.parentMethod();
        cc.childMethod();
        cc.parentMethod();
    }
}
