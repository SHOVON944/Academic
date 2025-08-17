class Employee {
    String name;
    int employeeId;
    double salary;

    static int count = 0;

    Employee(String n, int id, double s) {
        name = n;
        employeeId = id;
        salary = s;
        count++;
    }

    void raiseSalary(double percent) {
        if (percent > 0) {
            salary = salary + (salary * percent / 100);
        }
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + employeeId);
        System.out.println("Salary: " + salary);
    }

    static int getEmployeeCount() {
        return count;
    }
}

class Six {
    public static void main(String[] args) {
        Employee e1 = new Employee("SHOVON", 101, 30000);
        Employee e2 = new Employee("SHUVO", 102, 35000);

        e1.raiseSalary(10);
        e2.raiseSalary(5);

        e1.display();
        e2.display();

        System.out.println("Total Employees: " + Employee.getEmployeeCount());
    }
}