package attendance;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CalendarView {

    public static void displayCalendar(List<ClassAttendance> attendanceHistory, String individualCourseCode, LocalDate startDate, LocalDate endDate) {
        LocalDate currentMonthStart = startDate.withDayOfMonth(1);
        LocalDate currentSystemDate = LocalDate.now();
        LocalDate displayUntilDate = currentSystemDate.withDayOfMonth(1).plusMonths(1); // Start of next month from current date

        LocalDate semesterEndAdjusted = endDate.withDayOfMonth(1).plusMonths(1); // Start of next month from semester end date

        // Display up to the earlier of (semester end + 1 month) or (current date + 1 month)
        LocalDate loopEndDate = (displayUntilDate.isBefore(semesterEndAdjusted) ? displayUntilDate : semesterEndAdjusted);

        while (currentMonthStart.isBefore(loopEndDate)) {
            YearMonth yearMonth = YearMonth.from(currentMonthStart);
            String monthAndYear = yearMonth.getMonth().name() + " " + yearMonth.getYear();
            int totalWidth = 40; // Desired total width for the header line
            int monthAndYearWidth = monthAndYear.length();
            int hyphenLength = (totalWidth - monthAndYearWidth) / 2;

            String leftHyphens = new String(new char[hyphenLength]).replace('\0', '-');
            String rightHyphens = new String(new char[hyphenLength]).replace('\0', '-');

            System.out.println(ConsoleColors.CYAN + leftHyphens + ConsoleColors.RESET + ConsoleColors.BLUE_BOLD + monthAndYear + ConsoleColors.RESET + ConsoleColors.CYAN + rightHyphens + ConsoleColors.RESET);

            // Print day names starting from Friday
            String[] dayNames = {"Fr", "Sa", "Su", "Mo", "Tu", "We", "Th"};
            System.out.print(ConsoleColors.CYAN);
            for (String dayName : dayNames) {
                System.out.print(String.format("%-5s", dayName));
            }
            System.out.println(ConsoleColors.RESET);

            // Determine the day of the week for the first day of the month (1-7, Monday-Sunday)
            int firstDayOfMonthValue = currentMonthStart.getDayOfWeek().getValue();
            // Adjust to start printing from Friday (index 0 for "Fr")
            int spaces = (firstDayOfMonthValue - 5 + 7) % 7;


            for (int i = 0; i < spaces; i++) {
                System.out.print("     ");
            }

            int daysInMonth = yearMonth.lengthOfMonth();
            for (int dayOfMonth = 1; dayOfMonth <= daysInMonth; dayOfMonth++) {
                LocalDate currentDate = currentMonthStart.withDayOfMonth(dayOfMonth);

                // Skip days outside the actual start and end date
                if (currentDate.isBefore(startDate) || currentDate.isAfter(endDate)) {
                    System.out.print("     ");
                } else {
                    System.out.print(String.format("%-5d", dayOfMonth));
                }

                if ((spaces + dayOfMonth) % 7 == 0) {
                    System.out.println(); // New line after every 7 days
                    // Now print attendance details for the entire week
                    printAttendanceDetailsForWeek(currentDate.minusDays(6), currentDate, attendanceHistory, individualCourseCode);
                    System.out.println();
                }
            }

            // Print attendance details for remaining days if the month didn't end on a Saturday
            if ((spaces + daysInMonth) % 7 != 0) {
                int remainingSpaces = 7 - ((spaces + daysInMonth) % 7);
                for (int i = 0; i < remainingSpaces; i++) {
                    System.out.print("     ");
                }
                System.out.println();
                // Print attendance details for the last partial week
                LocalDate lastDayPrinted = currentMonthStart.withDayOfMonth(daysInMonth);
                LocalDate startOfWeek = lastDayPrinted.minusDays((lastDayPrinted.getDayOfWeek().getValue() - 5 + 7) % 7); // Calculate Friday of that week
                printAttendanceDetailsForWeek(startOfWeek, lastDayPrinted, attendanceHistory, individualCourseCode);
                System.out.println();
            }

            currentMonthStart = currentMonthStart.plusMonths(1);
        }
    }

    private static int getVisibleLength(String text) {
        // This regex matches common ANSI escape codes
        String ansiRegex = "\u001B\\[[;\\d]*m";
        return text.replaceAll(ansiRegex, "").length();
    }

    private static String padRightWithAnsi(String text, int desiredLength) {
        int visibleLength = getVisibleLength(text);
        int paddingNeeded = desiredLength - visibleLength;
        if (paddingNeeded <= 0) {
            return text; // No padding needed or string is already longer
        }
        return text + " ".repeat(paddingNeeded);
    }

    private static void printAttendanceDetailsForWeek(LocalDate weekStart, LocalDate weekEnd, List<ClassAttendance> allAttendanceHistory, String individualCourseCode) {
        // Data structure to hold course codes for each day of the week
        // Outer list: 7 days of the week
        // Inner list: course codes for that specific day
        List<List<String>> dailyCourseCodes = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            dailyCourseCodes.add(new ArrayList<>());
        }

        LocalDate tempDate = weekStart;
        int maxCoursesPerDay = 0;

        for (int i = 0; i < 7; i++) { // Iterate through each day of the week
            LocalDate day = tempDate.plusDays(i);

            // Only process if the day is within the valid range (not dummy cells from initial padding)
            if (day.isAfter(LocalDate.of(1, 1, 1)) && day.isBefore(LocalDate.of(3000, 1, 1))) {
                List<ClassAttendance> dailyAttendance = allAttendanceHistory.stream()
                        .filter(ca -> ca.getDate().equals(day))
                        .sorted(Comparator.comparing(ClassAttendance::getTime)) // Sort by time
                        .collect(Collectors.toList());

                for (ClassAttendance ca : dailyAttendance) {
                    // Filter by individualCourseCode if it's an individual view
                    if (individualCourseCode == null || (ca.getCourseCode() != null && ca.getCourseCode().equals(individualCourseCode))) {
                        String courseCode = ca.getCourseCode();
                        String alphanumericPart = courseCode.split("-")[0]; // e.g., CCE from CCE-111
                        String numericPart = courseCode.split("-")[1]; // e.g., 111 from CCE-111
                        char lastDigit = numericPart.charAt(numericPart.length() - 1); // e.g., 1 from 111

                        String displayCode = alphanumericPart + lastDigit; // e.g., CCE1

                        String coloredDisplayCode;
                        if (ca.isPresent()) {
                            coloredDisplayCode = ConsoleColors.GREEN + displayCode + ConsoleColors.RESET;
                        } else {
                            coloredDisplayCode = ConsoleColors.RED + displayCode + ConsoleColors.RESET;
                        }
                        dailyCourseCodes.get(i).add(coloredDisplayCode);
                    }
                }
            }
            maxCoursesPerDay = Math.max(maxCoursesPerDay, dailyCourseCodes.get(i).size());
        }

        // Now print the collected course codes, ensuring vertical alignment
        for (int lineIndex = 0; lineIndex < maxCoursesPerDay; lineIndex++) {
            StringBuilder line = new StringBuilder();
            for (int dayIndex = 0; dayIndex < 7; dayIndex++) {
                List<String> coursesForDay = dailyCourseCodes.get(dayIndex);
                                                if (lineIndex < coursesForDay.size()) {
                                                    line.append(padRightWithAnsi(coursesForDay.get(lineIndex), 5));
                                                } else {
                                                    line.append(padRightWithAnsi("", 5));                 // Empty space for alignment
                }
            }
            System.out.println(line.toString());
        }
    }
}
