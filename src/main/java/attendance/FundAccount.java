package attendance;

public class FundAccount {
    private String name;
    private FundType type;
    private String bankName;
    private double balance;

    public FundAccount(String name, FundType type, String bankName, double balance) {
        this.name = name;
        this.type = type;
        this.bankName = bankName;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FundType getType() {
        return type;
    }

    public void setType(FundType type) {
        this.type = type;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addMoney(double amount) {
        this.balance += amount;
    }

    public void deleteMoney(double amount) {
        this.balance -= amount;
    }
}
