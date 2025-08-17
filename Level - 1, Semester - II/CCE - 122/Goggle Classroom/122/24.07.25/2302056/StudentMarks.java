public class StudentMarks {
    String studentName;
    int[] marks = new int[5];

    public StudentMarks(String name, int[] marksArray) {
        studentName = name;
        for (int i = 0; i < 5; i++) {
            marks[i] = marksArray[i];
        }
    }

    public int calculateTotal() {
        int total = 0;
        for (int mark : marks) {
            total += mark;
        }
        return total;
    }

    public double calculateAverage() {
        return calculateTotal() / 5.0;
    }

    public void display() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Marks: ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println("\nTotal: " + calculateTotal());
        System.out.println("Average: " + calculateAverage());
    }

    public static void main(String[] args) {
        int[] marks = {85, 90, 78, 92, 88};
        StudentMarks student = new StudentMarks("Shovon", marks);
        student.display();
    }
}
