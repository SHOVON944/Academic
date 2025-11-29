package Lab_Problem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Fiften {

    public static void displayCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy h:mm:ss");
        System.out.println("Current date and time: " + now.format(formatter));
    }

    public static void main(String[] args) {
        displayCurrentDateTime();
    }
}
