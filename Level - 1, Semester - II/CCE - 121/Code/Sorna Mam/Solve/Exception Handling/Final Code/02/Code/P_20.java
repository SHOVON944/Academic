/*
 Banking ATM Simulation 
 ATM allows withdrawal only in multiples of 500. 
 If user requests an invalid amount → throw InvalidDenominationException. 
 If balance is insufficient → throw InsufficientFundsException. 
 Ensure ATM always prints “Thank you for using our service” using a finally block. 
*/


import java.util.Scanner;

class InvalidDenominationException extends Exception {
    InvalidDenominationException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

class ATM {
    private double balance;

    ATM(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InvalidDenominationException, InsufficientFundsException {
        if (amount % 500 != 0) {
            throw new InvalidDenominationException("Amount must be in multiples of 500!");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance. Your balance: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful. Remaining balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class P_20 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Initial Balance: ");
        double iniAmount = scan.nextDouble();
        ATM atm = new ATM(iniAmount);
        System.out.print("Enter amount to withdraw: ");
        double amount = scan.nextDouble();

        try {
            atm.withdraw(amount);
        } catch (InvalidDenominationException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Thank you for using our service");
        }

        scan.close();
    }
}
