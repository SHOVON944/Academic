// Exception সুপারক্লাস
class ExceptionA extends Exception {
    public ExceptionA(String message) {
        super(message);
    }
}

// ExceptionB, ExceptionA থেকে ইনহেরিট করে
class ExceptionB extends ExceptionA {
    public ExceptionB(String message) {
        super(message);
    }
}

// ExceptionC, ExceptionB থেকে ইনহেরিট করে
class ExceptionC extends ExceptionB {
    public ExceptionC(String message) {
        super(message);
    }
}

public class ExceptionDemo {
    public static void main(String[] args) {
        try {
            // Uncomment করে একেকটা এক্সসেপশন ট্রাই করতে পারেন
            // throw new ExceptionA("This is ExceptionA");
            // throw new ExceptionB("This is ExceptionB");
            throw new ExceptionC("This is ExceptionC");
        } catch (ExceptionA e) {
            // ExceptionA এর catch ব্লক ExceptionB এবং ExceptionC-ও ধরবে
            System.out.println("Caught by ExceptionA catch block: " + e.getMessage());
        }
    }
}
