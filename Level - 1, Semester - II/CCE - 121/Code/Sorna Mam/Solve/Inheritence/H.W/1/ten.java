class Employee {
    String name = "Alex";

    double calculateSalary() {
        return 50000;
    }
}

class Developer extends Employee {
    double calculateSalary() {
        return 60000;
    }
}

class Tester extends Employee {
    double calculateSalary() {
        return 55000;
    }
}

class Manager extends Employee {
    double calculateSalary() {
        return 80000;
    }
}

public class ten {
    public static void main(String[] args) {
        Developer d = new Developer();
        Tester t = new Tester();
        Manager m = new Manager();

        System.out.println(d.calculateSalary());
        System.out.println(t.calculateSalary());
        System.out.println(m.calculateSalary());
    }
}
