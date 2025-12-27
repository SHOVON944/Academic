/*
Banking Multi-Account Transfer 
 Create a method transfer(Account from, Account to, double 
amount) that throws: 
o InsufficientFundsException if sender doesn’t have enough money. 
o InvalidTransferAmountException if amount ≤ 0. 
 Ensure transactions are atomic (if exception occurs, rollback transfer). 
*/


import java.util.*;

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidTransferAmountException extends Exception {
    InvalidTransferAmountException(String message) {
        super(message);
    }
}

class Account {
    String name;
    double balance;

    Account(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void display() {
        System.out.println(name + " -> Balance: " + balance + " Tk");
    }
}

class Bank {
    ArrayList<Account> accounts = new ArrayList<>();    //....

    void addAccount(Account acc) {
        accounts.add(acc);
    }

    void showAllAccounts() {
        System.out.println("\n--- All Accounts ---");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println((i + 1) + ". " + accounts.get(i).name + " → " + accounts.get(i).balance + " Tk");
        }
    }

    void transfer(Account from, Account to, double amount)
            throws InsufficientFundsException, InvalidTransferAmountException {

        if (amount <= 0) {
            throw new InvalidTransferAmountException("Invalid amount! Must be greater than 0.");
        }

        if (from.balance < amount) {
            throw new InsufficientFundsException("Insufficient funds in " + from.name + "'s account.");
        }

        // Save previous balances (for rollback)
        double fromPrev = from.balance;
        double toPrev = to.balance;

        try {
            from.balance -= amount;
            to.balance += amount;
            System.out.println("✅ Transfer successful: " + amount + " Tk from " + from.name + " to " + to.name);
        } catch (Exception e) {
            // Rollback if anything goes wrong
            from.balance = fromPrev;
            to.balance = toPrev;
            System.out.println("⚠ Transaction failed and rolled back!");
        }
    }
}

public class P_36 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.print("How many accounts to create? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.print("\nEnter name for Account " + i + ": ");
            String name = sc.nextLine();
            System.out.print("Enter initial balance: ");
            double bal = sc.nextDouble();
            sc.nextLine();
            bank.addAccount(new Account(name, bal));
        }

        bank.showAllAccounts();

        // Multiple transfers
        char choice = 'y';  // must check it
        do {
            bank.showAllAccounts();
            System.out.print("\nEnter sender account number: ");
            int fromIndex = sc.nextInt() - 1;
            System.out.print("Enter receiver account number: ");
            int toIndex = sc.nextInt() - 1;

            if (fromIndex == toIndex) {
                System.out.println("⚠ Sender and receiver cannot be the same!");
                continue;
            }

            System.out.print("Enter amount to transfer: ");
            double amount = sc.nextDouble();

            try {
                bank.transfer(bank.accounts.get(fromIndex), bank.accounts.get(toIndex), amount);
            } catch (InvalidTransferAmountException | InsufficientFundsException e) {
                System.out.println("Error!" + e.getMessage());
            }

            System.out.print("\nDo you want to make another transfer? (y/n): ");
            choice = sc.next().toLowerCase().charAt(0);

        } while (choice == 'y');

        System.out.println("\n--- Final Balances ---");
        bank.showAllAccounts();

        sc.close();
    }
}
