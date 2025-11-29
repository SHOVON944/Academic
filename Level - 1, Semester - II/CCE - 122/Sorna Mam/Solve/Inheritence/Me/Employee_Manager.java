class member{
    String name;
    int age;
    String phn;
    String add;
    float salary;

    public void printSalary(){
        System.out.println("Salary is: " + salary);
    }
}

class Employee extends member{
    String specialization;
    public void printSpecialization(){
        System.out.println("Specialization is: " + specialization);
    }
}

class Manager extends member{
    String department;
    public void printDepartment(){
        System.out.println("Depertment is: " + department);
    }
}



public class Employee_Manager {
    public static void main(String[] args) {
        Employee objEmployee = new Employee();
        Manager objManager = new Manager();

        objEmployee.name = "SHOVON";
        objEmployee.age = 21;
        objEmployee.phn = "01705199367";
        objEmployee.add = "Satkhira";
        objEmployee.salary = 290000;
        objEmployee.specialization = "C++";

        objManager.name = "SHUVO";
        objManager.age = 24;
        objManager.phn = "01776523702";
        objManager.add = "Satkhira";
        objManager.salary = 300000;
        objManager.department = "Python";

        System.out.println("Manager Details :");
        System.out.println("Name : " + objEmployee.name);
        System.out.println("Age :" + objEmployee.age);
        System.out.println("Phone No: " + objEmployee.phn);
        System.out.println("Address : " + objEmployee.add);
        objEmployee.printSalary();
        objEmployee.printSpecialization();

        System.out.println("\n\nEmployee Details :");
        System.out.println("Name : " + objManager.name);
        System.out.println("Age :" + objManager.age);
        System.out.println("Phone No: " + objManager.phn);
        System.out.println("Address : " + objManager.add);
        objManager.printSalary();
        objManager.printDepartment();
    }
}