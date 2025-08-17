class Student {
    String name;
    int age;
    Student(String n, int a) {
        name = n;
        age = a;
    }

    Student(Student s) {
        name = s.name;
        age = s.age;
    }

    boolean isEqual(Student s) {
        return name.equals(s.name) && age == s.age;
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Nineteen {
    public static void main(String[] args) {
        Student s1 = new Student("Shovon", 21);
        Student s2 = new Student(s1);
        Student s3 = new Student("Rakib", 22);

        s1.display();
        s2.display();
        s3.display();

        System.out.println("s1 == s2 (reference)? " + (s1 == s2));
        System.out.println("s1 equals s2 (content)? " + s1.isEqual(s2));
        System.out.println("s1 equals s3 (content)? " + s1.isEqual(s3));
    }
}