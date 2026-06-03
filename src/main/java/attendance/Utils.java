package attendance;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            // Handle exceptions
        }
    }

    public static void printHeader(String title) {
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "========================================" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "      " + title);
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "========================================" + ConsoleColors.RESET);
    }

    public static void printFooter() {
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "========================================" + ConsoleColors.RESET);
    }

    public static String formatTime(LocalTime time) {
        if (time == null) {
            return "No specific time";
        }
        return time.format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
    }

    public static String formatDate(LocalDate date) {
        if (date == null) {
            return "No specific date";
        }
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public static String getDayOfWeek(LocalDate date) {
        if (date == null) {
            return "No specific day";
        }
        return date.format(DateTimeFormatter.ofPattern("EEEE"));
    }

    public static String formatDateWithDay(LocalDate date) {
        if (date == null) {
            return "No specific date";
        }
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy (EEE)"));
    }

    public static void printDigitalClock() {
        System.out.println(ConsoleColors.BLUE_BOLD + "Current Time: " + formatTime(LocalTime.now()) + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BOLD + "Current Date: " + formatDateWithDay(LocalDate.now()) + ConsoleColors.RESET);
    }

    public static String formatDateAndDayWithColors(LocalDate date, String mainDateColor, String dayOfWeekColor) {
        if (date == null) {
            return "N/A";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern(" (EEE)");
        
        String formattedDate = date.format(dateFormatter);
        String formattedDay = date.format(dayFormatter);
        
        return mainDateColor + formattedDate + ConsoleColors.RESET + dayOfWeekColor + formattedDay + ConsoleColors.RESET;
    }
}
