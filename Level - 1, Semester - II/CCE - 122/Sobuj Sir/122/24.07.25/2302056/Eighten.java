class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    void showMessage() {
        System.out.println("This is the Singleton instance.");
    }
}

class Eighteen {
    public static void main(String[] args) {
        Singleton obj1 = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();

        obj1.showMessage();

        if (obj1 == obj2) {
            System.out.println("Both are the same instance.");
        } else {
            System.out.println("Different instances created.");
        }
    }
}
