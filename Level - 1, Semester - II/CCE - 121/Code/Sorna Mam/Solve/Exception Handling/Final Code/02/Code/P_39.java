/*
Online Banking with Multi-Level Exception Handling 
 Create a method performTransaction() that internally calls 
authenticateUser(), validateAccount(), and processTransaction(). 
 Each method may throw its own exception, and they should propagate up to main(). 
 Show how exception propagation is handled. 
*/

import java.util.*;

class AuthenticationException extends Exception {
    AuthenticationException(String message) {
        super(message);
    }
}

class AccountValidationException extends Exception {
    AccountValidationException(String message) {
        super(message);
    }
}

class TransactionProcessingException extends Exception {
    TransactionProcessingException(String message) {
        super(message);
    }
}

class OnlineBanking {

    void authenticateUser(String username, String password) throws AuthenticationException {
        if (!username.equals("user123") || !password.equals("pass123")) {
            throw new AuthenticationException("User authentication failed!");
        }
        System.out.println("User authenticated successfully.");
    }

    void validateAccount(int accountNo) throws AccountValidationException {
        if (accountNo <= 0) {
            throw new AccountValidationException("Invalid account number!");
        }
        System.out.println("Account validated successfully.");
    }

    void processTransaction(double amount) throws TransactionProcessingException {
        if (amount <= 0) {
            throw new TransactionProcessingException("Invalid transaction amount!");
        }
        if (amount > 5000) {
            throw new TransactionProcessingException("Transaction exceeds allowed limit!");
        }
        System.out.println("Transaction processed successfully: " + amount + " Tk");
    }

    void performTransaction(String user, String pass, int accNo, double amount)
            throws AuthenticationException, AccountValidationException, TransactionProcessingException {

        // Multi-level call chain (exception may come from any of these)
        authenticateUser(user, pass);
        validateAccount(accNo);
        processTransaction(amount);
    }
}

public class P_39 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OnlineBanking bank = new OnlineBanking();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter account number: ");
        int accNo = sc.nextInt();

        System.out.print("Enter transaction amount: ");
        double amount = sc.nextDouble();

        System.out.println("\n--- Starting Transaction ---");

        try {
            bank.performTransaction(username, password, accNo, amount);
            System.out.println("Transaction completed successfully!");
        } 
        catch (AuthenticationException e) {
            System.out.println("Authentication Error: " + e.getMessage());
        } 
        catch (AccountValidationException e) {
            System.out.println("Account Validation Error: " + e.getMessage());
        } 
        catch (TransactionProcessingException e) {
            System.out.println("Transaction Error: " + e.getMessage());
        } 
        finally {
            System.out.println("--- Transaction process ended ---");
        }

        sc.close();
    }
}
