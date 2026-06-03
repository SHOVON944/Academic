package attendance;

import java.util.List;
import java.util.Objects;

public class Course {
    private String courseCode;
    private String courseTitle;
    private List<String> instructors;
    private double creditHours;
    private double midMarks;
    private double finalMarks;
    private double assignmentMarks;
    private double attendanceMarks; // Calculated based on attendance percentage
    private double totalMarks;    // mid + final + assignment + attendance
    private String letterGrade;
    private double gradePoint;
    private int totalClasses;
    private int attendedClasses;
    private String book;
    private String sheet;

    public Course(String courseCode, String courseTitle, List<String> instructors) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.instructors = instructors;
        this.creditHours = 0.0; // Default value
        this.midMarks = 0.0;    // Default value
        this.finalMarks = 0.0;  // Default value
        this.assignmentMarks = 0.0; // Default value
        this.attendanceMarks = 0.0;
        this.totalMarks = 0.0;
        this.letterGrade = "N/A";
        this.gradePoint = 0.0;
        this.totalClasses = 0;
        this.attendedClasses = 0;
        this.book = "N/A";
        this.sheet = "N/A";
    }
    
    public Course(String courseCode, String courseTitle, List<String> instructors, double creditHours, double midMarks, double finalMarks, double assignmentMarks) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.instructors = instructors;
        this.creditHours = creditHours;
        this.midMarks = midMarks;
        this.finalMarks = finalMarks;
        this.assignmentMarks = assignmentMarks;
        this.attendanceMarks = 0.0; // Will be calculated
        this.totalMarks = midMarks + finalMarks + assignmentMarks; // Initial, will add attendance
        this.letterGrade = "N/A"; // Will be calculated
        this.gradePoint = 0.0;    // Will be calculated
        this.totalClasses = 0;
        this.attendedClasses = 0;
        this.book = "N/A";
        this.sheet = "N/A";
    }

    // Getters and Setters
    public String getBook() {
        return (book == null) ? "N/A" : book;
    }

    public void setBook(String book) {
        this.book = (book == null) ? "N/A" : book;
    }

    public String getSheet() {
        return (sheet == null) ? "N/A" : sheet;
    }

    public void setSheet(String sheet) {
        this.sheet = (sheet == null) ? "N/A" : sheet;
    }
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public List<String> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<String> instructors) {
        this.instructors = instructors;
    }

    public double getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(double creditHours) {
        this.creditHours = creditHours;
    }

    public double getMidMarks() {
        return midMarks;
    }

    public void setMidMarks(double midMarks) {
        this.midMarks = midMarks;
        calculateTotalMarksAndGrade();
    }

    public double getFinalMarks() {
        return finalMarks;
    }

    public void setFinalMarks(double finalMarks) {
        this.finalMarks = finalMarks;
        calculateTotalMarksAndGrade();
    }

    public double getAssignmentMarks() {
        return assignmentMarks;
    }

    public void setAssignmentMarks(double assignmentMarks) {
        this.assignmentMarks = assignmentMarks;
        calculateTotalMarksAndGrade();
    }

    public double getAttendanceMarks() {
        return attendanceMarks;
    }

    public void setAttendanceMarks(double attendanceMarks) {
        this.attendanceMarks = attendanceMarks;
        calculateTotalMarksAndGrade();
    }

    public double getTotalMarks() {
        return totalMarks;
    }
    // No direct setter for totalMarks, it's calculated

    public String getLetterGrade() {
        return letterGrade;
    }
    // No direct setter for letterGrade, it's calculated

    public double getGradePoint() {
        return gradePoint;
    }
    // No direct setter for gradePoint, it's calculated

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
        calculateAttendanceMarks(); // Recalculate attendance marks if total classes change
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public void setAttendedClasses(int attendedClasses) {
        this.attendedClasses = attendedClasses;
        calculateAttendanceMarks(); // Recalculate attendance marks if attended classes change
    }

    public double getAttendancePercentage() {
        if (totalClasses == 0) {
            return 0.0;
        }
        return (double) attendedClasses / totalClasses * 100;
    }

    // Calculates attendance marks based on percentage
    public void calculateAttendanceMarks() {
        double percentage = getAttendancePercentage();
        if (percentage >= 89.5) {
            this.attendanceMarks = 10;
        } else if (percentage >= 79.5) {
            this.attendanceMarks = 9;
        } else if (percentage >= 69.5) {
            this.attendanceMarks = 8;
        } else if (percentage >= 59.5) {
            this.attendanceMarks = 7;
        } else {
            this.attendanceMarks = 0;
        }
        calculateTotalMarksAndGrade(); // Recalculate total marks and grade after attendance marks update
    }

    // Calculates total marks, letter grade, and grade point
    public void calculateTotalMarksAndGrade() {
        this.totalMarks = this.midMarks + this.finalMarks + this.assignmentMarks + this.attendanceMarks;
        
        if (this.totalMarks >= 79.5) {
            this.letterGrade = "A+";
            this.gradePoint = 4.00;
        } else if (this.totalMarks >= 74.5) {
            this.letterGrade = "A";
            this.gradePoint = 3.75;
        } else if (this.totalMarks >= 69.5) {
            this.letterGrade = "A-";
            this.gradePoint = 3.50;
        } else if (this.totalMarks >= 64.5) {
            this.letterGrade = "B+";
            this.gradePoint = 3.25;
        } else if (this.totalMarks >= 59.5) {
            this.letterGrade = "B";
            this.gradePoint = 3.00;
        } else if (this.totalMarks >= 54.5) {
            this.letterGrade = "B-";
            this.gradePoint = 2.75;
        } else if (this.totalMarks >= 49.5) {
            this.letterGrade = "C+";
            this.gradePoint = 2.50;
        } else if (this.totalMarks >= 44.5) {
            this.letterGrade = "C";
            this.gradePoint = 2.25;
        } else if (this.totalMarks > 39.5) {
            this.letterGrade = "D";
            this.gradePoint = 2.00;
        } else {
            this.letterGrade = "F";
            this.gradePoint = 0.00;
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", instructors=" + instructors +
                ", creditHours=" + creditHours +
                ", midMarks=" + midMarks +
                ", finalMarks=" + finalMarks +
                ", assignmentMarks=" + assignmentMarks +
                ", attendanceMarks=" + attendanceMarks +
                ", totalMarks=" + totalMarks +
                ", letterGrade='" + letterGrade + '\'' +
                ", gradePoint=" + gradePoint +
                ", totalClasses=" + totalClasses +
                ", attendedClasses=" + attendedClasses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Double.compare(course.creditHours, creditHours) == 0 &&
               Double.compare(course.midMarks, midMarks) == 0 &&
               Double.compare(course.finalMarks, finalMarks) == 0 &&
               Double.compare(course.assignmentMarks, assignmentMarks) == 0 &&
               Double.compare(course.attendanceMarks, attendanceMarks) == 0 &&
               Double.compare(course.totalMarks, totalMarks) == 0 &&
               Double.compare(course.gradePoint, gradePoint) == 0 &&
               totalClasses == course.totalClasses &&
               attendedClasses == course.attendedClasses &&
               Objects.equals(courseCode, course.courseCode) &&
               Objects.equals(courseTitle, course.courseTitle) &&
               Objects.equals(instructors, course.instructors) &&
               Objects.equals(letterGrade, course.letterGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode, courseTitle, instructors, creditHours, midMarks, finalMarks, assignmentMarks, attendanceMarks, totalMarks, letterGrade, gradePoint, totalClasses, attendedClasses);
    }
}
