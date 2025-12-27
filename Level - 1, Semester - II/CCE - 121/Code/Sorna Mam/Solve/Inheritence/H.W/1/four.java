class Employee{
    void work(){
        System.out.println("John is working");
    }

    void getSalary(){
        float salary = 5000.0f;
        System.out.println("Salary: " + salary + "$");
    }
}

class HRmanager extends Employee{
    void work(){
        System.out.println("Alice is managing HR tasks.");
        System.out.println("Alice added a new Employee: Bob");
    }

    void addEmployee(){
        System.out.println("Salary = 7000.0$");
    }
}


public class four {
    public static void main(String[] args) {
        Employee obj1 = new Employee();
        HRmanager obj2 = new HRmanager();

        obj1.work();
        obj1.getSalary();

        obj2.work();
        obj2.addEmployee();
    }
}
