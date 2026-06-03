package attendance;

public enum Semester {
    L1S1("Level-1, Semester-I"),
    L1S2("Level-1, Semester-II"),
    L2S1("Level-2, Semester-I"),
    L2S2("Level-2, Semester-II"),
    L3S1("Level-3, Semester-I"),
    L3S2("Level-3, Semester-II"),
    L4S1("Level-4, Semester-I"),
    L4S2("Level-4, Semester-II");

    private final String displayValue;

    Semester(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
