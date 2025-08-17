class BankAccount {
    String accountNumber;
    double balance;

    BankAccount(String acc, double bal) {
        accountNumber = acc;
        balance = bal;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid or insufficient funds.");
        }
    }

    void displayBalance() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Current Balance: " + balance);
    }
}

class Four {
    public static void main(String[] args) {
        BankAccount acc = new BankAccount("A123456789", 5000.0);
        acc.displayBalance();

        acc.deposit(1500.0);
        acc.withdraw(2000.0);
        acc.displayBalance();
    }
}