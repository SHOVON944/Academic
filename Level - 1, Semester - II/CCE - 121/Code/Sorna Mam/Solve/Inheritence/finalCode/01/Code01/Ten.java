/*
Write a Java program that creates a class hierarchy for employees of a company. 
The base class should be Employee, with subclasses Manager, Developer, and Programmer. 
Each subclass should have properties such as name, address, salary, and job title. 
Implement methods for calculating bonuses, generating performance reports, and 
managing projects.
Expected Output: 
=== Manager === 
Name: Alice 
Address: 123 Business Rd 
Salary: $90000.0 
Job Title: Manager 
Manager Report for Alice: Excellent leadership and planning. 
Alice is managing the entire project and leading the team. 
Bonus: $18000.0 
=== Developer === 
Name: Bob 
Address: 456 Code Ave 
Salary: $75000.0 
Job Title: Developer 
Developer Report for Bob: Good coding and debugging skills. 
Bob is developing key modules of the project. 
Bonus: $7500.0 
=== Programmer === 
Name: Charlie 
Address: 789 Dev Blvd 
Salary: $65000.0 
Job Title: Programmer 
Programmer Report for Charlie: Efficient in writing clean code. 
Charlie is writing and testing code for assigned tasks. 
Bonus: $5200.0
*/

import java.util.Scanner;

class Employee10 {
    String name;
    String address;
    double salary;
    String jobTitle;

    Employee10(String name, String address, double salary, String jobTitle) {
        this.name = name;
        this.address = address;
        this.salary = salary;
        this.jobTitle = jobTitle;
    }

    double calculateBonus() {
        return 0.0;
    }

    String generateReport() {
        return "No report available.";
    }

    String manageProjects() {
        return "No project assigned.";
    }

    void displayInfo10() {
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Salary: $" + salary);
        System.out.println("Job Title: " + jobTitle);
    }
}

class Manager10 extends Employee10 {
    Manager10(String name, String address, double salary) {
        super(name, address, salary, "Manager");
    }

    double calculateBonus() {
        return salary * 0.20;
    }

    String generateReport() {
        return "Manager Report for " + name + ": Excellent leadership and planning.";
    }

    String manageProjects() {
        return name + " is managing the entire project and leading the team.";
    }
}

class Developer10 extends Employee10 {
    Developer10(String name, String address, double salary) {
        super(name, address, salary, "Developer");
    }

    double calculateBonus() {
        return salary * 0.10;
    }

    String generateReport() {
        return "Developer Report for " + name + ": Good coding and debugging skills.";
    }

    String manageProjects() {
        return name + " is developing key modules of the project.";
    }
}

class Programmer10 extends Employee10 {
    Programmer10(String name, String address, double salary) {
        super(name, address, salary, "Programmer");
    }

    double calculateBonus() {
        return salary * 0.08;
    }

    String generateReport() {
        return "Programmer Report for " + name + ": Efficient in writing clean code.";
    }

    String manageProjects() {
        return name + " is writing and testing code for assigned tasks.";
    }
}

public class Ten {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Manager input
        System.out.println("Enter Manager details:");
        System.out.print("Name: ");
        String mName = scan.nextLine();
        System.out.print("Address: ");
        String mAddress = scan.nextLine();
        System.out.print("Salary: ");
        double mSalary = scan.nextDouble();
        scan.nextLine();
        Manager10 manager = new Manager10(mName, mAddress, mSalary);

        // Developer input
        System.out.println("\nEnter Developer details:");
        System.out.print("Name: ");
        String dName = scan.nextLine();
        System.out.print("Address: ");
        String dAddress = scan.nextLine();
        System.out.print("Salary: ");
        double dSalary = scan.nextDouble();
        scan.nextLine();
        Developer10 developer = new Developer10(dName, dAddress, dSalary);

        // Programmer input
        System.out.println("\nEnter Programmer details:");
        System.out.print("Name: ");
        String pName = scan.nextLine();
        System.out.print("Address: ");
        String pAddress = scan.nextLine();
        System.out.print("Salary: ");
        double pSalary = scan.nextDouble();
        scan.nextLine();
        Programmer10 programmer = new Programmer10(pName, pAddress, pSalary);

        // Output
        System.out.println("\n=== Manager ===");
        manager.displayInfo10();
        System.out.println(manager.generateReport());
        System.out.println(manager.manageProjects());
        System.out.println("Bonus: $" + manager.calculateBonus());

        System.out.println("\n=== Developer ===");
        developer.displayInfo10();
        System.out.println(developer.generateReport());
        System.out.println(developer.manageProjects());
        System.out.println("Bonus: $" + developer.calculateBonus());

        System.out.println("\n=== Programmer ===");
        programmer.displayInfo10();
        System.out.println(programmer.generateReport());
        System.out.println(programmer.manageProjects());
        System.out.println("Bonus: $" + programmer.calculateBonus());

        scan.close();
    }
}
