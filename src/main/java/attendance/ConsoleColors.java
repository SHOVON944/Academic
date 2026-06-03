package attendance;

public class ConsoleColors {
    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // BLACK
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String BLUE = "\033[0;34m";    // BLUE
    public static final String MAGENTA = "\033[38;5;165m"; // MAGENTA (256-color)
    public static final String PURPLE = "\033[0;35m";  // PURPLE
    public static final String CYAN = "\033[0;36m";    // CYAN
    public static final String WHITE = "\033[0;37m";   // WHITE
    public static final String ORANGE = "\033[0;38;5;208m"; // Orange (256-color)
    public static final String ORANGE_BOLD = "\033[1;38;5;208m"; // Orange Bold
    public static final String LIGHT_GRAY = "\033[0;38;5;248m"; // Light Gray (256-color)
    public static final String DARK_BLUE = "\033[0;38;5;20m"; // Dark Blue (256-color)
    public static final String DEEP_PURPLE = "\033[0;38;5;92m"; // Deep Purple (256-color)
    public static final String TEAL = "\033[0;38;5;30m"; // Teal (256-color)
    public static final String GOLD = "\033[0;38;5;214m"; // Gold (256-color) - Changed to a more vibrant gold
    public static final String GOLD_BOLD = "\033[1;38;5;214m"; // Gold Bold
    public static final String ROSE = "\033[0;38;5;204m"; // Rose (256-color)
    public static final String LIME_GREEN = "\033[0;38;5;121m"; // Lime Green (256-color) - Changed to a more vibrant lime green
    public static final String BRIGHT_MAGENTA = "\033[0;38;5;199m"; // Bright Magenta (256-color)
    public static final String GRAY = "\033[0;38;5;242m";    // GRAY (Darker Gray for line numbers)
    
    // JSON Specific Colors
    public static final String JSON_KEY = "\033[38;5;39m"; // Vibrant Blue for keys
    public static final String JSON_STRING_VALUE = "\033[38;5;121m"; // Bright Green for string values
    public static final String JSON_NUMBER_VALUE = "\033[38;5;214m"; // Rich Orange/Gold for number values
    public static final String JSON_BOOLEAN_VALUE = "\033[38;5;165m"; // Distinct Magenta/Purple for boolean values
    public static final String JSON_NULL_VALUE = "\033[38;5;196m"; // Bright Red for null values
    public static final String JSON_BRACKET_BRACE = "\033[38;5;248m"; // Light Gray for brackets/braces
    public static final String JSON_COMMA_COLON = "\033[38;5;250m"; // Slightly lighter gray for commas/colons
    public static final String JSON_LINE_NUMBER = "\033[38;5;242m"; // Darker gray for line numbers

    // New JSON specific colors for detailed formatting
    public static final String JSON_QUOTE_COLOR = "\033[38;5;247m"; // Light gray for quotes
    public static final String JSON_COLON_COLOR = "\033[38;5;251m"; // Slightly different gray for colon
    public static final String JSON_COMMA_COLOR = "\033[38;5;253m"; // Another light gray for comma

