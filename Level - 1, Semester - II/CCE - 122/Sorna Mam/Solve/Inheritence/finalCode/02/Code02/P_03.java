/*
Create a class named 'Member' having the following members: 
Data members 
1 - Name 
2 - Age 
3 - Phone number 
4 - Address 
5 – Salary 
It also has a method named 'printSalary' which prints the salary of the members. 
Two classes 'Employee' and 'Manager' inherits the 'Member' class. The 'Employee' and 
'Manager' classes have data members 'specialization' and 'department' respectively. Now, 
assign name, age, phone number, address and salary to an employee and a manager by 
making an object of both of these classes and print the same. 
Expected Output: 
---- Employee Details ---- 
Name: Alice 
Age: 28 
Phone Number: 0123456789 
Address: 123 Main Street 
Specialization: Software Development 
Salary: 50000.0
---- Manager Details ---- 
Name: Bob 
Age: 40 
Phone Number: 0987654321 
Address: 456 Office Avenue 
Department: IT Operations 
Salary: 80000.0 
*/


import java.util.Scanner;

class Member {
    String name;
    int age;
    String phoneNumber;
    String address;
    double salary;

    // Method to print salary
    public void printSalary() {
        System.out.println("Salary: " + salary);
    }
}

class Employee extends Member {
    String specialization;

    public void printDetails() {
        System.out.println("---- Employee Details ----");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Specialization: " + specialization);
        printSalary();
    }
}

class Manager extends Member {
    String department;

    public void printDetails() {
        System.out.println("---- Manager Details ----");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Department: " + department);
        printSalary();
    }
}

public class P_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Employee input
        Employee emp = new Employee();
        System.out.println("Enter Employee Details:");
        System.out.print("Name: ");
        emp.name = sc.nextLine();
        System.out.print("Age: ");
        emp.age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Phone Number: ");
        emp.phoneNumber = sc.nextLine();
        System.out.print("Address: ");
        emp.address = sc.nextLine();
        System.out.print("Specialization: ");
        emp.specialization = sc.nextLine();
        System.out.print("Salary: ");
        emp.salary = sc.nextDouble();
        sc.nextLine(); // consume newline

        // Manager input
        Manager mgr = new Manager();
        System.out.println("\nEnter Manager Details:");
        System.out.print("Name: ");
        mgr.name = sc.nextLine();
        System.out.print("Age: ");
        mgr.age = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Phone Number: ");
        mgr.phoneNumber = sc.nextLine();
        System.out.print("Address: ");
        mgr.address = sc.nextLine();
        System.out.print("Department: ");
        mgr.department = sc.nextLine();
        System.out.print("Salary: ");
        mgr.salary = sc.nextDouble();

        // Print Details
        System.out.println();
        emp.printDetails();
        mgr.printDetails();

        sc.close();
    }
}
