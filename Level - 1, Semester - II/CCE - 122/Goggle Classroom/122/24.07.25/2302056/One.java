class Student {
    String name;
    int rollNumber;

    Student(String n, int r) {
        name = n;
        rollNumber = r;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
    }
}

class One {
    public static void main(String[] args) {
        Student s = new Student("SHOVON", 56);
        s.display();
    }
}