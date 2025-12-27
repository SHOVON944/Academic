class BankAccount {
    double balance;

    BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: $" + amount);
    }

    void withdraw(double amount) {
        if (balance >= amount) { 
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        }
    }

    void displayBalance(){
        System.out.println("Current balance: $" + balance);
    }
}

class SavingsAccount extends BankAccount{
    SavingsAccount(double initialBalance){
        super(initialBalance);
    }

    void withdraw(double amount){
        if(balance - amount >= 100){
            balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else{
            System.out.println("Withdrawal denied: Balance cannot fall below $100");
        }
    }
}

public class five {
    public static void main(String[] args) {
        SavingsAccount acc = new SavingsAccount(500.0);
        
        System.out.print("Current balance: ");
        acc.displayBalance();
        
        acc.deposit(200.0);
        System.out.print("Current balance: ");
        acc.displayBalance();
        
        acc.withdraw(550.0);
        System.out.print("Current balance: ");
        acc.displayBalance();
        
        acc.withdraw(550.0);
        System.out.print("Current balance: ");
        acc.displayBalance();
    }
}