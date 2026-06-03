package attendance;

import java.time.LocalTime;

public class AnalogClock {

    public static void drawClock() {
        LocalTime now = LocalTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        // Convert to 12-hour format
        hour = hour % 12;

        double hourAngle = (hour + minute / 60.0) * 30;
        double minuteAngle = minute * 6;
        double secondAngle = second * 6;

        int size = 20;
        char[][] grid = new char[size][size];

        // Initialize grid with spaces
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = ' ';
            }
        }

        // Draw clock face
        int centerX = size / 2;
        int centerY = size / 2;
        int radius = size / 2 - 1;

        for (double angle = 0; angle < 360; angle += 1) {
            int x = (int) (centerX + radius * Math.cos(Math.toRadians(angle)));
            int y = (int) (centerY + radius * Math.sin(Math.toRadians(angle)));
            if (x >= 0 && x < size && y >= 0 && y < size) {
                grid[y][x] = '.';
            }
        }

        // Draw hands
        drawHand(grid, centerX, centerY, hourAngle, radius - 4, 'H');
        drawHand(grid, centerX, centerY, minuteAngle, radius - 2, 'M');
        drawHand(grid, centerX, centerY, secondAngle, radius - 1, 'S');

        // Print the clock
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = grid[i][j];
                if (c == 'H') {
                    System.out.print(ConsoleColors.RED_BOLD + c + ConsoleColors.RESET);
                } else if (c == 'M') {
                    System.out.print(ConsoleColors.GREEN_BOLD + c + ConsoleColors.RESET);
                } else if (c == 'S') {
                    System.out.print(ConsoleColors.BLUE_BOLD + c + ConsoleColors.RESET);
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    private static void drawHand(char[][] grid, int centerX, int centerY, double angle, int length, char handChar) {
        // Correcting the angle because 0 degrees is at 3 o'clock, but we want it at 12 o'clock
        angle -= 90;
        for (int i = 0; i < length; i++) {
            int x = (int) (centerX + i * Math.cos(Math.toRadians(angle)));
            int y = (int) (centerY + i * Math.sin(Math.toRadians(angle)));
            if (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length) {
                grid[y][x] = handChar;
            }
        }
    }
}
