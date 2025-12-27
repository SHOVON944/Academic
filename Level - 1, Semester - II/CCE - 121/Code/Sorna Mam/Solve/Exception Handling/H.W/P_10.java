/*
(Catching Exceptions with Superclasses) Use inheritance to create an exception 
superclass (called ExceptionA) and exception subclasses ExceptionB and ExceptionC, 
where ExceptionB inherits from ExceptionA and ExceptionC inherits from ExceptionB. 
Write a program to demonstrate that the catch block for type ExceptionA catches 
exceptions of types ExceptionB and ExceptionC.
*/


class ExceptionA extends Exception {
    public ExceptionA(String message) {
        super(message);
    }
}

class ExceptionB extends ExceptionA {
    public ExceptionB(String message) {
        super(message);
    }
}

class ExceptionC extends ExceptionB {
    public ExceptionC(String message) {
        super(message);
    }
}

public class P_10 {
    public static void main(String[] args) {
        try {
            throw new ExceptionC("This is ExceptionC");
        } catch (ExceptionA e) {
            System.out.println("Caught by ExceptionA catch block: " + e.getMessage());
        }
    }
}
