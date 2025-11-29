class member{ 
String name; 
int age; 
String phoneNo; 
String address; 
float salary; 
public void printSalary(){ 
System.out.println("Salary is: " + salary); 
} 
} 
class employee extends member{ 
String specialization; 
public void printSpecialization(){ 
System.out.println("Specialization is: " + specialization); 
} 
} 
class manager extends member{ 
String department; 
public void printDepartment(){ 
System.out.println("Department is: " + department); 
} 
} 
public class Employee_Manage { 
public static void main(String[] args) { 
employee objEmployee = new employee(); 
objEmployee.name = "John"; 
objEmployee.age = 25; 

        objEmployee.phoneNo = "1234567890"; 
        objEmployee.address = "New York"; 
        objEmployee.salary = 20000; 
        objEmployee.specialization = "Java"; 
         
        manager objManager = new manager(); 
        objManager.name = "Peter"; 
        objManager.age = 27; 
        objManager.phoneNo = "0987654321"; 
        objManager.address = "New Jersey"; 
        objManager.salary = 30000; 
        objManager.department = "Sales"; 
 
        System.out.println("\nEmployee Details:"); 
        System.out.println("Name: " + objEmployee.name); 
        System.out.println("Age: " + objEmployee.age); 
        System.out.println("Phone No: " + objEmployee.phoneNo); 
        System.out.println("Address: " + objEmployee.address); 
        objEmployee.printSalary(); 
        objEmployee.printSpecialization(); 
 
        System.out.println("\nManager Details"); 
        System.out.println("Name: " + objManager.name); 
        System.out.println("Age: " + objManager.age); 
        System.out.println("Phone No: " + objManager.phoneNo); 
        System.out.println("Address: " + objManager.address); 
        objManager.printSalary(); 
        objManager.printDepartment(); 
    } 
}