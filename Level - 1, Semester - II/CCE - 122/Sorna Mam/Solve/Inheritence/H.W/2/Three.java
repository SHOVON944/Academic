class Member{
    String name;
    int age;
    String phoneNumber;
    String address;
    double salary;

    void printSalary(){
        System.out.println("Salary: " + salary);
    }
}

class Employee extends Member{
    String specialization;
}

class Manager extends Member{
    String department;
}

class Three {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.name = "Shovon";
        emp.age = 21;
        emp.phoneNumber = "01705199367";
        emp.address = "Satkhira";
        emp.salary = 49000;
        emp.specialization = "Java Expert";

        Manager mgr = new Manager();
        mgr.name = "Shuvo";
        mgr.age = 24;
        mgr.phoneNumber = "01776523702";
        mgr.address = "Khulna";
        mgr.salary = 75000;
        mgr.department = "Engineering";

        System.out.println("Employee Details:");
        System.out.println("Name: " + emp.name);
        System.out.println("Age: " + emp.age);
        System.out.println("Phone: " + emp.phoneNumber);
        System.out.println("Address: " + emp.address);
        emp.printSalary();
        System.out.println("Specialization: " + emp.specialization);

        System.out.println("\nManager Details:");
        System.out.println("Name: " + mgr.name);
        System.out.println("Age: " + mgr.age);
        System.out.println("Phone: " + mgr.phoneNumber);
        System.out.println("Address: " + mgr.address);
        mgr.printSalary();
        System.out.println("Department: " + mgr.department);

    }
}