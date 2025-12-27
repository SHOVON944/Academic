class Parent{
    void method1(){
        System.out.println("This is parent class");
    }
}

class Child extends Parent{
    void method2(){
        System.out.println("This is child class");
    }
}

class One {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.method1();

        Child c = new Child();
        c.method2();

        Parent pc = new Child();
        pc.method1();
    }
}