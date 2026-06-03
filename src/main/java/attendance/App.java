package attendance;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonNull;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap; // Added HashMap import
import java.util.Set; // Added
import java.util.TimeZone;
import java.util.HashSet; // Added
import java.util.stream.Collectors;
import utils.ColorPrint;
import utils.Animation;
import java.util.Scanner;
import java.util.TimeZone; 
import attendance.OverallCgpaResult;
import java.util.Comparator;

public class App {
    private static DataManager dataManager;
    private static DataStorage dataStorage;
    private static TaskService taskService;
    private static AttendanceService attendanceService;
    private static CgpaService cgpaService;
    private static DailyBuyService dailyBuyService;
    private static RegistrationFeeService registrationFeeService;
    private static ExpenseService expenseService; // Added ExpenseService
    private static FundService fundService; // New FundService
    private static Scanner scanner = new Scanner(System.in);
    private static String SIGNATURE_IMAGE_PATH;
    private static int currentNavigationDepth = 0; // Tracks current menu depth for navigation flow
    private static int colorIndex = 0; // For cycling line number colors

    private static void logAction(String actionMessage) {
        dataStorage.getActionHistory().add(new DataStorage.ActionEntry(actionMessage, LocalDateTime.now()));
        dataManager.saveData(dataStorage);
    }

    private static void logNavigation(String menuName, String actionType) {
        // actionType should be "FORWARD" or "BACKWARD"
        if ("FORWARD".equals(actionType)) {
            currentNavigationDepth++;
        } else if ("BACKWARD".equals(actionType)) {
            currentNavigationDepth--;
            if (currentNavigationDepth < 0) { // Should not happen if logic is correct
                currentNavigationDepth = 0;
            }
        }
        dataStorage.getNavigationHistory().add(new DataStorage.NavigationEvent(
            LocalDateTime.now(), menuName, actionType, currentNavigationDepth));
        dataManager.saveData(dataStorage);
    }

    public static void main(String[] args) throws InterruptedException {
        // Welcome Animation
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Dhaka"));
        welcomeAnimation();
        String dataFilePath = ConfigManager.getDataFilePath();
        SIGNATURE_IMAGE_PATH = ConfigManager.getSignaturePath();
        // Initialize DataManager and Services
        dataManager = new DataManager(dataFilePath);
        dataStorage = dataManager.loadData();
        taskService = new TaskService(dataStorage, dataManager);
        attendanceService = new AttendanceService(dataStorage, dataManager);
        cgpaService = new CgpaService(dataStorage, dataManager, attendanceService);
        dailyBuyService = new DailyBuyService(dataStorage, dataManager);
        registrationFeeService = new RegistrationFeeService(dataStorage, dataManager);
        expenseService = new ExpenseService(dataStorage, dataManager, SIGNATURE_IMAGE_PATH); // Initialized ExpenseService
        fundService = new FundService(dataStorage, dataManager); // Initialized FundService
        // Conditionally populate initial attendance and configure courses for L1S1/L1S2
        // if no attendance data exists
        /*
         * if (dataStorage.getClassAttendances().isEmpty()||
         * dataStorage.getSemesterConfigurations().getOrDefault(Semester.L1S1, new
         * SemesterConfig()).getCourses().isEmpty() ||
         * dataStorage.getSemesterConfigurations().getOrDefault(Semester.L1S2, new
         * SemesterConfig()).getCourses().isEmpty()) {
         * populateInitialAttendanceAndCourses();
         * }
         */
        // Main Application Loop
        taskService.updateOverdueTasks(); // Update overdue tasks on startup
        runApplication();
    }

    private static void welcomeAnimation() throws InterruptedException {
        String mainWelcomeMessage = "SHOVON Daily Attendance & Task Tracker"; // Reinstated descriptive message

        System.out.println("Loading...");
        System.out.println(); // Ensure new line after "Loading..."
        Utils.clearConsole();

        // New ASCII art banner (plain text for maximum compatibility)
        System.out.println("\n");

        String welcomeAsciiArt =
                "╔══════════════════════════════════╗\n" +
                "║░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░║\n" +
                "╚══════════════════════════════════╝\n" +
                " ▒▓█░▒▓█░▒▓█░▒▓█░▒▓█░▒▓█░▒▓█░▒▓█░▒▓ \n" +
                "************************************\n" +
                "*                                  *\n" +
                "*               SHREA              *\n" +
                "*  M. D.  S A K I B U L  I S L A M *\n" +
                "*            S H O V O N           *\n" +
                "*                0 6               *\n" +
                "*                                  *\n" +
                "************************************";


        System.out.println("\n");
        Animation.printWithRandomColorAnimation(welcomeAsciiArt, 5);

        System.out.println(); // Space after banner

        // Main welcome text with colors
        System.out.println(ConsoleColors.GREEN_BOLD + "Welcome, SHOVON!" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.YELLOW + mainWelcomeMessage + ConsoleColors.RESET);

        Thread.sleep(1500); // Increased pause to admire the welcome screen
        Utils.clearConsole();
    }

    private static void runApplication() throws InterruptedException {
        logNavigation("Main Menu", "FORWARD"); // Log entry into Main Menu
        while (true) {
            displayMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:

                    logNavigation("Study Menu", "FORWARD");
                    manageUniversityMenu();
                    logNavigation("Study Menu", "BACKWARD");
                    break;
                case 2:

                    logNavigation("Daily Routine Menu", "FORWARD");
                    manageDailyRoutine();
                    logNavigation("Daily Routine Menu", "BACKWARD");
                    break;
                case 3:

                    logNavigation("Daily Buy Menu", "FORWARD");
                    manageDailyBuy();
                    logNavigation("Daily Buy Menu", "BACKWARD");
                    break;
                case 4:

                    logNavigation("View Report Menu", "FORWARD");
                    viewReport();
                    logNavigation("View Report Menu", "BACKWARD");
                    break;
                case 5: // New Flow option

                    logNavigation("Flow Menu", "FORWARD"); // Treat viewFlow as entering a temporary "Flow" menu
                    viewFlow();
                    logNavigation("Flow Menu", "BACKWARD"); // Exiting the "Flow" menu
                    break;
                case 6: // New Data option

                    logNavigation("Data View", "FORWARD");
                    viewDataJson();
                    logNavigation("Data View", "BACKWARD");
                    break;
                case 7: // Shifted from 6 (Generate Reports (PDF))

                    logNavigation("Generate Reports (PDF)", "FORWARD");
                    generatePdfReport();
                    logNavigation("Generate Reports (PDF)", "BACKWARD");
                    break;
                case 0:
                    logAction("Exiting Application");
                    System.out.println(
                            ConsoleColors.CYAN_BOLD + "Exiting application. Goodbye, SHOVON!" + ConsoleColors.RESET);
                    scanner.close();
                    logNavigation("Main Menu", "BACKWARD"); // Log exit from Main Menu
                    return;
                default:
                    logAction("Invalid Main Menu choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
            Utils.clearConsole();
        }
    }

    private static void displayMainMenu() {
        // Analog Clock
        AnalogClock.drawClock();
        System.out.println(); // Add space after analog clock

        // Digital Clock
        Utils.printDigitalClock();
        System.out.println(); // Add space after digital clock

        Utils.printHeader("         Main Menu");
        System.out.println(); // Add space after header
        System.out.println(ConsoleColors.CYAN + "  ‍🏫 1. Study" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  📅 2. Manage Daily Routine" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  🛒 3. Cash" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  📚 4. View Report" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  🔄 5. Flow" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  📊 6. Data" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  📄 7. Generate Reports (PDF)" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "  🔚 0. Exit" + ConsoleColors.RESET);
        System.out.println(); // Add space before footer
        
        Utils.printFooter();
        System.out.println(); // Add space after footer
        System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
    }

    private static void manageUniversityMenu() throws InterruptedException {
        while (true) {
            universityMainMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    logNavigation("Attendance Options", "FORWARD");
                    manageAttendanceOptions();
                    logNavigation("Attendance Options", "BACKWARD");
                    break;
                case 2:
                    logNavigation("Done Study Management", "FORWARD");
                    manageDoneStudy();
                    logNavigation("Done Study Management", "BACKWARD");
                    break;
                case 3:

                    logNavigation("Configure Course Section", "FORWARD");
                    manageConfigureCourseSection();
                    logNavigation("Configure Course Section", "BACKWARD");
                    break;
                case 4:

                    logNavigation("Make Current Semester", "FORWARD");
                    makeCurrentSemester();
                    logNavigation("Make Current Semester", "BACKWARD");
                    break;
                case 5:

                    logNavigation("Set Course Details", "FORWARD");
                    setCourseDetails();
                    logNavigation("Set Course Details", "BACKWARD");
                    break;
                case 6:

                    logNavigation("CGPA Calculation", "FORWARD");
                    manageCgpaCalculation();
                    logNavigation("CGPA Calculation", "BACKWARD");
                    break;
                case 7:

                    logNavigation("My Details", "FORWARD");
                    setStudentDetails();
                    logNavigation("My Details", "BACKWARD");
                    break;
                case 8:

                    logNavigation("Registration Fee Management", "FORWARD");
                    manageRegistrationFee();
                    logNavigation("Registration Fee Management", "BACKWARD");
                    break;
                case 9:

                    logNavigation("Generate University Reports (PDF)", "FORWARD");
                    generateUniversityReports();
                    logNavigation("Generate University Reports (PDF)", "BACKWARD");
                    break;
                case 10:

                    logNavigation("Semester Details", "FORWARD");
                    manageSemesterDetails(); // New
                    logNavigation("Semester Details", "BACKWARD");
                    break;
                case 11:
                    logNavigation("Comprehensive University Data", "FORWARD");
                    viewComprehensiveUniversityData();
                    logNavigation("Comprehensive University Data", "BACKWARD");
                    break;
                case 0:

                    logNavigation("Study Menu", "BACKWARD"); // Log exit from Study Menu
                    return; // Return to main menu
                default:
                    logAction("Invalid Study Menu choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
            }
            Utils.clearConsole();
        }
    }

    private static void manageDoneStudy() throws InterruptedException {

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Done Study Management");
            Utils.printDigitalClock();
            System.out.println();

            System.out.println(ConsoleColors.CYAN + "  1. Mark Class Sessions as Done Study" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. View Overall Course Study Progress" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. View Individual Course Study Progress" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. View All Course Details" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  5. Pending Study Details" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  6. Attendance History (with Topics)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  7. Remove Done Study Mark" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  ↩️ 0. Back to Study Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    markClassSessionsAsDoneStudy();
                    break;
                case 2:
                    viewCourseStudyProgress();
                    break;
                case 3:
                    viewIndividualCourseStudyProgress();
                    break;
                case 4:
                    viewAllClassDetails();
                    break;
                case 5:
                    viewPendingStudyDetails();
                    break;
                case 6:
                    viewAllAttendanceRecords();
                    break;
                case 7:
                    removeDoneStudyMark();
                    break;
                case 0:
                    return;
                default:
                    logAction("Invalid Done Study Management choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static String stripAnsi(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    private static void printPadded(String text, int width) {
        String plainText = stripAnsi(text);
        int padding = Math.max(0, width - plainText.length());
        System.out.print(text + " ".repeat(padding));
    }

    private static void viewAllClassDetails() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("All Class Details for Current Semester");
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }
        System.out.println(ConsoleColors.CYAN_BOLD + "Showing details for semester: " + currentSemester.getDisplayValue() + ConsoleColors.RESET);
        System.out.println();

        List<ClassAttendance> semesterAttendances = dataStorage.getClassAttendances().stream()
                .filter(ca -> ca.getSemester() == currentSemester)
                .collect(Collectors.toList());

        if (semesterAttendances.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No class attendance data found for the current semester." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        Map<LocalDate, List<ClassAttendance>> groupedByDate = semesterAttendances.stream()
                .collect(Collectors.groupingBy(ClassAttendance::getDate, () -> new java.util.TreeMap<>(Comparator.naturalOrder()), Collectors.toList()));

        String separator = ConsoleColors.GRAY + " | " + ConsoleColors.RESET;
        String classLabel = "Class t";
        String doneLabel = "Done t";
        int labelWidth = Math.max(classLabel.length(), doneLabel.length());
        
        int col1Width = 38;
        int col2Width = 23;

        String classEntrySeparator = ConsoleColors.PURPLE_BOLD + "|| " + ConsoleColors.RESET + ConsoleColors.BLUE + "---------------------------------------------------------------------------------------------" + ConsoleColors.PURPLE_BOLD + " || " + ConsoleColors.RESET; // 79 hyphens
        String dateBlockSeparator = ConsoleColors.PURPLE_BOLD + " =================================================================================================" + ConsoleColors.RESET; // 79 equals

        for (Map.Entry<LocalDate, List<ClassAttendance>> entry : groupedByDate.entrySet()) {
            LocalDate date = entry.getKey();
            List<ClassAttendance> dayAttendances = entry.getValue();
            dayAttendances.sort(Comparator.comparing(ClassAttendance::getTime));

            System.out.println(ConsoleColors.YELLOW_BOLD + "Date: " + Utils.formatDateAndDayWithColors(date, ConsoleColors.YELLOW_BOLD, ConsoleColors.LIGHT_GRAY) + ConsoleColors.RESET);
            System.out.println(dateBlockSeparator);

            for (int i = 0; i < dayAttendances.size(); i++) {
                ClassAttendance ca = dayAttendances.get(i);
                Course course = findCourse(ca.getCourseCode(), ca.getSemester());
                String courseName = (course != null) ? course.getCourseTitle() : "N/A";
                String courseCode = ca.getCourseCode();
                String teacherName = ca.getTeacherName() != null ? ca.getTeacherName() : "N/A";
                
                // --- Prepare Line 1 parts ---
                String classDateStr = date.format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                String classTimeStr = ca.getTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
                String classLine = String.format("%s%-" + labelWidth + "s%s: %s%s%s at %s%s%s", 
                                                 ConsoleColors.YELLOW, classLabel, ConsoleColors.RESET,
                                                 ConsoleColors.CYAN_BRIGHT, classDateStr, ConsoleColors.RESET,
                                                 ConsoleColors.BLUE_BRIGHT, classTimeStr, ConsoleColors.RESET + 
                                                 ConsoleColors.PURPLE_BOLD + " ||" + ConsoleColors.RESET);

                // --- Print Line 1 ---
                printPadded(ConsoleColors.PURPLE_BOLD + "|| " + ConsoleColors.RESET + ConsoleColors.BLUE_BOLD + courseName + ConsoleColors.RESET, col1Width);
                System.out.print(separator);
                printPadded(ConsoleColors.COLOR_CYCLE_13 + teacherName + ConsoleColors.RESET, col2Width);
                System.out.print(separator);
                System.out.println(classLine);

                // --- Prepare Line 2 parts ---
                String attendanceChar = ca.isPresent() ? "P" : "A";
                String onlineIndicator = ca.isOnline() ? ConsoleColors.GREEN + "●" + ConsoleColors.RESET : "";
                String attendanceColor = ca.isPresent() ? ConsoleColors.GREEN_BOLD : ConsoleColors.RED_BOLD;
                String coloredAttendance = attendanceColor + attendanceChar + onlineIndicator + ConsoleColors.RESET;

                String studiedString = ca.isStudied() ? "Done" : "Pending";
                String studiedColor = ca.isStudied() ? ConsoleColors.GREEN_BOLD : ConsoleColors.YELLOW_BOLD;
                String coloredStudied = studiedColor + studiedString + ConsoleColors.RESET;

                String statusLine = coloredAttendance + " " + separator + " " + coloredStudied;
                
                String doneLine;
                if (ca.isStudied() && ca.getStudiedDate() != null && ca.getStudiedTime() != null) {
                    String doneTimeStr = ca.getStudiedTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
                    String doneDateStr = ca.getStudiedDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                    doneLine = String.format("%s%-" + labelWidth + "s%s: %s%s%s on %s%s%s",
                                             ConsoleColors.YELLOW, doneLabel, ConsoleColors.RESET,
                                             ConsoleColors.YELLOW_BRIGHT, doneTimeStr, ConsoleColors.RESET,
                                             ConsoleColors.GREEN_BRIGHT, doneDateStr, ConsoleColors.RESET + 
                                             ConsoleColors.PURPLE_BOLD + " ||" + ConsoleColors.RESET);
                } else {
                    doneLine = ConsoleColors.PURPLE_BOLD + "Done t : " + ConsoleColors.ROSE + "  - - - - - - - - - " + ConsoleColors.PURPLE_BOLD + " ||" + ConsoleColors.RESET;
                }
                
                // --- Print Line 2 ---
                printPadded(ConsoleColors.PURPLE_BOLD + "|| " + ConsoleColors.RESET + ConsoleColors.LIME_GREEN + courseCode + ConsoleColors.RESET, col1Width);
                System.out.print(separator);
                printPadded(statusLine, col2Width);
                System.out.print(separator);
                System.out.println(doneLine);

                // --- Print Topic Line (with Word Wrap) ---
                if (ca.getTopic() != null && !ca.getTopic().isEmpty()) {
                    String topicText = ca.getTopic();
                    int firstLineMax = 86; // Space available on the first line after "Topic: "
                    int subsequentLineMax = 93; // Space available on subsequent lines (under the whole block)
                    boolean firstLine = true;

                    while (!topicText.isEmpty()) {
                        System.out.print(ConsoleColors.PURPLE_BOLD + "|| " + ConsoleColors.RESET);
                        int currentMax;
                        if (firstLine) {
                            System.out.print(ConsoleColors.CYAN + "Topic: " + ConsoleColors.RESET);
                            currentMax = firstLineMax;
                        } else {
                            currentMax = subsequentLineMax;
                        }

                        if (topicText.length() <= currentMax) {
                            printPadded(topicText, currentMax);
                            topicText = "";
                        } else {
                            // Find wrap index
                            int wrapIndex = topicText.lastIndexOf(' ', currentMax);
                            if (wrapIndex <= 0) wrapIndex = currentMax; // No space, force break

                            String chunk = topicText.substring(0, wrapIndex).trim();
                            printPadded(chunk, currentMax);
                            topicText = topicText.substring(wrapIndex).trim();
                        }
                        System.out.println(ConsoleColors.PURPLE_BOLD + " ||" + ConsoleColors.RESET);
                        firstLine = false;
                    }
                }

                // --- Print Materials Line (Session Specific) ---
                if (ca.getMaterials() != null && !ca.getMaterials().isEmpty()) {
                    System.out.print(ConsoleColors.PURPLE_BOLD + "|| " + ConsoleColors.RESET);
                    String materialsLine = ConsoleColors.CYAN + "Preferred by Sir: " + ConsoleColors.YELLOW + String.join(", ", ca.getMaterials()) + ConsoleColors.RESET;
                    printPadded(materialsLine, 93);
                    System.out.println(ConsoleColors.PURPLE_BOLD + " ||" + ConsoleColors.RESET);
                }
                
                // --- Conditional Separator ---
                if (i < dayAttendances.size() - 1) {
                    System.out.println(classEntrySeparator);
                } else {
                    System.out.println(dateBlockSeparator);
                }
            }
            System.out.println();
        }

        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }
    
    private static Course findCourse(String courseCode, Semester semester) {
        if (semester == null || courseCode == null) {
            return null;
        }
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig != null) {
            for (Course course : semesterConfig.getCourses()) {
                if (course.getCourseCode().equals(courseCode)) {
                    return course;
                }
            }
        }
        return null;
    }


    private static void viewIndividualCourseStudyProgress() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("View Individual Course Study Progress");
            System.out.println();

            Semester currentSemester = dataStorage.getCurrentSemester();
            if (currentSemester == null) {
                System.out.println(ConsoleColors.RED
                        + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
                Thread.sleep(2000);
                return;
            }

            // Get courses for the current semester
            List<Course> semesterCourses = dataStorage.getSemesterConfigurations().get(currentSemester)
                    .getCourses();

            if (semesterCourses.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No courses configured for "
                        + currentSemester.getDisplayValue() + "." + ConsoleColors.RESET);
                Thread.sleep(2000);
                return;
            }

            // Display courses and let user select one
            System.out.println("\n" + ConsoleColors.CYAN + "Courses in " + currentSemester.getDisplayValue() + ":"
                    + ConsoleColors.RESET);
            for (int i = 0; i < semesterCourses.size(); i++) {
                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + semesterCourses.get(i).getCourseCode()
                        + " - " + semesterCourses.get(i).getCourseTitle() + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);

            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
            int courseChoice = getUserChoice();

            if (courseChoice == 0) {
                return;
            }

            if (courseChoice < 1 || courseChoice > semesterCourses.size()) {
                System.out.println(ConsoleColors.RED + "Invalid course choice." + ConsoleColors.RESET);
                continue;
            }

            Course selectedCourse = semesterCourses.get(courseChoice - 1);
            String courseCode = selectedCourse.getCourseCode();

            // Get all class sessions for the selected course
            List<ClassAttendance> allCourseClassSessions = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getSemester() == currentSemester && ca.getCourseCode().equals(courseCode) && ca.isPresent())
                    .sorted(Comparator.comparing(ClassAttendance::getDate))
                    .collect(Collectors.toList());

