// eikhane file run hoina full...use Intellij IDEA

/*
Write a Java program to create a class called Employee with methods called work() and 
getSalary(). Create a subclass called HRManager that overrides the work() method and 
adds a new method called addEmployee(). 
Expected Output: 
John is working. 
Salary: $50000.0 
Alice is managing HR tasks. 
Alice added a new employee: Bob 
Salary: $70000.0 
*/

import java.util.Scanner;

class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void work() {
        System.out.println(name + " is working.");
    }

    void getSalary() {
        System.out.println("Salary: $" + salary);
    }
}

class HRManager extends Employee {
    HRManager(String name, double salary) {
        super(name, salary); // parent class constructor call
    }

    void work() {
        System.out.println(name + " is managing HR tasks.");
    }

    void addEmployee(String newEmployeeName) {
        System.out.println(name + " added a new employee: " + newEmployeeName);
    }
}

public class Four {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // ----- First take all inputs -----
        System.out.print("Enter employee name: ");
        String empName = scan.nextLine();

        System.out.print("Enter employee salary: ");
        double empSalary = scan.nextDouble();
        scan.nextLine(); // consume newline

        System.out.print("Enter HR manager name: ");
        String hrName = scan.nextLine();

        System.out.print("Enter HRManager salary: ");
        double hrSalary = scan.nextDouble();
        scan.nextLine(); // consume newline

        System.out.print("Enter name of new employee to add: ");
        String newEmpName = scan.nextLine();

        // ----- Then show outputs -----
        Employee emp = new Employee(empName, empSalary);
        emp.work();
        emp.getSalary();

        HRManager hrM = new HRManager(hrName, hrSalary);
        hrM.work();
        hrM.addEmployee(newEmpName);
        hrM.getSalary();

        scan.close();
    }
}
