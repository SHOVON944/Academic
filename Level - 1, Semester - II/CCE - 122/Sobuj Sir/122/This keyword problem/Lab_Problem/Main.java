import java.util.Scanner;

class Employee {
    private String name;
    private int id;

    public void setDetails(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void show() {
        Printer p = new Printer();
        p.printDetails(this);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}

class Printer {
    public void printDetails(Employee e) {
        System.out.println("Employee Name: " + e.getName());
        System.out.println("Employee ID: " + e.getId());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Employee Name: ");
        String name = scan.nextLine();
        System.out.print("Enter Employee ID: ");
        int id = scan.nextInt();

        Employee emp = new Employee();
        emp.setDetails(name, id);
        emp.show();

        scan.close();
    }
}
