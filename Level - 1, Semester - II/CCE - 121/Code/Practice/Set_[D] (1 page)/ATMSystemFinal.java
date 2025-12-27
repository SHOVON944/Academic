/*
Write a Java program to create a class called "Bank" with a collection of accounts and methods to add and remove accounts, and to deposit and withdraw money.
Also, define a class called "Account" to maintain account details of a particular customer.
Add some constraints like balance will not be zero or negative.

Create an Account class which has deposit() and withdraw() methods.
You should also make the program work as an ATM menu interface.
Keep account information as private instance variables.
*/

import java.util.*;

// 🔹 Account class: stores information of a single customer
class Account {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // Constructor
    public Account(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        if (balance <= 0) {
            throw new IllegalArgumentException("Initial balance cannot be zero or negative!");
        }
        this.balance = balance;
    }

    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    // 🔸 Deposit method
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Deposit must be greater than zero.");
            return;
        }
        balance += amount;
        System.out.println("✅ Deposit successful! Current balance: " + balance);
    }

    // 🔸 Withdraw method
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Withdrawal must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance!");
        } else if (balance - amount <= 0) {
            System.out.println("❌ Balance cannot become zero or negative!");
        } else {
            balance -= amount;
            System.out.println("💵 Withdrawal successful! Current balance: " + balance);
        }
    }

    // 🔹 toString method for readable output
    @Override
    public String toString() {
        return "Account No: " + accountNumber +
                ", Name: " + accountHolder +
                ", Balance: " + balance;
    }
}

// 🔹 Bank class: manages all accounts
class Bank {
    private ArrayList<Account> accounts = new ArrayList<>();

    // Add account
    public void addAccount(Account acc) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(acc.getAccountNumber())) {
                System.out.println("❌ This account number already exists!");
                return;
            }
        }
        accounts.add(acc);
        System.out.println("✅ New account added successfully!");
    }

    // Find account by account number
    public Account findAccount(String accNo) {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accNo)) {
                return a;
            }
        }
        return null;
    }

    // Remove account using String (account number)
    public void removeAccount(String accNo) {
        String accToRemove = null;

        // Step 1: find the account number
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accNo)) {
                accToRemove = a.getAccountNumber();
                break;
            }
        }

        // Step 2: if found, remove object from list
        if (accToRemove != null) {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getAccountNumber().equals(accToRemove)) {
                    accounts.remove(i);
                    System.out.println("🗑️ Account removed successfully!");
                    return;
                }
            }
        } else {
            System.out.println("❌ Account not found!");
        }
    }

    // Show all accounts
    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("🔍 No accounts found.");
        } else {
            System.out.println("\n---- All Accounts ----");
            for (Account a : accounts) {
                System.out.println(a); // calls toString()
            }
        }
    }
}

// 🔹 Main class: ATM menu interface
public class ATMSystemFinal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n===== 🏧 ATM Menu =====");
            System.out.println("1. Create new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Remove account");
            System.out.println("5. Show all accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // buffer clear

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accNo = sc.nextLine();
                    System.out.print("Enter account holder name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    double bal = sc.nextDouble();
                    try {
                        bank.addAccount(new Account(accNo, name, bal));
                    } catch (Exception e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLine();
                    Account acc1 = bank.findAccount(accNo);
                    if (acc1 != null) {
                        System.out.print("Enter deposit amount: ");
                        double dep = sc.nextDouble();
                        acc1.deposit(dep);
                    } else
                        System.out.println("❌ Account not found!");
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLine();
                    Account acc2 = bank.findAccount(accNo);
                    if (acc2 != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double wd = sc.nextDouble();
                        acc2.withdraw(wd);
                    } else
                        System.out.println("❌ Account not found!");
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    accNo = sc.nextLine();
                    bank.removeAccount(accNo);
                    break;

                case 5:
                    bank.showAllAccounts();
                    break;

                case 6:
                    System.out.println(" Thank you! See you again.");
                    sc.close();
                    return;

                default:
                    System.out.println("⚠️ Invalid input!");
            }
        }
    }
}
