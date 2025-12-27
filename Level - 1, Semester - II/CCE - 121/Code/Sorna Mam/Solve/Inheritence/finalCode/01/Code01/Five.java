/* 
 * Write a Java program to create a class known as "BankAccount" with methods called 
deposit() and withdraw(). Create a subclass called SavingsAccount that overrides the 
withdraw() method to prevent withdrawals if the account balance falls below one 
hundred. 
Expected Output: 
Current balance: $500.0 
Deposited: $200.0 
Current balance: $700.0 
Withdrew: $550.0 
Current balance: $150.0 
Withdrawal denied: Balance cannot fall below $100 
Current balance: $150.0 
 */

import java.util.Scanner;

class BankAccount{
    double balance;

    BankAccount(double initialBalance){
        this.balance = initialBalance;
    }
    void deposit(double amount){
        balance += amount;
        System.out.println("Deposited: $" + amount);   // note-> amount hobe kintu.
    }
    void withdraw(double amount){
    }
    void displayBalance(){
        System.out.println("Current Balance: " + balance);
    }
}

class SavingsAccount extends BankAccount{
    SavingsAccount(double initialBalance){
        super(initialBalance);  // this.balance = initialBalance;
    }
    void withdraw(double amount){
        if(balance - amount < 100){
            System.out.println("Withdrawal denied: Balance cannot fall below $100");
        } else{
            balance -= amount;
            System.out.println("Withdraw: $" + amount);   // note-> amount hobe
        }
    }
}

public class Five {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter initial balance: ");
        double iniB = scan.nextDouble();

        BankAccount acc = new SavingsAccount(iniB);
        acc.displayBalance();

        System.out.println("Enter deposit amount: ");
        double depo = scan.nextDouble();
        acc.deposit(depo);
        acc.displayBalance();

        System.out.println("Enter first withdraw balance: ");
        double w1 = scan.nextDouble();
        acc.withdraw(w1);
        acc.displayBalance();

        System.out.println("Enter second withdraw balance: ");
        double w2 = scan.nextDouble();
        acc.withdraw(w2);
        acc.displayBalance();

        scan.close();
    }
}
