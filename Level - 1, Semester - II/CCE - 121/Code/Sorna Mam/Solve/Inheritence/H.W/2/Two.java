class Parent2{
    private void method1(){
        System.out.println("This is parent class");
    }
}

class Child2 extends Parent2{
    void method2(){
        System.out.println("This is child class");
    }
}

class Two {
    public static void main(String[] args) {
        Parent2 p = new Parent2();
        p.method1();

        Child2 c = new Child2();
        c.method2();

        Parent2 pc = new Child2();
        pc.method1();
    }
}