package attendance;

import java.time.LocalDateTime;

public class FundTransaction {
    private String accountName;
    private FundType accountType; // Added
    private String bankName; // Added
    private double amount;
    private String type; // e.g., "ADD", "DELETE"
    private LocalDateTime timestamp;
    private String note;

    public FundTransaction(String accountName, FundType accountType, String bankName, double amount, String type, String note) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.bankName = bankName;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.note = note;
    }

    public String getAccountName() {
        return accountName;
    }

    public FundType getAccountType() {
        return accountType;
    }

    public String getBankName() {
        return bankName;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getNote() {
        return note;
    }
}
