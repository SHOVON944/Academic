/*
E-Learning Platform 
 Students must enroll in courses online. 
 Throw: 
o DuplicateEnrollmentException if the student is already enrolled. 
o InvalidCourseException if the course doesn’t exist. 
*/

import java.util.HashSet;
import java.util.Scanner;

class DuplicateEnrollmentException extends Exception {
    DuplicateEnrollmentException(String message) {
        super(message);
    }
}

class InvalidCourseException extends Exception {
    InvalidCourseException(String message) {
        super(message);
    }
}

class ELearningPlatform {
    HashSet<String> availableCourses = new HashSet<>();
    HashSet<String> enrolledCourses = new HashSet<>();

    ELearningPlatform() {
        availableCourses.add("Java Programming");
        availableCourses.add("Python Basics");
        availableCourses.add("Data Structures");
        availableCourses.add("Web Development");
    }

    public void enrollCourse(String courseName)
            throws DuplicateEnrollmentException, InvalidCourseException {

        if (!availableCourses.contains(courseName))
            throw new InvalidCourseException("Course '" + courseName + "' does not exist.");

        if (enrolledCourses.contains(courseName))
            throw new DuplicateEnrollmentException("Already enrolled in '" + courseName + "'.");

        enrolledCourses.add(courseName);
        System.out.println("✅ Successfully enrolled in: " + courseName);
    }

    public void showAvailableCourses() {
        System.out.println("\n📚 Available Courses:");
        for (String c : availableCourses) {
            System.out.println("- " + c);
        }
    }
}

public class P_32 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ELearningPlatform platform = new ELearningPlatform();

        while (true) {
            platform.showAvailableCourses();
            System.out.print("\nEnter course name to enroll (or type 'exit' to quit): ");
            String courseName = scan.nextLine();

            if (courseName.equalsIgnoreCase("exit")) break;

            try {
                platform.enrollCourse(courseName);
            } catch (DuplicateEnrollmentException e) {
                System.out.println("Duplicate Enrollment: " + e.getMessage());
            } catch (InvalidCourseException e) {
                System.out.println("Invalid Course: " + e.getMessage());
            }
        }

        System.out.println("\nThank you for using the E-Learning Platform!");
        scan.close();
    }
}
