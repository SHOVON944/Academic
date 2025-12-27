/*
Write a Java program to create a class known as Person with methods called 
getFirstName() and getLastName(). Create a subclass called Employee that adds a new method named getEmployeeId() and overrides the getLastName() method to include the 
employee's job title. 
Expected Output: 
Person: Jane Doe 
Employee: John Smith (Manager) 
Employee ID: E456  */

import java.util.Scanner;

class Person{       /* Top-level class (যেমন class Person, class Employee, class Main) – এগুলো public না দিলে ডিফল্টে package-private হয়ে যাবে। মানে ওই ক্লাস শুধু একই package-এর ভেতর থেকে access করা যাবে, অন্য package থেকে না। সাধারণত একটা প্রোগ্রামে সব এক ফাইলে লিখলে কোনো সমস্যা হয় না।*/
    private String firstName;
    private String lasttName;

    Person(String firstName,  String lasttName){    // public na dile মানে ওই constructor শুধু একই package থেকে object বানাতে পারবে।
        this.firstName = firstName;
        this.lasttName = lasttName;
    }
    String getFirstName(){  // public na dile ওই method একই package-এর ভেতর থেকে call করা যাবে।
        return firstName;
    }
    String getLastName(){
        return lasttName;
    }
}

class Employee7 extends Person{
    private String employeeID;
    private String jobTitle;

    Employee7 (String firstName, String lastName, String employeeID, String jobTitle){
        super(firstName, lastName);
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
    }
    String getEmployeeID(){
        return employeeID;
    }
    String getLastName(){   //jobTitle override method-এর ভেতরেই ব্যবহার করা হয়েছে, তাই আলাদা getter method দেওয়া হয়নি।
        return super.getLastName() + " ( " + jobTitle + ")"; 
    }
}

public class Seven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // Person input
        System.out.print("Enter Person First Name: ");
        String pFirst = scan.nextLine();
        System.out.print("Enter Person Last Name: ");
        String pLast = scan.nextLine();
        Person p1 = new Person(pFirst, pLast);

        System.out.println("Person: " + p1.getFirstName() + " " + p1.getLastName());

        // Employee input
        System.out.print("Enter Employee First Name: ");
        String eFirst = scan.nextLine();
        System.out.print("Enter Employee Last Name: ");
        String eLast = scan.nextLine();
        System.out.print("Enter Employee ID: ");
        String eId = scan.nextLine();
        System.out.print("Enter Employee Job Title: ");
        String eJob = scan.nextLine();

        Employee7 e1 = new Employee7(eFirst, eLast, eId, eJob);

        System.out.println("Employee: " + e1.getFirstName() + " " + e1.getLastName());
        System.out.println("Employee ID: " + e1.getEmployeeID());

        scan.close();
    }
}
