package utils;

public class ColorPrint {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_MAGENTA = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static final String[] COLORS = {
            ANSI_RED,
            ANSI_GREEN,
            ANSI_YELLOW,
            ANSI_BLUE,
            ANSI_MAGENTA,
            ANSI_CYAN
    };

    public static void printRed(String text) {
        System.out.println(ANSI_RED + text + ANSI_RESET);
    }

    public static void printGreen(String text) {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }

    public static void printYellow(String text) {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }

    public static void printBlue(String text) {
        System.out.println(ANSI_BLUE + text + ANSI_RESET);
    }

    public static void printCyan(String text) {
        System.out.println(ANSI_CYAN + text + ANSI_RESET);
    }

    public static void printMagenta(String text) {
        System.out.println(ANSI_MAGENTA + text + ANSI_RESET);
    }

    public static void printCentered(String text, String colorCode, int consoleWidth) {
        String plainText = text.replaceAll("\u001B\\[[;\\\\d]*m", "");
        int padding = (consoleWidth - plainText.length()) / 2;
        String paddedText = " ".repeat(Math.max(0, padding)) + text;
        System.out.println(colorCode + paddedText + ANSI_RESET);
    }

    public static void printCentered(String text, int consoleWidth) {
        String plainText = text.replaceAll("\u001B\\[[;\\\\d]*m", "");
        int padding = (consoleWidth - plainText.length()) / 2;
        String paddedText = " ".repeat(Math.max(0, padding)) + text;
        System.out.println(paddedText);
    }

    public static String getCenteredString(String text, int consoleWidth) {
        String plainText = text.replaceAll("\u001B\\[[;\\\\d]*m", "");
        int padding = (consoleWidth - plainText.length()) / 2;
        String paddedText = " ".repeat(Math.max(0, padding)) + text;
        return paddedText;
    }
}