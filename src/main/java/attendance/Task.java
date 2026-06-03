package attendance;

import java.time.LocalDate;
import java.time.LocalTime;

public class Task {
    private String taskName;
    private TaskCategory category;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private TaskStatus status;

    // Constructor
    public Task(String taskName, TaskCategory category, LocalTime startTime, LocalTime endTime, LocalDate date) {
        this.taskName = taskName;
        this.category = category;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.status = TaskStatus.PENDING; // Default status
    }

    // Getters and Setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{"
                + "taskName='" + taskName + "'" +
                ", category=" + category +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
