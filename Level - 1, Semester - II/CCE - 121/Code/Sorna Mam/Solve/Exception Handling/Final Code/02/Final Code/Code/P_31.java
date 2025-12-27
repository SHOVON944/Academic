/*
31. Banking Loan Application 
 A method applyForLoan(double amount, int creditScore) throws: 
o LowCreditScoreException if creditScore < 600. 
o InvalidLoanAmountException if amount ≤ 0 or amount > max loan limit. 
*/

import java.util.Scanner;

class LowCreditScoreException extends Exception {
    LowCreditScoreException(String message) {
        super(message);
    }
}

class InvalidLoanAmountException extends Exception {
    InvalidLoanAmountException(String message) {
        super(message);
    }
}

class Bank {
    private double maxLoanLimit = 1000000;

    void applyForLoan(double amount, int creditScore)
            throws LowCreditScoreException, InvalidLoanAmountException {

        if (creditScore < 600) {
            throw new LowCreditScoreException("Credit score too low! Minimum 600 required.");
        }

        if (amount <= 0) {
            throw new InvalidLoanAmountException("Loan amount must be positive!");
        } else if (amount > maxLoanLimit) {
            throw new InvalidLoanAmountException("Loan amount exceeds maximum limit!");
        }

        System.out.println("Loan Approved! Amount: " + amount + " | Credit Score: " + creditScore);
    }
}

public class P_31 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        try {
            System.out.print("Enter loan amount: ");
            double amount = sc.nextDouble();

            System.out.print("Enter credit score: ");
            int score = sc.nextInt();

            bank.applyForLoan(amount, score);
        } 
        catch (LowCreditScoreException e) {
            System.out.println("Loan Denied: " + e.getMessage());
        } 
        catch (InvalidLoanAmountException e) {
            System.out.println("Loan Denied: " + e.getMessage());
        } 
        finally {
            System.out.println("Thank you for using our banking system!");
        }

        sc.close();
    }
}