    // Specific colors for common JSON keys
    public static final String KEY_ITEM_NAME = "\033[38;5;33m";     // Blue
    public static final String KEY_PRICE = "\033[38;5;47m";         // Green
    public static final String KEY_DATE = "\033[38;5;220m";         // Yellow/Gold
    public static final String KEY_TIME = "\033[38;5;170m";         // Light Magenta
    public static final String KEY_STATUS = "\033[38;5;202m";       // Orange-Red
    public static final String KEY_COURSE_CODE = "\033[38;5;160m";    // Bright Red
    public static final String KEY_COURSE_NAME = "\033[38;5;108m";    // Light Green
    public static final String KEY_TEACHER_NAME = "\033[38;5;25m";    // Darker Blue
    public static final String KEY_PRESENT = "\033[38;5;141m";        // Medium Purple
    public static final String KEY_IS_EXTRA_CLASS = "\033[38;5;48m"; // Light Cyan
    public static final String KEY_SEMESTER = "\033[38;5;200m";       // Pink
    public static final String KEY_STUDIED = "\033[38;5;70m";         // Dark Green
    public static final String KEY_TIMESTAMP = "\033[38;5;111m";      // Sky Blue
    public static final String KEY_MENU_NAME = "\033[38;5;210m";      // Light Orange
    public static final String KEY_ACTION_TYPE = "\033[38;5;135m";    // Lavender
    public static final String KEY_DEPTH = "\033[38;5;172m";          // Dark Orange
    public static final String KEY_TASK_NAME = "\033[38;5;190m";      // Yellow-Green
    public static final String KEY_CATEGORY = "\033[38;5;93m";        // Dark Magenta
    public static final String KEY_START_TIME = "\033[38;5;86m";     // Spring Green
    public static final String KEY_END_TIME = "\033[38;5;184m";       // Cream
    public static final String KEY_STUDENT_NAME = "\033[38;5;228m";   // Light Yellow
    public static final String KEY_FACULTY = "\033[38;5;153m";        // Pale Green
    public static final String KEY_STUDENT_ID = "\033[38;5;219m";     // Light Pink
    public static final String KEY_REGISTRATION_NUMBER = "\033[38;5;207m"; // Hot Pink
    public static final String KEY_SESSION = "\033[38;5;38m";         // Dark Cyan

    // New constants for grade-related keys
    public static final String KEY_CREDIT_HOURS = "\033[38;5;166m";    // Orange
    public static final String KEY_MID_MARKS = "\033[38;5;120m";       // Greenish-Yellow
    public static final String KEY_FINAL_MARKS = "\033[38;5;198m";     // Light Red/Pink
    public static final String KEY_ASSIGNMENT_MARKS = "\033[38;5;34m"; // Dark Green
    public static final String KEY_ATTENDANCE_MARKS = "\033[38;5;203m";// Deep Orange
    public static final String KEY_TOTAL_MARKS = "\033[38;5;51m";      // Bright Cyan
    public static final String KEY_LETTER_GRADE = "\033[38;5;226m";    // Yellow
    public static final String KEY_GRADE_POINT = "\033[38;5;105m";     // Light Purple
    public static final String KEY_TOTAL_CLASSES = "\033[38;5;139m";   // Blue-Purple
    public static final String KEY_ATTENDED_CLASSES = "\033[38;5;40m"; // Dark Cyan

    // New constant for action key
    public static final String KEY_ACTION = "\033[38;5;189m"; // Light Blue/Cyan

    public static final String DEFAULT_JSON_KEY = "\033[38;5;39m";  // Vibrant Blue for other keys

    // Common color for all JSON values (string, number, boolean, null)
    public static final String JSON_COMMON_VALUE_COLOR = "\033[38;5;121m"; // Lime Green

