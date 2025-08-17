class Person {
    String name;
    int age;

    Person(String n, int a) {
        name = n;
        age = a;
    }
}

class Employee extends Person {
    int employeeId;
    double salary;

    Employee(String n, int a, int id, double s) {
        super(n, a);
        employeeId = id;
        salary = s;
    }

    void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Salary: " + salary);
    }
}

public class Fifteen {
    public static void main(String[] args) {
        Employee emp = new Employee("Shuvo", 25, 101, 50000.0);
        emp.displayDetails();
    }
}
