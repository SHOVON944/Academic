class Employee {
    void work() {
        System.out.println("Working");
    }

    double getSalary() {
        return 50000;
    }
}

class HRManager extends Employee {
    void work() {
        System.out.println("Managing HR tasks");
    }

    void addEmployee() {
        System.out.println("Employee added");
    }
}

public class j {
    public static void main(String[] args) {
        HRManager hr = new HRManager();
        hr.work();
        System.out.println(hr.getSalary());
        hr.addEmployee();
    }
}
