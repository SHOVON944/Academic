// It not only makes the code verbose.
// It also requires precision from the programmer to ensure that typecasting is done properly; 
// otherwise, there are fair chances of getting the ClassCastException.

class A1 {
    A1 foo() {
        return this;
    }

    void print() {
        System.out.println("Inside the class A1");
    }
}

class A2 extends A1 {
    A1 foo() {
        return this;
    }

    void print() {
        System.out.println("Inside the class A2");
    }
}

class A3 extends A2 {
    A1 foo() {
        return this;
    }

    void print() {
        System.out.println("Inside the class A3");
    }
}

public class TypeCasting {
    public static void main(String[] args) {
        // important part
        A1 a1 = new A1();
        // this is ok
        a1.foo().print();

        A2 a2 = new A2();
        // we need to do the type casting to make it
        ((A2) a2.foo()).print();

        A3 a3 = new A3();
        ((A3) a3.foo()).print();
    }
}

// the better way to write the above is

/*
class A1 {
    A1 foo() {
        return this;
    }

    void print() {
        System.out.println("Inside the class A1");
    }
}

// A2 is the child class of A1

class A1 {
    A1 foo() {
        return this;
    }

    void print() {
        System.out.println("Inside the class A1");
    }
}

// A2 is the child class of A1
class A2 extends A1 {
    @Override
    A2 foo() {
        return this;
    }

    void print() {
        System.out.println("Inside the class A2");
    }
}

// A3 is the child class of A2
class A3 extends A2 {
    @Override
    A3 foo() {
        return this;
    }

    @Override
    void print() {
        System.out.println("Inside the class A3");
    }
}

public class CovariantExample {
    // main method
    public static void main(String argvs[]) {
        A1 a1 = new A1();

        a1.foo().print();

        A2 a2 = new A2();

        a2.foo().print();

        A3 a3 = new A3();

        a3.foo().print();

    }
}
*/