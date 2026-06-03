package attendance;

public enum FundType {
    BKASH("bKash"),
    BANK("Bank"),
    NAGAD("Nagad"),
    ROCKET("Rocket"),
    CASH("Cash (Hand Cash)");

    private final String displayName;

    FundType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
