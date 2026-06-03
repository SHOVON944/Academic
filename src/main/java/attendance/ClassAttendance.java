package attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClassAttendance {
    private String courseCode;
    private String courseName;
    private String teacherName;
    private String topic; // New field
    private LocalDate date;
    private LocalTime time;
    private boolean present;
    private boolean isOnline; // New field
    private boolean isExtraClass; // New field
    private boolean attendanceTaken;
    private int attendanceSignatures;
    private Semester semester;
    private boolean studied;
    private LocalDate studiedDate;
    private LocalTime studiedTime;
    private java.util.List<String> materials;

    public ClassAttendance(String courseCode, String courseName, String teacherName, String topic, LocalDate date, LocalTime time, boolean isExtraClass, Semester semester) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.topic = topic;
        this.date = date;
        this.time = time;
        this.present = false; // Default to not present
        this.isOnline = false; // Default to offline
        this.isExtraClass = isExtraClass; // Initialize new field
        this.attendanceTaken = false;
        this.attendanceSignatures = 0;
        this.semester = semester;
        this.studied = false;
        this.studiedDate = null;
        this.studiedTime = null;
        this.materials = new java.util.ArrayList<>();
    }

    // Getters and Setters
    public java.util.List<String> getMaterials() {
        if (materials == null) {
            materials = new java.util.ArrayList<>();
        }
        return materials;
    }

    public void setMaterials(java.util.List<String> materials) {
        this.materials = materials;
    }

    public void addMaterial(String material) {
        getMaterials().add(material);
    }
    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Semester getSemester() {
        return semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public boolean isExtraClass() {
        return isExtraClass;
    }

    public void setExtraClass(boolean extraClass) {
        isExtraClass = extraClass;
    }

    public boolean isAttendanceTaken() {
        return attendanceTaken;
    }

    public void setAttendanceTaken(boolean attendanceTaken) {
        this.attendanceTaken = attendanceTaken;
    }

    public int getAttendanceSignatures() {
        return attendanceSignatures;
    }

    public void setAttendanceSignatures(int attendanceSignatures) {
        this.attendanceSignatures = attendanceSignatures;
    }

    public boolean isStudied() {
        return studied;
    }

    public void setStudied(boolean studied) {
        this.studied = studied;
    }


    public LocalDate getStudiedDate() {
        return studiedDate;
    }

    public void setStudiedDate(LocalDate studiedDate) {
        this.studiedDate = studiedDate;
    }

    public LocalTime getStudiedTime() {
        return studiedTime;
    }

    public void setStudiedTime(LocalTime studiedTime) {
        this.studiedTime = studiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassAttendance that = (ClassAttendance) o;
        return courseCode.equals(that.courseCode) &&
               teacherName.equals(that.teacherName) &&
               date.equals(that.date) &&
               time.equals(that.time) &&
               semester == that.semester;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(courseCode, teacherName, date, time, semester);
    }

    @Override
    public String toString() {
        return "ClassAttendance{" +
                "courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", topic='" + topic + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", present=" + present +
                ", isExtraClass=" + isExtraClass +
                ", studied=" + studied +
                ", studiedDate=" + studiedDate +
                ", studiedTime=" + studiedTime +
                '}';
    }
}