            if (allCourseClassSessions.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No present class sessions found for " + courseCode + " in "
                        + currentSemester.getDisplayValue() + "." + ConsoleColors.RESET);
                Thread.sleep(2000);
                continue;
            }

            // Group sessions by teacher
            Map<String, List<ClassAttendance>> sessionsByTeacher = allCourseClassSessions.stream()
                    .collect(Collectors.groupingBy(ca -> ca.getTeacherName() != null ? ca.getTeacherName() : "N/A"));

            Utils.clearConsole();
            Utils.printHeader("Class Sessions for " + courseCode + " - " + currentSemester.getDisplayValue());
            System.out.println();

            for (Map.Entry<String, List<ClassAttendance>> entry : sessionsByTeacher.entrySet()) {
                String teacherName = entry.getKey();
                List<ClassAttendance> teacherSessions = entry.getValue();

                System.out.println(ConsoleColors.YELLOW_BOLD + "Instructor: " + teacherName + ConsoleColors.RESET);

                System.out.println(ConsoleColors.BLUE_BOLD + String.format("%-3s | %-16s | %-5s | %-8s | %-6s | %-20s",
                        "No.", "Date", "Time", "Sd. stts", "Done t", "Done Date") + ConsoleColors.RESET);
                System.out.println(ConsoleColors.BLUE_BOLD
                        + "----------------------------------------------------------------------" // 79 hyphens
                        + ConsoleColors.RESET);

                for (int i = 0; i < teacherSessions.size(); i++) {
                    ClassAttendance session = teacherSessions.get(i);
                    String statusText = session.isStudied() ? "DONE" : "PENDING";
                    String studiedStatusFormatted = session.isStudied() 
                            ? ConsoleColors.GREEN + statusText + ConsoleColors.RESET
                            : ConsoleColors.RED + statusText + ConsoleColors.RESET;

                    String studiedDateStr = session.getStudiedDate() != null
                            ? session.getStudiedDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
                            : "N/A";
                    String studiedTimeStr = session.getStudiedTime() != null
                            ? session.getStudiedTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                            : "N/A";
                    // Print No.
                    System.out.print(ConsoleColors.ROSE);
                    printPadded(String.valueOf(i + 1), 3);
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print Date
                    printPadded(Utils.formatDateAndDayWithColors(session.getDate(), ConsoleColors.CYAN_BRIGHT, ConsoleColors.LIGHT_GRAY), 16);
                    System.out.print(" | ");

                    // Print Time
                    System.out.print(ConsoleColors.YELLOW);
                    printPadded(session.getTime().format(DateTimeFormatter.ofPattern("HH:mm")), 5);
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print Studied Status
                    printPadded(studiedStatusFormatted, 8);
                    System.out.print(" | ");

                    // Print St. Time
                    System.out.print(ConsoleColors.GOLD);
                    printPadded(studiedTimeStr, 6);
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print St. Date
                    printPadded((session.getStudiedDate() != null ? Utils.formatDateAndDayWithColors(session.getStudiedDate(), ConsoleColors.GREEN_BOLD_BRIGHT, ConsoleColors.MAGENTA_BRIGHT) : "N/A"), 20);
                    System.out.println(); // Newline at the end
                }
                
                long totalSessions = teacherSessions.size();
                long studiedSessions = teacherSessions.stream().filter(ClassAttendance::isStudied).count();
                double progressPercentage = (totalSessions > 0) ? ((double) studiedSessions / totalSessions) * 100 : 0.0;
                String progressColor = getProgressBarColor(progressPercentage);

                System.out.println(ConsoleColors.BLUE_BOLD
                        + "----------------------------------------------------------------------"
                        + ConsoleColors.RESET);
                System.out.println(String.format(ConsoleColors.CYAN_BOLD + "  Study Progress: %s%.2f%%" + ConsoleColors.RESET, progressColor, progressPercentage));
                System.out.println();
            }
            
            System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
            scanner.nextLine();
        }
    }

    private static void removeDoneStudyMark() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Remove Done Study Mark");
            System.out.println();

            Semester currentSemester = dataStorage.getCurrentSemester();
            if (currentSemester == null) {
                System.out.println(ConsoleColors.RED
                        + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
                return;
            }

            // Get courses for the current semester
            List<Course> semesterCourses = dataStorage.getSemesterConfigurations().get(currentSemester)
                    .getCourses();

            if (semesterCourses.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No courses configured for "
                        + currentSemester.getDisplayValue() + "." + ConsoleColors.RESET);
                return;
            }

            // Display courses and let user select one
            System.out.println("\n" + ConsoleColors.CYAN + "Courses in " + currentSemester.getDisplayValue() + ":"
                    + ConsoleColors.RESET);
            for (int i = 0; i < semesterCourses.size(); i++) {
                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + semesterCourses.get(i).getCourseCode()
                        + " - " + semesterCourses.get(i).getCourseTitle() + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);

            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
            int courseChoice = getUserChoice();

            if (courseChoice == 0) {
                return;
            }

            if (courseChoice < 1 || courseChoice > semesterCourses.size()) {
                System.out.println(ConsoleColors.RED + "Invalid course choice." + ConsoleColors.RESET);
                continue;
            }

            Course selectedCourse = semesterCourses.get(courseChoice - 1);
            String courseCode = selectedCourse.getCourseCode();

            // Teacher Selection
            List<String> instructors = selectedCourse.getInstructors();
            String selectedInstructor = null;

            if (instructors != null && !instructors.isEmpty()) {
                if (instructors.size() == 1) {
                    selectedInstructor = instructors.get(0);
                } else {
                    System.out.println("\n" + ConsoleColors.CYAN + "Select an instructor for " + selectedCourse.getCourseCode() + ":" + ConsoleColors.RESET);
                    for (int i = 0; i < instructors.size(); i++) {
                        System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + instructors.get(i) + ConsoleColors.RESET);
                    }
                    System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
                    System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
                    int instructorChoice = getUserChoice();

                    if (instructorChoice == 0) {
                        continue; // Go back to course selection
                    }
                    if (instructorChoice > 0 && instructorChoice <= instructors.size()) {
                        selectedInstructor = instructors.get(instructorChoice - 1);
                    } else {
                        System.out.println(ConsoleColors.RED + "Invalid instructor choice." + ConsoleColors.RESET);
                        continue;
                    }
                }
            }

            final String finalSelectedInstructor = selectedInstructor;

            // Filter class attendance records for the selected course and semester, where
            // present is true AND studied is true
            List<ClassAttendance> studiedClassSessions = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getSemester() == currentSemester && ca.getCourseCode().equals(courseCode)
                            && ca.isPresent() && ca.isStudied() && (finalSelectedInstructor == null || finalSelectedInstructor.equals(ca.getTeacherName())))
                    .sorted(Comparator.comparing(ClassAttendance::getDate))
                    .collect(Collectors.toList());

            if (studiedClassSessions.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No class sessions marked as 'DONE STUDY' found for "
                        + courseCode + (selectedInstructor != null ? " with instructor " + selectedInstructor : "") + " in " + currentSemester.getDisplayValue() + "." + ConsoleColors.RESET);
                Thread.sleep(2000);
                continue;
            }

            while (true) {
                Utils.clearConsole();
                Utils.printHeader("Marked Sessions for " + courseCode + (selectedInstructor != null ? " (" + selectedInstructor + ")" : "") + " - " + currentSemester.getDisplayValue());
                System.out.println();

                                System.out.println(ConsoleColors.BLUE_BOLD
                                        + String.format("%-3s | %-16s | %-5s | %-8s | %-6s | %-20s | %-20s",
                                                "No.", "Date", "Time", "Sd. stts", "Sdd. T", "Sdd. On", "Topic")
                                        + ConsoleColors.RESET);
                System.out.println(ConsoleColors.BLUE_BOLD
                        + "------------------------------------------------------------------------------------------------------" 
                        + ConsoleColors.RESET);

                for (int i = 0; i < studiedClassSessions.size(); i++) {
                    ClassAttendance session = studiedClassSessions.get(i);
                    String studiedStatus = session.isStudied() ? ConsoleColors.GREEN + "DONE"
                            : ConsoleColors.RED + "PENDING";
                    String studiedDateStr = session.getStudiedDate() != null
                            ? session.getStudiedDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
                            : "N/A";
                    String studiedTimeStr = session.getStudiedTime() != null
                            ? session.getStudiedTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                            : "N/A";
                    // Print No.
                    System.out.print(ConsoleColors.ROSE);
                    printPadded(String.valueOf(i + 1), 3);
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print Date
                    printPadded(Utils.formatDateAndDayWithColors(session.getDate(), ConsoleColors.CYAN_BRIGHT, ConsoleColors.LIGHT_GRAY), 16);
                    System.out.print(" | ");

                    // Print Time
                    System.out.print(ConsoleColors.YELLOW);
                    printPadded(session.getTime().format(DateTimeFormatter.ofPattern("HH:mm")), 5);
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" |   ");

                    // Print Study Stt
                    printPadded(studiedStatus + ConsoleColors.RESET, 6); // Adjusted width
                    System.out.print(" | ");

                    // Print St. Time
                    System.out.print(ConsoleColors.GOLD);
                    printPadded(studiedTimeStr, 6); // Adjusted width
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print St. Date
                    printPadded((session.getStudiedDate() != null ? Utils.formatDateAndDayWithColors(session.getStudiedDate(), ConsoleColors.GREEN_BOLD_BRIGHT, ConsoleColors.MAGENTA_BRIGHT) : "N/A"), 20);
                    System.out.print(" | ");

                    // Print Topic
                    System.out.print(ConsoleColors.CYAN);
                    printPadded(session.getTopic() != null ? session.getTopic() : "---", 20);
                    System.out.println(ConsoleColors.RESET); // Newline at the end
                }
                System.out.println(ConsoleColors.BLUE_BOLD
                        + "-----------------------------------------------------------------------" // 79 hyphens
                        + ConsoleColors.RESET); // This separator was previously 76. Make it 79.
                System.out.println(ConsoleColors.RED + "  0. Back to Course Selection" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW
                        + "Enter serial number to remove 'Done Study' mark, or 0 to go back: " + ConsoleColors.RESET);

                int sessionChoice = getUserChoice();
                if (sessionChoice == 0) {
                    break; // Back to course selection
                }

                if (sessionChoice < 1 || sessionChoice > studiedClassSessions.size()) {
                    System.out.println(ConsoleColors.RED + "Invalid session number." + ConsoleColors.RESET);
                    Thread.sleep(1500);
                    continue;
                }

                ClassAttendance sessionToUpdate = studiedClassSessions.get(sessionChoice - 1);
                sessionToUpdate.setStudied(false);
                sessionToUpdate.setStudiedDate(null);
                sessionToUpdate.setStudiedTime(null);
                dataManager.saveData(dataStorage);
                logAction("Removed 'DONE' mark for " + sessionToUpdate.getCourseCode() + " on " + sessionToUpdate.getDate());
                System.out.println(ConsoleColors.GREEN + "Done Study mark removed successfully." + ConsoleColors.RESET);
            }
        }
    } // Closing brace for removeDoneStudyMark()

    private static void markClassSessionsAsDoneStudy() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Mark Class Sessions as Done Study");
            System.out.println();

            Semester currentSemester = dataStorage.getCurrentSemester();
            if (currentSemester == null) {
                System.out.println(ConsoleColors.RED
                        + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
                return;
            }

            // Get courses for the current semester
            List<Course> semesterCourses = dataStorage.getSemesterConfigurations().get(currentSemester)
                    .getCourses();

            if (semesterCourses.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No courses configured for "
                        + currentSemester.getDisplayValue() + "." + ConsoleColors.RESET);
                return;
            }

            // Display courses and let user select one
            System.out.println("\n" + ConsoleColors.CYAN + "Courses in " + currentSemester.getDisplayValue() + ":"
                    + ConsoleColors.RESET);
            for (int i = 0; i < semesterCourses.size(); i++) {
                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + semesterCourses.get(i).getCourseCode()
                        + " - " + semesterCourses.get(i).getCourseTitle() + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);

            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
            int courseChoice = getUserChoice();

            if (courseChoice == 0) {
                return;
            }

            if (courseChoice < 1 || courseChoice > semesterCourses.size()) {
                System.out.println(ConsoleColors.RED + "Invalid course choice." + ConsoleColors.RESET);
                continue;
            }

            Course selectedCourse = semesterCourses.get(courseChoice - 1);
            String courseCode = selectedCourse.getCourseCode();

            // Teacher Selection
            List<String> instructors = selectedCourse.getInstructors();
            String selectedInstructor = null;

            if (instructors != null && !instructors.isEmpty()) {
                if (instructors.size() == 1) {
                    selectedInstructor = instructors.get(0);
                } else {
                    System.out.println("\n" + ConsoleColors.CYAN + "Select an instructor for " + selectedCourse.getCourseCode() + ":" + ConsoleColors.RESET);
                    for (int i = 0; i < instructors.size(); i++) {
                        System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + instructors.get(i) + ConsoleColors.RESET);
                    }
                    System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
                    System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
                    int instructorChoice = getUserChoice();

                    if (instructorChoice == 0) {
                        continue; // Go back to course selection
                    }
                    if (instructorChoice > 0 && instructorChoice <= instructors.size()) {
                        selectedInstructor = instructors.get(instructorChoice - 1);
                    } else {
                        System.out.println(ConsoleColors.RED + "Invalid instructor choice." + ConsoleColors.RESET);
                        continue;
                    }
                }
            }
            
            final String finalSelectedInstructor = selectedInstructor;

            // Filter class attendance records for the selected course and semester, where
            // present is true
            List<ClassAttendance> courseClassSessions = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getSemester() == currentSemester && ca.getCourseCode().equals(courseCode)
                            && ca.isPresent() && (finalSelectedInstructor == null || finalSelectedInstructor.equals(ca.getTeacherName())))
                    .sorted(Comparator.comparing(ClassAttendance::getDate))
                    .collect(Collectors.toList());

            if (courseClassSessions.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No present class sessions found for " + courseCode + 
                        (selectedInstructor != null ? " with instructor " + selectedInstructor : "") + " in "
                        + currentSemester.getDisplayValue() + "." + ConsoleColors.RESET);
                Thread.sleep(2000);
                continue;
            }

            while (true) {
                Utils.clearConsole();
                Utils.printHeader("Class Sessions for " + courseCode + (selectedInstructor != null ? " (" + selectedInstructor + ")" : "") + " - " + currentSemester.getDisplayValue());
                System.out.println();

                // Adjusted column widths for better alignment and included Studied Time
                // Use a larger width for 'Status' to accommodate ANSI escape codes
                System.out.println(ConsoleColors.BLUE_BOLD + String.format("%-3s | %-19s | %-5s | %-8s | %-6s | %-20s | %-20s",
                                "No.", "Date", "Time", "Sd. stts", "Done t", "Done Date", "Topic") + ConsoleColors.RESET);
                System.out.println(ConsoleColors.BLUE_BOLD
                        + "------------------------------------------------------------------------------------------------------" 
                        + ConsoleColors.RESET); // Extended separator

                for (int i = 0; i < courseClassSessions.size(); i++) {
                    ClassAttendance session = courseClassSessions.get(i);
                    // Generate studiedStatus with colors, but ensure it's not part of the length
                    // calculation
                    String statusText = session.isStudied() ? "DONE" : "PENDING";
                    String studiedStatusFormatted = session.isStudied() 
                            ? ConsoleColors.GREEN + statusText + ConsoleColors.RESET
                            : ConsoleColors.RED + statusText + ConsoleColors.RESET;

                    String studiedDateStr = session.getStudiedDate() != null
                            ? session.getStudiedDate().format(DateTimeFormatter.ISO_LOCAL_DATE)
                            : "N/A";
                    String studiedTimeStr = session.getStudiedTime() != null
                            ? session.getStudiedTime().format(DateTimeFormatter.ofPattern("HH:mm"))
                            : "N/A";
                    // Print No.
                    System.out.print(ConsoleColors.ROSE);
                    printPadded(String.valueOf(i + 1), 3);
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print Date
                    printPadded(Utils.formatDateAndDayWithColors(session.getDate(), ConsoleColors.CYAN_BRIGHT, ConsoleColors.LIGHT_GRAY), 19);
                    System.out.print(" | ");

                    // Print Time
                    System.out.print(ConsoleColors.YELLOW);
                    printPadded(session.getTime().format(DateTimeFormatter.ofPattern("HH:mm")), 5);
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print Study Stt
                    printPadded(studiedStatusFormatted, 8); // Adjusted width
                    System.out.print(" | ");

                    // Print St. Time
                    System.out.print(ConsoleColors.GOLD);
                    printPadded(studiedTimeStr, 6); // Adjusted width
                    System.out.print(ConsoleColors.RESET);
                    System.out.print(" | ");

                    // Print St. Date
                    printPadded((session.getStudiedDate() != null ? Utils.formatDateAndDayWithColors(session.getStudiedDate(), ConsoleColors.GREEN_BOLD_BRIGHT, ConsoleColors.MAGENTA_BRIGHT) : "N/A"), 20);
                    System.out.print(" | ");

                    // Print Topic
                    System.out.print(ConsoleColors.CYAN);
                    printPadded(session.getTopic() != null ? session.getTopic() : "---", 20);
                    System.out.println(ConsoleColors.RESET); // Newline at the end
                }
                // Calculate progress
                long totalSessions = courseClassSessions.size();
                long studiedSessions = courseClassSessions.stream().filter(ClassAttendance::isStudied).count();
                double progressPercentage = (totalSessions > 0) ? ((double) studiedSessions / totalSessions) * 100 : 0.0;
                String progressColor = getProgressBarColor(progressPercentage);

                System.out.println(ConsoleColors.BLUE_BOLD
                        + "-------------------------------------------------------------------------" // 79 hyphens
                        + ConsoleColors.RESET); // Extended separator
                System.out.println(String.format(ConsoleColors.CYAN_BOLD + "Study Progress: %s%.2f%%" + ConsoleColors.RESET, progressColor, progressPercentage));
                System.out.println(ConsoleColors.RED + "  0. Back to Course Selection" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW
                        + "Enter serial number to toggle 'Done Study' status, or 0 to go back: " + ConsoleColors.RESET);

                int sessionChoice = getUserChoice();
                if (sessionChoice == 0) {
                    break; // Back to course selection
                }

                if (sessionChoice < 1 || sessionChoice > courseClassSessions.size()) {
                    System.out.println(ConsoleColors.RED + "Invalid session number." + ConsoleColors.RESET);
                    Thread.sleep(1500);
                    continue;
                }

                ClassAttendance sessionToUpdate = courseClassSessions.get(sessionChoice - 1);
                // Modified logic: only mark as done if currently pending
                if (!sessionToUpdate.isStudied()) {
                    sessionToUpdate.setStudied(true);
                    sessionToUpdate.setStudiedDate(LocalDate.now());
                    sessionToUpdate.setStudiedTime(LocalTime.now());
                    dataManager.saveData(dataStorage);
                    logAction("Marked " + sessionToUpdate.getCourseCode() + " session on " + sessionToUpdate.getDate() + " as DONE");
                    System.out.println(ConsoleColors.GREEN + "Session marked as DONE STUDY." + ConsoleColors.RESET);
                } else {
                    System.out.println(
                            ConsoleColors.YELLOW + "Session is already marked as DONE STUDY." + ConsoleColors.RESET);
                }
            }
        }
    }

    private static void viewCourseStudyProgress() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("View Course Study Progress");
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." 
                    + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        List<Course> semesterCourses = dataStorage.getSemesterConfigurations().get(currentSemester)
                .getCourses();

        if (semesterCourses.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses configured for " + currentSemester.getDisplayValue()
                    + "." + ConsoleColors.RESET);
            Thread.sleep(1500);
            return;
        }

        Utils.clearConsole();
        Utils.printHeader("Study Progress for " + currentSemester.getDisplayValue());
        System.out.println();

        System.out.println(ConsoleColors.BLUE_BOLD + String.format("%-12s | %-15s | %-10s | %-10s | %-10s",
                "Course Code", "Total Sessions", "Studied", "Pending", "Progress") + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD + "----------------------------------------------------------------"
                + ConsoleColors.RESET);

        long totalAllSessions = 0;
        long totalAllStudiedSessions = 0;

        for (Course course : semesterCourses) {
            List<ClassAttendance> courseClassSessions = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getSemester() == currentSemester
                            && ca.getCourseCode().equals(course.getCourseCode()) && ca.isPresent())
                    .collect(Collectors.toList());

            long totalSessions = courseClassSessions.size();
            long studiedSessions = courseClassSessions.stream().filter(ClassAttendance::isStudied).count();
            long pendingSessions = totalSessions - studiedSessions;
            double progressPercentage = (totalSessions > 0) ? ((double) studiedSessions / totalSessions) * 100 : 0.0;



            totalAllSessions += totalSessions;
            totalAllStudiedSessions += studiedSessions;

            String progressColor;
            String completedIndicator = "";
            if (totalSessions == 0) {
                progressColor = ConsoleColors.WHITE;
            } else {
                progressColor = getProgressBarColor(progressPercentage);
            }

            System.out.println(progressColor + String.format("%-12s | %-15d | %-10d | %-10d | %-9.2f%% %s",
                    course.getCourseCode(),
                    totalSessions,
                    studiedSessions,
                    pendingSessions,
                    progressPercentage,
                    completedIndicator) + ConsoleColors.RESET);
        }

        System.out.println(ConsoleColors.BLUE_BOLD + "----------------------------------------------------------------"
                + ConsoleColors.RESET);

        double overallProgressPercentage = (totalAllSessions > 0) ? ((double) totalAllStudiedSessions / totalAllSessions) * 100 : 0.0;
        String overallProgressColor = getProgressBarColor(overallProgressPercentage);

        System.out.println(String.format(ConsoleColors.CYAN_BOLD + "Overall Progress: " + overallProgressColor + "%.2f%%" + ConsoleColors.RESET, overallProgressPercentage));
        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void viewPendingStudyDetails() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Pending Study Details");
        Utils.printDigitalClock();
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." 
                    + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        List<Course> semesterCourses = dataStorage.getSemesterConfigurations().get(currentSemester)
                .getCourses();

        if (semesterCourses.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses configured for " + currentSemester.getDisplayValue()
                    + "." + ConsoleColors.RESET);
            Thread.sleep(1500);
            return;
        }

        System.out.println(ConsoleColors.CYAN_BOLD + "Showing Pending Study sessions for " + currentSemester.getDisplayValue() + ConsoleColors.RESET);
        System.out.println();

        int globalSlNo = 1;
        boolean foundAnyPending = false;

        for (Course course : semesterCourses) {
            // Get ALL sessions for this course to find the specific class index
            List<ClassAttendance> allSessions = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getSemester() == currentSemester
                            && ca.getCourseCode().equals(course.getCourseCode()) 
                            && ca.isPresent())
                    .sorted(Comparator.comparing(ClassAttendance::getDate).thenComparing(ClassAttendance::getTime))
                    .collect(Collectors.toList());

            List<ClassAttendance> pendingSessions = allSessions.stream()
                    .filter(ca -> !ca.isStudied())
                    .collect(Collectors.toList());

            if (!pendingSessions.isEmpty()) {
                foundAnyPending = true;
                System.out.println(ConsoleColors.PURPLE_BOLD + ">> Course: " + ConsoleColors.YELLOW_BOLD + course.getCourseCode() + ConsoleColors.RESET);
                
                // Course specific table header
                System.out.print(ConsoleColors.BLUE_BOLD);
                printPadded("No.", 10);
                System.out.print(" | ");
                printPadded("Date", 19);
                System.out.print(" | ");
                printPadded("Time", 6);
                System.out.print(" | ");
                printPadded("Topic", 30);
                System.out.println(ConsoleColors.RESET);
                System.out.println(ConsoleColors.BLUE + "----------------------------------------------------------------------------------" + ConsoleColors.RESET);

                for (ClassAttendance session : pendingSessions) {
                    // Find class index for this course (1-based)
                    int classIndex = allSessions.indexOf(session) + 1;
                    String noColumnText = String.format("%d [%d]", globalSlNo++, classIndex);

                    // No. [Class No.]
                    System.out.print(ConsoleColors.ROSE);
                    printPadded(noColumnText, 10);
                    System.out.print(ConsoleColors.RESET + " | ");

                    // Date
                    printPadded(Utils.formatDateAndDayWithColors(session.getDate(), ConsoleColors.CYAN_BRIGHT, ConsoleColors.LIGHT_GRAY), 19);
                    System.out.print(" | ");

                    // Time
                    System.out.print(ConsoleColors.YELLOW);
                    printPadded(session.getTime().format(DateTimeFormatter.ofPattern("HH:mm")), 6);
                    System.out.print(ConsoleColors.RESET + " | ");

                    // Topic
                    System.out.print(ConsoleColors.CYAN);
                    printPadded(session.getTopic() != null ? session.getTopic() : "---", 30);
                    System.out.println(ConsoleColors.RESET);
                }
                System.out.println(); // Space after each course section
            }
        }

        if (!foundAnyPending) {
            System.out.println(ConsoleColors.GREEN_BOLD + "  🎉 No pending study sessions found! All courses are completed!" + ConsoleColors.RESET);
            System.out.println();
        }

        System.out.println(ConsoleColors.BLUE_BOLD + "----------------------------------------------------------------------" + ConsoleColors.RESET);
        
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static String getProgressBarColor(double percentage) {
        if (percentage == 100.0) {
            return ConsoleColors.GREEN_BOLD_BRIGHT;
        } else if (percentage >= 90.0) {
            return ConsoleColors.GREEN_BRIGHT;
        } else if (percentage >= 80.0) {
            return ConsoleColors.GREEN;
        } else if (percentage >= 70.0) {
            return ConsoleColors.GREEN_BOLD;
        } else if (percentage >= 60.0) {
            return ConsoleColors.YELLOW_BOLD_BRIGHT;
        } else if (percentage >= 50.0) {
            return ConsoleColors.YELLOW_BRIGHT;
        } else if (percentage >= 40.0) {
            return ConsoleColors.YELLOW;
        } else if (percentage >= 30.0) {
            return ConsoleColors.YELLOW_BOLD;
        } else if (percentage >= 20.0) {
            return ConsoleColors.RED_BOLD_BRIGHT;
        } else if (percentage > 0.0) {
            return ConsoleColors.RED_BOLD;
        } else {
            return ConsoleColors.RED;
        }
    }

    private static void universityMainMenu() {
        Utils.printHeader("    Study Menu");
        System.out.println(); // Add space after header
        System.out.println(String.format("%s  %2d. Attendance%s", ConsoleColors.CYAN, 1, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Done Study%s", ConsoleColors.CYAN, 2, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Configure Course Section%s", ConsoleColors.CYAN, 3, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Make Current Semester%s", ConsoleColors.CYAN, 4, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Set Course Details%s", ConsoleColors.CYAN, 5, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. CGPA Calculation%s", ConsoleColors.CYAN, 6, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. My Details%s", ConsoleColors.CYAN, 7, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Registration Fee%s", ConsoleColors.CYAN, 8, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Generate PDF Reports%s", ConsoleColors.CYAN, 9, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Semester Details%s", ConsoleColors.CYAN, 10, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Comprehensive University Data (VIEW ALL)%s", ConsoleColors.CYAN, 11, ConsoleColors.RESET));
        System.out.println(ConsoleColors.RED + "  ↩️ 0. Back to Main Menu" + ConsoleColors.RESET);
        System.out.println(); // Add space before footer
        Utils.printFooter();
        System.out.println(); // Add space after footer
        System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
    }

    private static void printRecentActions() {
        System.out.println(ConsoleColors.YELLOW_BOLD + "--- Recent Actions ---" + ConsoleColors.RESET);
        List<DataStorage.ActionEntry> history = dataStorage.getActionHistory(); // Changed to ActionEntry list
        if (history.isEmpty()) {
            System.out.println(ConsoleColors.CYAN + "No recent actions." + ConsoleColors.RESET);
        } else {
            int startIndex = Math.max(0, history.size() - 5);
            // Iterate backward to show newest of the recent actions first
            for (int i = history.size() - 1; i >= startIndex; i--) {
                DataStorage.ActionEntry entry = history.get(i);
                String timestampFormatted = entry.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                System.out.println(ConsoleColors.CYAN + "  - [" + ConsoleColors.PURPLE_BRIGHT + timestampFormatted + ConsoleColors.CYAN + "] " + ConsoleColors.YELLOW_BOLD + entry.getAction() + ConsoleColors.RESET);
            }
        }
    }

    private static LocalDate getLocalDateInput(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate date = null;
        while (date == null) {
            System.out.print(ConsoleColors.YELLOW + prompt + " (DD-MM-YY): " + ConsoleColors.RESET);
            String input = scanner.nextLine().trim();
            try {
                date = LocalDate.parse(input, formatter);
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println(
                        ConsoleColors.RED + "Invalid date format. Please use DD-MM-YY." + ConsoleColors.RESET);
            }
        }
        return date;
    }

    private static void viewComprehensiveUniversityData() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Comprehensive University Data");
        Utils.printDigitalClock();
        System.out.println();

        // 1. Student Details
        System.out.println(ConsoleColors.YELLOW_BOLD + "--- Student Details ---" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  Name: " + ConsoleColors.WHITE + (dataStorage.getStudentName() != null ? dataStorage.getStudentName() : "N/A") + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  Faculty: " + ConsoleColors.WHITE + (dataStorage.getFaculty() != null ? dataStorage.getFaculty() : "N/A") + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  Student ID: " + ConsoleColors.WHITE + (dataStorage.getStudentId() != null ? dataStorage.getStudentId() : "N/A") + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  Reg Number: " + ConsoleColors.WHITE + (dataStorage.getRegistrationNumber() != null ? dataStorage.getRegistrationNumber() : "N/A") + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  Session: " + ConsoleColors.WHITE + (dataStorage.getSession() != null ? dataStorage.getSession() : "N/A") + ConsoleColors.RESET);
        System.out.println();

        // 2. Semester Configurations
        System.out.println(ConsoleColors.YELLOW_BOLD + "--- Semester & Course Configurations ---" + ConsoleColors.RESET);
        Map<Semester, SemesterConfig> configs = dataStorage.getSemesterConfigurations();
        if (configs.isEmpty()) {
            System.out.println(ConsoleColors.RED + "  No semesters configured." + ConsoleColors.RESET);
        } else {
            configs.keySet().stream().sorted().forEach(semester -> {
                SemesterConfig config = configs.get(semester);
                String currentMark = (semester == dataStorage.getCurrentSemester()) ? ConsoleColors.GREEN_BOLD + " (CURRENT)" : "";
                System.out.println(ConsoleColors.PURPLE_BOLD + "  " + semester.getDisplayValue() + currentMark + ConsoleColors.RESET);
                if (config.isEntered()) {
                    System.out.println(ConsoleColors.CYAN + "    Duration: " + ConsoleColors.WHITE + config.getStartDate() + " to " + config.getEndDate() + ConsoleColors.RESET);
                    List<Course> courses = config.getCourses();
                    for (Course course : courses) {
                        System.out.printf(ConsoleColors.YELLOW + "    - %-10s: %-30s | Credits: %.1f%n", 
                                course.getCourseCode(), course.getCourseTitle(), course.getCreditHours());
                        System.out.printf(ConsoleColors.GRAY + "      Attendance: %d/%d (%.1f%%) | Marks: Mid(%.1f), Final(%.1f), Assig(%.1f), Att(%.1f)%s%n",
                                course.getAttendedClasses(), course.getTotalClasses(), 
                                (course.getTotalClasses() > 0 ? (course.getAttendedClasses() * 100.0 / course.getTotalClasses()) : 0),
                                course.getMidMarks(), course.getFinalMarks(), course.getAssignmentMarks(), course.getAttendanceMarks(), ConsoleColors.RESET);
                    }
                } else {
                    System.out.println(ConsoleColors.GRAY + "    (Configuration incomplete)" + ConsoleColors.RESET);
                }
            });
        }
        System.out.println();

        // 3. CGPA Summary
        System.out.println(ConsoleColors.YELLOW_BOLD + "--- Academic Performance ---" + ConsoleColors.RESET);
        CgpaService localCgpaService = new CgpaService(dataStorage, dataManager, attendanceService);
        OverallCgpaResult overall = localCgpaService.calculateOverallCgpa();
        System.out.printf(ConsoleColors.CYAN + "  Overall CGPA: " + ConsoleColors.GREEN_BOLD + "%.2f" + ConsoleColors.RESET + 
                          ConsoleColors.CYAN + " | Total Credits: " + ConsoleColors.WHITE + "%.1f%n" + ConsoleColors.RESET, 
                          overall.overallCgpa(), overall.totalCreditHours());
        System.out.println();

        // 4. Registration Fees
        System.out.println(ConsoleColors.YELLOW_BOLD + "--- Financial Summary (Registration Fees) ---" + ConsoleColors.RESET);
        RegistrationFeeService localFeeService = new RegistrationFeeService(dataStorage, dataManager);
        double totalFees = localFeeService.calculateOverallTotalFees();
        if (totalFees == 0) {
            System.out.println(ConsoleColors.CYAN + "  No registration fees recorded." + ConsoleColors.RESET);
        } else {
            dataStorage.getRegistrationFees().keySet().stream().sorted().forEach(semester -> {
                double semTotal = localFeeService.calculateTotalFeesForSemester(semester);
                if (semTotal > 0) {
                    System.out.printf(ConsoleColors.CYAN + "  %-20s: " + ConsoleColors.WHITE + "%.2f BDT%n", 
                            semester.getDisplayValue(), semTotal);
                }
            });
            System.out.println(ConsoleColors.PURPLE + "  ----------------------------------------" + ConsoleColors.RESET);
            System.out.printf(ConsoleColors.GREEN_BOLD + "  OVERALL TOTAL FEES  : %.2f BDT%n", totalFees);
        }

        System.out.println("\n" + ConsoleColors.PURPLE + "Press Enter to return to menu..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void setStudentDetails() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Set Student Details");
        Utils.printDigitalClock();
        System.out.println();

        System.out.print(ConsoleColors.YELLOW + "Enter Student Name (current: "
                + (dataStorage.getStudentName() != null ? dataStorage.getStudentName() : "N/A") + "): "
                + ConsoleColors.RESET);
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            dataStorage.setStudentName(name);
        }

        System.out.print(ConsoleColors.YELLOW + "Enter Faculty (current: "
                + (dataStorage.getFaculty() != null ? dataStorage.getFaculty() : "N/A") + "): " + ConsoleColors.RESET);
        String faculty = scanner.nextLine().trim();
        if (!faculty.isEmpty()) {
            dataStorage.setFaculty(faculty);
        }

        System.out.print(ConsoleColors.YELLOW + "Enter Student ID (current: "
                + (dataStorage.getStudentId() != null ? dataStorage.getStudentId() : "N/A") + "): "
                + ConsoleColors.RESET);
        String studentId = scanner.nextLine().trim();
        if (!studentId.isEmpty()) {
            dataStorage.setStudentId(studentId);
        }

        System.out.print(ConsoleColors.YELLOW + "Enter Registration No. (current: "
                + (dataStorage.getRegistrationNumber() != null ? dataStorage.getRegistrationNumber() : "N/A") + "): "
                + ConsoleColors.RESET);
        String regNo = scanner.nextLine().trim();
        if (!regNo.isEmpty()) {
            dataStorage.setRegistrationNumber(regNo);
        }

        System.out.print(ConsoleColors.YELLOW + "Enter Session (current: "
                + (dataStorage.getSession() != null ? dataStorage.getSession() : "N/A") + "): " + ConsoleColors.RESET);
        String session = scanner.nextLine().trim();
        if (!session.isEmpty()) {
            dataStorage.setSession(session);
        }

        logAction("Updated student details."); // Moved logAction before saveData
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Student details updated successfully!" + ConsoleColors.RESET);
    }

    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println(ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
            scanner.next(); // Consume the invalid input
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    private static void manageDailyRoutine() throws InterruptedException {

        logNavigation("Daily Routine Management", "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Daily Routine Management");
            Utils.printDigitalClock(); // Add digital clock
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  ➕ 1. Add New Task" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  ⏳ 2. View Pending Tasks" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  🗓️ 3. View Tasks for Today" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  🔄 4. Update Task Status" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📋 5. View All Tasks" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  🗑️ 6. Delete Task" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📄 7. Generate Task Reports (PDF)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  ↩️ 0. Back to Main Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:

                    logNavigation("Add New Task", "FORWARD");
                    addNewTask();
                    logNavigation("Add New Task", "BACKWARD");
                    break;
                case 2: // New case for View Pending Tasks

                    logNavigation("View Pending Tasks", "FORWARD");
                    viewPendingTasks();
                    logNavigation("View Pending Tasks", "BACKWARD");
                    break;
                case 3: // Shifted from 2

                    logNavigation("View Tasks for Today", "FORWARD");
                    viewTasksForToday();
                    logNavigation("View Tasks for Today", "BACKWARD");
                    break;
                case 4: // Was 5 (Update Task Status)

                    logNavigation("Update Task Status", "FORWARD");
                    updateTaskStatus();
                    logNavigation("Update Task Status", "BACKWARD");
                    break;
                case 5: // Was 4 (View All Tasks)

                    logNavigation("View All Tasks", "FORWARD");
                    viewAllTasks();
                    logNavigation("View All Tasks", "BACKWARD");
                    break;
                case 6: // Shifted from 5

                    logNavigation("Delete Task", "FORWARD");
                    deleteTask();
                    logNavigation("Delete Task", "BACKWARD");
                    break;
                case 7: // Shifted from 6

                    logNavigation("Generate Task Reports (PDF)", "FORWARD");
                    generateTaskPdfReports();
                    logNavigation("Generate Task Reports (PDF)", "BACKWARD");
                    break;
                case 0:

                    logNavigation("Daily Routine Management", "BACKWARD");
                    return;
                default:
                    logAction("Invalid Daily Routine Management choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void manageDailyBuy() throws InterruptedException {

        logNavigation("Cash Management", "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Cash Management");
            Utils.printDigitalClock();
            System.out.println();

            System.out.println(ConsoleColors.CYAN + "  ➕ 1. Add New Buy Record" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📝 2. Edit Buy Record" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  🗑️ 3. Remove Buy Record" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  💰 4. View Pending Expense" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  💰 5. Expense Management" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  🏦 6. Money Manager" + ConsoleColors.RESET); // NEW
            System.out.println(ConsoleColors.CYAN + "  🗓️ 7. View Today's Buy Records" + ConsoleColors.RESET); // Shifted
            System.out.println(ConsoleColors.CYAN + "  🗓️ 8. View All Buy Records" + ConsoleColors.RESET); // Shifted
            System.out.println(ConsoleColors.CYAN + "  📊 9. View Total Expenditure" + ConsoleColors.RESET); // Shifted
            System.out.println(ConsoleColors.CYAN + "  📄 10. Generate Daily Buy Report (PDF)" + ConsoleColors.RESET); // Shifted
            System.out.println(ConsoleColors.RED + "  ↩️ 0. Back to Main Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:

                    logNavigation("Add New Buy Record", "FORWARD");
                    addDailyBuyRecord();
                    logNavigation("Add New Buy Record", "BACKWARD");
                    break;
                case 2:

                    logNavigation("Edit Buy Record", "FORWARD");
                    editDailyBuyRecord();
                    logNavigation("Edit Buy Record", "BACKWARD");
                    break;
                case 3:

                    logNavigation("Remove Buy Record", "FORWARD");
                    removeDailyBuyRecord();
                    logNavigation("Remove Buy Record", "BACKWARD");
                    break;
                case 4:
                    logNavigation("View Pending Expense", "FORWARD");
                    expenseService.viewPendingBuys();
                    logNavigation("View Pending Expense", "BACKWARD");
                    break;
                case 5:

                    logNavigation("Expense Management", "FORWARD");
                    expenseService.manageExpenses();
                    logNavigation("Expense Management", "BACKWARD");
                    break;
                case 6: // NEW - Money Manager

                    logNavigation("Money Manager", "FORWARD");
                    fundService.manageFunds();
                    logNavigation("Money Manager", "BACKWARD");
                    break;
                case 7: // Shifted from 6

                    logNavigation("View Today's Buy Records", "FORWARD");
                    viewTodayDailyBuy(); 
                    logNavigation("View Today's Buy Records", "BACKWARD");
                    break;
                case 8: // Shifted from 7

                    logNavigation("View All Buy Records", "FORWARD");
                    viewAllDailyBuyRecords();
                    logNavigation("View All Buy Records", "BACKWARD");
                    break;
                case 9: // Shifted from 8

                    logNavigation("View Total Expenditure", "FORWARD");
                    viewTotalDailyExpenditure();
                    logNavigation("View Total Expenditure", "BACKWARD");
                    break;
                case 10: // Shifted from 9

                    logNavigation("Generate Daily Buy Report (PDF)", "FORWARD");
                    generateDailyBuyPdfReport();
                    logNavigation("Generate Daily Buy Report (PDF)", "BACKWARD");
                    break;
                case 0:

                    logNavigation("Daily Buy Management", "BACKWARD");
                    return;
                default:
                    logAction("Invalid Daily Buy Management choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void printDailyBuyDetails(DailyBuy buy, int index) {
        System.out.println(String.format("%s  %d. %s",
                ConsoleColors.WHITE_BOLD, (index + 1), buy.getItemName()));
        System.out.println(String.format("     %sPrice: %.2f BDT",
                ConsoleColors.YELLOW, buy.getPrice()));
        System.out.println(String.format("     %sDate: %s at %s",
                ConsoleColors.BLUE, Utils.formatDateWithDay(buy.getDate()), Utils.formatTime(buy.getTime())));
        System.out.println("     " + ConsoleColors.GRAY + "----------------------------------------" + ConsoleColors.RESET);
    }

    private static void viewTodayDailyBuy() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Today's Buy Records");
        Utils.printDigitalClock();
        System.out.println();

        LocalDate today = LocalDate.now();
        List<DailyBuy> todayBuys = dataStorage.getDailyBuys().stream()
                .filter(buy -> buy.getDate().equals(today))
                .collect(Collectors.toList());

        if (todayBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No buy records for today (" + today.format(DateTimeFormatter.ISO_LOCAL_DATE) + ")." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.CYAN_BOLD + "Buy Records for " + today.format(DateTimeFormatter.ISO_LOCAL_DATE) + ":" + ConsoleColors.RESET);
            double totalToday = 0;
            for (int i = 0; i < todayBuys.size(); i++) {
                DailyBuy buy = todayBuys.get(i);
                System.out.println(String.format("%s  %d. %s (%.2f BDT)",
                        ConsoleColors.WHITE_BOLD, (i + 1), buy.getItemName(), buy.getPrice()));
                totalToday += buy.getPrice();
            }
            System.out.println(ConsoleColors.GREEN_BOLD + "\nTotal for Today: " + String.format("%.2f", totalToday) + " BDT" + ConsoleColors.RESET);
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void addDailyBuyRecord() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Add New Buy Record");
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. Add Purchase for Today" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. Add Purchase for Another Day" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    addPurchaseForDate(LocalDate.now());
                    return; // Return to the daily buy menu
                case 2:
                    LocalDate date = selectDate();
                    if (date != null) {
                        addPurchaseForDate(date);
                    }
                    return; // Return to the daily buy menu
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
            }
        }
    }

    private static void addPurchaseForDate(LocalDate date) throws InterruptedException {
        List<DailyBuy> existingBuys = dailyBuyService.getDailyBuysForDate(date);

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Add New Buy Record for " + Utils.formatDateWithDay(date));
            Utils.printDigitalClock();
            System.out.println();

            double todaysTotal = 0;
            if (!existingBuys.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "--- Items Added Today ---" + ConsoleColors.RESET);
                for (int i = 0; i < existingBuys.size(); i++) {
                    // Use the helper for consistent display
                    printDailyBuyDetails(existingBuys.get(i), i);
                    todaysTotal += existingBuys.get(i).getPrice();
                }
                System.out.println(
                        ConsoleColors.GREEN_BOLD + "----------------------------------------" + ConsoleColors.RESET);
            }

            if (todaysTotal > 0) {
                System.out.println(ConsoleColors.GREEN_BOLD + "Total for " + Utils.formatDateWithDay(date) + ": "
                        + String.format("%.2f", todaysTotal) + " BDT" + ConsoleColors.RESET);
                System.out.println();
            }

            System.out.print(ConsoleColors.YELLOW + "Enter Item Name (or 'done' to finish): " + ConsoleColors.RESET);
            String itemName = scanner.nextLine().trim();

            if (itemName.equalsIgnoreCase("done")) {
                break;
            }
            if (itemName.isEmpty()) {
                System.out.println(ConsoleColors.RED + "Item Name cannot be empty." + ConsoleColors.RESET);
                continue;
            }

            String lowerCaseItemName = itemName.toLowerCase();
            boolean isPossibleRegistrationFee = lowerCaseItemName.contains("fee") ||
                    lowerCaseItemName.contains("enrollment") ||
                    lowerCaseItemName.contains("tuition") ||
                    lowerCaseItemName.contains("semester") ||
                    lowerCaseItemName.contains("reg");

            if (isPossibleRegistrationFee) {
                System.out
                        .println(ConsoleColors.YELLOW + "Warning: '" + itemName + "' sounds like a registration fee. " +
                                "Are you sure you want to add this as a Daily Buy? (yes/no)" + ConsoleColors.RESET);
                String confirmation = scanner.nextLine().trim();
                if (!(confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))) {
                    System.out.println(ConsoleColors.YELLOW + "Item not added. Please enter a different item."
                            + ConsoleColors.RESET);
                    continue; 
                }
            }

            double price = -1;
            while (price < 0) {
                try {
                    System.out.print(ConsoleColors.YELLOW + "Enter Price: " + ConsoleColors.RESET);
                    String priceStr = scanner.nextLine().trim();
                    price = Double.parseDouble(priceStr);

                    if (price < 0) {
                        System.out.println(ConsoleColors.RED + "Price cannot be negative." + ConsoleColors.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ConsoleColors.RED + "Invalid input. Please enter a valid number for price."
                            + ConsoleColors.RESET);
                    price = -1; 
                }
            }

            LocalTime time = LocalTime.now();
            dailyBuyService.addDailyBuy(itemName, price, date, time);
            existingBuys.add(new DailyBuy(itemName, price, date, time));
            logAction("Added daily buy: " + itemName + " (Price: " + price + " BDT) for " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(
                ConsoleColors.PURPLE + "\nPress Enter to return to the Daily Buy Menu..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void editDailyBuyRecord() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Edit Buy Record");
        Utils.printDigitalClock();
        System.out.println();

        List<DailyBuy> allBuys = dailyBuyService.getAllDailyBuys().stream()
                .sorted(Comparator.comparing(DailyBuy::getDate).thenComparing(DailyBuy::getTime))
                .collect(Collectors.toList());
        if (allBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No buy records to edit." + ConsoleColors.RESET);
            return;
        }

        System.out.println(ConsoleColors.YELLOW + "Select a record to edit:" + ConsoleColors.RESET);
        for (int i = 0; i < allBuys.size(); i++) {
            printDailyBuyDetails(allBuys.get(i), i);
        }
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter choice (0 to cancel): " + ConsoleColors.RESET);
        int choice = getUserChoice();
        System.out.println();

        if (choice == 0)
            return;

        if (choice > 0 && choice <= allBuys.size()) {
            DailyBuy buyToEdit = allBuys.get(choice - 1);

            System.out.print(ConsoleColors.YELLOW + "Enter New Item Name (current: " + buyToEdit.getItemName() + "): "
                    + ConsoleColors.RESET);
            String newItemName = scanner.nextLine().trim();
            if (newItemName.isEmpty()) {
                newItemName = buyToEdit.getItemName();
            }

            double newPrice = -1;
            while (newPrice < 0) {
                try {
                    System.out.print(ConsoleColors.YELLOW + "Enter New Price (current: " + buyToEdit.getPrice() + "): "
                            + ConsoleColors.RESET);
                    String priceStr = scanner.nextLine().trim();
                    if (priceStr.isEmpty()) {
                        newPrice = buyToEdit.getPrice();
                        break;
                    }
                    newPrice = Double.parseDouble(priceStr);
                    if (newPrice < 0) {
                        System.out.println(ConsoleColors.RED + "Price cannot be negative." + ConsoleColors.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ConsoleColors.RED + "Invalid input. Please enter a valid number for price."
                            + ConsoleColors.RESET);
                }
            }

            LocalDate newDate = selectDate();
            if (newDate == null)
                newDate = buyToEdit.getDate();

            LocalTime newTime = getUserTime("buy");
            if (newTime == null)
                newTime = buyToEdit.getTime();

            dailyBuyService.editDailyBuy(buyToEdit, newItemName, newPrice, newDate, newTime);
            logAction("Edited daily buy: '" + buyToEdit.getItemName() + "' (Price: " + buyToEdit.getPrice() + " BDT) to '" + newItemName + "' (Price: " + newPrice + " BDT)");
        } else {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }

    }

    private static void removeDailyBuyRecord() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Remove Buy Record");
        Utils.printDigitalClock();
        System.out.println();

        List<DailyBuy> allBuys = dailyBuyService.getAllDailyBuys().stream()
                .sorted(Comparator.comparing(DailyBuy::getDate).thenComparing(DailyBuy::getTime))
                .collect(Collectors.toList());
        if (allBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No buy records to remove." + ConsoleColors.RESET);
            return;
        }

        System.out.println(ConsoleColors.YELLOW + "Select a record to remove (0 to cancel):" + ConsoleColors.RESET);
        for (int i = 0; i < allBuys.size(); i++) {
            printDailyBuyDetails(allBuys.get(i), i);
        }
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter choice (0 to cancel): " + ConsoleColors.RESET);
        int choice = getUserChoice();
        System.out.println();

        if (choice == 0)
            return;

        if (choice > 0 && choice <= allBuys.size()) {
            DailyBuy buyToRemove = allBuys.get(choice - 1);
            System.out.print(ConsoleColors.RED + "Are you sure you want to remove '" + buyToRemove.getItemName() + "'? (y/n): " + ConsoleColors.RESET);
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println(ConsoleColors.YELLOW + "Removal cancelled." + ConsoleColors.RESET);
                Thread.sleep(1000);
                return;
            }
            dailyBuyService.removeDailyBuy(buyToRemove);
            logAction("Removed daily buy: '" + buyToRemove.getItemName() + "' (Price: " + buyToRemove.getPrice() + " BDT) from " + buyToRemove.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }
    }

    private static void viewAllDailyBuyRecords() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("All Daily Buy Records");
        Utils.printDigitalClock();
        System.out.println();

        List<DailyBuy> allBuys = dailyBuyService.getAllDailyBuys();
        if (allBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No daily buy records found." + ConsoleColors.RESET);
        } else {
            // Find the earliest date from the buy records
            LocalDate firstDate = allBuys.stream()
                    .map(DailyBuy::getDate)
                    .min(LocalDate::compareTo)
                    .orElse(LocalDate.now());

            LocalDate currentDate = LocalDate.now();
            double overallTotal = 0.0;

            // Iterate from the first date to the current date
            for (LocalDate date = firstDate; !date.isAfter(currentDate); date = date.plusDays(1)) {
                LocalDate finalDate = date; // Variable to be used in lambda
                List<DailyBuy> buysForDate = allBuys.stream()
                        .filter(buy -> buy.getDate().equals(finalDate))
                        .collect(Collectors.toList());

                System.out.println("\n" + ConsoleColors.YELLOW_BOLD + "=== Records for " + Utils.formatDateWithDay(date) + " ===" + ConsoleColors.RESET);

                if (buysForDate.isEmpty()) {
                    System.out.println(ConsoleColors.CYAN + "     No records for this day." + ConsoleColors.RESET);
                } else {
                    double dailyTotal = 0.0;
                    for (int i = 0; i < buysForDate.size(); i++) {
                        printDailyBuyDetails(buysForDate.get(i), i);
                        dailyTotal += buysForDate.get(i).getPrice();
                    }
                    System.out.println(ConsoleColors.YELLOW_BOLD
                            + String.format("     %-20s Total - %.2f BDT", " ", dailyTotal) + ConsoleColors.RESET);
                    overallTotal += dailyTotal;
                }
                 System.out.println(ConsoleColors.GREEN_BOLD + "Running Total Expenditure: "
                    + String.format("%.2f", overallTotal) + " BDT" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GRAY + "----------------------------------------" + ConsoleColors.RESET); // Separator after each date group
            }
            System.out.println(ConsoleColors.GREEN_BOLD + "\nFinal Overall Total Expenditure: "
                    + String.format("%.2f", overallTotal) + " BDT" + ConsoleColors.RESET);
        }
        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void viewTotalDailyExpenditure() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Total Daily Expenditure");
        Utils.printDigitalClock();
        System.out.println();

        List<DailyBuy> allBuys = dailyBuyService.getAllDailyBuys();
        if (allBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No daily buy records found to calculate expenditure."
                    + ConsoleColors.RESET);
        } else {
            double total = dailyBuyService.calculateTotalExpenditure(allBuys);
            System.out.println(ConsoleColors.GREEN_BOLD + "Total Expenditure: " + String.format("%.2f", total) + " BDT" 
                    + ConsoleColors.RESET);
        }
        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void generateTaskPdfReports() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Generate Task Reports (PDF)");
            Utils.printDigitalClock();
            System.out.println();

            System.out.println(ConsoleColors.YELLOW + "Select Task Report Type:" + ConsoleColors.RESET);
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. Today's Tasks Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. All Tasks Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. Pending Tasks Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. Completed Tasks Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to Daily Routine Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            String dest = "Task_Report.pdf"; // Default destination
            List<Task> tasksToReport = null;

            try {
                switch (choice) {
                    case 1: // Today's Tasks Report
                        dest = "Today_Tasks_Report.pdf";
                        tasksToReport = taskService.getTasksForDate(LocalDate.now()); // Get today's tasks
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.TASK_LIST_ALL, null, tasksToReport); // Use TASK_LIST_ALL type but pass
                                                                                // filtered list
                        break;
                    case 2: // All Tasks Report
                        dest = "All_Tasks_Report.pdf";
                        tasksToReport = dataStorage.getTasks();
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.TASK_LIST_ALL, null, tasksToReport);
                        break;
                    case 3: // Pending Tasks Report
                        dest = "Pending_Tasks_Report.pdf";
                        tasksToReport = dataStorage.getTasks().stream().filter(t -> t.getStatus() == TaskStatus.PENDING || t.getStatus() == TaskStatus.INCOMPLETE)
                                .collect(Collectors.toList());
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.TASK_LIST_PENDING, null, tasksToReport);
                        break;
                    case 4: // Completed Tasks Report
                        dest = "Completed_Tasks_Report.pdf";
                        tasksToReport = dataStorage.getTasks().stream()
                                .filter(t -> t.getStatus() == TaskStatus.COMPLETED).collect(Collectors.toList());
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.TASK_LIST_COMPLETED, null, tasksToReport);
                        break;
                    case 0:
                        return;
                    default:
                        System.out
                                .println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                        Thread.sleep(1000);
                        continue; // Continue the while loop for valid input
                }
                System.out.println(
                        ConsoleColors.GREEN + "PDF report generated successfully: " + dest + ConsoleColors.RESET);
                openPdf(dest);
            } catch (Exception e) {
                System.out.println(
                        ConsoleColors.RED + "Error generating PDF report: " + e.getMessage() + ConsoleColors.RESET);
                e.printStackTrace();
            }
            Utils.printFooter();
            System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
            scanner.nextLine();
        }
    }

    private static void generateDailyBuyPdfReport() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Generate Daily Buy Report PDF");
        Utils.printDigitalClock();
        System.out.println();

        try {
            String dest = "Daily_Buy_Report.pdf";
            List<DailyBuy> allBuys = dailyBuyService.getAllDailyBuys();
            // The PdfGenerator will fetch data from DataStorage directly.
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    SIGNATURE_IMAGE_PATH, ReportType.DAILY_BUY, null, null);
            System.out.println(
                    ConsoleColors.GREEN + "Daily Buy Report PDF generated successfully: " + dest + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error generating Daily Buy Report PDF: " + e.getMessage() 
                    + ConsoleColors.RESET);
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    private static void viewDataJson() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("data.json Content");
        Utils.printDigitalClock();
        System.out.println();

        String dataFilePath = ConfigManager.getDataFilePath(); // Assuming data.json is next to config.properties
        File file = new File(dataFilePath);
        
        if (!file.exists()) {
            System.out.println(ConsoleColors.RED + "Error: data.json file not found at " + dataFilePath + ConsoleColors.RESET);
            System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
            scanner.nextLine();
            return;
        }

        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(dataFilePath)));
            JsonElement jsonElement = JsonParser.parseString(jsonContent);

            int[] lineNumber = {1}; // Use an array to pass by reference for modification within recursion
            printJsonElement(jsonElement, 0, lineNumber, true); // Initial call, root is considered last element if not inside another array

        } catch (IOException e) {
            System.out.println(ConsoleColors.RED + "Error reading data.json: " + e.getMessage() + ConsoleColors.RESET);
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            System.out.println(ConsoleColors.RED + "Error parsing data.json: Invalid JSON syntax. " + e.getMessage() + ConsoleColors.RESET);
            e.printStackTrace();
        }

        Thread.sleep(0); // 300ms sleep as requested
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    // Helper method to print a line with a line number
    private static void printNumberedLine(int[] lineNumber, String indent, String content) throws InterruptedException {
        String lineColor = ConsoleColors.LINE_NUMBER_COLORS[colorIndex];
        System.out.printf("%s%4d %s%s%s%n", lineColor, lineNumber[0]++, indent, content, ConsoleColors.RESET);
         Thread.sleep(30);
        colorIndex = (colorIndex + 1) % ConsoleColors.LINE_NUMBER_COLORS.length;
    }

    // Helper method to print an indented line without a line number
    private static void printLine(String indent, String content) {
        System.out.printf("%s%s%n", indent, content);
    }

    private static void printJsonElement(JsonElement jsonElement, int indentLevel, int[] lineNumber, boolean isLastElement) throws InterruptedException {
        String indent = "    ".repeat(indentLevel);
        String childIndent = indent + "    ";

        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            
            // Print opening brace
            printNumberedLine(lineNumber, indent, ConsoleColors.JSON_BRACKET_BRACE + "{" + ConsoleColors.RESET);

            // Iterate over entries
            int entryCount = 0;
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                entryCount++;
                boolean currentEntryIsLast = (entryCount == jsonObject.size());

                String key = entry.getKey();
                String keyString = ConsoleColors.JSON_QUOTE_COLOR + "\"" + ConsoleColors.RESET // Opening quote for key
                                   + getKeyColor(key) + key + ConsoleColors.RESET               // Key with specific color
                                   + ConsoleColors.JSON_QUOTE_COLOR + "\"" + ConsoleColors.RESET // Closing quote for key
                                   + ConsoleColors.JSON_COLON_COLOR + ": " + ConsoleColors.RESET; // Colon
                
                // Recursively print the value
                if (entry.getValue().isJsonPrimitive() || entry.getValue().isJsonNull()) {
                    System.out.print(ConsoleColors.JSON_LINE_NUMBER + String.format("%4d ", lineNumber[0]++) + childIndent + keyString); // Print line number and key
                    printPrimitiveOrNull(entry.getValue(), currentEntryIsLast); // Print value, optional comma, newline
                } else {
                    // Value is a complex object or array. Its own line number will be handled by recursive call.
                    // Print key on its own line (with line number).
                    printNumberedLine(lineNumber, childIndent, keyString);
                    printJsonElement(entry.getValue(), indentLevel + 1, lineNumber, currentEntryIsLast);
                }
            }
            
            String closingBraceContent = ConsoleColors.JSON_BRACKET_BRACE + "}" + ConsoleColors.RESET;
            if (!isLastElement) {
                closingBraceContent += ConsoleColors.JSON_COMMA_COLOR + "," + ConsoleColors.RESET;
            }
            printNumberedLine(lineNumber, indent, closingBraceContent);

        } else if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            
            // Print opening bracket
            printNumberedLine(lineNumber, indent, ConsoleColors.JSON_BRACKET_BRACE + "[" + ConsoleColors.RESET);

            // Iterate over elements
            int elementCount = 0;
            for (JsonElement element : jsonArray) {
                elementCount++;
                boolean currentElementIsLast = (elementCount == jsonArray.size());

                // Recursively print the element
                if (element.isJsonPrimitive() || element.isJsonNull()) {
                    System.out.print(ConsoleColors.JSON_LINE_NUMBER + String.format("%4d ", lineNumber[0]++) + childIndent); // Print line number and indent
                    printPrimitiveOrNull(element, currentElementIsLast); // Print value, optional comma, newline
                } else {
                    // Value is a complex object or array. Its own line number will be handled by recursive call.
                    // Just pass control to the recursive call.
                    printJsonElement(element, indentLevel + 1, lineNumber, currentElementIsLast);
                }
            }
            
            String closingBracketContent = ConsoleColors.JSON_BRACKET_BRACE + "]" + ConsoleColors.RESET;
            if (!isLastElement) {
                closingBracketContent += ConsoleColors.JSON_COMMA_COLOR + "," + ConsoleColors.RESET;
            }
            printNumberedLine(lineNumber, indent, closingBracketContent);

        } else { // This case handles root primitives or nulls
            printPrimitiveOrNull(jsonElement, isLastElement);
        }
    }

    // Helper method to print primitive or null values
    private static void printPrimitiveOrNull(JsonElement jsonElement, boolean isLastElement) throws InterruptedException {
        if (jsonElement.isJsonPrimitive()) {
            JsonPrimitive primitive = jsonElement.getAsJsonPrimitive();
            if (primitive.isBoolean()) {
                System.out.print(ConsoleColors.JSON_COMMON_VALUE_COLOR + primitive.getAsBoolean() + ConsoleColors.RESET);
            } else if (primitive.isNumber()) {
                System.out.print(ConsoleColors.JSON_COMMON_VALUE_COLOR + primitive.getAsNumber() + ConsoleColors.RESET);
            } else if (primitive.isString()) {
                System.out.print(ConsoleColors.JSON_QUOTE_COLOR + "\"" + ConsoleColors.RESET +
                               ConsoleColors.JSON_COMMON_VALUE_COLOR + primitive.getAsString() + ConsoleColors.RESET +
                               ConsoleColors.JSON_QUOTE_COLOR + "\"" + ConsoleColors.RESET);
            } else {
                System.out.print(ConsoleColors.JSON_COMMON_VALUE_COLOR + primitive.toString() + ConsoleColors.RESET); // Fallback
            }
        } else if (jsonElement.isJsonNull()) {
            System.out.print(ConsoleColors.JSON_COMMON_VALUE_COLOR + "null" + ConsoleColors.RESET);
        }

        // Add comma if it's not the last element, then always add a newline
        if (!isLastElement) {
            System.out.print(ConsoleColors.JSON_COMMA_COLOR + "," + ConsoleColors.RESET);
        }
        System.out.println(); // Always add a newline after a primitive/null value (and its optional comma)
        // Thread.sleep(0);
    }

    // Helper method to get color based on key name
    private static String getKeyColor(String key) {
        switch (key) {
            case "itemName":
                return ConsoleColors.KEY_ITEM_NAME;
            case "price":
                return ConsoleColors.KEY_PRICE;
            case "date":
                return ConsoleColors.KEY_DATE;
            case "time":
                return ConsoleColors.KEY_TIME;
            case "status":
                return ConsoleColors.KEY_STATUS;
            case "courseCode":
                return ConsoleColors.KEY_COURSE_CODE;
            case "courseName":
                return ConsoleColors.KEY_COURSE_NAME;
            case "teacherName":
                return ConsoleColors.KEY_TEACHER_NAME;
            case "present":
                return ConsoleColors.KEY_PRESENT;
            case "isExtraClass":
                return ConsoleColors.KEY_IS_EXTRA_CLASS;
            case "semester":
                return ConsoleColors.KEY_SEMESTER;
            case "studied":
                return ConsoleColors.KEY_STUDIED;
            case "timestamp":
                return ConsoleColors.KEY_TIMESTAMP;
            case "menuName":
                return ConsoleColors.KEY_MENU_NAME;
            case "actionType":
                return ConsoleColors.KEY_ACTION_TYPE;
            case "depth":
                return ConsoleColors.KEY_DEPTH;
            case "taskName":
                return ConsoleColors.KEY_TASK_NAME;
            case "category":
                return ConsoleColors.KEY_CATEGORY;
            case "startTime":
                return ConsoleColors.KEY_START_TIME;
            case "endTime":
                return ConsoleColors.KEY_END_TIME;
            case "studentName":
                return ConsoleColors.KEY_STUDENT_NAME;
            case "faculty":
                return ConsoleColors.KEY_FACULTY;
            case "studentId":
                return ConsoleColors.KEY_STUDENT_ID;
            case "registrationNumber":
                return ConsoleColors.KEY_REGISTRATION_NUMBER;
            case "session":
                return ConsoleColors.KEY_SESSION;
            case "creditHours":
                return ConsoleColors.KEY_CREDIT_HOURS;
            case "midMarks":
                return ConsoleColors.KEY_MID_MARKS;
            case "finalMarks":
                return ConsoleColors.KEY_FINAL_MARKS;
            case "assignmentMarks":
                return ConsoleColors.KEY_ASSIGNMENT_MARKS;
            case "attendanceMarks":
                return ConsoleColors.KEY_ATTENDANCE_MARKS;
            case "totalMarks":
                return ConsoleColors.KEY_TOTAL_MARKS;
            case "letterGrade":
                return ConsoleColors.KEY_LETTER_GRADE;
            case "gradePoint":
                return ConsoleColors.KEY_GRADE_POINT;
            case "totalClasses":
                return ConsoleColors.KEY_TOTAL_CLASSES;
            case "attendedClasses":
                return ConsoleColors.KEY_ATTENDED_CLASSES;
            case "action":
                return ConsoleColors.KEY_ACTION; // Added new action key
            default:
                return ConsoleColors.DEFAULT_JSON_KEY;
        }
    }

    private static void addNewTask() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Add New Task");
        Utils.printDigitalClock(); // Add digital clock
        System.out.println(); // Add space after digital clock

        TaskCategory category = selectTaskCategory();
        if (category == null)
            return; // User cancelled
        System.out.println(); // Add space after category selection

        System.out.print("Enter task name: ");
        String taskName = scanner.nextLine();
        System.out.println(); // Add space after task name

        LocalTime startTime = getUserTime("start");
        System.out.println(); // Add space after start time
        LocalTime endTime = getUserTime("end");
        System.out.println(); // Add space after end time

        LocalDate date = selectDate();
        System.out.println(); // Add space after date selection

        taskService.addTask(taskName, category, startTime, endTime, date);
        logAction("Added task: " + taskName + " (Category: " + category.name() + (date != null ? ", Date: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE) : "") + ")");
        System.out.println(); // Add space after task added message
    }

    private static LocalTime getUserTime(String timeType) {
        while (true) {
            System.out.print("Enter " + timeType + " time (HH.MM, e.g., 10.21) or type 'no' for no specific time: ");
            String timeStr = scanner.nextLine();
            if (timeStr.equalsIgnoreCase("no")) {
                return null;
            }

            System.out.print("Enter AM/PM (a/p): ");
            String amPmStr = scanner.nextLine().toLowerCase();
            if (amPmStr.isEmpty()) {
                System.out.println(
                        ConsoleColors.RED + "Invalid AM/PM input. Please enter 'a' or 'p'." + ConsoleColors.RESET);
                continue;
            }
            char amPm = amPmStr.charAt(0);

            if (amPm != 'a' && amPm != 'p') {
                System.out.println(
                        ConsoleColors.RED + "Invalid AM/PM input. Please enter 'a' or 'p'." + ConsoleColors.RESET);
                continue;
            }

            try {
                String[] parts = timeStr.split("\\.");
                int hour = Integer.parseInt(parts[0]);
                int minute = Integer.parseInt(parts[1]);

                if (hour < 1 || hour > 12 || minute < 0 || minute > 59) {
                    System.out.println(ConsoleColors.RED
                            + "Invalid time format. Hour must be between 1 and 12, and minute between 0 and 59."
                            + ConsoleColors.RESET);
                    continue;
                }

                if (amPm == 'p' && hour != 12) {
                    hour += 12;
                } else if (amPm == 'a' && hour == 12) {
                    hour = 0;
                }

                return LocalTime.of(hour, minute);
            } catch (Exception e) {
                System.out.println(
                        ConsoleColors.RED + "Invalid time format. Please use HH.MM format." + ConsoleColors.RESET);
            }
        }
    }

    private static TaskCategory selectTaskCategory() {
        while (true) {
            System.out.println(ConsoleColors.YELLOW + "Select Task Category:" + ConsoleColors.RESET);
            System.out.println();
            int i = 1;
            for (TaskCategory cat : TaskCategory.values()) {
                System.out.println(ConsoleColors.CYAN + "  " + (i++) + ". " + cat.name() + ConsoleColors.RESET);
            }
            System.out.println();
            System.out.println(ConsoleColors.RED + "  0. Cancel" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);
            int categoryChoice = getUserChoice();

            if (categoryChoice == 0)
                return null;
            if (categoryChoice > 0 && categoryChoice <= TaskCategory.values().length) {
                return TaskCategory.values()[categoryChoice - 1];
            } else {
                System.out.println(
                        ConsoleColors.RED + "Invalid category choice. Please try again." + ConsoleColors.RESET);
                System.out.println();
            }
        }
    }

    private static LocalDate selectDate() {
        while (true) {
            System.out.print("Is this task for today? (yes/no): ");
            String todayChoice = scanner.nextLine().trim().toLowerCase();
            System.out.println();
            if (todayChoice.equals("yes") || todayChoice.equals("y")) {
                return LocalDate.now();
            } else if (todayChoice.equals("no") || todayChoice.equals("n")) {
                System.out.print("Enter date (DD-MM-YY, e.g., 22-11-25) or 'no' for no date: ");
                String dateStr = scanner.nextLine().trim(); // Trim the input
                if (dateStr.equalsIgnoreCase("no")) {
                    return null;
                }
                System.out.println();
                try {
                    // Pre-process date string to handle single-digit months/days
                    String[] parts = dateStr.split("-");
                    if (parts.length == 3) {
                        String day = String.format("%02d", Integer.parseInt(parts[0]));
                        String month = String.format("%02d", Integer.parseInt(parts[1]));
                        String year = parts[2];
                        dateStr = day + "-" + month + "-" + year;
                    }
                    return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yy"));
                } catch (Exception e) {
                    System.out.println(
                            ConsoleColors.RED + "Invalid date format. Please use DD-MM-YY." + ConsoleColors.RESET);
                    System.out.println();
                }
            } else {
                System.out.println(
                        ConsoleColors.RED + "Invalid input. Please enter 'yes', 'y', 'no', or 'n'." + ConsoleColors.RESET);
                System.out.println();
            }
        }
    }

    private static Semester selectSemesterForAction(String action) {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Select Semester to " + action);
            Utils.printDigitalClock();
            System.out.println();

            System.out.println(ConsoleColors.YELLOW + "Select a semester:" + ConsoleColors.RESET);
            System.out.println();

            Semester[] semesters = Semester.values();
            for (int i = 0; i < semesters.length; i++) {
                Semester sem = semesters[i];
                SemesterConfig config = dataStorage.getSemesterConfigurations().get(sem);
                String color = ConsoleColors.CYAN;
                String status = "";

                if (config != null && config.isEntered()) {
                    status = " (Configured)";
                } else {
                    status = " (Not Configured)";
                }
                System.out
                        .println(color + "  " + (i + 1) + ". " + sem.getDisplayValue() + status + ConsoleColors.RESET);
            }
            System.out.println();
            System.out.println(ConsoleColors.RED + "  0. Cancel" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            System.out.println();

            if (choice == 0)
                return null;

            if (choice > 0 && choice <= semesters.length) {
                return semesters[choice - 1];
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private static void viewTasksForToday() throws InterruptedException {
        taskService.updateOverdueTasks(); // Update overdue tasks before displaying
        Utils.clearConsole();
        Utils.printHeader("Tasks for Today (" + Utils.formatDateWithDay(LocalDate.now()) + ")");
        Utils.printDigitalClock(); // Add digital clock
        System.out.println();
        List<Task> todayTasks = taskService.getTasksForDate(LocalDate.now());

        if (todayTasks.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No tasks scheduled for today." + ConsoleColors.RESET);
            System.out.println();
        } else {
            for (int i = 0; i < todayTasks.size(); i++) {
                printTaskDetails(todayTasks.get(i), i);
            }
            System.out.println();
        }
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine(); // Wait for user to press enter
    }

    private static void viewAllTasks() throws InterruptedException {
        taskService.updateOverdueTasks(); // Update overdue tasks before displaying
        Utils.clearConsole();
        Utils.printHeader("All Tasks");
        Utils.printDigitalClock(); // Add digital clock
        System.out.println();
        List<Task> allTasks = dataStorage.getTasks(); // Get all tasks from DataStorage

        if (allTasks.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No tasks recorded yet." + ConsoleColors.RESET);
            System.out.println();
        } else {
            // Sort tasks by date and then by end time
            allTasks.sort(Comparator.comparing(Task::getDate, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Task::getEndTime,
                    Comparator.nullsLast(Comparator.naturalOrder())));

            for (int i = 0; i < allTasks.size(); i++) {
                printTaskDetails(allTasks.get(i), i);
            }
            System.out.println();
        }
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine(); // Wait for user to press enter
    }

    private static void viewPendingTasks() throws InterruptedException {
        taskService.updateOverdueTasks(); // Update overdue tasks before displaying
        Utils.clearConsole();
        Utils.printHeader("Pending Tasks");
        Utils.printDigitalClock(); // Add digital clock
        System.out.println();
        List<Task> pendingTasks = dataStorage.getTasks().stream()
                                            .filter(t -> t.getStatus() == TaskStatus.PENDING || t.getStatus() == TaskStatus.INCOMPLETE)
                                            .collect(Collectors.toList());

        if (pendingTasks.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No pending tasks found." + ConsoleColors.RESET);
            System.out.println();
        } else {
            // Sort tasks by date and then by end time
            pendingTasks.sort(Comparator.comparing(Task::getDate, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Task::getEndTime,
                    Comparator.nullsLast(Comparator.naturalOrder())));

            for (int i = 0; i < pendingTasks.size(); i++) {
                printTaskDetails(pendingTasks.get(i), i);
            }
            System.out.println();
        }        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine(); // Wait for user to press enter
    }

    private static void printTaskDetails(Task task, int index) {
        String statusColor = getTaskStatusColor(task.getStatus());
        String statusSymbol;
        switch (task.getStatus()) {
            case PENDING:
                statusSymbol = "⏳ PENDING";
                break;
            case COMPLETED:
                statusSymbol = "✅ COMPLETED";
                break;
            case INCOMPLETE:
                statusSymbol = "❌ INCOMPLETE";
                break;
            default:
                statusSymbol = "";
        }

        String formattedDate = task.getDate() != null ? Utils.formatDateWithDay(task.getDate()) : "No date";
        String formattedStartTime = task.getStartTime() != null ? Utils.formatTime(task.getStartTime()) : "N/A";
        String formattedEndTime = task.getEndTime() != null ? Utils.formatTime(task.getEndTime()) : "N/A";

        // Task Header
        System.out.println(ConsoleColors.WHITE_BOLD + "  ═════════════════════════════════════════════════════" + ConsoleColors.RESET);
        System.out.println(String.format("%s  %s%d. %s %s[%s]%s",
                ConsoleColors.WHITE_BOLD, ConsoleColors.YELLOW_BOLD, (index + 1), task.getTaskName(),
                ConsoleColors.CYAN, task.getCategory(), ConsoleColors.RESET));

        // Details
        System.out.println(String.format("%s     📅 Date: %s%s",
                ConsoleColors.BLUE, formattedDate, ConsoleColors.RESET));

        System.out.println(String.format("%s     ⏰ Time: %s%s - %s%s",
                ConsoleColors.PURPLE, formattedStartTime, ConsoleColors.RESET, formattedEndTime, ConsoleColors.RESET));

        System.out.println(String.format("%s     ✨ Status: %s%s",
                statusColor, statusSymbol, ConsoleColors.RESET));

        System.out.println(ConsoleColors.WHITE_BOLD + "  ═════════════════════════════════════════════════════" + ConsoleColors.RESET);
    }

        private static void updateTaskStatus() throws InterruptedException {
            Utils.clearConsole();
            Utils.printHeader("Update Task Status");
            Utils.printDigitalClock(); // Add digital clock
            System.out.println();
            taskService.updateOverdueTasks(); // Ensure statuses are current before showing list
    
            List<Task> allTasks = dataStorage.getTasks(); // Fetch ALL tasks
    
            if (allTasks.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No tasks recorded yet to update." + ConsoleColors.RESET);
                System.out.println();
                Thread.sleep(1500);
                return;
            }
    
            // Sort tasks for consistent display (optional but good practice)
            allTasks.sort(Comparator.comparing(Task::getDate, Comparator.nullsLast(Comparator.naturalOrder())).thenComparing(Task::getEndTime,
                    Comparator.nullsLast(Comparator.naturalOrder())));
    
            System.out.println(ConsoleColors.YELLOW + "Select a task to update:" + ConsoleColors.RESET);
            for (int i = 0; i < allTasks.size(); i++) {
                printTaskDetails(allTasks.get(i), i); // Use the helper for consistent display
            }
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter the number of the task to update: " + ConsoleColors.RESET);
            int taskChoice = getUserChoice();
            System.out.println();
    
            if (taskChoice > 0 && taskChoice <= allTasks.size()) { // Check against allTasks.size()
                Task taskToUpdate = allTasks.get(taskChoice - 1); // Get task from allTasks

                LocalDate today = LocalDate.now();
                boolean isOverdue = taskToUpdate.getDate() != null && taskToUpdate.getDate().isBefore(today);
    
                if (isOverdue) {
                    System.out.println(
                            ConsoleColors.YELLOW + "This task is overdue. You can only toggle between COMPLETED and INCOMPLETE." + ConsoleColors.RESET);
                    if (taskToUpdate.getStatus() == TaskStatus.INCOMPLETE) {
                        taskService.updateTaskStatus(taskToUpdate, TaskStatus.COMPLETED);
                        logAction("Updated overdue task '" + taskToUpdate.getTaskName() + "' to COMPLETED");
                    } else if (taskToUpdate.getStatus() == TaskStatus.COMPLETED) {
                        taskService.updateTaskStatus(taskToUpdate, TaskStatus.INCOMPLETE);
                        logAction("Updated overdue task '" + taskToUpdate.getTaskName() + "' to INCOMPLETE");
                    } else {
                        taskService.updateTaskStatus(taskToUpdate, TaskStatus.INCOMPLETE);
                        logAction("Updated overdue task '" + taskToUpdate.getTaskName() + "' to INCOMPLETE");
                    }
                } else {
                    // This is the original logic for non-overdue tasks.
                    System.out.println(ConsoleColors.YELLOW + "Select new status for '" + taskToUpdate.getTaskName() + "':"
                            + ConsoleColors.RESET);
                    System.out.println();
                    // Display task status options in the requested order
                    TaskStatus[] orderedStatuses = { TaskStatus.COMPLETED, TaskStatus.PENDING, TaskStatus.INCOMPLETE };
                    int i = 1;
                    for (TaskStatus status : orderedStatuses) {
                        System.out.println(ConsoleColors.CYAN + "  " + (i++) + ". " + status.name() + ConsoleColors.RESET);
                    }
                    System.out.println();
                    System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);
                    int statusChoice = getUserChoice();
                    System.out.println();
    
                    if (statusChoice > 0 && statusChoice <= orderedStatuses.length) {
                        TaskStatus newStatus = orderedStatuses[statusChoice - 1];
                        taskService.updateTaskStatus(taskToUpdate, newStatus);
                        logAction("Updated task '" + taskToUpdate.getTaskName() + "' to " + newStatus.name());
                    } else {
                        System.out.println(ConsoleColors.RED + "Invalid status choice." + ConsoleColors.RESET);
                        System.out.println();
                    }
                }
            } else {
                System.out.println(ConsoleColors.RED + "Invalid task choice." + ConsoleColors.RESET);
                System.out.println();
            }
        }
    
        private static void deleteTask() throws InterruptedException {
            Utils.clearConsole();
            Utils.printHeader("Delete Task");
            Utils.printDigitalClock(); // Add digital clock
            System.out.println();
    
            TaskCategory categoryToDeleteFrom = selectTaskCategory(); // Select category first
            if (categoryToDeleteFrom == null) { // User cancelled category selection
                return;
            }
    
            List<Task> tasksInCategory = dataStorage.getTasks().stream()
                    .filter(task -> task.getCategory().equals(categoryToDeleteFrom))
                    .collect(Collectors.toList());
    
            if (tasksInCategory.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No tasks to delete in category '" + categoryToDeleteFrom.name() 
                        + "'." + ConsoleColors.RESET);
                System.out.println();
                Thread.sleep(1500);
                return;
            }
    
            System.out.println(ConsoleColors.YELLOW + "Tasks in category '" + categoryToDeleteFrom.name() + "':"
                    + ConsoleColors.RESET);
            for (int i = 0; i < tasksInCategory.size(); i++) {
                printTaskDetails(tasksInCategory.get(i), i); // Use the helper for consistent display
            }
            System.out.println();
            Utils.printFooter();
            System.out.println();
    
            System.out.print(ConsoleColors.YELLOW
                    + "Enter the number of the task to delete, 'Ad' to delete ALL in this category, or '0' to cancel: "
                    + ConsoleColors.RESET);
            String input = scanner.nextLine().trim();
            System.out.println();
    
            if (input.equalsIgnoreCase("0"))
                return;
    
            if (input.equals("Ad")) { // Case-sensitive check for "Ad"
                                System.out.print(ConsoleColors.RED + "Are you sure you want to delete ALL tasks in category '"
                                        + categoryToDeleteFrom.name() + "'? (Yes/No): " + ConsoleColors.RESET);
                                String confirmation = scanner.nextLine().trim();
                                System.out.println();
                                if (confirmation.equalsIgnoreCase("Yes") || confirmation.equalsIgnoreCase("y")) {
                                    logAction("Deleted ALL tasks in category: " + categoryToDeleteFrom.name());
                    taskService.deleteAllTasks(categoryToDeleteFrom);
                } else {
                    System.out.println(ConsoleColors.YELLOW + "Deletion cancelled." + ConsoleColors.RESET);
                }
            } else {
                try {
                    int taskChoice = Integer.parseInt(input);
                    if (taskChoice > 0 && taskChoice <= tasksInCategory.size()) {
                        Task taskToDelete = tasksInCategory.get(taskChoice - 1);
                        logAction("Deleted task: '" + taskToDelete.getTaskName() + "' from category '" + categoryToDeleteFrom.name() + "'");
                        taskService.deleteTask(taskToDelete);
                    } else {
                        System.out.println(ConsoleColors.RED + "Invalid task choice." + ConsoleColors.RESET);
                        System.out.println();
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ConsoleColors.RED + "Invalid input. Please enter a number, 'Ad', or '0'."
                            + ConsoleColors.RESET);
                    System.out.println();
                }
            }
        }
    
        private static void manageAttendanceOptions() throws InterruptedException {
                                    logNavigation("Attendance Options", "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Attendance Options");
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. Mark Today's Attendance" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. Edit Attendance Info" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. Extra Class" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. Mark Missed Attendance" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  5. Special Attendance" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  6. Attendance Details & Reports" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  7. Remove Attendance Record" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to University Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:

                    logNavigation("Mark Today's Attendance", "FORWARD");
                    markTodayAttendance();
                    logNavigation("Mark Today's Attendance", "BACKWARD");
                    break;
                case 2:
                    logNavigation("Edit Attendance Info Menu", "FORWARD");
                    manageEditAttendanceInfoMenu();
                    logNavigation("Edit Attendance Info Menu", "BACKWARD");
                    break;
                case 3:
                    logNavigation("Extra Class", "FORWARD");
                    handleExtraClass();
                    logNavigation("Extra Class", "BACKWARD");
                    break;
                case 4:

                    logNavigation("Mark Missed Attendance", "FORWARD");
                    markMissedAttendance();
                    logNavigation("Mark Missed Attendance", "BACKWARD");
                    break;
                case 5:

                    logNavigation("Special Attendance", "FORWARD");
                    manageSpecialAttendance();
                    logNavigation("Special Attendance", "BACKWARD");
                    break;
                case 6:

                    logNavigation("Attendance Details & Reports", "FORWARD");
                    viewAttendanceDetailsAndReports(); // New method
                    logNavigation("Attendance Details & Reports", "BACKWARD");
                    break;
                case 7:

                    logNavigation("Remove Attendance Record", "FORWARD");
                    removeAttendanceRecord();
                    logNavigation("Remove Attendance Record", "BACKWARD");
                    break;
                case 0:
                    logAction("Navigated Back: Attendance Options -> University Menu");
                    logNavigation("Attendance Options", "BACKWARD");
                    return;
                default:
                    logAction("Invalid Attendance Options choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void manageStudyMaterials() throws InterruptedException {
        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester set." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Study Materials (Book/Sheet)");

            Course selectedCourse = selectCourseForSemester(currentSemester);
            if (selectedCourse == null) return;

            // Get unique teachers for this course from attendance records
            List<String> teachers = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getCourseCode().equals(selectedCourse.getCourseCode()) && ca.getSemester() == currentSemester)
                    .map(ClassAttendance::getTeacherName)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            if (teachers.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No attendance records found for " + selectedCourse.getCourseCode() + ConsoleColors.RESET);
                Thread.sleep(2000);
                continue;
            }

            while (true) {
                Utils.clearConsole();
                Utils.printHeader("Materials for: " + selectedCourse.getCourseCode());
                System.out.println(ConsoleColors.YELLOW + "Select Teacher:" + ConsoleColors.RESET);
                for (int i = 0; i < teachers.size(); i++) {
                    System.out.println(ConsoleColors.GREEN + (i + 1) + ". " + teachers.get(i) + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + "0. Back to Course Selection" + ConsoleColors.RESET);
                System.out.print(ConsoleColors.YELLOW + "Choice: " + ConsoleColors.RESET);

                int tChoice = getUserChoice();
                if (tChoice == 0) break;
                if (tChoice < 1 || tChoice > teachers.size()) {
                    System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                    Thread.sleep(1000);
                    continue;
                }

                String selectedTeacher = teachers.get(tChoice - 1);
                manageTeacherClassList(selectedCourse, selectedTeacher);
            }
        }
    }

    private static void manageTeacherClassList(Course course, String teacher) throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Classes by " + teacher + " (" + course.getCourseCode() + ")");

            List<ClassAttendance> sessions = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getCourseCode().equals(course.getCourseCode()) && ca.getTeacherName().equals(teacher))
                    .sorted(Comparator.comparing(ClassAttendance::getDate).reversed().thenComparing(ClassAttendance::getTime).reversed())
                    .collect(Collectors.toList());

            System.out.println(ConsoleColors.YELLOW + "Select a class to manage preferred materials:" + ConsoleColors.RESET);
            for (int i = 0; i < sessions.size(); i++) {
                ClassAttendance ca = sessions.get(i);
                String dateStr = ca.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                System.out.println(ConsoleColors.GREEN + (i + 1) + ". " + dateStr + " - " + ca.getTime().format(DateTimeFormatter.ofPattern("hh:mm a")));
                if (!ca.getMaterials().isEmpty()) {
                    System.out.println(ConsoleColors.GRAY + "   - Materials: " + String.join(", ", ca.getMaterials()) + ConsoleColors.RESET);
                }
            }
            System.out.println(ConsoleColors.RED + "0. Back to Teacher Selection" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW + "Select class: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            if (choice == 0) return;
            if (choice < 1 || choice > sessions.size()) {
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                Thread.sleep(1000);
                continue;
            }

            ClassAttendance selectedCa = sessions.get(choice - 1);
            modifySessionMaterials(selectedCa);
        }
    }

    private static void modifySessionMaterials(ClassAttendance ca) throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Manage Materials: " + ca.getCourseCode() + " (" + ca.getDate() + ")");
            System.out.println(ConsoleColors.CYAN + "Preferred by " + ca.getTeacherName() + ":" + ConsoleColors.RESET);
            
            List<String> mats = ca.getMaterials();
            if (mats.isEmpty()) {
                System.out.println(ConsoleColors.GRAY + "  No materials added yet." + ConsoleColors.RESET);
            } else {
                for (int i = 0; i < mats.size(); i++) {
                    System.out.println(ConsoleColors.GREEN + "  " + (i + 1) + ". " + mats.get(i) + ConsoleColors.RESET);
                }
            }
            
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. 📚 Add Book" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. 📄 Add Sheet" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. 📝 Add Other Material" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  4. 🗑️ Delete a Material" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE + "  0. ↩️ Back to Class List" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW + "Choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            if (choice == 0) return;

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    String type = (choice == 1) ? "Book: " : (choice == 2) ? "Sheet: " : "Other: ";
                    System.out.print(ConsoleColors.YELLOW + "Enter Material Name: " + ConsoleColors.RESET);
                    String name = scanner.nextLine().trim();
                    if (!name.isEmpty()) {
                        ca.addMaterial(type + name);
                    }
                    break;
                case 4:
                    if (mats.isEmpty()) {
                        System.out.println(ConsoleColors.RED + "Nothing to delete." + ConsoleColors.RESET);
                        Thread.sleep(1000);
                        continue;
                    }
                    System.out.print(ConsoleColors.YELLOW + "Enter Material Number to delete: " + ConsoleColors.RESET);
                    int dIdx = getUserChoice();
                    if (dIdx > 0 && dIdx <= mats.size()) {
                        String removed = mats.remove(dIdx - 1);
                        System.out.println(ConsoleColors.RED + "Deleted: " + removed + ConsoleColors.RESET);
                    } else {
                        System.out.println(ConsoleColors.RED + "Invalid number." + ConsoleColors.RESET);
                        Thread.sleep(1000);
                        continue;
                    }
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                    Thread.sleep(1000);
                    continue;
            }
            
            logAction("Updated materials for " + ca.getCourseCode() + " session on " + ca.getDate());
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Updated successfully!" + ConsoleColors.RESET);
            Thread.sleep(800);
        }
    }

    private static void handleExtraClass() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Add Extra Class Attendance");
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        Course selectedCourse = selectCourseForSemester(currentSemester);
        if (selectedCourse == null) {
            return;
        }

        String selectedInstructor = selectInstructorForCourse(selectedCourse);
        if (selectedInstructor == null) {
            return;
        }

        LocalDate date = selectDate();
        if (date == null) {
            return;
        }

        LocalTime time = getUserTime("class");

        boolean isPresent = false;
        while (true) {
            System.out.print(ConsoleColors.YELLOW + "Were you present in this class? (yes/no): " + ConsoleColors.RESET);
            String presentChoice = scanner.nextLine().trim().toLowerCase();
            if (presentChoice.equals("yes") || presentChoice.equals("y")) {
                isPresent = true;
                break;
            } else if (presentChoice.equals("no") || presentChoice.equals("n")) {
                isPresent = false;
                break;
            } else {
                System.out.println(ConsoleColors.RED + "Invalid input. Please enter 'yes' or 'no'." + ConsoleColors.RESET);
            }
        }

        int signatures = 0;
        if (isPresent) {
            System.out.print(ConsoleColors.YELLOW + "How many attendance signatures were collected? " + ConsoleColors.RESET);
            signatures = getUserChoice();
        }

        boolean attendanceTaken = (signatures > 0);

        System.out.print(ConsoleColors.YELLOW + "Enter Topic for this extra class (or press Enter to skip): " + ConsoleColors.RESET);
        String topic = scanner.nextLine().trim();

        attendanceService.markAttendance(
                selectedCourse.getCourseCode(),
                selectedCourse.getCourseTitle(),
                selectedInstructor,
                topic,
                date,
                time,
                isPresent, // present
                true, // isExtraClass
                attendanceTaken, // Dynamically set based on signatures
                signatures,
                currentSemester
        );        logAction("Added extra class for " + selectedCourse.getCourseCode() + " by " + selectedInstructor + " on " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));

        System.out.println(ConsoleColors.GREEN + "Extra class attendance added successfully." + ConsoleColors.RESET);
        Thread.sleep(2000);
    }

    private static Course selectCourseForSemester(Semester semester) {
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null || semesterConfig.getCourses().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses configured for the current semester: " + semester.getDisplayValue() + ConsoleColors.RESET);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // ignore
            }
            return null;
        }

        List<Course> semesterCourses = semesterConfig.getCourses();
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Select a Course for " + semester.getDisplayValue());
            for (int i = 0; i < semesterCourses.size(); i++) {
                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + semesterCourses.get(i).getCourseCode() + " - " + semesterCourses.get(i).getCourseTitle() + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Select a course: " + ConsoleColors.RESET);

            int choice = getUserChoice();

            if (choice == 0) {
                return null;
            } else if (choice > 0 && choice <= semesterCourses.size()) {
                return semesterCourses.get(choice - 1);
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }
    }

    private static String selectInstructorForCourse(Course course) {
        List<String> instructors = course.getInstructors();
        if (instructors == null || instructors.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No instructors configured for this course." + ConsoleColors.RESET);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // ignore
            }
            return null;
        }

        if (instructors.size() == 1) {
            return instructors.get(0);
        }

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Select an Instructor for " + course.getCourseCode());
            for (int i = 0; i < instructors.size(); i++) {
                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + instructors.get(i) + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int instructorChoice = getUserChoice();
            if (instructorChoice == 0) {
                return null;
            }
            if (instructorChoice > 0 && instructorChoice <= instructors.size()) {
                return instructors.get(instructorChoice - 1);
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // ignore
                }
            }
        }
    }


    private static void manageSpecialAttendance() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Special Attendance");
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." 
                    + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(currentSemester);
        if (semesterConfig == null || semesterConfig.getCourses().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses configured for the current semester: "
                    + currentSemester.getDisplayValue() + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        List<Course> semesterCourses = semesterConfig.getCourses();
        List<Course> coursesToConsider = new ArrayList<>();

        // Step 1: Select Course(s)
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Special Attendance for " + currentSemester.getDisplayValue());
            System.out.println(ConsoleColors.CYAN + "  1. All Courses" + ConsoleColors.RESET);
            for (int i = 0; i < semesterCourses.size(); i++) {
                System.out.println(ConsoleColors.CYAN + "  " + (i + 2) + ". " + semesterCourses.get(i).getCourseCode()
                        + " - " + semesterCourses.get(i).getCourseTitle() + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Select course(s): " + ConsoleColors.RESET);

            int choice = getUserChoice();

            if (choice == 0) {
                return;
            } else if (choice == 1) {
                coursesToConsider.addAll(semesterCourses);
                break;
            } else if (choice > 1 && choice <= semesterCourses.size() + 1) {
                coursesToConsider.add(semesterCourses.get(choice - 2));
                break;
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                Thread.sleep(1000);
            }
        }

        // Step 2: Get Dates
        System.out.print(ConsoleColors.YELLOW + "Enter dates (DD-MM-YY, comma-separated): " + ConsoleColors.RESET);
        String datesString = scanner.nextLine().trim();
        String[] dateTokens = datesString.split(",");
        List<LocalDate> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        boolean allDatesValid = true;
        for (String token : dateTokens) {
            try {
                dates.add(LocalDate.parse(token.trim(), formatter));
            } catch (java.time.format.DateTimeParseException e) {
                System.out.println(ConsoleColors.RED + "Invalid date format for: " + token + ". Please use DD-MM-YY."
                        + ConsoleColors.RESET);
                allDatesValid = false;
            }
        }

        if (!allDatesValid || dates.isEmpty()) {
            System.out.println(ConsoleColors.RED + "No valid dates provided. Aborting." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        System.out.print(ConsoleColors.YELLOW + "Is this an extra class? (yes/no): " + ConsoleColors.RESET);
        String extraClassChoice = scanner.nextLine().trim().toLowerCase();
        boolean isExtraClass = extraClassChoice.equals("yes") || extraClassChoice.equals("y");
        System.out.println();

        System.out.print(ConsoleColors.YELLOW + "Enter Topic for this special attendance (or press Enter to skip): " + ConsoleColors.RESET);
        String topic = scanner.nextLine().trim();
        System.out.println();

        // Step 3: Iterate through courses and select instructor
        for (Course course : coursesToConsider) {
            String selectedInstructor = null;
            List<String> instructors = course.getInstructors();

            if (instructors.size() > 1) {
                while (true) {
                    Utils.clearConsole();
                    Utils.printHeader("Select Instructor for " + course.getCourseCode());
                    for (int i = 0; i < instructors.size(); i++) {
                        System.out.println(
                                ConsoleColors.CYAN + "  " + (i + 1) + ". " + instructors.get(i) + ConsoleColors.RESET);
                    }
                    System.out.println(ConsoleColors.RED + "  0. Skip this course" + ConsoleColors.RESET);
                    System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

                    int instructorChoice = getUserChoice();
                    if (instructorChoice == 0) {
                        selectedInstructor = null;
                        break;
                    }
                    if (instructorChoice > 0 && instructorChoice <= instructors.size()) {
                        selectedInstructor = instructors.get(instructorChoice - 1);
                        break;
                    } else {
                        System.out
                                .println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                        Thread.sleep(1000);
                    }
                }
            } else if (instructors.size() == 1) {
                selectedInstructor = instructors.get(0);
            }

            if (selectedInstructor != null) {
                for (LocalDate date : dates) {
                    attendanceService.markAttendance(course.getCourseCode(), course.getCourseTitle(),
                            selectedInstructor, topic, date, LocalTime.now(), true, isExtraClass, currentSemester);
                    logAction("Marked special attendance for " + course.getCourseCode() + " by " + selectedInstructor + " on " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
                    updateCourseAttendanceData(course.getCourseCode(), currentSemester);
                }
            } else {
                System.out.println(ConsoleColors.YELLOW + "Skipping course " + course.getCourseCode() 
                        + " as no instructor was selected." + ConsoleColors.RESET);
                Thread.sleep(1500);
            }
        }

        System.out.println(ConsoleColors.GREEN + "Special attendance marking process completed." + ConsoleColors.RESET);
        Thread.sleep(2000);
    }

    private static void viewAttendanceDetailsAndReports() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Attendance Details & Reports");
            Utils.printDigitalClock();
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. All Attendance Records" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. Current Semester Attendance Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. Calendar Attendance" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. Online Class Details" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  5. Generate Attendance Calendar PDF (for a Semester)"
                    + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to Attendance Options" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    viewAllAttendanceRecords();
                    break;
                case 2:
                    viewCurrentSemesterAttendanceRecords();
                    break;
                case 3:
                    viewCourseWiseAttendanceCalendar();
                    break;
                case 4:
                    viewOnlineClassDetails();
                    break;
                case 5:
                    generateAttendanceCalendarPdfForSemesterApp(); // New helper
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void viewCurrentSemesterAttendanceRecords() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Current Semester Attendance Records");
        Utils.printDigitalClock();
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        List<ClassAttendance> allAttendance = dataStorage.getClassAttendances();
        List<ClassAttendance> semesterAttendance = allAttendance.stream()
                .filter(att -> currentSemester.equals(att.getSemester()))
                .collect(Collectors.toList());

        if (semesterAttendance.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No attendance records found for " + currentSemester.getDisplayValue() + ConsoleColors.RESET);
        } else {
            Map<String, Map<String, Map<LocalDate, List<ClassAttendance>>>> coursesInSemester = semesterAttendance.stream()
                .collect(Collectors.groupingBy(
                    ClassAttendance::getCourseCode,
                    Collectors.groupingBy(
                        ClassAttendance::getTeacherName,
                        Collectors.groupingBy(ClassAttendance::getDate)
                    )
                ));

            int semesterTotalPresent = 0;
            int semesterTotalClasses = 0;
            long semesterTotalEcTaken = 0;
            long semesterTotalSignatures = 0;
            long semesterRegularClasses = 0;

            List<String> sortedCourseCodes = coursesInSemester.keySet().stream().sorted().collect(Collectors.toList());

            for (String courseCode : sortedCourseCodes) {
                Map<String, Map<LocalDate, List<ClassAttendance>>> instructorsInCourse = coursesInSemester.get(courseCode);

                System.out.println(ConsoleColors.GREEN_BOLD + "\nCourse: " + courseCode + ConsoleColors.RESET);

                List<String> sortedInstructorNames = instructorsInCourse.keySet().stream().sorted().collect(Collectors.toList());
                for (String instructorName : sortedInstructorNames) {
                    Map<LocalDate, List<ClassAttendance>> attendanceByDate = instructorsInCourse.get(instructorName);

                    long instructorTotalPresent = attendanceByDate.values().stream().flatMap(List::stream).filter(ClassAttendance::isPresent).count();
                    long instructorTotalClasses = attendanceByDate.values().stream().flatMap(List::stream).count();
                    long instructorTotalEcTaken = attendanceByDate.values().stream().flatMap(List::stream).filter(ClassAttendance::isExtraClass).count();
                    long instructorRegularClasses = instructorTotalClasses - instructorTotalEcTaken;
                    long instructorTotalSignatures = attendanceByDate.values().stream().flatMap(List::stream).mapToLong(ClassAttendance::getAttendanceSignatures).sum();

                    semesterTotalPresent += instructorTotalPresent;
                    semesterTotalClasses += instructorTotalClasses;
                    semesterRegularClasses += instructorRegularClasses;
                    semesterTotalEcTaken += instructorTotalEcTaken;
                    semesterTotalSignatures += instructorTotalSignatures;

                    System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW_BOLD + " Instructor: " + instructorName + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD + String.format("%-3s | %-12s | %-12s | %-12s | %-7s | %-20s", "No.", "Date", "Day", "Time", "Status", "Topic") + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------" + ConsoleColors.RESET);

                    List<LocalDate> sortedDates = attendanceByDate.keySet().stream().sorted().collect(Collectors.toList());
                    int recordNo = 1;
                    for (LocalDate date : sortedDates) {
                        List<ClassAttendance> recordsForDay = attendanceByDate.get(date);
                        if (recordsForDay.isEmpty()) continue;

                        long presentCount = recordsForDay.stream().filter(ClassAttendance::isPresent).count();
                        long extraCount = recordsForDay.stream().filter(ClassAttendance::isExtraClass).count();
                        long totalSignaturesForDay = recordsForDay.stream().mapToLong(ClassAttendance::getAttendanceSignatures).sum();
                        boolean allOnline = recordsForDay.stream().allMatch(ClassAttendance::isOnline);
                        String topics = recordsForDay.stream()
                                .map(ClassAttendance::getTopic)
                                .filter(t -> t != null && !t.isEmpty())
                                .distinct()
                                .collect(Collectors.joining(", "));

                        LocalTime time = recordsForDay.get(0).getTime();

                        String status;
                        if (presentCount == recordsForDay.size()) {
                            String presentStr = (totalSignaturesForDay > 1) ? totalSignaturesForDay + "P" : "P";
                            status = ConsoleColors.GREEN_BOLD_BRIGHT + presentStr + ConsoleColors.RESET;
                        } else if (presentCount == 0) {
                            long absentCount = recordsForDay.size();
                            String absentStr = (absentCount > 1) ? absentCount + "A" : "A";
                            status = ConsoleColors.RED_BOLD_BRIGHT + absentStr + ConsoleColors.RESET;
                        } else {
                            status = ConsoleColors.YELLOW_BOLD_BRIGHT + "Mix" + ConsoleColors.RESET;
                        }

                        String onlineIndicator = allOnline ? ConsoleColors.GREEN + "●" + ConsoleColors.RESET : "";
                        String extraClassColor = (extraCount > 1) ? ConsoleColors.ORANGE : ConsoleColors.COLOR_CYCLE_17;
                        String summary = extraCount > 0 ? " " + extraClassColor + "[" + extraCount + "]" + ConsoleColors.RESET : "";
                        String finalStatus = status + onlineIndicator + summary;

                        System.out.print(ConsoleColors.ROSE);
                        printPadded(String.valueOf(recordNo++), 3);
                        System.out.print(ConsoleColors.RESET + " | ");
                        printPadded(ConsoleColors.BLUE + Utils.formatDate(date), 12);
                        System.out.print(ConsoleColors.RESET + " | ");
                        printPadded(ConsoleColors.PURPLE + Utils.getDayOfWeek(date), 12);
                        System.out.print(ConsoleColors.RESET + " | ");
                        printPadded(ConsoleColors.YELLOW_BRIGHT + Utils.formatTime(time), 12);
                        System.out.print(ConsoleColors.RESET + " | ");
                        printPadded(finalStatus, 7);
                        System.out.print(ConsoleColors.RESET + " | ");

                        String topicText = topics.isEmpty() ? "---" : topics;
                        int topicWidth = 20;
                        boolean firstChunk = true;

                        while (!topicText.isEmpty()) {
                            if (!firstChunk) {
                                System.out.print(String.format("%-3s | %-12s | %-12s | %-12s | %-7s | ", "", "", "", "", ""));
                            }
                            String chunk;
                            if (topicText.length() <= topicWidth) {
                                chunk = topicText;
                                topicText = "";
                            } else {
                                chunk = topicText.substring(0, topicWidth);
                                topicText = topicText.substring(topicWidth);
                            }
                            System.out.println(ConsoleColors.CYAN + chunk + ConsoleColors.RESET);
                            firstChunk = false;
                        }
                    }
                    System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------" + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.YELLOW + " Instructor Summary: Present: " + instructorTotalPresent + " | Signatures: " + instructorTotalSignatures + 
                            " | Reg: " + instructorRegularClasses + " | Extra: " + instructorTotalEcTaken + ConsoleColors.RESET);
                }
            }
            System.out.println(ConsoleColors.YELLOW_UNDERLINED + "\nSemester Summary (" + currentSemester.getDisplayValue() + "):" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "  Present: " + semesterTotalPresent + " | Signatures: " + semesterTotalSignatures + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW + "  Class: " + semesterRegularClasses + " | Extra Class: " + semesterTotalEcTaken + " | Total: " + semesterTotalClasses + ConsoleColors.RESET);
        }

        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }
    private static void viewOnlineClassDetails() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Online Class Details");
        Utils.printDigitalClock();
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        List<ClassAttendance> onlineAttendances = dataStorage.getClassAttendances().stream()
                .filter(ca -> ca.getSemester() == currentSemester && ca.isOnline())
                .sorted(Comparator.comparing(ClassAttendance::getDate).thenComparing(ClassAttendance::getTime))
                .collect(Collectors.toList());

        if (onlineAttendances.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No online class records found for the current semester." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.BLUE_BOLD + String.format("%-3s | %-12s | %-12s | %-12s | %-7s | %-20s", "No.", "Date", "Day", "Course", "Status", "Topic") + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "------------------------------------------------------------------------------------------------" + ConsoleColors.RESET);

            for (int i = 0; i < onlineAttendances.size(); i++) {
                ClassAttendance ca = onlineAttendances.get(i);
                String status = ca.isPresent() ? ConsoleColors.GREEN_BOLD + "P" + ConsoleColors.RESET : ConsoleColors.RED_BOLD + "A" + ConsoleColors.RESET;
                
                System.out.print(ConsoleColors.ROSE);
                printPadded(String.valueOf(i + 1), 3);
                System.out.print(ConsoleColors.RESET + " | ");
                
                printPadded(ConsoleColors.BLUE + ca.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yy")), 12);
                System.out.print(ConsoleColors.RESET + " | ");
                
                printPadded(ConsoleColors.PURPLE + Utils.getDayOfWeek(ca.getDate()), 12);
                System.out.print(ConsoleColors.RESET + " | ");
                
                printPadded(ConsoleColors.CYAN + ca.getCourseCode(), 12);
                System.out.print(ConsoleColors.RESET + " | ");
                
                printPadded(status, 7);
                System.out.print(ConsoleColors.RESET + " | ");
                
                String topic = (ca.getTopic() != null && !ca.getTopic().isEmpty()) ? ca.getTopic() : "---";
                System.out.println(ConsoleColors.WHITE + topic + ConsoleColors.RESET);
            }
        }

        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void generateAttendanceCalendarPdfForSemesterApp() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Generate Attendance Calendar PDF");
        Utils.printDigitalClock();
        System.out.println();

        Semester selectedSemester = selectSemesterForAction("generate attendance calendar for");
        if (selectedSemester == null) {
            return;
        }

        try {
            String dest = "Attendance_Calendar_" + selectedSemester.name() + ".pdf";
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    SIGNATURE_IMAGE_PATH, ReportType.ATTENDANCE_CALENDAR_SEMESTER, selectedSemester, null);
            System.out.println(ConsoleColors.GREEN + "Attendance Calendar PDF for " + selectedSemester.getDisplayValue()
                    + " generated successfully: " + dest + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error generating Attendance Calendar PDF: " + e.getMessage() 
                    + ConsoleColors.RESET);
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    private static void manageConfigureCourseSection() throws InterruptedException {
        logNavigation("Configure Course Section", "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Configure Course Section");
            Utils.printDigitalClock();
            System.out.println();
            System.out.println(ConsoleColors.YELLOW + "Select a semester to configure:" + ConsoleColors.RESET);
            System.out.println();

            Semester[] semesters = Semester.values();
            for (int i = 0; i < semesters.length; i++) {
                Semester sem = semesters[i];
                SemesterConfig config = dataStorage.getSemesterConfigurations().get(sem);
                String color = (config != null && config.isEntered()) ? ConsoleColors.GREEN_BOLD : ConsoleColors.CYAN;
                String status = (config != null && config.isEntered()) ? " (Configured)" : " (Not Configured)";
                System.out
                        .println(color + "  " + (i + 1) + ". " + sem.getDisplayValue() + status + ConsoleColors.RESET);
            }
            System.out.println();
            System.out.println(ConsoleColors.RED + "  0. Back to University Menu" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            System.out.println();

            if (choice == 0) {
                logNavigation("Configure Course Section", "BACKWARD");
                return;
            }

            if (choice > 0 && choice <= semesters.length) {
                Semester selectedSemester = semesters[choice - 1];
                logNavigation("Configure Courses for " + selectedSemester.getDisplayValue(), "FORWARD");
                configureCoursesForSemester(selectedSemester);
                logNavigation("Configure Courses for " + selectedSemester.getDisplayValue(), "BACKWARD");
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                Thread.sleep(1500);
            }
        }
    }

    private static void configureCoursesForSemester(Semester selectedSemester) throws InterruptedException {
        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().getOrDefault(selectedSemester,
                new SemesterConfig());
        List<Course> courses = new ArrayList<>(semesterConfig.getCourses()); // Use a mutable copy

        // --- Prompt for Semester Start and End Dates ---
        System.out.println("\n" + ConsoleColors.YELLOW + "--- Configure Semester Dates ---" + ConsoleColors.RESET);

        // Display current dates if available
        if (semesterConfig.getStartDate() != null) {
            System.out.println(ConsoleColors.CYAN + "Current Start Date: "
                    + semesterConfig.getStartDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"))
                    + ConsoleColors.RESET);
        }
        LocalDate newStartDate = getLocalDateInput("Enter Semester Start Date");
        semesterConfig.setStartDate(newStartDate);

        if (semesterConfig.getEndDate() != null) {
            System.out.println(ConsoleColors.CYAN + "Current End Date: "
                    + semesterConfig.getEndDate().format(DateTimeFormatter.ofPattern("dd-MM-yy"))
                    + ConsoleColors.RESET);
        }
        LocalDate newEndDate = getLocalDateInput("Enter Semester End Date");
        semesterConfig.setEndDate(newEndDate);

        // --- End of Date Configuration ---

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Configuring " + selectedSemester.getDisplayValue());
            if (!courses.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "Current courses:" + ConsoleColors.RESET);
                for (int i = 0; i < courses.size(); i++) {
                    System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + courses.get(i).getCourseCode()
                            + " - " + courses.get(i).getCourseTitle() + ConsoleColors.RESET);
                }
                System.out.println();
            }

            System.out.print(ConsoleColors.YELLOW + "Enter Course Code (or 'ok' to finish): " + ConsoleColors.RESET);
            String courseCode = scanner.nextLine().trim();
            if (courseCode.equalsIgnoreCase("ok")) {
                break;
            }

            System.out.print(ConsoleColors.YELLOW + "Enter Course Title: " + ConsoleColors.RESET);
            String courseTitle = scanner.nextLine().trim();

            List<String> instructors = new ArrayList<>();
            while (true) {
                System.out.print(
                        ConsoleColors.YELLOW + "Enter Teacher Name (or 'done' to finish): " + ConsoleColors.RESET);
                String teacherName = scanner.nextLine().trim();
                if (teacherName.equalsIgnoreCase("done")) {
                    if (instructors.isEmpty()) {
                        System.out.println(
                                ConsoleColors.RED + "At least one teacher must be added." + ConsoleColors.RESET);
                        continue;
                    }
                    break;
                }
                instructors.add(teacherName);
            }

            // For now, credit hours and marks are not asked here as per the new flow.
            // They will be set in the "Set Course Details" section.
            // We'll use default values.
            courses.add(new Course(courseCode, courseTitle, instructors, 0, 0, 0, 0));
            System.out.println(ConsoleColors.GREEN + "Course '" + courseCode + "' added." + ConsoleColors.RESET);
            Thread.sleep(1000);
        }

        if (!courses.isEmpty()) {
            semesterConfig.setCourses(courses);
            semesterConfig.setEntered(true);
            dataStorage.getSemesterConfigurations().put(selectedSemester, semesterConfig);
            logAction("Configured semester " + selectedSemester.getDisplayValue() + " with start date " + semesterConfig.getStartDate() + " and end date " + semesterConfig.getEndDate()); // Moved logAction before saveData
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Configuration saved for " + selectedSemester.getDisplayValue() 
                    + "." + ConsoleColors.RESET);
        } else {
            System.out.println(
                    ConsoleColors.YELLOW + "No courses were added. Configuration not saved." + ConsoleColors.RESET);
        }
        Thread.sleep(2000);
    }

    private static void setCourseDetails() throws InterruptedException {
        Semester selectedSemester = dataStorage.getCurrentSemester();
        if (selectedSemester == null) {
            selectedSemester = selectSemesterForAction("set details for");
        }

        if (selectedSemester == null) {
            return; // Go back if user cancels
        }

        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(selectedSemester);
        if (semesterConfig == null || !semesterConfig.isEntered()) {
            System.out.println(ConsoleColors.RED + "This semester (" + selectedSemester.getDisplayValue() + ") has not been configured. Please configure it first."
                    + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        logNavigation("Manage Course Details for " + selectedSemester.getDisplayValue(), "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Manage Course Details for " + selectedSemester.getDisplayValue());
            System.out.println(ConsoleColors.YELLOW_BOLD + "Courses in this semester:" + ConsoleColors.RESET);
            List<Course> courses = semesterConfig.getCourses();
            if (courses.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No courses found." + ConsoleColors.RESET);
            } else {
                // Use a set to display each unique course code only once
                courses.stream()
                        .map(Course::getCourseCode) // Get all course codes
                        .distinct() // Get unique course codes
                        .sorted() // Sort the unique course codes
                        .forEach(courseCode -> {
                            // Find the corresponding Course object for the courseCode
                            Course course = courses.stream().filter(c -> c.getCourseCode().equals(courseCode)) 
                                    .findFirst().orElse(null);
                            if (course != null) { // Should not be null if courseCode came from original list
                                System.out.println(ConsoleColors.CYAN + "  - " + course.getCourseCode() + " - " 
                                        + course.getCourseTitle() + ConsoleColors.RESET);
                            }
                        });
            }
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "1. Add Course" + ConsoleColors.RESET);
            System.out.println(
                    ConsoleColors.CYAN + "2. Enter Particular Course to Edit Marks/Credits" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "3. Delete Course" + ConsoleColors.RESET); // New option
            System.out.println(ConsoleColors.RED + "0. Back to University Menu" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    logNavigation("Add Course", "FORWARD");
                    addCourseToSemester(selectedSemester, semesterConfig);
                    logNavigation("Add Course", "BACKWARD");
                    break;
                case 2:
                    logNavigation("Edit Marks/Credits", "FORWARD");
                    enterParticularCourse(semesterConfig, selectedSemester); // Pass semester
                    logNavigation("Edit Marks/Credits", "BACKWARD");
                    break;
                case 3:
                    logNavigation("Delete Course", "FORWARD");
                    deleteCourseFromSemester(semesterConfig, selectedSemester);
                    logNavigation("Delete Course", "BACKWARD");
                    break;
                case 0:
                    logNavigation("Manage Course Details for " + selectedSemester.getDisplayValue(), "BACKWARD");
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void deleteCourseFromSemester(SemesterConfig semesterConfig, Semester semester)
            throws InterruptedException {
        List<Course> courses = semesterConfig.getCourses();
        if (courses.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses to delete in this semester." + ConsoleColors.RESET);
            Thread.sleep(1500);
            return;
        }

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Select a Course to Delete");

            List<String> uniqueCourseCodes = courses.stream()
                    .map(Course::getCourseCode)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            for (int i = 0; i < uniqueCourseCodes.size(); i++) {
                String courseCode = uniqueCourseCodes.get(i);
                String courseTitle = courses.stream()
                        .filter(c -> c.getCourseCode().equals(courseCode))
                        .findFirst().get().getCourseTitle();
                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + courseCode + " - "
                        + courseTitle + ConsoleColors.RESET);
            }
            System.out.println();
            System.out.println(ConsoleColors.RED + "0. Back" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            if (choice == 0) {
                return;
            }
            if (choice > 0 && choice <= uniqueCourseCodes.size()) {
                String chosenCourseCode = uniqueCourseCodes.get(choice - 1);

                // Find and remove all instances of the course (in case of duplicates, though
                // ideally course codes are unique)
                boolean removed = courses.removeIf(c -> c.getCourseCode().equals(chosenCourseCode));

                if (removed) {
                    dataManager.saveData(dataStorage);
                    logAction("Deleted course: " + chosenCourseCode + " from " + semester.getDisplayValue());
                    System.out.println(ConsoleColors.GREEN + "Course '" + chosenCourseCode + "' deleted successfully."
                            + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + "Failed to delete course '" + chosenCourseCode + "'."
                            + ConsoleColors.RESET);
                }
                Thread.sleep(1500);
                return; // Exit after deletion
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                Thread.sleep(1000);
            }
        }
    }

    private static void addCourseToSemester(Semester semester, SemesterConfig config) throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Add New Course to " + semester.getDisplayValue());

        System.out.print(ConsoleColors.YELLOW + "Enter Course Code: " + ConsoleColors.RESET);
        String courseCode = scanner.nextLine().trim();
        if (courseCode.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Course Code cannot be empty." + ConsoleColors.RESET);
            Thread.sleep(1500);
            return;
        }

        System.out.print(ConsoleColors.YELLOW + "Enter Course Title: " + ConsoleColors.RESET);
        String courseTitle = scanner.nextLine().trim();

        List<String> instructors = new ArrayList<>();
        while (true) {
            System.out.print(ConsoleColors.YELLOW + "Enter Teacher Name (or 'done' to finish): " + ConsoleColors.RESET);
            String teacherName = scanner.nextLine().trim();
            if (teacherName.equalsIgnoreCase("done")) {
                if (instructors.isEmpty()) {
                    System.out.println(ConsoleColors.RED + "At least one teacher must be added." + ConsoleColors.RESET);
                    continue;
                }
                break;
            }
            instructors.add(teacherName);
        }

        config.getCourses().add(new Course(courseCode, courseTitle, instructors, 0, 0, 0, 0));
        logAction("Added course: " + courseCode + " - " + courseTitle + " to " + semester.getDisplayValue()); // Moved logAction before saveData
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Course added successfully." + ConsoleColors.RESET);
        Thread.sleep(1500);
    }

    private static void enterParticularCourse(SemesterConfig semesterConfig, Semester semester)
            throws InterruptedException {
        List<Course> courses = semesterConfig.getCourses();
        if (courses.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses to edit in this semester." + ConsoleColors.RESET);
            Thread.sleep(1500);
            return;
        }

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Select a Course to Edit");

            // Display unique courses by code
            List<String> uniqueCourseCodes = courses.stream()
                    .map(Course::getCourseCode)
                    .distinct()
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println("\n" + ConsoleColors.PURPLE_BOLD + "  ╭──────────────────────────────────────────────────────────╮");
            System.out.printf ("  │ %-56s │%n", ConsoleColors.CYAN_BOLD + "  AVAILABLE COURSES:" + ConsoleColors.PURPLE_BOLD);
            System.out.println("  ├──────────────────────────────────────────────────────────┤");

            for (int i = 0; i < uniqueCourseCodes.size(); i++) {
                String courseCode = uniqueCourseCodes.get(i);
                String courseTitle = courses.stream()
                        .filter(c -> c.getCourseCode().equals(courseCode))
                        .findFirst().get().getCourseTitle();
                
                String label = String.format("  %d. %-10s - %s", (i + 1), courseCode, courseTitle);
                // Truncate label if it's too long for the box
                if (label.length() > 54) {
                    label = label.substring(0, 51) + "...";
                }
                
                System.out.printf (ConsoleColors.WHITE_BOLD + "  │ %-56s " + ConsoleColors.PURPLE_BOLD + "│%n", label);
            }
            
            System.out.println("  ╰──────────────────────────────────────────────────────────╯" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            if (choice == 0) {
                return;
            }
            if (choice > 0 && choice <= uniqueCourseCodes.size()) {
                String chosenCourseCode = uniqueCourseCodes.get(choice - 1);
                // Find the first course in the original list that matches the chosen unique
                // course code
                Course courseToEdit = courses.stream().filter(c -> c.getCourseCode().equals(chosenCourseCode))
                        .findFirst().get();
                editCourseDetails(courseToEdit, semester); // Pass semester
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                Thread.sleep(1000);
            }
        }
    }

    private static void updateCourseAttendanceData(String courseCode, Semester semester) {
        if (courseCode == null || semester == null) {
            return;
        }

        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null) {
            return;
        }

        Course courseToUpdate = semesterConfig.getCourses().stream()
                .filter(c -> c.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst()
                .orElse(null);

        if (courseToUpdate != null) {
            int totalClasses = attendanceService.getTotalClassesForCourse(courseCode, semester);
            int attendedClasses = attendanceService.getAttendedClassesForCourse(courseCode, semester);
            courseToUpdate.setTotalClasses(totalClasses);
            courseToUpdate.setAttendedClasses(attendedClasses);
            dataManager.saveData(dataStorage);
        }
    }

    private static void editCourseDetails(Course course, Semester semester) throws InterruptedException {
        // Update attendance marks first
        int totalClasses = attendanceService.getTotalClassesForCourse(course.getCourseCode(), semester);
        int attendedClasses = attendanceService.getAttendedClassesForCourse(course.getCourseCode(), semester);
        course.setTotalClasses(totalClasses);
        course.setAttendedClasses(attendedClasses);
        course.calculateAttendanceMarks();

        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Editing Details for " + course.getCourseCode());
            Utils.printDigitalClock();
            System.out.println();
            System.out.println(ConsoleColors.YELLOW + "Current Details:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  Course Code: " + course.getCourseCode() + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  Course Title: " + course.getCourseTitle() + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  Instructors: " + String.join(", ", course.getInstructors())
                    + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  Credit Hours: " + course.getCreditHours() + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN
                    + String.format("  Mid Marks: %.1f / %.1f", course.getMidMarks(), 15.0) + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN
                    + String.format("  Final Marks: %.1f / %.1f", course.getFinalMarks(), 70.0) + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN
                    + String.format("  Assignment Marks: %.1f / %.1f", course.getAssignmentMarks(), 5.0)
                    + ConsoleColors.RESET); // Corrected max for Assignment
            System.out
                    .println(ConsoleColors.GREEN_BOLD
                            + String.format("  Present Marks: %.1f / %.1f (from %d/%d classes)",
                                    course.getAttendanceMarks(), 10.0, attendedClasses, totalClasses)
                            + ConsoleColors.RESET);

            // Calculate total marks for the course
            double currentTotalMarks = course.getTotalMarks(); // This is calculated in Course class out of 110
            double maxTotalMarks = 15.0 + 70.0 + 5.0 + 10.0; // Mid Max + Final Max + Assignment Max + Present Max = 
                                                             // 100.0 (Corrected) 

            String neededFor80 = formatNeededFor80Total(currentTotalMarks);

            System.out
                    .println(ConsoleColors.YELLOW_BOLD + String.format("  Total Marks: %.1f / %.1f (Needed for 80: %s)",
                            currentTotalMarks, maxTotalMarks, neededFor80) + ConsoleColors.RESET);
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "1. Edit Course Code" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "2. Edit Course Title" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "3. Edit Instructors" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "4. Edit Credit Hours" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "5. Edit Mid Marks" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "6. Edit Final Marks" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "7. Edit Assignment Marks" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "8. Edit All" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "0. Back" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    String oldCourseCode = course.getCourseCode();
                    System.out.print(ConsoleColors.YELLOW + "Enter new Course Code: " + ConsoleColors.RESET);
                    String newCourseCode = scanner.nextLine();
                    course.setCourseCode(newCourseCode);
                    logAction("Edited Course Code: " + oldCourseCode + " to " + newCourseCode + " in " + semester.getDisplayValue());
                    break;
                case 2:
                    String oldCourseTitle = course.getCourseTitle();
                    System.out.print(ConsoleColors.YELLOW + "Enter new Course Title: " + ConsoleColors.RESET);
                    String newCourseTitle = scanner.nextLine();
                    course.setCourseTitle(newCourseTitle);
                    logAction("Edited Course Title: " + oldCourseTitle + " to " + newCourseTitle + " for course " + course.getCourseCode() + " in " + semester.getDisplayValue());
                    break;
                case 3:
                    List<String> oldInstructors = new ArrayList<>(course.getInstructors());
                    List<String> newInstructors = new ArrayList<>();
                    while (true) {
                        System.out.print(ConsoleColors.YELLOW + "Enter new Teacher Name (or 'done' to finish): "
                                + ConsoleColors.RESET);
                        String teacherName = scanner.nextLine().trim();
                        if (teacherName.equalsIgnoreCase("done")) {
                            if (newInstructors.isEmpty()) {
                                System.out.println(ConsoleColors.RED + "At least one teacher must be added."
                                        + ConsoleColors.RESET);
                                continue;
                            }
                            break;
                        }
                        newInstructors.add(teacherName);
                    }
                    course.setInstructors(newInstructors);
                    logAction("Edited Instructors for " + course.getCourseCode() + " from " + String.join(", ", oldInstructors) + " to " + String.join(", ", newInstructors) + " in " + semester.getDisplayValue());
                    break;
                case 4:
                    double oldCreditHours = course.getCreditHours();
                    System.out.print(ConsoleColors.YELLOW + "Enter new Credit Hours: " + ConsoleColors.RESET);
                    double newCreditHours = Double.parseDouble(scanner.nextLine());
                    course.setCreditHours(newCreditHours);
                    logAction("Edited Credit Hours for " + course.getCourseCode() + " from " + oldCreditHours + " to " + newCreditHours + " in " + semester.getDisplayValue());
                    break;
                case 5:
                    double oldMidMark = course.getMidMarks();
                    double midMark = -1;
                    while (midMark < 0 || midMark > 15) {
                        try {
                            System.out.print(
                                    ConsoleColors.YELLOW + "Enter new Mid Marks (out of 15): " + ConsoleColors.RESET);
                            midMark = Double.parseDouble(scanner.nextLine());
                            if (midMark < 0 || midMark > 15) {
                                System.out.println(
                                        ConsoleColors.RED + "Invalid marks. Please enter a value between 0 and 15."
                                                + ConsoleColors.RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
                            midMark = -1;
                        }
                    }
                    course.setMidMarks(midMark);
                    logAction("Edited Mid Marks for " + course.getCourseCode() + " from " + oldMidMark + " to " + midMark + " in " + semester.getDisplayValue());
                    break;
                case 6:
                    double oldFinalMark = course.getFinalMarks();
                    double finalMark = -1;
                    while (finalMark < 0 || finalMark > 70) {
                        try {
                            System.out.print(
                                    ConsoleColors.YELLOW + "Enter new Final Marks (out of 70): " + ConsoleColors.RESET);
                            finalMark = Double.parseDouble(scanner.nextLine());
                            if (finalMark < 0 || finalMark > 70) {
                                System.out.println(
                                        ConsoleColors.RED + "Invalid marks. Please enter a value between 0 and 70."
                                                + ConsoleColors.RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
                            finalMark = -1;
                        }
                    }
                    course.setFinalMarks(finalMark);
                    logAction("Edited Final Marks for " + course.getCourseCode() + " from " + oldFinalMark + " to " + finalMark + " in " + semester.getDisplayValue());
                    break;
                case 7:
                    double oldAssignmentMark = course.getAssignmentMarks();
                    double assignmentMark = -1;
                    while (assignmentMark < 0 || assignmentMark > 5) {
                        try {
                            System.out.print(ConsoleColors.YELLOW + "Enter new Assignment Marks (out of 5): "
                                    + ConsoleColors.RESET);
                            assignmentMark = Double.parseDouble(scanner.nextLine());
                            if (assignmentMark < 0 || assignmentMark > 5) {
                                System.out.println(ConsoleColors.RED
                                        + "Invalid marks. Please enter a value between 0 and 5." + ConsoleColors.RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
                            assignmentMark = -1;
                        }
                    }

                    System.out.print(ConsoleColors.YELLOW + "Add extra marks? (yes/no): " + ConsoleColors.RESET);
                    String extraChoice = scanner.nextLine();
                    if (extraChoice.equalsIgnoreCase("yes") || extraChoice.equalsIgnoreCase("y")) {
                        System.out.print(ConsoleColors.YELLOW + "Enter extra marks: " + ConsoleColors.RESET);
                        try {
                            double extraMark = Double.parseDouble(scanner.nextLine());
                            double potentialAssignmentMark = assignmentMark + extraMark;
                            // Check if potential total marks exceed 100 with this extra mark
                            double currentTotalWithoutAssignment = course.getMidMarks() + course.getFinalMarks() 
                                    + course.getAttendanceMarks();
                            if (currentTotalWithoutAssignment + potentialAssignmentMark > 100.0) {
                                System.out.println(ConsoleColors.RED
                                        + "The combined marks would exceed 100.0. Adjustment not applied. Total potential: "
                                        + potentialAssignmentMark + ConsoleColors.RESET);
                                Thread.sleep(2000);
                            } else {
                                assignmentMark = potentialAssignmentMark;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(ConsoleColors.RED + "Invalid input for extra marks. Not added."
                                    + ConsoleColors.RESET);
                            Thread.sleep(1500);
                        }
                    }
                    course.setAssignmentMarks(assignmentMark);
                    logAction("Edited Assignment Marks for " + course.getCourseCode() + " from " + oldAssignmentMark + " to " + assignmentMark + " in " + semester.getDisplayValue());
                    break;
                case 8:
                    String oldAllCourseCode = course.getCourseCode();
                    String oldAllCourseTitle = course.getCourseTitle();
                    List<String> oldAllInstructors = new ArrayList<>(course.getInstructors());
                    double oldAllCreditHours = course.getCreditHours();
                    double oldAllMidMark = course.getMidMarks();
                    double oldAllFinalMark = course.getFinalMarks();
                    double oldAllAssignmentMark = course.getAssignmentMarks();

                    // Edit All - with validation
                    System.out.print(ConsoleColors.YELLOW + "Enter new Course Code: " + ConsoleColors.RESET);
                    String newAllCourseCode = scanner.nextLine();
                    course.setCourseCode(newAllCourseCode);
                    System.out.print(ConsoleColors.YELLOW + "Enter new Course Title: " + ConsoleColors.RESET);
                    String newAllCourseTitle = scanner.nextLine();
                    course.setCourseTitle(newAllCourseTitle);

                    List<String> allNewInstructors = new ArrayList<>();
                    while (true) {
                        System.out.print(ConsoleColors.YELLOW + "Enter new Teacher Name (or 'done' to finish): "
                                + ConsoleColors.RESET);
                        String teacherName = scanner.nextLine().trim();
                        if (teacherName.equalsIgnoreCase("done")) {
                            if (allNewInstructors.isEmpty()) {
                                System.out.println(ConsoleColors.RED + "At least one teacher must be added."
                                        + ConsoleColors.RESET);
                                continue;
                            }
                            break;
                        }
                        allNewInstructors.add(teacherName);
                    }
                    course.setInstructors(allNewInstructors);

                    System.out.print(ConsoleColors.YELLOW + "Enter new Credit Hours: " + ConsoleColors.RESET);
                    double newAllCreditHours = Double.parseDouble(scanner.nextLine());
                    course.setCreditHours(newAllCreditHours);

                    double editedMidMark = -1;
                    while (editedMidMark < 0 || editedMidMark > 15) {
                        try {
                            System.out.print(
                                    ConsoleColors.YELLOW + "Enter new Mid Marks (out of 15): " + ConsoleColors.RESET);
                            editedMidMark = Double.parseDouble(scanner.nextLine());
                            if (editedMidMark < 0 || editedMidMark > 15) {
                                System.out.println(
                                        ConsoleColors.RED + "Invalid marks. Please enter a value between 0 and 15."
                                                + ConsoleColors.RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
                            editedMidMark = -1;
                        }
                    }
                    course.setMidMarks(editedMidMark);

                    double editedFinalMark = -1;
                    while (editedFinalMark < 0 || editedFinalMark > 70) {
                        try {
                            System.out.print(
                                    ConsoleColors.YELLOW + "Enter new Final Marks (out of 70): " + ConsoleColors.RESET);
                            editedFinalMark = Double.parseDouble(scanner.nextLine());
                            if (editedFinalMark < 0 || editedFinalMark > 70) {
                                System.out.println(
                                        ConsoleColors.RED + "Invalid marks. Please enter a value between 0 and 70."
                                                + ConsoleColors.RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
                            editedFinalMark = -1;
                        }
                    }
                    course.setFinalMarks(editedFinalMark);

                    double editedAssignmentMark = -1;
                    while (editedAssignmentMark < 0 || editedAssignmentMark > 5) {
                        try {
                            System.out.print(ConsoleColors.YELLOW + "Enter new Assignment Marks (out of 5): "
                                    + ConsoleColors.RESET);
                            editedAssignmentMark = Double.parseDouble(scanner.nextLine());
                            if (editedAssignmentMark < 0 || editedAssignmentMark > 5) {
                                System.out.println(ConsoleColors.RED
                                        + "Invalid marks. Please enter a value between 0 and 5." + ConsoleColors.RESET);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
                            editedAssignmentMark = -1;
                        }
                    }

                    double potentialExtraMark = 0.0;
                    System.out.print(ConsoleColors.YELLOW + "Add extra marks? (yes/no): " + ConsoleColors.RESET);
                    String allExtraChoice = scanner.nextLine();
                    if (allExtraChoice.equalsIgnoreCase("yes") || allExtraChoice.equalsIgnoreCase("y")) {
                        System.out.print(ConsoleColors.YELLOW + "Enter extra marks: " + ConsoleColors.RESET);
                        try {
                            potentialExtraMark = Double.parseDouble(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println(ConsoleColors.RED + "Invalid input for extra marks. Not added."
                                    + ConsoleColors.RESET);
                            Thread.sleep(1500);
                        }
                    }

                    // Now, validate the total before setting the assignment mark
                    double tempMid = editedMidMark;
                    double tempFinal = editedFinalMark;
                    double tempPresent = course.getAttendanceMarks(); // Current attendance marks are not edited in this
                                                                      // block

                    double potentialTotal = tempMid + tempFinal + (editedAssignmentMark + potentialExtraMark)
                            + tempPresent;
                    if (potentialTotal > 100.0) {
                        System.out.println(ConsoleColors.RED
                                + "The combined marks would exceed 100.0. Adjustment not applied. Total potential: "
                                + potentialTotal + ConsoleColors.RESET);
                        Thread.sleep(2000);
                    } else {
                        editedAssignmentMark += potentialExtraMark;
                        course.setMidMarks(tempMid);
                        course.setFinalMarks(tempFinal);
                        course.setAssignmentMarks(editedAssignmentMark);
                    }
                    logAction("Edited ALL details for course " + oldAllCourseCode + " (now " + newAllCourseCode + ") in " + semester.getDisplayValue() + ". Changes include Title, Instructors, Credit Hours, Mid Marks, Final Marks, Assignment Marks.");
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static String formatNeededFor80Total(double currentTotalMarks) {
        double diff = currentTotalMarks - 80;
        if (diff == 0) {
            return "0";
        } else if (diff > 0) {
            if (diff == 1) {
                return "++";
            } else {
                return "+=" + String.format("%.1f", diff); // Display float
            }
        } else {
            return String.format("%.1f", 80 - currentTotalMarks); // Marks needed to reach 80
        }
    }

    private static void generateUniversityReports() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Generate University Reports");
            Utils.printDigitalClock();
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. All University Details Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. Attendance Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. Course List Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. Registration Fee Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  5. SGPA Report for a Semester" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  6. Overall CGPA Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to University Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            String dest = "Report.pdf"; // Default destination
            Semester selectedSemester = null;

            try {
                switch (choice) {
                    case 1: // Comprehensive Report
                        dest = "University_Details_Report.pdf";
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.COMPREHENSIVE, null, null);
                        break;
                    case 2: // Attendance Calendar for a Semester (All Attendance)
                        selectedSemester = selectSemesterForAction("generate attendance calendar for");
                        if (selectedSemester == null)
                            continue;
                        dest = "Attendance_Calendar_" + selectedSemester.name() + ".pdf";
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.ATTENDANCE_CALENDAR_SEMESTER, selectedSemester, null);
                        break;
                    case 3: // Course List Report
                        dest = "Course_List_Report.pdf";
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.COURSE_LIST, null, null);
                        break;
                    case 4: // Registration Fee Report
                        generateRegistrationFeePdfUniversity();
                        break;
                    case 5: // SGPA Report for a Semester
                        selectedSemester = selectSemesterForAction("generate SGPA report for");
                        if (selectedSemester == null)
                            continue;
                        dest = "SGPA_Report_" + selectedSemester.name() + ".pdf";
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.CGPA_SEMESTER, selectedSemester, null);
                        break;
                    case 6: // Overall CGPA Report
                        dest = "Overall_CGPA_Report.pdf";
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.CGPA_OVERALL, null, null);
                        break;
                    case 0:
                        return;
                    default:
                        System.out
                                .println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                        Thread.sleep(1000);
                        continue;
                }
                System.out.println(
                        ConsoleColors.GREEN + "PDF report generated successfully: " + dest + ConsoleColors.RESET);
                openPdf(dest);
            } catch (Exception e) {
                System.out.println(
                        ConsoleColors.RED + "Error generating PDF report: " + e.getMessage() + ConsoleColors.RESET);
                e.printStackTrace();
            }
            Utils.printFooter();
            System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
            scanner.nextLine();
        }
    }

    private static void generateRegistrationFeePdfUniversity() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Generate Registration Fee Report");
            Utils.printDigitalClock();
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. All Semesters" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. Specific Semester" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    generateOverallFeePdfReportApp();
                    break;
                case 2:
                    generateSemesterFeePdfReportApp();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void generateSemesterFeePdfReportApp() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Generate Semester Fee Report PDF");
        Utils.printDigitalClock();
        System.out.println();
        Semester semester = selectSemesterForAction("generate fee report for");
        if (semester == null)
            return;

        try {
            String dest = "Registration_Fee_Report_" + semester.name() + ".pdf";
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    SIGNATURE_IMAGE_PATH, ReportType.REGISTRATION_FEE_SEMESTER, semester, null);
            System.out.println(ConsoleColors.GREEN + "Semester Fee Report PDF generated successfully: " + dest
                    + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error generating Semester Fee Report PDF: " + e.getMessage()
                    + ConsoleColors.RESET);
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    private static void generateOverallFeePdfReportApp() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Generate Overall Fee Report PDF");
        Utils.printDigitalClock();
        System.out.println();

        try {
            String dest = "Overall_Registration_Fee_Report.pdf";
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    SIGNATURE_IMAGE_PATH, ReportType.REGISTRATION_FEE_OVERALL, null, null);
            System.out.println(ConsoleColors.GREEN + "Overall Fee Report PDF generated successfully: " + dest
                    + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error generating Overall Fee Report PDF: " + e.getMessage()
                    + ConsoleColors.RESET);
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    private static void _markAttendance(LocalDate date) throws InterruptedException {
        _markAttendance(date, false); // Default to not an extra class
    }

    private static void _markAttendance(LocalDate date, boolean isExtraClassParam) throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Mark Attendance for " + Utils.formatDateWithDay(date) + (isExtraClassParam ? " (Extra Class)" : ""));
            Utils.printDigitalClock();
            System.out.println();

            Semester currentSemester = dataStorage.getCurrentSemester();
            if (currentSemester == null) {
                System.out.println(ConsoleColors.RED + "No current semester set. Please set a current semester first." + ConsoleColors.RESET);
                return;
            }

            SemesterConfig currentSemesterConfig = dataStorage.getSemesterConfigurations().get(currentSemester);
            if (currentSemesterConfig == null || !currentSemesterConfig.isEntered() || currentSemesterConfig.getCourses().isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No courses configured for the current semester (" + currentSemester.getDisplayValue() + "). Please configure courses first." + ConsoleColors.RESET);
                return;
            }

            List<ClassAttendance> markedToday = attendanceService.getAttendanceForDate(date);
            Map<String, Set<String>> markedCourses = new HashMap<>();
            for (ClassAttendance att : markedToday) {
                markedCourses.computeIfAbsent(att.getCourseCode(), k -> new HashSet<>()).add(att.getTeacherName());
            }

            List<Course> availableCourses = currentSemesterConfig.getCourses();
            System.out.println(ConsoleColors.YELLOW + "Select a course:" + ConsoleColors.RESET);
            System.out.println();
            boolean[] isCourseMarked = new boolean[availableCourses.size()];
            for (int i = 0; i < availableCourses.size(); i++) {
                Course course = availableCourses.get(i);
                Set<String> markedInstructors = markedCourses.getOrDefault(course.getCourseCode(), new HashSet<>());
                if (markedInstructors.containsAll(course.getInstructors())) {
                    isCourseMarked[i] = true;
                    System.out.println(ConsoleColors.RED + "  " + (i + 1) + ". " + course.getCourseCode() + " - " + course.getCourseTitle() + " (Marked)" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + course.getCourseCode() + " - " + course.getCourseTitle() + ConsoleColors.RESET);
                }
            }
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter choice (0 to cancel): " + ConsoleColors.RESET);
            int courseChoice = getUserChoice();
            System.out.println();

            if (courseChoice == 0) return;

            if (courseChoice > 0 && courseChoice <= availableCourses.size()) {
                if (isCourseMarked[courseChoice - 1]) {
                    System.out.println(ConsoleColors.RED + "Attendance for this course is already fully marked for today." + ConsoleColors.RESET);
                    Thread.sleep(1500);
                    continue;
                }

                Course selectedCourse = availableCourses.get(courseChoice - 1);
                String selectedInstructor = null;

                if (selectedCourse.getInstructors().size() > 1) {
                    while(true) {
                        System.out.println(ConsoleColors.YELLOW + "Select an instructor for " + selectedCourse.getCourseCode() + ":" + ConsoleColors.RESET);
                        System.out.println();
                        List<String> instructors = selectedCourse.getInstructors();
                        boolean[] isInstructorMarked = new boolean[instructors.size()];
                        Set<String> markedInstructorsForCourse = markedCourses.getOrDefault(selectedCourse.getCourseCode(), new HashSet<>());

                        for (int i = 0; i < instructors.size(); i++) {
                            if (markedInstructorsForCourse.contains(instructors.get(i))) {
                                isInstructorMarked[i] = true;
                                System.out.println(ConsoleColors.RED + "  " + (i + 1) + ". " + instructors.get(i) + " (Marked)" + ConsoleColors.RESET);
                            } else {
                                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + instructors.get(i) + ConsoleColors.RESET);
                            }
                        }
                        System.out.println();
                        System.out.print(ConsoleColors.YELLOW + "Enter choice (0 to cancel): " + ConsoleColors.RESET);
                        int instructorChoice = getUserChoice();
                        System.out.println();

                        if (instructorChoice == 0) break;

                        if (instructorChoice > 0 && instructorChoice <= instructors.size()) {
                            if(isInstructorMarked[instructorChoice - 1]) {
                                System.out.println(ConsoleColors.RED + "Attendance for this instructor is already marked today." + ConsoleColors.RESET);
                                Thread.sleep(1500);
                                continue;
                            }
                            selectedInstructor = instructors.get(instructorChoice - 1);
                            break; 
                        } else {
                            System.out.println(ConsoleColors.RED + "Invalid instructor choice." + ConsoleColors.RESET);
                        }
                    }
                     if (selectedInstructor == null) continue;
                } else {
                    selectedInstructor = selectedCourse.getInstructors().get(0);
                }

                LocalTime classTime = getUserTime("class");
                if (classTime == null) classTime = LocalTime.now();
                System.out.println();

                System.out.print(ConsoleColors.YELLOW + "Were you present for " + selectedCourse.getCourseCode() + " with " + selectedInstructor + " at " + Utils.formatTime(classTime) + " on " + Utils.formatDateWithDay(date) + "? (P for present, . for absent): " + ConsoleColors.RESET);
                String presentChoice = scanner.nextLine().trim();
                System.out.println();
                boolean present = presentChoice.equalsIgnoreCase("P");

                boolean isExtraClass = isExtraClassParam; // Use the parameter value
                
                System.out.print(ConsoleColors.YELLOW + "How many attendance records were taken? " + ConsoleColors.RESET);
                int attendanceSignatures = getUserChoice();
                if (attendanceSignatures < 0) {
                    System.out.println(ConsoleColors.RED + "Number of attendance records must be non-negative. Defaulting to 0." + ConsoleColors.RESET);
                    attendanceSignatures = 0;
                }
                boolean attendanceTaken = (attendanceSignatures > 0);
                System.out.println(); // Add a newline after input

                System.out.print(ConsoleColors.YELLOW + "Enter Topic for this class (or press Enter to skip): " + ConsoleColors.RESET);
                String topic = scanner.nextLine().trim();
                System.out.println();

                int numberOfClasses = 1;
                // If it's an extra class from the specific menu option, we ask for count
                if (isExtraClass) {
                    System.out.print(ConsoleColors.YELLOW + "How many extra classes? " + ConsoleColors.RESET);
                    try {
                        numberOfClasses = Integer.parseInt(scanner.nextLine().trim());
                        if (numberOfClasses <= 0) {
                            System.out.println(ConsoleColors.RED + "Number of classes must be positive. Defaulting to 1." + ConsoleColors.RESET);
                            numberOfClasses = 1;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(ConsoleColors.RED + "Invalid number. Defaulting to 1 extra class." + ConsoleColors.RESET);
                        numberOfClasses = 1;
                    }
                }

                for (int i = 0; i < numberOfClasses; i++) {
                    LocalTime recordTime = classTime.plusMinutes(i);
                    attendanceService.markAttendance(selectedCourse.getCourseCode(), selectedCourse.getCourseTitle(), selectedInstructor, topic, date, recordTime, present, isExtraClass, attendanceTaken, attendanceSignatures, currentSemester);
                }
                String logMessage = "Marked " + numberOfClasses + (isExtraClass ? " extra" : "") + " class(es) for " + selectedCourse.getCourseCode() + " on " + date + " as " + (present ? "Present" : "Absent");
                logAction(logMessage);

                updateCourseAttendanceData(selectedCourse.getCourseCode(), currentSemester);

            } else {
                System.out.println(ConsoleColors.RED + "Invalid course choice." + ConsoleColors.RESET);
                Thread.sleep(1500);
            }
        }
    }

    private static void markTodayAttendance() throws InterruptedException {
        _markAttendance(LocalDate.now(), false); // Explicitly mark as not an extra class
    }

    private static void manageEditAttendanceInfoMenu() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Edit Attendance Info");
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. Edit Topic" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. Edit Online/Offline" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. Edit Attendance Status" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. Edit Attendance Count" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  5. Study Materials (Book/Sheet)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    editAttendanceField("topic");
                    break;
                case 2:
                    editAttendanceField("online");
                    break;
                case 3:
                    editAttendanceField("attendance");
                    break;
                case 4:
                    editAttendanceField("attendanceCount");
                    break;
                case 5:
                    logNavigation("Study Materials (Book/Sheet)", "FORWARD");
                    manageStudyMaterials();
                    logNavigation("Study Materials (Book/Sheet)", "BACKWARD");
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void editAttendanceField(String fieldType) throws InterruptedException {
        LocalDate filterDate = null;
        Semester currentSemester = dataStorage.getCurrentSemester();

        while (true) {
            Utils.clearConsole();
            String headerTitle = fieldType.substring(0, 1).toUpperCase() + fieldType.substring(1);
            if (fieldType.equals("attendanceCount")) headerTitle = "Attendance Count";
            Utils.printHeader("Edit " + headerTitle);
            System.out.println();

            if (currentSemester == null) {
                System.out.println(ConsoleColors.RED + "No current semester is set. Defaulting to all records." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.CYAN + "Current Semester: " + currentSemester.getDisplayValue() + ConsoleColors.RESET);
            }
            System.out.println();

            final LocalDate currentFilter = filterDate;
            final Semester finalCurrentSemester = currentSemester;

            List<ClassAttendance> allAttendances = dataStorage.getClassAttendances().stream()
                    .filter(ca -> (finalCurrentSemester == null || ca.getSemester() == finalCurrentSemester))
                    .filter(ca -> currentFilter == null || ca.getDate().equals(currentFilter))
                    .sorted(Comparator.comparing(ClassAttendance::getDate).thenComparing(ClassAttendance::getTime))
                    .collect(Collectors.toList());

            if (allAttendances.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No attendance records found" + (filterDate != null ? " for " + filterDate : "") + "." + ConsoleColors.RESET);
            } else {
                for (int i = 0; i < allAttendances.size(); i++) {
                    ClassAttendance ca = allAttendances.get(i);
                    String onlineIndicator = ca.isOnline() ? ConsoleColors.GREEN + "●" + ConsoleColors.RESET : "";
                    String timeStr = Utils.formatTime(ca.getTime());
                    String statusStr = ca.isPresent() ? (ca.getAttendanceSignatures() > 1 ? ca.getAttendanceSignatures() + "P" : "P") : "A";
                    System.out.printf("%s%2d. [%s] %s%s%s | %-10s | %-15s | %s %s%n", 
                        ConsoleColors.YELLOW, (i + 1), ca.getDate(), 
                        ConsoleColors.MAGENTA_BRIGHT, timeStr, ConsoleColors.RESET,
                        ca.getCourseCode(), ca.getTeacherName(), 
                        statusStr, onlineIndicator + ConsoleColors.RESET);
                    System.out.println("   Topic: " + (ca.getTopic() != null ? ca.getTopic() : "N/A") + " | Count: " + ca.getAttendanceSignatures());
                }
            }

            System.out.println();
            if (filterDate != null) {
                System.out.println(ConsoleColors.CYAN + "Filtered by: " + filterDate + " (Enter 'c' to clear filter)" + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.YELLOW + "Enter number to edit, date (DD-MM-YY) to filter, or 0 to back: " + ConsoleColors.RESET);
            String input = scanner.nextLine().trim();

            if (input.equals("0")) return;
            if (input.equalsIgnoreCase("c")) {
                filterDate = null;
                continue;
            }

            if (input.contains("-")) {
                try {
                    // Pre-process date string to handle single-digit months/days
                    String[] parts = input.split("-");
                    if (parts.length == 3) {
                        String day = String.format("%02d", Integer.parseInt(parts[0]));
                        String month = String.format("%02d", Integer.parseInt(parts[1]));
                        String year = parts[2];
                        input = day + "-" + month + "-" + year;
                    }
                    filterDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yy"));
                    continue;
                } catch (Exception e) {
                    System.out.println(ConsoleColors.RED + "Invalid date format. Use DD-MM-YY." + ConsoleColors.RESET);
                    Thread.sleep(1000);
                    continue;
                }
            }

            try {
                int index = Integer.parseInt(input);
                if (index > 0 && index <= allAttendances.size()) {
                    ClassAttendance selected = allAttendances.get(index - 1);
                    performAttendanceEdit(selected, fieldType);
                    System.out.println(ConsoleColors.GREEN + "Updated successfully." + ConsoleColors.RESET);
                    Thread.sleep(1000);
                } else {
                    System.out.println(ConsoleColors.RED + "Invalid number." + ConsoleColors.RESET);
                    Thread.sleep(1000);
                }
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Invalid input." + ConsoleColors.RESET);
                Thread.sleep(1000);
            }
        }
    }

    private static void performAttendanceEdit(ClassAttendance ca, String fieldType) {
        switch (fieldType) {
            case "topic":
                System.out.print("Enter new topic (current: " + ca.getTopic() + "): ");
                String newTopic = scanner.nextLine().trim();
                attendanceService.updateTopic(ca, newTopic);
                logAction("Edited topic for " + ca.getCourseCode() + " on " + ca.getDate() + " to: " + newTopic);
                break;
            case "online":
                System.out.print("Is it Online class? (y/n, current: " + (ca.isOnline() ? "Online" : "Offline") + "): ");
                String onlineChoice = scanner.nextLine().trim();
                boolean isOnline = onlineChoice.equalsIgnoreCase("y");
                attendanceService.updateOnlineStatus(ca, isOnline);
                logAction("Set " + (isOnline ? "Online" : "Offline") + " for " + ca.getCourseCode() + " on " + ca.getDate());
                break;
            case "attendance":
                System.out.print("Change attendance status? (P for Present, . for Absent, current: " + (ca.isPresent() ? "P" : "A") + "): ");
                String attChoice = scanner.nextLine().trim();
                boolean isPresent = attChoice.equalsIgnoreCase("P");
                attendanceService.updateAttendanceStatus(ca, isPresent);
                logAction("Set attendance " + (isPresent ? "Present" : "Absent") + " for " + ca.getCourseCode() + " on " + ca.getDate());
                break;
            case "attendanceCount":
                System.out.print("Enter new attendance count (current: " + ca.getAttendanceSignatures() + "): ");
                try {
                    int count = Integer.parseInt(scanner.nextLine().trim());
                    attendanceService.updateAttendanceSignatures(ca, count);
                    logAction("Edited attendance count for " + ca.getCourseCode() + " on " + ca.getDate() + " to: " + count);
                } catch (NumberFormatException e) {
                    System.out.println(ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
                }
                break;
        }
    }

    private static void markMissedAttendance() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Mark Missed Attendance");
        Utils.printDigitalClock();
        System.out.println();
        LocalDate date = selectDate(); // Re-using selectDate helper
        if (date == null)
            return;
        _markAttendance(date, false); // Explicitly mark as not an extra class
    }


    private static void viewAttendanceDetails() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Attendance Details");
            Utils.printDigitalClock();
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. All Attendance Records" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. Calendar Attendance" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to Attendance Options" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    viewAllAttendanceRecords();
                    break;
                case 2:
                    viewCourseWiseAttendanceCalendar();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void viewAllAttendanceRecords() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("All Attendance Records");
        Utils.printDigitalClock();
        System.out.println();

        List<ClassAttendance> allAttendance = dataStorage.getClassAttendances();

        if (allAttendance.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No attendance records found." + ConsoleColors.RESET);
        } else {
            Map<Semester, Map<String, Map<String, Map<LocalDate, List<ClassAttendance>>>>> groupedAttendance = allAttendance.stream()
                .filter(att -> att.getSemester() != null)
                .collect(Collectors.groupingBy(
                    ClassAttendance::getSemester,
                    Collectors.groupingBy(
                        ClassAttendance::getCourseCode,
                        Collectors.groupingBy(
                            ClassAttendance::getTeacherName,
                            Collectors.groupingBy(ClassAttendance::getDate)
                        )
                    )
                ));

            Semester currentSemester = dataStorage.getCurrentSemester();
            int grandTotalPresent = 0;
            int grandTotalClasses = 0;
            long grandTotalEcTaken = 0;
            long grandTotalSignatures = 0;
            long grandTotalRegularClasses = 0;

            List<Semester> sortedSemesters = groupedAttendance.keySet().stream()
                .filter(s -> currentSemester == null || s.compareTo(currentSemester) <= 0)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

            for (Semester semester : sortedSemesters) {
                System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "\nSemester: " + semester.getDisplayValue() + ConsoleColors.RESET);
                Map<String, Map<String, Map<LocalDate, List<ClassAttendance>>>> coursesInSemester = groupedAttendance.get(semester);

                int semesterTotalPresent = 0;
                int semesterTotalClasses = 0;
                long semesterTotalEcTaken = 0;
                long semesterTotalSignatures = 0;
                long semesterRegularClasses = 0;

                List<String> sortedCourseCodes = coursesInSemester.keySet().stream().sorted().collect(Collectors.toList());

                for (String courseCode : sortedCourseCodes) {
                    Map<String, Map<LocalDate, List<ClassAttendance>>> instructorsInCourse = coursesInSemester.get(courseCode);

                    // Calculate progress for this course to show indicator
                    List<ClassAttendance> courseClassSessions = dataStorage.getClassAttendances().stream()
                            .filter(ca -> ca.getSemester() == semester
                                    && ca.getCourseCode().equals(courseCode) && ca.isPresent())
                            .collect(Collectors.toList());
                    long totalSessions = courseClassSessions.size();
                    long studiedSessions = courseClassSessions.stream().filter(ClassAttendance::isStudied).count();
                    double progressPercentage = (totalSessions > 0) ? ((double) studiedSessions / totalSessions) * 100 : 0.0;
                    String completedIndicator = "";

                    System.out.println(ConsoleColors.GREEN_BOLD + "\nCourse: " + courseCode + completedIndicator + ConsoleColors.RESET);

                    List<String> sortedInstructorNames = instructorsInCourse.keySet().stream().sorted().collect(Collectors.toList());
                    for (String instructorName : sortedInstructorNames) {
                        Map<LocalDate, List<ClassAttendance>> attendanceByDate = instructorsInCourse.get(instructorName);

                        long instructorTotalPresent = attendanceByDate.values().stream().flatMap(List::stream).filter(ClassAttendance::isPresent).count();
                        long instructorTotalClasses = attendanceByDate.values().stream().flatMap(List::stream).count();
                        long instructorTotalEcTaken = attendanceByDate.values().stream().flatMap(List::stream).filter(ClassAttendance::isExtraClass).count();
                        long instructorRegularClasses = instructorTotalClasses - instructorTotalEcTaken;
                        long instructorTotalSignatures = attendanceByDate.values().stream().flatMap(List::stream).mapToLong(ClassAttendance::getAttendanceSignatures).sum();

                        semesterTotalPresent += instructorTotalPresent;
                        semesterTotalClasses += instructorTotalClasses;
                        semesterRegularClasses += instructorRegularClasses;
                        semesterTotalEcTaken += instructorTotalEcTaken;
                        semesterTotalSignatures += instructorTotalSignatures;

                        System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------" + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.YELLOW_BOLD + " Instructor: " + instructorName + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.BLUE_BOLD + String.format("%-3s | %-12s | %-12s | %-12s | %-7s | %-20s", "No.", "Date", "Day", "Time", "Status", "Topic") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------" + ConsoleColors.RESET);

                        List<LocalDate> sortedDates = attendanceByDate.keySet().stream().sorted().collect(Collectors.toList());
                        int recordNo = 1;
                        for (LocalDate date : sortedDates) {
                            List<ClassAttendance> recordsForDay = attendanceByDate.get(date);
                            if (recordsForDay.isEmpty()) continue;

                            long presentCount = recordsForDay.stream().filter(ClassAttendance::isPresent).count();
                            long extraCount = recordsForDay.stream().filter(ClassAttendance::isExtraClass).count();
                            long totalSignaturesForDay = recordsForDay.stream().mapToLong(ClassAttendance::getAttendanceSignatures).sum();
                            boolean allOnline = recordsForDay.stream().allMatch(ClassAttendance::isOnline);
                            String topics = recordsForDay.stream()
                                    .map(ClassAttendance::getTopic)
                                    .filter(t -> t != null && !t.isEmpty())
                                    .distinct()
                                    .collect(Collectors.joining(", "));

                            LocalTime time = recordsForDay.get(0).getTime();

                            String status;
                            if (presentCount == recordsForDay.size()) {
                                String presentStr = (totalSignaturesForDay > 1) ? totalSignaturesForDay + "P" : "P";
                                status = ConsoleColors.GREEN_BOLD_BRIGHT + presentStr + ConsoleColors.RESET;
                            } else if (presentCount == 0) {
                                long absentCount = recordsForDay.size();
                                String absentStr = (absentCount > 1) ? absentCount + "A" : "A";
                                status = ConsoleColors.RED_BOLD_BRIGHT + absentStr + ConsoleColors.RESET;
                            } else {
                                status = ConsoleColors.YELLOW_BOLD_BRIGHT + "Mix" + ConsoleColors.RESET; // Shortened "Mixed" to "Mix"
                            }

                            String onlineIndicator = allOnline ? ConsoleColors.GREEN + "●" + ConsoleColors.RESET : "";
                            String extraClassColor = (extraCount > 1) ? ConsoleColors.ORANGE : ConsoleColors.COLOR_CYCLE_17;
                            String summary = extraCount > 0 ? " " + extraClassColor + "[" + extraCount + "]" + ConsoleColors.RESET : "";
                            String finalStatus = status + onlineIndicator + summary;

                            // Print the first line of the record
                            System.out.print(ConsoleColors.ROSE);
                            printPadded(String.valueOf(recordNo++), 3);
                            System.out.print(ConsoleColors.RESET + " | ");
                            printPadded(ConsoleColors.BLUE + Utils.formatDate(date), 12);
                            System.out.print(ConsoleColors.RESET + " | ");
                            printPadded(ConsoleColors.PURPLE + Utils.getDayOfWeek(date), 12);
                            System.out.print(ConsoleColors.RESET + " | ");
                            printPadded(ConsoleColors.YELLOW_BRIGHT + Utils.formatTime(time), 12);
                            System.out.print(ConsoleColors.RESET + " | ");
                            printPadded(finalStatus, 7);
                            System.out.print(ConsoleColors.RESET + " | ");

                            String topicText = topics.isEmpty() ? "---" : topics;
                            int topicWidth = 20;
                            boolean firstChunk = true;

                            while (!topicText.isEmpty()) {
                                if (!firstChunk) {
                                    // Print alignment spaces for the second line onwards
                                    System.out.print(String.format("%-3s | %-12s | %-12s | %-12s | %-7s | ", "", "", "", "", ""));
                                }
                                
                                String chunk;
                                if (topicText.length() <= topicWidth) {
                                    chunk = topicText;
                                    topicText = "";
                                } else {
                                    chunk = topicText.substring(0, topicWidth);
                                    topicText = topicText.substring(topicWidth);
                                }
                                System.out.println(ConsoleColors.CYAN + chunk + ConsoleColors.RESET);
                                firstChunk = false;
                            }
                        }
                        System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------" + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.YELLOW + " Instructor Summary: Present: " + instructorTotalPresent + " | Signatures: " + instructorTotalSignatures + 
                                " | Reg: " + instructorRegularClasses + " | Extra: " + instructorTotalEcTaken + ConsoleColors.RESET);
                    }
                }
                // Print Semester Summary
                System.out.println(ConsoleColors.YELLOW_UNDERLINED + "\nSemester Summary (" + semester.getDisplayValue() + "):" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "  Present: " + semesterTotalPresent + " | Signatures: " + semesterTotalSignatures + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + "  Class: " + semesterRegularClasses + " | Extra Class: " + semesterTotalEcTaken + " | Total: " + semesterTotalClasses + ConsoleColors.RESET);
                System.out.println();

                // Add to grand totals
                grandTotalPresent += semesterTotalPresent;
                grandTotalClasses += semesterTotalClasses;
                grandTotalRegularClasses += semesterRegularClasses;
                grandTotalEcTaken += semesterTotalEcTaken;
                grandTotalSignatures += semesterTotalSignatures;
            }

            // Print Grand Total Summary
            System.out.println(ConsoleColors.BLUE_UNDERLINED + "\nOverall Summary:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------------" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.YELLOW_BOLD + String.format("%-22s | %-7s | %-10s | %-5s | %-5s | %-5s", 
                "Semester", "Present", "Signatures", "Class", "Extra", "Total") + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------------" + ConsoleColors.RESET);
            
            for (Semester semester : sortedSemesters) {
                Map<String, Map<String, Map<LocalDate, List<ClassAttendance>>>> coursesInSem = groupedAttendance.get(semester);
                long semPresent = 0, semTotal = 0, semSignatures = 0, semEc = 0;
                
                for (var course : coursesInSem.values()) {
                    for (var instructor : course.values()) {
                        for (var dateRecords : instructor.values()) {
                            semPresent += dateRecords.stream().filter(ClassAttendance::isPresent).count();
                            semTotal += dateRecords.size();
                            semSignatures += dateRecords.stream().mapToLong(ClassAttendance::getAttendanceSignatures).sum();
                            semEc += dateRecords.stream().filter(ClassAttendance::isExtraClass).count();
                        }
                    }
                }
                long semReg = semTotal - semEc;
                
                System.out.println(String.format("%s%-22s%s | %s%7d%s | %s%10d%s | %s%5d%s | %s%5d%s | %s%5d%s",
                    ConsoleColors.CYAN, semester.getDisplayValue(), ConsoleColors.RESET,
                    ConsoleColors.GREEN_BRIGHT, semPresent, ConsoleColors.RESET,
                    ConsoleColors.GOLD, semSignatures, ConsoleColors.RESET,
                    ConsoleColors.YELLOW, semReg, ConsoleColors.RESET,
                    ConsoleColors.PURPLE, semEc, ConsoleColors.RESET,
                    ConsoleColors.WHITE_BOLD, semTotal, ConsoleColors.RESET
                ));
            }
            
            System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------------" + ConsoleColors.RESET);
            System.out.println(String.format("%s%-22s%s | %s%7d%s | %s%10d%s | %s%5d%s | %s%5d%s | %s%5d%s",
                ConsoleColors.GREEN_BOLD_BRIGHT, "GRAND TOTAL", ConsoleColors.RESET,
                ConsoleColors.GREEN_BOLD_BRIGHT, grandTotalPresent, ConsoleColors.RESET,
                ConsoleColors.GOLD_BOLD, grandTotalSignatures, ConsoleColors.RESET,
                ConsoleColors.YELLOW_BOLD, grandTotalRegularClasses, ConsoleColors.RESET,
                ConsoleColors.PURPLE_BOLD, grandTotalEcTaken, ConsoleColors.RESET,
                ConsoleColors.WHITE_BOLD_BRIGHT, grandTotalClasses, ConsoleColors.RESET
            ));
            System.out.println(ConsoleColors.BLUE_BOLD + "---------------------------------------------------------------------------------------" + ConsoleColors.RESET);
        }

        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }
    private static void viewCourseWiseAttendanceCalendar() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Course-wise Attendance Calendar");
        Utils.printDigitalClock();
        System.out.println();

        Semester selectedSemester = selectSemesterForAction("view attendance calendar for");
        if (selectedSemester == null) {
            return;
        }

        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(selectedSemester);
        if (semesterConfig == null || !semesterConfig.isEntered() || semesterConfig.getCourses().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses configured for " + selectedSemester.getDisplayValue()
                    + ". Cannot view calendar." + ConsoleColors.RESET);
            System.out.println();
            Thread.sleep(2000);
            return;
        }

        System.out.println(ConsoleColors.CYAN + "  1. All Courses" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN + "  2. Individual Course" + ConsoleColors.RESET);
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter your choice (0 to cancel): " + ConsoleColors.RESET);
        int calendarTypeChoice = getUserChoice();
        if (calendarTypeChoice == 0)
            return;

        // Get start and end dates from semester config
        LocalDate calendarStartDate = semesterConfig.getStartDate();
        LocalDate calendarEndDate = semesterConfig.getEndDate();

        // Check if start/end dates are configured
        if (calendarStartDate == null || calendarEndDate == null) {
            System.out.println(ConsoleColors.RED
                    + "Semester start or end date not configured. Please configure the semester first."
                    + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        if (calendarTypeChoice == 1) { // All Courses
            List<ClassAttendance> allSemesterAttendance = dataStorage.getClassAttendances().stream()
                    .filter(ca -> ca.getSemester().equals(selectedSemester))
                    .collect(Collectors.toList());

            Utils.clearConsole();
            // Call the new displayCalendar method for all courses
            CalendarView.displayCalendar(allSemesterAttendance, null, calendarStartDate, calendarEndDate); // individualCourseCode
                                                                                                           // is null
                                                                                                           // for all
                                                                                                           // courses
            System.out.println(); // Add space after calendar

        } else if (calendarTypeChoice == 2) { // Individual Course
            List<Course> availableCourses = semesterConfig.getCourses();
            System.out.println(ConsoleColors.YELLOW + "Select a course:" + ConsoleColors.RESET);
            for (int i = 0; i < availableCourses.size(); i++) {
                Course course = availableCourses.get(i);
                System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + course.getCourseCode() + " - "
                        + course.getCourseTitle() + ConsoleColors.RESET);
            }
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter choice (0 to cancel): " + ConsoleColors.RESET);
            int courseChoice = getUserChoice();
            if (courseChoice == 0)
                return;

            if (courseChoice > 0 && courseChoice <= availableCourses.size()) {
                Course selectedCourse = availableCourses.get(courseChoice - 1);

                List<ClassAttendance> history = dataStorage.getClassAttendances().stream()
                        .filter(ca -> ca.getCourseCode().equals(selectedCourse.getCourseCode())
                                && ca.getSemester().equals(selectedSemester))
                        .collect(Collectors.toList());

                Utils.clearConsole();
                // Call the new displayCalendar method for individual course
                CalendarView.displayCalendar(history, selectedCourse.getCourseCode(), calendarStartDate,
                        calendarEndDate);
                System.out.println(); // Add space after calendar
            } else {
                System.out.println(ConsoleColors.RED + "Invalid course choice." + ConsoleColors.RESET);
                System.out.println();
            }
        } else {
            System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
            Thread.sleep(1500);
        }
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void removeAttendanceRecord() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Remove Attendance Record");
        Utils.printDigitalClock(); // Add digital clock
        System.out.println();

        Semester currentSemester = dataStorage.getCurrentSemester();
        if (currentSemester == null) {
            System.out.println(ConsoleColors.RED + "No current semester is set. Please set a current semester first." + ConsoleColors.RESET);
            Thread.sleep(1500);
            return;
        }

        List<ClassAttendance> semesterAttendance = dataStorage.getClassAttendances().stream()
                .filter(ca -> ca.getSemester() != null && ca.getSemester().equals(currentSemester))
                .sorted(Comparator.comparing(ClassAttendance::getDate, Comparator.nullsFirst(Comparator.naturalOrder()))
                        .thenComparing(ClassAttendance::getTime, Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList());

        if (semesterAttendance.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No attendance records found for the current semester (" + currentSemester.getDisplayValue() + ") to remove." + ConsoleColors.RESET);
            System.out.println();
            Thread.sleep(1500);
            return;
        }

        System.out.println(ConsoleColors.YELLOW + "Attendance Records for " + currentSemester.getDisplayValue() + ":" + ConsoleColors.RESET);
        System.out.println();

        LocalDate lastDisplayedDate = null;
        for (int i = 0; i < semesterAttendance.size(); i++) {
            ClassAttendance record = semesterAttendance.get(i);
            if (!record.getDate().equals(lastDisplayedDate)) {
                System.out.println(ConsoleColors.CYAN_BOLD + "  " + Utils.formatDateWithDay(record.getDate()) + ConsoleColors.RESET);
                lastDisplayedDate = record.getDate();
            }
            System.out.println(String.format("%s    %2d. %-10s (%-15s) at %-10s - %s%s",
                    ConsoleColors.WHITE, (i + 1), record.getCourseCode(), record.getTeacherName(),
                    Utils.formatTime(record.getTime()), (record.isPresent() ? ConsoleColors.GREEN + "Present" : ConsoleColors.RED + "Absent"), ConsoleColors.RESET));
        }
        System.out.println();
        Utils.printFooter();
        System.out.println();
        System.out.print(ConsoleColors.YELLOW
                + "Enter the number of the record to remove, 'Ad' to delete ALL in this semester, or '0' to cancel: "
                + ConsoleColors.RESET);
        String input = scanner.nextLine().trim();
        System.out.println();

        if (input.equalsIgnoreCase("0"))
            return;

        if (input.equals("Ad")) { // Case-sensitive check for "Ad"
            System.out.print(ConsoleColors.RED + "Are you sure you want to delete ALL attendance records for " + currentSemester.getDisplayValue() + "? (Yes/No): "
                    + ConsoleColors.RESET);
            String confirmation = scanner.nextLine().trim();
            System.out.println();

            if (confirmation.equalsIgnoreCase("Yes") || confirmation.equalsIgnoreCase("y")) {
                logAction("Cleared ALL attendance records for semester: " + currentSemester.getDisplayValue());
                attendanceService.clearSemesterAttendance(currentSemester); // New method to clear semester-specific attendance
            } else {
                System.out.println(ConsoleColors.YELLOW + "Deletion cancelled." + ConsoleColors.RESET);
            }
        } else {
            try {
                int recordChoice = Integer.parseInt(input);
                if (recordChoice > 0 && recordChoice <= semesterAttendance.size()) {
                    ClassAttendance recordToRemove = semesterAttendance.get(recordChoice - 1);
                    logAction("Removed attendance for " + recordToRemove.getCourseCode() + " on " + recordToRemove.getDate() + " from semester " + recordToRemove.getSemester().getDisplayValue());
                    attendanceService.removeAttendance(recordToRemove);
                    updateCourseAttendanceData(recordToRemove.getCourseCode(), recordToRemove.getSemester());
                } else {
                    System.out.println(ConsoleColors.RED + "Invalid record choice." + ConsoleColors.RESET);
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Invalid input. Please enter a number, 'Ad', or '0'."
                        + ConsoleColors.RESET);
                System.out.println();
            }
        }
        Thread.sleep(1500);
    }

    private static void viewReport() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Action Report Log");
        Utils.printDigitalClock();
        System.out.println();

        List<DataStorage.ActionEntry> history = dataStorage.getActionHistory(); // Changed to ActionEntry list

        if (history.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No actions have been recorded yet." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN + "--- Full Action Log ---" + ConsoleColors.RESET);
            // Iterate forward to show oldest actions first
            for (int i = 0; i < history.size(); i++) {
                DataStorage.ActionEntry entry = history.get(i);
                // Alternating color for the serial number
                String serialColor = (i % 2 == 0) ? ConsoleColors.CYAN : ConsoleColors.WHITE;
                String timeFormatted = entry.getTimestamp().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                String dateFormatted = entry.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yy (EEEE)"));
                
                System.out.println(String.format("%s%3d. %s%s%s", serialColor, (i + 1), ConsoleColors.YELLOW_BOLD, entry.getAction(), ConsoleColors.RESET));
                System.out.println(String.format("     %sTime: %s%s", ConsoleColors.PURPLE_BRIGHT, timeFormatted, ConsoleColors.RESET));
                System.out.println(String.format("     %sDate: %s%s", ConsoleColors.BLUE_BRIGHT, dateFormatted, ConsoleColors.RESET));
                System.out.println(); // Add an empty line between entries for better readability
            }
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void generatePdfReport() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Generate Reports (PDF)");
            Utils.printDigitalClock();
            System.out.println();

            System.out.println(ConsoleColors.YELLOW + "Select PDF Report Option:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  1. University, Routine, Cash, Flow & Data Report (Option 1 to 6)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. View Report" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. Flow" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. Data" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to Main Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            if (choice == 0) return;

            String dest = "Report_" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".pdf";

            try {
                switch (choice) {
                    case 1: // University, Routine, Cash, Flow & Data (Option 1 to 6)
                        dest = "University_Routine_Cash_Flow_Data_" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".pdf";
                        System.out.println(ConsoleColors.YELLOW + "Generating University, Routine, Cash, Flow & Data Report..." + ConsoleColors.RESET);
                        PdfGenerator.generateStudyAndRoutineReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH);
                        break;
                    case 2: // View Report (Action Report Log)
                        dest = "Action_Report_Log_" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".pdf";
                        System.out.println(ConsoleColors.YELLOW + "Generating Action Report Log..." + ConsoleColors.RESET);
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.ACTION_LOG, null, null);
                        break;
                    case 3: // Flow (Navigation Flow)
                        dest = "Navigation_Flow_History_" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".pdf";
                        System.out.println(ConsoleColors.YELLOW + "Generating Navigation Flow Report..." + ConsoleColors.RESET);
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.NAVIGATION_FLOW, null, null);
                        break;
                    case 4: // Data (Data JSON)
                        dest = "Data_Json_View_" + LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + ".pdf";
                        System.out.println(ConsoleColors.YELLOW + "Generating Data JSON View Report..." + ConsoleColors.RESET);
                        PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(),
                                dataStorage.getFaculty(), dataStorage.getStudentId(),
                                dataStorage.getRegistrationNumber(), dataStorage.getSession(), SIGNATURE_IMAGE_PATH,
                                ReportType.DATA_JSON_VIEW, null, null);
                        break;
                    default:
                        System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                        Thread.sleep(1000);
                        continue;
                }
                System.out.println(ConsoleColors.GREEN_BOLD + "\n✅ PDF report generated successfully!" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.CYAN + "File saved as: " + ConsoleColors.WHITE_BOLD + dest + ConsoleColors.RESET);
                openPdf(dest);

            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "Error generating PDF report: " + e.getMessage() + ConsoleColors.RESET);
                e.printStackTrace();
            }
            
            System.out.println("\n" + ConsoleColors.PURPLE + "Press Enter to continue..." + ConsoleColors.RESET);
            scanner.nextLine();
        }
    }

    private static void viewFlow() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Navigation Flow History");

        List<DataStorage.NavigationEvent> navigationHistory = dataStorage.getNavigationHistory();

        if (navigationHistory.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No navigation history available yet." + ConsoleColors.RESET);
        } else {
            LocalDate lastDisplayedDate = null;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            for (DataStorage.NavigationEvent event : navigationHistory) {
                LocalDate currentDate = event.getTimestamp().toLocalDate();

                // Check for date change
                if (lastDisplayedDate == null || !currentDate.equals(lastDisplayedDate)) {
                    System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "\n--- Date: " + currentDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " ---" + ConsoleColors.RESET);
                    lastDisplayedDate = currentDate;
                }

                // Determine arrow and overall line color based on direction
                String arrow = "";
                String directionColor = ConsoleColors.RESET; // Color for the entire line (timestamp, menu name, indent)
                String arrowColor = ConsoleColors.RESET; // Color for the arrow itself
                String timeColor = ConsoleColors.WHITE_BOLD_BRIGHT; // Consistent color for timestamp

                if ("FORWARD".equals(event.getActionType())) {
                    arrow = "->";
                    directionColor = ConsoleColors.GREEN_BOLD;
                    arrowColor = ConsoleColors.GREEN; // Arrow is also green
                } else if ("BACKWARD".equals(event.getActionType())) {
                    arrow = "<-";
                    directionColor = ConsoleColors.RED_BOLD;
                    arrowColor = ConsoleColors.RED; // Arrow is also red
                } else {
                    arrow = "--"; // For initial or unknown actions
                    directionColor = ConsoleColors.YELLOW_BOLD;
                    arrowColor = ConsoleColors.YELLOW;
                }

                // Calculate indentation
                String indent = "  ".repeat(Math.max(0, event.getDepth()));

                // Print with direction-based color for timestamp and menu name, and arrow-specific color for arrow
                System.out.println(directionColor + indent + timeColor + "[" + event.getTimestamp().format(timeFormatter) + "] "
                                   + directionColor + event.getMenuName() + " " + arrowColor + arrow + ConsoleColors.RESET);
                Thread.sleep(30);
            }
        }

        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }



    private static void openPdf(String dest) {
        File pdfFile = new File(dest);
        if (!pdfFile.exists()) {
            System.out.println(ConsoleColors.RED + "PDF file not found: " + dest + ConsoleColors.RESET);
            return;
        }

        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("linux")) {
                // Assuming Termux on Android or other Linux with termux-open
                Process process = new ProcessBuilder("termux-open", dest).start();
                int exitCode = process.waitFor();
                if (exitCode == 0) {
                    System.out.println(ConsoleColors.GREEN + "Opened PDF using termux-open." + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + "termux-open command failed with exit code: " + exitCode + ConsoleColors.RESET);
                }
            } else if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                // Desktop environment (Windows, macOS, etc.)
                Desktop.getDesktop().open(pdfFile);
                System.out.println(ConsoleColors.GREEN + "Opened PDF using desktop application." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Could not open PDF. Your system does not support the required action." + ConsoleColors.RESET);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(ConsoleColors.RED + "Error opening PDF: " + e.getMessage() + ConsoleColors.RESET);
            //e.printStackTrace();
        }
    }
    
    private static void makeCurrentSemester() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Make Current Semester");
        Utils.printDigitalClock();
        System.out.println();

        System.out.println(ConsoleColors.YELLOW + "Select a semester to make it current:" + ConsoleColors.RESET);
        System.out.println();

        Semester[] semesters = Semester.values();
        for (int i = 0; i < semesters.length; i++) {
            Semester sem = semesters[i];
            String color = ConsoleColors.CYAN;
            if (dataStorage.getCurrentSemester() != null && dataStorage.getCurrentSemester().equals(sem)) {
                color = ConsoleColors.GREEN_BOLD; // Highlight current semester
            }
            System.out.println(color + "  " + (i + 1) + ". " + sem.getDisplayValue() + ConsoleColors.RESET);
        }
        System.out.println();
        System.out.println(ConsoleColors.RED + "  0. Cancel" + ConsoleColors.RESET);
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);

        int choice = getUserChoice();
        System.out.println();

        if (choice == 0)
            return;

        if (choice > 0 && choice <= semesters.length) { // Corrected from >
            Semester selectedSemester = semesters[choice - 1];
            dataStorage.setCurrentSemester(selectedSemester);
            logAction("Set current semester to: " + selectedSemester.getDisplayValue()); // Added logAction
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Current semester set to " + selectedSemester.getDisplayValue() 
                    + "." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }

    }

    private static void manageCgpaCalculation() throws InterruptedException {

        logNavigation("CGPA Calculation", "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("CGPA Calculation");
            Utils.printDigitalClock();
            System.out.println();

            System.out.println(ConsoleColors.CYAN + "  1. View Semester SGPA" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. View Overall CGPA" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  3. Clear Semester CGPA Data" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  4. Clear All CGPA Data" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to University Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:

                    logNavigation("View Semester SGPA", "FORWARD");
                    viewSemesterSgpa();
                    logNavigation("View Semester SGPA", "BACKWARD");
                    break;
                case 2:

                    logNavigation("View Overall CGPA", "FORWARD");
                    viewOverallCgpa();
                    logNavigation("View Overall CGPA", "BACKWARD");
                    break;
                case 3:

                    logNavigation("Clear Semester CGPA Data", "FORWARD");
                    clearSemesterCgpaData();
                    logNavigation("Clear Semester CGPA Data", "BACKWARD");
                    break;
                case 4:

                    logNavigation("Clear All CGPA Data", "FORWARD");
                    clearAllCgpaData();
                    logNavigation("Clear All CGPA Data", "BACKWARD");
                    break;
                case 0:

                    logNavigation("CGPA Calculation", "BACKWARD");
                    return;
                default:
                    logAction("Invalid CGPA Calculation choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void manageRegistrationFee() throws InterruptedException {

        logNavigation("Registration Fee Management", "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Registration Fee Management");
            Utils.printDigitalClock();
            System.out.println();

            System.out.println(ConsoleColors.CYAN + "  ➕ 1. Add New Registration Fee" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📝 2. Edit Registration Fee" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  🗑️ 3. Remove Registration Fee" + ConsoleColors.RESET);
            System.out
                    .println(ConsoleColors.CYAN + "  🗓️ 4. View Registration Fees for Semester" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📊 5. View Total Fees (Semester)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📈 6. View Overall Total Fees" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📄 7. Generate Semester Fee Report (PDF)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  📄 8. Generate Overall Fee Report (PDF)" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  ↩️ 0. Back to University Daily Execution" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:

                    logNavigation("Add New Registration Fee", "FORWARD");
                    addRegistrationFeeRecord();
                    logNavigation("Add New Registration Fee", "BACKWARD");
                    break;
                case 2:

                    logNavigation("Edit Registration Fee", "FORWARD");
                    editRegistrationFeeRecord();
                    logNavigation("Edit Registration Fee", "BACKWARD");
                    break;
                case 3:

                    logNavigation("Remove Registration Fee", "FORWARD");
                    removeRegistrationFeeRecord();
                    logNavigation("Remove Registration Fee", "BACKWARD");
                    break;
                case 4:

                    logNavigation("View Registration Fees for Semester", "FORWARD");
                    viewRegistrationFeesForSemester();
                    logNavigation("View Registration Fees for Semester", "BACKWARD");
                    break;
                case 5:

                    logNavigation("View Total Fees (Semester)", "FORWARD");
                    viewTotalFeesForSemester();
                    logNavigation("View Total Fees (Semester)", "BACKWARD");
                    break;
                case 6:

                    logNavigation("View Overall Total Fees", "FORWARD");
                    viewOverallTotalFees();
                    logNavigation("View Overall Total Fees", "BACKWARD");
                    break;
                case 7:

                    logNavigation("Generate Semester Fee Report (PDF)", "FORWARD");
                    generateSemesterFeePdfReport();
                    logNavigation("Generate Semester Fee Report (PDF)", "BACKWARD");
                    break;
                case 8:

                    logNavigation("Generate Overall Fee Report (PDF)", "FORWARD");
                    generateOverallFeePdfReport();
                    logNavigation("Generate Overall Fee Report (PDF)", "BACKWARD");
                    break;
                case 0:

                    logNavigation("Registration Fee Management", "BACKWARD");
                    return;
                default:
                    logAction("Invalid Registration Fee Management choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void addRegistrationFeeRecord() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Add New Registration Fee");
        Utils.printDigitalClock();
        System.out.println();

        Semester semester = selectSemesterForAction("add fee for");
        if (semester == null)
            return;

        RegistrationFee.FeeType feeType = selectFeeType();
        if (feeType == null)
            return;

        System.out.print(ConsoleColors.YELLOW + "Enter Description (e.g., Semester Fee, Club Membership): "
                + ConsoleColors.RESET);
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println(ConsoleColors.RED + "Description cannot be empty." + ConsoleColors.RESET);
            return;
        }

        double amount = -1;
        while (amount < 0) {
            try {
                System.out.print(ConsoleColors.YELLOW + "Enter Amount: " + ConsoleColors.RESET);
                amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount < 0) {
                    System.out.println(ConsoleColors.RED + "Amount cannot be negative." + ConsoleColors.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "Invalid input. Please enter a valid number for amount."
                        + ConsoleColors.RESET);
            }
        }

        registrationFeeService.addRegistrationFee(semester, feeType, description, amount);
        logAction("Added registration fee: " + description + " (Amount: " + amount + " BDT) for " + semester.getDisplayValue());
    }

    private static RegistrationFee.FeeType selectFeeType() {
        while (true) {
            System.out.println(ConsoleColors.YELLOW + "Select Fee Type:" + ConsoleColors.RESET);
            int i = 1;
            for (RegistrationFee.FeeType type : RegistrationFee.FeeType.values()) {
                System.out.println(ConsoleColors.CYAN + "  " + (i++) + ". " + type.name() + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.RED + "  0. Cancel" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);
            int choice = getUserChoice();

            if (choice == 0)
                return null;
            if (choice > 0 && choice <= RegistrationFee.FeeType.values().length) {
                return RegistrationFee.FeeType.values()[choice - 1];
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private static void editRegistrationFeeRecord() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Edit Registration Fee");
        Utils.printDigitalClock();
        System.out.println();

        Semester semester = selectSemesterForAction("edit fee for");
        if (semester == null)
            return;

        List<RegistrationFee> fees = registrationFeeService.getRegistrationFeesForSemester(semester);
        if (fees.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No registration fees to edit for " + semester.getDisplayValue() 
                    + "." + ConsoleColors.RESET);
            return;
        }

        System.out.println(ConsoleColors.YELLOW + "Select a fee record to edit:" + ConsoleColors.RESET);
        for (int i = 0; i < fees.size(); i++) {
            RegistrationFee fee = fees.get(i);
            System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + fee.toString() + ConsoleColors.RESET);
        }
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter choice (0 to cancel): " + ConsoleColors.RESET);
        int choice = getUserChoice();
        System.out.println();

        if (choice == 0)
            return;

        if (choice > 0 && choice <= fees.size()) {
            RegistrationFee feeToEdit = fees.get(choice - 1);

            RegistrationFee.FeeType newFeeType = selectFeeType();
            if (newFeeType == null)
                newFeeType = feeToEdit.getFeeType();

            System.out.print(ConsoleColors.YELLOW + "Enter New Description (current: " + feeToEdit.getDescription() 
                    + "): " + ConsoleColors.RESET);
            String newDescription = scanner.nextLine().trim();
            if (newDescription.isEmpty()) {
                newDescription = feeToEdit.getDescription(); // Keep old if empty
            }

            double newAmount = -1;
            while (newAmount < 0) {
                try {
                    System.out.print(ConsoleColors.YELLOW + "Enter New Amount (current: " + feeToEdit.getAmount() 
                            + "): " + ConsoleColors.RESET);
                    String amountStr = scanner.nextLine().trim();
                    if (amountStr.isEmpty()) {
                        newAmount = feeToEdit.getAmount(); // Keep old if empty
                        break;
                    }
                    newAmount = Double.parseDouble(amountStr);
                    if (newAmount < 0) {
                        System.out.println(ConsoleColors.RED + "Amount cannot be negative." + ConsoleColors.RESET);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(ConsoleColors.RED + "Invalid input. Please enter a valid number for amount."
                            + ConsoleColors.RESET);
                }
            }

            registrationFeeService.editRegistrationFee(semester, feeToEdit, newFeeType, newDescription, newAmount);
            logAction("Edited registration fee for " + semester.getDisplayValue() + ": '" + feeToEdit.getDescription() + "' (Amount: " + feeToEdit.getAmount() + " BDT) to '" + newDescription + "' (Amount: " + newAmount + " BDT)");
        } else {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }
    }

    private static void removeRegistrationFeeRecord() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Remove Registration Fee");
        Utils.printDigitalClock();
        System.out.println();

        Semester semester = selectSemesterForAction("remove fee from");
        if (semester == null)
            return;

        List<RegistrationFee> fees = registrationFeeService.getRegistrationFeesForSemester(semester);
        if (fees.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No registration fees to remove for " + semester.getDisplayValue() 
                    + "." + ConsoleColors.RESET);
            return;
        }

        System.out.println(ConsoleColors.YELLOW + "Select a fee record to remove (0 to cancel):" + ConsoleColors.RESET);
        for (int i = 0; i < fees.size(); i++) {
            RegistrationFee fee = fees.get(i);
            System.out.println(ConsoleColors.CYAN + "  " + (i + 1) + ". " + fee.toString() + ConsoleColors.RESET);
        }
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter choice (0 to cancel): " + ConsoleColors.RESET);
        int choice = getUserChoice();
        System.out.println();

        if (choice == 0)
            return;

        if (choice > 0 && choice <= fees.size()) {
            RegistrationFee feeToRemove = fees.get(choice - 1);
            registrationFeeService.removeRegistrationFee(semester, feeToRemove);
            logAction("Removed registration fee: '" + feeToRemove.getDescription() + "' (Amount: " + feeToRemove.getAmount() + " BDT) from " + semester.getDisplayValue());
        } else {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }
    }

    private static void viewRegistrationFeesForSemester() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("View Registration Fees for Semester");
        Utils.printDigitalClock();
        System.out.println();

        Semester semester = selectSemesterForAction("view fees for");
        if (semester == null)
            return;

        List<RegistrationFee> fees = registrationFeeService.getRegistrationFeesForSemester(semester);
        if (fees.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No registration fees found for " + semester.getDisplayValue() 
                    + "." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.CYAN_BOLD + "Registration Fees for " + semester.getDisplayValue() + ":"
                    + ConsoleColors.RESET);
            for (int i = 0; i < fees.size(); i++) {
                RegistrationFee fee = fees.get(i);
                System.out.println("  " + (i + 1) + ". " + fee.toString());
            }
            double totalFees = registrationFeeService.calculateTotalFeesForSemester(semester);
            System.out.println(ConsoleColors.GREEN_BOLD + "\nTotal Fees for " + semester.getDisplayValue() + ": "
                    + String.format("%.2f", totalFees) + " BDT" + ConsoleColors.RESET);
        }
        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void viewTotalFeesForSemester() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("View Total Fees for Semester");
        Utils.printDigitalClock();
        System.out.println();

        Semester semester = selectSemesterForAction("view total fees for");
        if (semester == null)
            return;

        double totalFees = registrationFeeService.calculateTotalFeesForSemester(semester);
        if (totalFees == 0.0) {
            System.out.println(ConsoleColors.YELLOW + "No registration fees found for " + semester.getDisplayValue() 
                    + "." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "Total Fees for " + semester.getDisplayValue() + ": "
                    + String.format("%.2f", totalFees) + " BDT" + ConsoleColors.RESET);
        }
        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void viewOverallTotalFees() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("View Overall Total Fees");
        Utils.printDigitalClock();
        System.out.println();

        double overallTotalFees = registrationFeeService.calculateOverallTotalFees();
        if (overallTotalFees == 0.0) {
            System.out.println(ConsoleColors.YELLOW + "No registration fees available for overall calculation."
                    + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD + "Overall Total Fees: "
                    + String.format("%.2f", overallTotalFees) + " BDT" + ConsoleColors.RESET);
        }
        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void printCourseDetailsTable(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses to display." + ConsoleColors.RESET);
            return;
        }

        // Print table header
        System.out.println(String.format(
                ConsoleColors.BLUE_BOLD + "%-12s | %-5s | %-5s | %-5s | %-5s | %-5s | %-7s | %-10s | %-7s | %-10s"
                        + ConsoleColors.RESET,
                "Course", "CrHr", "M", "A", "P", "F", "Total", "Attend.", "Grade", "Point"));
        System.out.println(ConsoleColors.BLUE_BOLD
                + "------------------------------------------------------------------------------------------------------------"
                + ConsoleColors.RESET);

        // Print course details
        for (Course course : courses) {
            String gradeColor = getGradeColor(course.getLetterGrade());
            String attendanceRatio = course.getAttendedClasses() + "/" + course.getTotalClasses();
            System.out.println(gradeColor + String.format(
                    "%-12s | %-5.1f | %-5.1f | %-5.1f | %-5.1f | %-5.1f | %-7.2f | %-10s | %-7s | %-10.2f",
                    course.getCourseCode(),
                    course.getCreditHours(),
                    course.getMidMarks(),
                    course.getAssignmentMarks(),
                    course.getAttendanceMarks(),
                    course.getFinalMarks(),
                    course.getTotalMarks(),
                    attendanceRatio,
                    course.getLetterGrade(),
                    course.getGradePoint()) + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.BLUE_BOLD
                + "------------------------------------------------------------------------------------------------------------"
                + ConsoleColors.RESET);
    }

    private static String getGradeColor(String grade) {
        if (grade == null) return ConsoleColors.RESET;
        switch (grade) {
            case "A+":
            case "A":
                return ConsoleColors.GREEN_BOLD_BRIGHT;
            case "A-":
                return ConsoleColors.GREEN_BOLD;
            case "B+":
                return ConsoleColors.CYAN_BOLD_BRIGHT;
            case "B":
                return ConsoleColors.CYAN_BOLD;
            case "B-":
                return ConsoleColors.CYAN;
            case "C+":
                return ConsoleColors.YELLOW_BOLD_BRIGHT;
            case "C":
                return ConsoleColors.YELLOW_BOLD;
            case "D":
                return ConsoleColors.RED_BOLD;
            case "F":
                return ConsoleColors.RED_BOLD_BRIGHT;
            default:
                return ConsoleColors.RESET;
        }
    }

    private static void viewSemesterSgpa() throws InterruptedException {
        Semester semester = selectSemesterForAction("view SGPA for");
        if (semester == null) {
            return;
        }

        Utils.clearConsole();
        Utils.printHeader("SGPA for " + semester.getDisplayValue());
        Utils.printDigitalClock();
        System.out.println();

        // Single call to get all data consistently
        SgpaResult result = cgpaService.getSgpaBreakdown(semester);
        double sgpa = result.sgpa();
        List<Course> courses = result.courses();

        // The console log is now inside getSgpaBreakdown, but we still print the table
        printCourseDetailsTable(courses);

        System.out.println();
        if (sgpa == 0.0 && courses.isEmpty()) {
            // This case is handled by the breakdown method, but as a fallback.
            System.out
                    .println(ConsoleColors.YELLOW + "Semester not configured or has no courses." + ConsoleColors.RESET);
        } else {
            System.out.println(
                    ConsoleColors.GREEN_BOLD + "Semester SGPA: " + String.format("%.3f", sgpa) + ConsoleColors.RESET);
            if (sgpa == 0.0) {
                System.out.println(ConsoleColors.YELLOW
                        + "(SGPA is 0. This might be because credit hours or marks for courses have not been set.)"
                        + ConsoleColors.RESET);
            }
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void viewOverallCgpa() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Overall CGPA Report");
        Utils.printDigitalClock();
        System.out.println();

        List<Semester> allSemesters = new ArrayList<>(dataStorage.getSemesterConfigurations().keySet());
        allSemesters.sort(Comparator.naturalOrder());

        boolean hasAnyConfigured = false;
        List<Semester> processedSemesters = new ArrayList<>();

        for (Semester semester : allSemesters) {
            processedSemesters.add(semester);
            SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
            if (semesterConfig != null && semesterConfig.isEntered() && !semesterConfig.getCourses().isEmpty()) {
                hasAnyConfigured = true;
                System.out.println(
                        ConsoleColors.CYAN_BOLD + "--- " + semester.getDisplayValue() + " ---" + ConsoleColors.RESET);

                SgpaResult sgpaResult = cgpaService.getSgpaBreakdown(semester);
                double sgpa = sgpaResult.sgpa();
                List<Course> courses = sgpaResult.courses();

                printCourseDetailsTable(courses);

                if (sgpa == 0.0) {
                    System.out.println(
                            ConsoleColors.YELLOW_BOLD + "SGPA: " + String.format("%.3f", sgpa) + ConsoleColors.RESET);
                } else {
                    System.out.println(
                            ConsoleColors.GREEN_BOLD + "SGPA: " + String.format("%.3f", sgpa) + ConsoleColors.RESET);
                }

                if (semester != Semester.L1S1) {
                    OverallCgpaResult cumulativeCgpaResult = cgpaService.calculateOverallCgpa(processedSemesters);
                    double cumulativeCgpa = cumulativeCgpaResult.overallCgpa();
                    if (cumulativeCgpa == 0.0) {
                        System.out.println(
                                ConsoleColors.YELLOW_BOLD + "Cumulative CGPA: " + String.format("%.3f", cumulativeCgpa) + ConsoleColors.RESET);
                    } else {
                        System.out.println(
                                ConsoleColors.GREEN_BOLD + "Cumulative CGPA: " + String.format("%.3f", cumulativeCgpa) + ConsoleColors.RESET);
                    }
                }
                System.out.println("\n"); // Add space between semesters
            }
        }

        if (!hasAnyConfigured) {
            System.out.println(
                    ConsoleColors.YELLOW + "No configured semesters with courses found." + ConsoleColors.RESET);
        } else {
            OverallCgpaResult overallCgpaResult = cgpaService.calculateOverallCgpa();
            System.out.println(ConsoleColors.BLUE_BOLD
                    + "------------------------------------------------------------------------------------------------------------"
                    + ConsoleColors.RESET);
            if (overallCgpaResult.overallCgpa() == 0.0) {
                System.out.println(ConsoleColors.YELLOW_BOLD + "Final Overall CGPA: "
                        + String.format("%.3f", overallCgpaResult.overallCgpa()) + " (Total Credit Hours: "
                        + String.format("%.1f", overallCgpaResult.totalCreditHours()) + ")" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW
                        + "(CGPA is 0. This might be because credit hours or marks for courses have not been set.)"
                        + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.GREEN_BOLD + "Final Overall CGPA: "
                        + String.format("%.3f", overallCgpaResult.overallCgpa()) + " (Total Credit Hours: "
                        + String.format("%.1f", overallCgpaResult.totalCreditHours()) + ")" + ConsoleColors.RESET);
            }
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void clearSemesterCgpaData() throws InterruptedException {
        Semester semester = selectSemesterForAction("clear CGPA data for");
        if (semester == null) {
            return;
        }
        cgpaService.clearSemesterCgpa(semester);
        logAction("Cleared CGPA data for semester: " + semester.getDisplayValue());
    }

    private static void clearAllCgpaData() throws InterruptedException {
        System.out.print(ConsoleColors.RED
                + "Are you sure you want to delete ALL CGPA data for all semesters? (Yes/No): " + ConsoleColors.RESET);
        String confirmation = scanner.nextLine().trim();
        if (confirmation.equalsIgnoreCase("Yes") || confirmation.equalsIgnoreCase("y")) {
            cgpaService.clearAllCgpaData();
            logAction("Cleared ALL CGPA data for all semesters.");
        } else {
            System.out.println(ConsoleColors.YELLOW + "Deletion cancelled." + ConsoleColors.RESET);
        }
    }

    private static void generateSemesterFeePdfReport() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Generate Semester Fee Report PDF");
        Utils.printDigitalClock();
        System.out.println();
        Semester semester = selectSemesterForAction("generate fee report for");
        if (semester == null)
            return;

        try {
            String dest = "Registration_Fee_Report_" + semester.name() + ".pdf";
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    SIGNATURE_IMAGE_PATH, ReportType.REGISTRATION_FEE_SEMESTER, semester, null);
            System.out.println(ConsoleColors.GREEN + "Semester Fee Report PDF generated successfully: " + dest
                    + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error generating Semester Fee Report PDF: " + e.getMessage()
                    + ConsoleColors.RESET);
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    private static void generateOverallFeePdfReport() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Generate Overall Fee Report PDF");
        Utils.printDigitalClock();
        System.out.println();

        try {
            String dest = "Overall_Registration_Fee_Report.pdf";
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    SIGNATURE_IMAGE_PATH, ReportType.REGISTRATION_FEE_OVERALL, null, null);
            System.out.println(ConsoleColors.GREEN + "Overall Fee Report PDF generated successfully: " + dest
                    + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error generating Overall Fee Report PDF: " + e.getMessage()
                    + ConsoleColors.RESET);
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    private static void manageSemesterDetails() throws InterruptedException {

        logNavigation("Semester Details", "FORWARD");
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Semester Details");
            Utils.printDigitalClock();
            System.out.println();
            System.out.println(ConsoleColors.CYAN + "  1. View Class Details" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.CYAN + "  2. View Marks Details" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "  0. Back to University Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1:

                    logNavigation("View Class Details", "FORWARD");
                    viewClassDetails();
                    logNavigation("View Class Details", "BACKWARD");
                    break;
                case 2:

                    logNavigation("View Marks Details", "FORWARD");
                    viewMarksDetails();
                    logNavigation("View Marks Details", "BACKWARD");
                    break;
                case 0:

                    logNavigation("Semester Details", "BACKWARD");
                    return;
                default:
                    logAction("Invalid Semester Details choice: " + choice);
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private static void viewClassDetails() throws InterruptedException {
        Semester semester = selectSemesterForAction("view class details for");
        if (semester == null) {
            return;
        }

        Utils.clearConsole();
        Utils.printHeader("Class Details for " + semester.getDisplayValue());
        Utils.printDigitalClock();
        System.out.println();

        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null || !semesterConfig.isEntered() || semesterConfig.getCourses().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses configured for this semester." + ConsoleColors.RESET);
        } else {
            List<Course> courses = cgpaService.getSemesterCgpa(semester); // Gets unique courses

            for (Course course : courses) {
                System.out.println(ConsoleColors.CYAN_BOLD + "Course: " + course.getCourseCode() + " - "
                        + course.getCourseTitle() + ConsoleColors.RESET);

                Map<String, Long> classesByTeacher = dataStorage.getClassAttendances().stream()
                        .filter(att -> att.getSemester() == semester
                                && att.getCourseCode().equalsIgnoreCase(course.getCourseCode()))
                        .collect(Collectors.groupingBy(ClassAttendance::getTeacherName, Collectors.counting()));

                if (classesByTeacher.isEmpty()) {
                    System.out.println("\t" + ConsoleColors.YELLOW + "No attendance records found for this course."
                            + ConsoleColors.RESET);
                } else {
                    for (Map.Entry<String, Long> entry : classesByTeacher.entrySet()) {
                        System.out.println("\t" + ConsoleColors.CYAN + entry.getKey() + ": " + entry.getValue() 
                                + " classes" + ConsoleColors.RESET);
                    }
                }
                // Get total classes from the already-updated course object
                int totalClasses = attendanceService.getTotalClassesForCourse(course.getCourseCode(), semester);
                System.out.println(
                        "\t" + ConsoleColors.GREEN_BOLD + "Total Classes: " + totalClasses + ConsoleColors.RESET);
                System.out.println();
            }
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static void viewMarksDetails() throws InterruptedException {
        Semester semester = selectSemesterForAction("view marks details for");
        if (semester == null)
            return;

        Utils.clearConsole();
        Utils.printHeader("Marks Details for " + semester.getDisplayValue());
        Utils.printDigitalClock();
        System.out.println();

        SemesterConfig semesterConfig = dataStorage.getSemesterConfigurations().get(semester);
        if (semesterConfig == null || !semesterConfig.isEntered() || semesterConfig.getCourses().isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No courses configured for this semester." + ConsoleColors.RESET);
        } else {
            List<Course> courses = cgpaService.getSemesterCgpa(semester); // Gets unique courses with updated marks
            printCourseDetailsTable(courses);
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private static String getTaskStatusColor(TaskStatus status) {
        switch (status) {
            case PENDING:
                return ConsoleColors.YELLOW;
            case COMPLETED:
                return ConsoleColors.GREEN;
            case INCOMPLETE:
                return ConsoleColors.RED;
            default:
                return ConsoleColors.RESET;
        }
    }
}