package attendance;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceService {
    private DataStorage dataStorage;
    private DataManager dataManager;

    public AttendanceService(DataStorage dataStorage, DataManager dataManager) {
        this.dataStorage = dataStorage;
        this.dataManager = dataManager;
    }

    public void markAttendance(String courseCode, String courseName, String teacherName, String topic, LocalDate date, LocalTime time, boolean present, boolean isExtraClass, boolean attendanceTaken, int attendanceSignatures, Semester semester) {
        ClassAttendance newAttendance = new ClassAttendance(courseCode, courseName, teacherName, topic, date, time, isExtraClass, semester);
        newAttendance.setPresent(present);
        newAttendance.setAttendanceTaken(attendanceTaken);
        newAttendance.setAttendanceSignatures(attendanceSignatures);
        dataStorage.getClassAttendances().add(newAttendance);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN + "Attendance for " + courseCode + " (" + teacherName + ") on " + Utils.formatDateWithDay(date) + " at " + Utils.formatTime(time) + " marked as " + (present ? "Present" : "Absent") + (isExtraClass ? " (Extra Class)" : "") + (topic != null && !topic.isEmpty() ? " Topic: " + topic : "") + "." + ConsoleColors.RESET);
    }

    public void markAttendance(String courseCode, String courseName, String teacherName, String topic, LocalDate date, LocalTime time, boolean present, boolean isExtraClass, Semester semester) {
        markAttendance(courseCode, courseName, teacherName, topic, date, time, present, isExtraClass, false, 0, semester);
    }

    public void markAttendance(String courseCode, String courseName, String teacherName, String topic, LocalDate date, LocalTime time, boolean present, boolean isExtraClass) {
        markAttendance(courseCode, courseName, teacherName, topic, date, time, present, isExtraClass, false, 0, dataStorage.getCurrentSemester());
    }

    public void updateTopic(ClassAttendance record, String newTopic) {
        record.setTopic(newTopic);
        dataManager.saveData(dataStorage);
    }

    public void updateOnlineStatus(ClassAttendance record, boolean isOnline) {
        record.setOnline(isOnline);
        dataManager.saveData(dataStorage);
    }

    public void updateAttendanceStatus(ClassAttendance record, boolean isPresent) {
        record.setPresent(isPresent);
        dataManager.saveData(dataStorage);
    }

    public void updateAttendanceSignatures(ClassAttendance record, int signatures) {
        record.setAttendanceSignatures(signatures);
        record.setAttendanceTaken(signatures > 0);
        dataManager.saveData(dataStorage);
    }

    public List<ClassAttendance> getAttendanceHistory(String courseCode, String teacherName) {
        return dataStorage.getClassAttendances().stream()
                .filter(att -> att.getCourseCode().equalsIgnoreCase(courseCode) && att.getTeacherName().equalsIgnoreCase(teacherName))
                .collect(Collectors.toList());
    }

    public List<ClassAttendance> getAttendanceForDate(LocalDate date) {
        return dataStorage.getClassAttendances().stream()
                .filter(att -> att.getDate().isEqual(date))
                .collect(Collectors.toList());
    }

    public void removeAttendance(String courseCode, String teacherName, LocalDate date) {
        List<ClassAttendance> toRemove = dataStorage.getClassAttendances().stream()
                .filter(att -> att.getCourseCode().equalsIgnoreCase(courseCode) &&
                               att.getTeacherName().equalsIgnoreCase(teacherName) &&
                               att.getDate().isEqual(date))
                .collect(Collectors.toList());

        if (!toRemove.isEmpty()) {
            dataStorage.getClassAttendances().removeAll(toRemove);
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Attendance for " + courseCode + " (" + teacherName + ") on " + Utils.formatDateWithDay(date) + " removed." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "No attendance found for " + courseCode + " (" + teacherName + ") on " + Utils.formatDateWithDay(date) + "." + ConsoleColors.RESET);
        }
    }

    public void removeAttendance(ClassAttendance attendance) {
        if (dataStorage.getClassAttendances().remove(attendance)) {
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Attendance record for " + attendance.getCourseCode() + " (" + attendance.getTeacherName() + ") on " + Utils.formatDateWithDay(attendance.getDate()) + " removed." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "Attendance record not found." + ConsoleColors.RESET);
        }
    }

    public void clearAllAttendance() {
        int count = dataStorage.getClassAttendances().size();
        if (count > 0) {
            dataStorage.getClassAttendances().clear();
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + count + " attendance records cleared successfully." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "No attendance records to clear." + ConsoleColors.RESET);
        }
    }

    public int getTotalClassesForCourse(String courseCode, Semester semester) {
        return (int) dataStorage.getClassAttendances().stream()
                .filter(att -> att.getSemester() == semester &&
                               att.getCourseCode().equalsIgnoreCase(courseCode))
                .mapToLong(att -> att.isExtraClass() ? 2 : 1)
                .sum();
    }

    public int getAttendedClassesForCourse(String courseCode, Semester semester) {
        return (int) dataStorage.getClassAttendances().stream()
                .filter(att -> att.getSemester() == semester &&
                               att.getCourseCode().equalsIgnoreCase(courseCode) &&
                               att.isPresent())
                .mapToLong(att -> att.isExtraClass() ? 2 : 1)
                .sum();
    }

    public void clearSemesterAttendance(Semester semester) {
        if (semester == null) {
            System.out.println(ConsoleColors.RED + "Cannot clear attendance for a null semester." + ConsoleColors.RESET);
            return;
        }

        List<ClassAttendance> toRemove = dataStorage.getClassAttendances().stream()
                .filter(att -> att.getSemester() != null && att.getSemester().equals(semester))
                .collect(Collectors.toList());

        if (!toRemove.isEmpty()) {
            dataStorage.getClassAttendances().removeAll(toRemove);
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + toRemove.size() + " attendance records for " + semester.getDisplayValue() + " cleared successfully." + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.YELLOW + "No attendance records found for " + semester.getDisplayValue() + " to clear." + ConsoleColors.RESET);
        }
    }
}