package attendance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

public class CgpaService {
    private DataStorage dataStorage;
    private DataManager dataManager;
    private AttendanceService attendanceService;

    public CgpaService(DataStorage dataStorage, DataManager dataManager, AttendanceService attendanceService) {
        this.dataStorage = dataStorage;
        this.dataManager = dataManager;
        this.attendanceService = attendanceService;
    }

    public void addOrUpdateCgpaCourse(Semester semester, Course newCourse) {
        // Ensure semester is configured before adding CGPA data
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null || !semesterConfig.isEntered()) {
            System.out.println(ConsoleColors.RED + "Semester " + semester.getDisplayValue() + " is not configured. Please configure it first." + ConsoleColors.RESET);
            return;
        }

        List<Course> courses = dataStorage.getCgpaData().getOrDefault(semester, new ArrayList<>());
        
        // Update attendance and calculate marks/grade for the new course
        updateCourseAttendanceAndMarks(newCourse, semester);

        boolean found = false;
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseCode().equals(newCourse.getCourseCode())) {
                courses.set(i, newCourse);
                found = true;
                break;
            }
        }
        if (!found) {
            courses.add(newCourse);
        }
        
        dataStorage.getCgpaData().put(semester, courses);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Course " + newCourse.getCourseCode() + " CGPA data added/updated for " + semester.getDisplayValue() + "." + ConsoleColors.RESET);
    }

    private void updateCourseAttendanceAndMarks(Course course, Semester semester) {
        int totalClasses = attendanceService.getTotalClassesForCourse(course.getCourseCode(), semester);
        int attendedClasses = attendanceService.getAttendedClassesForCourse(course.getCourseCode(), semester);
        
        course.setTotalClasses(totalClasses);
        course.setAttendedClasses(attendedClasses);
        course.calculateAttendanceMarks(); // This will also call calculateTotalMarksAndGrade
    }

    public SgpaResult getSgpaBreakdown(Semester semester) {
        System.out.println(ConsoleColors.CYAN_BOLD + "\n--- Starting SGPA Calculation for " + semester.getDisplayValue() + " ---" + ConsoleColors.RESET);
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null || !semesterConfig.isEntered() || semesterConfig.getCourses().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "Semester not configured or has no courses." + ConsoleColors.RESET);
            return new SgpaResult(0.0, new ArrayList<>());
        }

        List<Course> coursesInSemester = semesterConfig.getCourses();
        List<Course> uniqueCoursesForDisplay = new ArrayList<>();
        
        double totalWeightedGradePoints = 0;
        double totalCredits = 0;
        Set<String> processedCourseCodes = new HashSet<>();

        System.out.println(ConsoleColors.YELLOW + "Processing courses..." + ConsoleColors.RESET);
        for (Course course : coursesInSemester) {
            if (!processedCourseCodes.add(course.getCourseCode())) {
                continue;
            }
            updateCourseAttendanceAndMarks(course, semester);
            uniqueCoursesForDisplay.add(course);

            double weightedPoints = course.getGradePoint() * course.getCreditHours();
            totalWeightedGradePoints += weightedPoints;
            totalCredits += course.getCreditHours();

            System.out.println(String.format("  - %sCourse: %s%s, %sCredits: %s%.1f, %sGrade Point: %s%.2f, %sWeighted: %s%.2f",
                ConsoleColors.WHITE, ConsoleColors.GREEN_BRIGHT, course.getCourseCode(),
                ConsoleColors.WHITE, ConsoleColors.CYAN_BRIGHT, course.getCreditHours(),
                ConsoleColors.WHITE, ConsoleColors.PURPLE_BRIGHT, course.getGradePoint(),
                ConsoleColors.WHITE, ConsoleColors.YELLOW_BRIGHT, weightedPoints) + ConsoleColors.RESET);
        }
        
        System.out.println(ConsoleColors.CYAN_BOLD + "\n--- Final Calculation ---" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "Total Weighted Grade Points: " + ConsoleColors.GREEN_BOLD_BRIGHT + String.format("%.2f", totalWeightedGradePoints) + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + "Total Credit Hours: " + ConsoleColors.GREEN_BOLD_BRIGHT + String.format("%.1f", totalCredits) + ConsoleColors.RESET);

        double sgpa = (totalCredits == 0) ? 0.0 : totalWeightedGradePoints / totalCredits;
        
        String sgpaColor = (sgpa >= 3.0) ? ConsoleColors.GREEN_BOLD_BRIGHT : (sgpa >= 2.0) ? ConsoleColors.YELLOW_BOLD_BRIGHT : ConsoleColors.RED_BOLD_BRIGHT;
        System.out.println(ConsoleColors.CYAN_BOLD + "Final SGPA: " + sgpaColor + String.format("%.3f", sgpa) + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN_BOLD + "---------------------------------------\n" + ConsoleColors.RESET);

        return new SgpaResult(sgpa, uniqueCoursesForDisplay);
    }

    public double calculateSGPA(Semester semester) {
        return getSgpaBreakdown(semester).sgpa();
    }
    
    public List<Course> getSemesterCgpa(Semester semester) {
        return getSgpaBreakdown(semester).courses();
    }

    public OverallCgpaResult calculateOverallCgpa() {
        double totalWeightedGradePoints = 0;
        double totalCredits = 0;
        Set<String> processedCourseCodes = new HashSet<>();

        List<Semester> allSemesters = new ArrayList<>(dataStorage.getSemesterConfigurations().keySet());
        allSemesters.sort(Comparator.naturalOrder());

        for (Semester semester : allSemesters) {
            SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
            if (semesterConfig != null && semesterConfig.isEntered() && !semesterConfig.getCourses().isEmpty()) {
                for (Course course : semesterConfig.getCourses()) {
                     if (!processedCourseCodes.add(course.getCourseCode())) {
                        continue;
                    }
                    updateCourseAttendanceAndMarks(course, semester);
                    totalWeightedGradePoints += (course.getGradePoint() * course.getCreditHours());
                    totalCredits += course.getCreditHours();
                }
            }
        }

        if (totalCredits == 0) {
            return new OverallCgpaResult(0.0, 0.0);
        }
        return new OverallCgpaResult(totalWeightedGradePoints / totalCredits, totalCredits);
    }

    public OverallCgpaResult calculateOverallCgpa(List<Semester> semestersToInclude) {
        double totalWeightedGradePoints = 0;
        double totalCredits = 0;
        Set<String> processedCourseCodes = new HashSet<>();

        // Sort the provided semesters to ensure consistent calculation order
        semestersToInclude.sort(Comparator.naturalOrder());

        for (Semester semester : semestersToInclude) {
            SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
            if (semesterConfig != null && semesterConfig.isEntered() && !semesterConfig.getCourses().isEmpty()) {
                for (Course course : semesterConfig.getCourses()) {
                     if (!processedCourseCodes.add(course.getCourseCode())) {
                        continue;
                    }
                    updateCourseAttendanceAndMarks(course, semester);
                    totalWeightedGradePoints += (course.getGradePoint() * course.getCreditHours());
                    totalCredits += course.getCreditHours();
                }
            }
        }

        if (totalCredits == 0) {
            return new OverallCgpaResult(0.0, 0.0);
        }
        return new OverallCgpaResult(totalWeightedGradePoints / totalCredits, totalCredits);
    }

    public void removeCgpaCourse(Semester semester, String courseCode) {
        // This method will now operate on the courses within SemesterConfig directly
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig != null) {
            boolean removed = semesterConfig.getCourses().removeIf(c -> c.getCourseCode().equals(courseCode));
            if (removed) {
                dataManager.saveData(dataStorage);
                System.out.println(ConsoleColors.GREEN + "Course " + courseCode + " removed from " + semester.getDisplayValue() + "." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.YELLOW + "Course " + courseCode + " not found in " + semester.getDisplayValue() + "." + ConsoleColors.RESET);
            }
        } else {
            System.out.println(ConsoleColors.YELLOW + "No configuration for " + semester.getDisplayValue() + "." + ConsoleColors.RESET);
        }
    }

    public void clearSemesterCgpa(Semester semester) {
        // This method will now clear courses from SemesterConfig
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig != null) {
            semesterConfig.getCourses().clear();
            semesterConfig.setEntered(false); // Mark as not configured if courses are cleared
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "All courses cleared for " + semester.getDisplayValue() + "." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "No configuration for " + semester.getDisplayValue() + " to clear." + ConsoleColors.RESET);
        }
    }
    
    public void clearAllCgpaData() {
        // This will clear courses from all semester configurations
        for (SemesterConfig config : dataStorage.getSemesterConfigurations().values()) {
            config.getCourses().clear();
            config.setEntered(false);
        }
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "All course data cleared for all semesters." + ConsoleColors.RESET);
    }
}