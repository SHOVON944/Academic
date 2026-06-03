package attendance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SemesterConfig {
    private boolean isEntered;
    private List<Course> courses;
    private LocalDate startDate;
    private LocalDate endDate;

    public SemesterConfig() {
        this.courses = new ArrayList<>();
        this.isEntered = false;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public boolean isEntered() {
        return isEntered;
    }

    public void setEntered(boolean entered) {
        isEntered = entered;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}