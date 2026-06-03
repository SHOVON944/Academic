package attendance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

public class DataStorage {
    private List<Course> courses;
    private List<Task> tasks;
    private List<ClassAttendance> classAttendances;
    private Map<Semester, SemesterConfig> semesterConfigurations;
    private Semester currentSemester;
    private Map<Semester, List<Course>> cgpaData;
    private List<DailyBuy> dailyBuys;
    private Map<Semester, List<RegistrationFee>> registrationFees;
    private List<FundAccount> fundAccounts; // New
    private List<FundTransaction> fundTransactions; // New

    private String studentName;
    private String faculty;
    private String studentId;
    private String registrationNumber;
    private String session;
    private List<ActionEntry> actionHistory;
    private List<NavigationEvent> navigationHistory; // Added for navigation flow

    public DataStorage() {
        this.courses = new ArrayList<>(); // This will be deprecated/migrated later
        this.tasks = new ArrayList<>();
        this.classAttendances = new ArrayList<>();
        this.semesterConfigurations = new HashMap<>();
        this.currentSemester = null; // No default current semester initially
        this.cgpaData = new HashMap<>();
        this.dailyBuys = new ArrayList<>();
        this.registrationFees = new HashMap<>();
        this.fundAccounts = new ArrayList<>(); // Initialize
        this.fundTransactions = new ArrayList<>(); // Initialize
        this.actionHistory = new ArrayList<>();
        this.navigationHistory = new ArrayList<>(); // Initialize navigation history

        this.studentName = null;
        this.faculty = null;
        this.studentId = null;
        this.registrationNumber = null;
        this.session = null;
    }

    // Getters and Setters
    public List<FundAccount> getFundAccounts() {
        return fundAccounts;
    }

    public void setFundAccounts(List<FundAccount> fundAccounts) {
        this.fundAccounts = fundAccounts;
    }

    public List<FundTransaction> getFundTransactions() {
        return fundTransactions;
    }

    public void setFundTransactions(List<FundTransaction> fundTransactions) {
        this.fundTransactions = fundTransactions;
    }
    public List<ActionEntry> getActionHistory() {
        return actionHistory;
    }

    public void setActionHistory(List<ActionEntry> actionHistory) {
        this.actionHistory = actionHistory;
    }

    public List<NavigationEvent> getNavigationHistory() {
        return navigationHistory;
    }

    public void setNavigationHistory(List<NavigationEvent> navigationHistory) {
        this.navigationHistory = navigationHistory;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<ClassAttendance> getClassAttendances() {
        return classAttendances;
    }

    public void setClassAttendances(List<ClassAttendance> classAttendances) {
        this.classAttendances = classAttendances;
    }

    public Map<Semester, SemesterConfig> getSemesterConfigurations() {
        return semesterConfigurations;
    }

    public void setSemesterConfigurations(Map<Semester, SemesterConfig> semesterConfigurations) {
        this.semesterConfigurations = semesterConfigurations;
    }

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Semester currentSemester) {
        this.currentSemester = currentSemester;
    }

    public Map<Semester, List<Course>> getCgpaData() {
        return cgpaData;
    }

    public void setCgpaData(Map<Semester, List<Course>> cgpaData) {
        this.cgpaData = cgpaData;
    }

    public List<DailyBuy> getDailyBuys() {
        return dailyBuys;
    }

    public void setDailyBuys(List<DailyBuy> dailyBuys) {
        this.dailyBuys = dailyBuys;
    }

    public Map<Semester, List<RegistrationFee>> getRegistrationFees() {
        return registrationFees;
    }

    public void setRegistrationFees(Map<Semester, List<RegistrationFee>> registrationFees) {
        this.registrationFees = registrationFees;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    // New static nested class to hold action and timestamp
    public static class ActionEntry {
        private String action;
        private LocalDateTime timestamp; // Timestamp of the action

        public ActionEntry(String action, LocalDateTime timestamp) {
            this.action = action;
            this.timestamp = timestamp;
        }

        // Getters for ActionEntry fields
        public String getAction() {
            return action;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }
    }

    // New static nested class to hold navigation events
    public static class NavigationEvent {
        private LocalDateTime timestamp;
        private String menuName;
        private String actionType; // e.g., "FORWARD", "BACKWARD"
        private int depth;

        public NavigationEvent(LocalDateTime timestamp, String menuName, String actionType, int depth) {
            this.timestamp = timestamp;
            this.menuName = menuName;
            this.actionType = actionType;
            this.depth = depth;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getMenuName() {
            return menuName;
        }

        public String getActionType() {
            return actionType;
        }

        public int getDepth() {
            return depth;
        }
    }
}
