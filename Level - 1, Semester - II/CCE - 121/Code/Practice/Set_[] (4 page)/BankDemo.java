/*
Write a Java program to create an abstract class BankAccount with abstract methods deposit() and withdraw().
Create subclasses: SavingsAccount and CurrentAccount that extend the BankAccount class and implement the respective methods to handle deposits and withdrawals for each account type.
*/

import java.util.Scanner;

abstract class BankAccount {
    protected double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    abstract void deposit(double amount);
    abstract void withdraw(double amount);

    public void showBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount {

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " to Savings Account.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " from Savings Account.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}

class CurrentAccount extends BankAccount {

    public CurrentAccount(double initialBalance) {
        super(initialBalance);
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited " + amount + " to Current Account.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    void withdraw(double amount) {
        // Allow overdraft up to -1000 for Current Account
        if (amount > 0 && (balance - amount) >= -1000) {
            balance -= amount;
            System.out.println("Withdrawn " + amount + " from Current Account.");
        } else {
            System.out.println("Cannot withdraw beyond overdraft limit (-1000).");
        }
    }
}

public class BankDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create SavingsAccount
        System.out.print("Enter initial balance for Savings Account: ");
        double saBalance = sc.nextDouble();
        SavingsAccount sa = new SavingsAccount(saBalance);

        // Perform deposit and withdraw on SavingsAccount
        System.out.print("Enter deposit amount for Savings Account: ");
        sa.deposit(sc.nextDouble());
        System.out.print("Enter withdrawal amount for Savings Account: ");
        sa.withdraw(sc.nextDouble());
        sa.showBalance();

        System.out.println("\n--- Current Account ---");
        // Create CurrentAccount
        System.out.print("Enter initial balance for Current Account: ");
        double caBalance = sc.nextDouble();
        CurrentAccount ca = new CurrentAccount(caBalance);

        // Perform deposit and withdraw on CurrentAccount
        System.out.print("Enter deposit amount for Current Account: ");
        ca.deposit(sc.nextDouble());
        System.out.print("Enter withdrawal amount for Current Account: ");
        ca.withdraw(sc.nextDouble());
        ca.showBalance();

        sc.close();
    }
}
