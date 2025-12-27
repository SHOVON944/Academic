/*
Banking Loan Application 
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

class BankP_31 {
    double maxLoanLimit;

    BankP_31(double maxLoanLimit) {
        this.maxLoanLimit = maxLoanLimit;
    }

    public void applyForLoan(double amount, int creditScore) 
            throws LowCreditScoreException, InvalidLoanAmountException {
        if (amount <= 0 || amount > maxLoanLimit) 
            throw new InvalidLoanAmountException("Loan amount must be > 0 and <= " + maxLoanLimit);
        if (creditScore < 600) 
            throw new LowCreditScoreException("Credit score too low for loan.");
        System.out.println("Loan of $" + amount + " approved for credit score " + creditScore);
    }
}

public class P_31 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BankP_31 bank = new BankP_31(50000);  // Maximum loan limit $50,000

        while (true) {
            System.out.print("\nEnter loan amount (or 0 to exit): ");
            double amount;
            try {
                amount = Double.parseDouble(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
                continue;
            }

            if (amount == 0) break;

            System.out.print("Enter credit score: ");
            int creditScore;
            try {
                creditScore = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
                continue;
            }

            try {
                bank.applyForLoan(amount, creditScore);
            } catch (InvalidLoanAmountException e) {
                System.out.println("Cannot apply: " + e.getMessage());
            } catch (LowCreditScoreException e) {
                System.out.println("Cannot apply: " + e.getMessage());
            }
        }

        System.out.println("Thank you for using Banking Loan Application!");
        scan.close();
    }
}
