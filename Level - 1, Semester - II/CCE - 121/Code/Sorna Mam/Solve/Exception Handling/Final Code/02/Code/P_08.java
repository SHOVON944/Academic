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
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}

class BankAccount{
    private double balance;

    BankAccount(double balance) {
        this.balance = balance;
    }

    void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance)
            throw new InsufficientBalanceException("Insufficient balance.");
        balance -= amount;
    }

    double getBalance(){
        return balance;
    }
}

public class P_08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter starting balance: ");
        double bal = sc.nextDouble();
        BankAccount acc = new BankAccount(bal);
        System.out.print("Enter withdrawal amount: ");
        double w = sc.nextDouble();
        try {
            acc.withdraw(w);
            System.out.println("Withdrawal successful. New balance: " + acc.getBalance());
        } catch (InsufficientBalanceException e) {
            System.out.println("Transaction failed: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
