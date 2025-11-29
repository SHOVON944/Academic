class Employee{
    float salary = 40000;
}

class Programmer extends Employee{
    int bonus = 10;
}

public class Basic{
    public static void main(String[] args) {
        Programmer p = new Programmer();
        System.out.println("Programmer Salary: " + p.salary);
        System.out.println("Programmer Salary: " + p.bonus);
    }
}