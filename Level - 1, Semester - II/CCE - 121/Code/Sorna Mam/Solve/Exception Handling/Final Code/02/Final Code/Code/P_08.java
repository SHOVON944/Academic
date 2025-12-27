/*
Bank Transaction Simulation 
o Create a class BankAccount with a balance field. 
o Write a method withdraw(double amount) that throws 
InsufficientBalanceException if the withdrawal amount is greater than 
the balance. 
o Demonstrate exception handling when performing transactions. 
*/

import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Withdrawal failed! Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful! Remaining balance: " + balance);
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class P_08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount(5000.0);
        System.out.println("Current Balance: " + account.getBalance());
        try {
            System.out.print("Enter amount to withdraw: ");
            double amount = sc.nextDouble();
            account.withdraw(amount);
        } catch (InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input!");
        } finally {
            sc.close();
            System.out.println("Transaction process completed.");
        }
    }
}
