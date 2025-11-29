class Person {
    String getFirstName() {
        return "Jane";
    }

    String getLastName() {
        return "Doe";
    }
}

class Employee extends Person {
    String getFirstName() {
        return "John";
    }

    String getLastName() {
        return "Smith (Manager)";
    }

    String getEmployeeId() {
        return "E456";
    }
}

public class seven {
    public static void main(String[] args) {
        Person p = new Person();
        Employee e = new Employee();
        
        System.out.println("Person: " + p.getFirstName() + " " + p.getLastName());
        System.out.println("Employee: " + e.getFirstName() + " " + e.getLastName());
        System.out.println("Employee ID: " + e.getEmployeeId());
    }
}