    // A large pool of 256-colors for cycling through line numbers
    public static final String COLOR_CYCLE_1 = "\033[38;5;196m"; // Bright Red
    public static final String COLOR_CYCLE_2 = "\033[38;5;202m"; // Orange-Red
    public static final String COLOR_CYCLE_3 = "\033[38;5;208m"; // Orange
    public static final String COLOR_CYCLE_4 = "\033[38;5;214m"; // Gold
    public static final String COLOR_CYCLE_5 = "\033[38;5;220m"; // Yellow
    public static final String COLOR_CYCLE_6 = "\033[38;5;226m"; // Light Yellow
    public static final String COLOR_CYCLE_7 = "\033[38;5;190m"; // Yellow-Green
    public static final String COLOR_CYCLE_8 = "\033[38;5;154m"; // Green-Yellow
    public static final String COLOR_CYCLE_9 = "\033[38;5;118m"; // Lime Green
    public static final String COLOR_CYCLE_10 = "\033[38;5;82m"; // Bright Green
    public static final String COLOR_CYCLE_11 = "\033[38;5;46m"; // Sea Green
    public static final String COLOR_CYCLE_12 = "\033[38;5;47m"; // Dark Sea Green
    public static final String COLOR_CYCLE_13 = "\033[38;5;48m"; // Medium Spring Green
    public static final String COLOR_CYCLE_14 = "\033[38;5;49m"; // Turquoise
    public static final String COLOR_CYCLE_15 = "\033[38;5;50m"; // Light Blue-Green
    public static final String COLOR_CYCLE_16 = "\033[38;5;51m"; // Bright Cyan
    public static final String COLOR_CYCLE_17 = "\033[38;5;39m"; // Blue
    public static final String COLOR_CYCLE_18 = "\033[38;5;27m"; // Dark Blue
    public static final String COLOR_CYCLE_19 = "\033[38;5;63m"; // Deep Blue
    public static final String COLOR_CYCLE_20 = "\033[38;5;57m"; // Indigo
    public static final String COLOR_CYCLE_21 = "\033[38;5;93m"; // Dark Magenta
    public static final String COLOR_CYCLE_22 = "\033[38;5;129m"; // Plum
    public static final String COLOR_CYCLE_23 = "\033[38;5;165m"; // Medium Violet
    public static final String COLOR_CYCLE_24 = "\033[38;5;201m"; // Hot Pink
    public static final String COLOR_CYCLE_25 = "\033[38;5;205m"; // Light Crimson
    public static final String COLOR_CYCLE_26 = "\033[38;5;135m"; // Lavender
    public static final String COLOR_CYCLE_27 = "\033[38;5;177m"; // Pale Violet Red
    public static final String COLOR_CYCLE_28 = "\033[38;5;213m"; // Light Coral
    public static final String COLOR_CYCLE_29 = "\033[38;5;219m"; // Pink
    public static final String COLOR_CYCLE_30 = "\033[38;5;225m"; // Pale Goldenrod
    public static final String COLOR_CYCLE_31 = "\033[38;5;229m"; // Misty Rose
    public static final String COLOR_CYCLE_32 = "\033[38;5;231m"; // White
    public static final String COLOR_CYCLE_33 = "\033[38;5;240m"; // Dark Gray
    public static final String COLOR_CYCLE_34 = "\033[38;5;245m"; // Gray
    public static final String COLOR_CYCLE_35 = "\033[38;5;250m"; // Light Gray
    public static final String COLOR_CYCLE_36 = "\033[38;5;255m"; // Bright White

    // Array to hold the cycle colors
    public static final String[] LINE_NUMBER_COLORS = {
        COLOR_CYCLE_1, COLOR_CYCLE_2, COLOR_CYCLE_3, COLOR_CYCLE_4, COLOR_CYCLE_5,
        COLOR_CYCLE_6, COLOR_CYCLE_7, COLOR_CYCLE_8, COLOR_CYCLE_9, COLOR_CYCLE_10,
        COLOR_CYCLE_11, COLOR_CYCLE_12, COLOR_CYCLE_13, COLOR_CYCLE_14, COLOR_CYCLE_15,
        COLOR_CYCLE_16, COLOR_CYCLE_17, COLOR_CYCLE_18, COLOR_CYCLE_19, COLOR_CYCLE_20,
        COLOR_CYCLE_21, COLOR_CYCLE_22, COLOR_CYCLE_23, COLOR_CYCLE_24, COLOR_CYCLE_25,
        COLOR_CYCLE_26, COLOR_CYCLE_27, COLOR_CYCLE_28, COLOR_CYCLE_29, COLOR_CYCLE_30,
        COLOR_CYCLE_31, COLOR_CYCLE_32, COLOR_CYCLE_33, COLOR_CYCLE_34, COLOR_CYCLE_35,
        COLOR_CYCLE_36
    };

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED_BOLD = "\033[1;31m";    // RED
    public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
    public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
    public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
    public static final String RED_UNDERLINED = "\033[4;31m";    // RED
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
    public static final String RED_BACKGROUND = "\033[41m";    // RED
    public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
    public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
    public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
    public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
    public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
    public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
    public static final String RED_BRIGHT = "\033[0;91m";    // RED
    public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
    public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
    public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE
    public static final String MAGENTA_BRIGHT = "\033[0;95m"; // MAGENTA

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";   // BLUE
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m"; // PURPLE
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";   // CYAN
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE
    public static final String MAGENTA_BOLD_BRIGHT = "\033[1;95m"; // MAGENTA

    // High Intensity backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
}
