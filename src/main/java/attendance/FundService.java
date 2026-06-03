package attendance;

import attendance.ReportType;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FundService {
    private final DataStorage dataStorage;
    private final DataManager dataManager;
    private final Scanner scanner;

    private static final List<String> BD_BANKS = Arrays.asList(
        "Sonali Bank", "Janata Bank", "Agrani Bank", "Rupali Bank",
        "Dutch-Bangla Bank", "BRAC Bank", "Islami Bank", "City Bank",
        "Eastern Bank", "Mutual Trust Bank", "Standard Chartered",
        "HSBC", "UCB", "Prime Bank", "Bank Asia", "Pubali Bank"
    );

    public FundService(DataStorage dataStorage, DataManager dataManager) {
        this.dataStorage = dataStorage;
        this.dataManager = dataManager;
        this.scanner = new Scanner(System.in);
    }

    public void manageFunds() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("💰 MONEY MANAGER DASHBOARD 💰");
            Utils.printDigitalClock();
            System.out.println();
            
            displayAllAccountsSummary();

System.out.println(ConsoleColors.PURPLE_BOLD + "  ╭─────────────────────────────────────────╮" + ConsoleColors.RESET);
System.out.println(ConsoleColors.CYAN_BOLD   + "  │  ➕ 1. Create New Fund Account          │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.GREEN_BOLD  + "  │  💰 2. Deposit Money (Add)              │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.RED_BOLD    + "  │  💸 3. Withdraw/Spend Money (Delete)    │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.YELLOW_BOLD + "  │  📋 4. Transaction Statement            │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.ORANGE      + "  │  ❌ 5. Undo/Remove Last Record          │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.RED         + "  │  🗑️ 6. Close/Remove Account             │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.BLUE_BOLD   + "  │  📄 7. Generate PDF Report              │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.YELLOW      + "  │  🔧 8. Manual Balance Adjustment        │" + ConsoleColors.RESET);
System.out.println(ConsoleColors.PURPLE_BOLD + "  ╰─────────────────────────────────────────╯" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.ROSE + "  ↩️ 0. Back to Cash Menu" + ConsoleColors.RESET);
            System.out.println();
            Utils.printFooter();
            System.out.println();
            System.out.print(ConsoleColors.YELLOW + "Enter choice: " + ConsoleColors.RESET);

            int choice = getUserChoice();
            switch (choice) {
                case 1: addNewAccount(); break;
                case 2: manageMoney(true); break;
                case 3: manageMoney(false); break;
                case 4: viewRecords(); break;
                case 5: removeTransaction(); break;
                case 6: removeAccount(); break;
                case 7: generatePdfReport(); break;
                case 8: setManualBalance(); break;
                case 0: return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid selection!" + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private String getAccountIcon(FundType type) {
        if (type == null) return "💰";
        switch (type) {
            case BKASH: return "📱";
            case BANK: return "🏦";
            case NAGAD: return "💸";
            case ROCKET: return "🚀";
            case CASH: return "💵";
            default: return "💰";
        }
    }

    private void displayAllAccountsSummary() {
        List<FundAccount> accounts = dataStorage.getFundAccounts();
        
        System.out.println(ConsoleColors.PURPLE_BOLD + "  ╔══════════════════════════════════════════╗");
        System.out.printf ("  ║ %-40s ║%n", "CURRENT ASSET SUMMARY");
        System.out.println("  ╠══════════════════════════════════════════╣" + ConsoleColors.RESET);

        if (accounts.isEmpty()) {
            System.out.printf ("  ║ %-40s ║%n", "No active accounts found.");
        } else {
            double totalBalance = 0;
            for (FundAccount account : accounts) {
                String icon = getAccountIcon(account.getType());
                String name = account.getName();
                String displayName = icon + " " + name;
                
                System.out.printf (ConsoleColors.PURPLE_BOLD + "  ║ " + ConsoleColors.CYAN_BOLD + "%-16s " + ConsoleColors.RESET + ": " + (account.getBalance() >= 0 ? ConsoleColors.GREEN_BOLD : ConsoleColors.RED_BOLD) + "%15.2f BDT   " + ConsoleColors.PURPLE_BOLD + "║%n" + ConsoleColors.RESET, 
                    displayName, account.getBalance());
                
                String typeStr = "└─ " + ((account.getType() == FundType.BANK) ? account.getBankName() : account.getType().getDisplayName());
                System.out.printf (ConsoleColors.PURPLE_BOLD + "  ║ %-40s ║%n" + ConsoleColors.RESET, "   " + typeStr);
                
                totalBalance += account.getBalance();
            }
            System.out.println(ConsoleColors.PURPLE_BOLD + "  ╠══════════════════════════════════════════╣");
            System.out.printf ("  ║ " + ConsoleColors.WHITE_BOLD + "%-16s " + ConsoleColors.RESET + ": " + (totalBalance >= 0 ? ConsoleColors.CYAN_BOLD : ConsoleColors.RED_BOLD) + "%15.2f BDT   " + ConsoleColors.PURPLE_BOLD + "║%n" + ConsoleColors.RESET, 
                "NET WORTH", totalBalance);
        }
        System.out.println(ConsoleColors.PURPLE_BOLD + "  ╚══════════════════════════════════════════╝" + ConsoleColors.RESET);
    }

    private void addNewAccount() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("OPEN NEW ACCOUNT");
        System.out.println();

        System.out.print(ConsoleColors.YELLOW + "  Enter Name (e.g. My Wallet): " + ConsoleColors.RESET);
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) return;

        System.out.println("\n" + ConsoleColors.PURPLE_BOLD + "  ╭──────────────────────────────────────────╮");
        System.out.printf ("  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", "  SELECT PLATFORM:");
        System.out.println("  ├──────────────────────────────────────────┤");
        FundType[] types = FundType.values();
        for (int i = 0; i < types.length; i++) {
            String label = String.format("  %d. %s %s", (i + 1), getAccountIcon(types[i]), types[i].getDisplayName());
            System.out.printf (ConsoleColors.WHITE_BOLD + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", label);
        }
        System.out.println("  ╰──────────────────────────────────────────╯" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Select (1-" + types.length + "): " + ConsoleColors.RESET);
        int typeChoice = getUserChoice();
        if (typeChoice < 1 || typeChoice > types.length) return;
        FundType selectedType = types[typeChoice - 1];

        String bankName = "";
        if (selectedType == FundType.BANK) {
            System.out.println("\n" + ConsoleColors.PURPLE_BOLD + "  ╭──────────────────────────────────────────╮");
            System.out.printf ("  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", "  SELECT BANK:");
            System.out.println("  ├──────────────────────────────────────────┤");
            for (int i = 0; i < BD_BANKS.size(); i++) {
                String label = String.format("  %d. %s", (i + 1), BD_BANKS.get(i));
                System.out.printf (ConsoleColors.WHITE + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", label);
            }
            String other = String.format("  %d. OTHER BANK", (BD_BANKS.size() + 1));
            System.out.printf (ConsoleColors.WHITE + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", other);
            System.out.println("  ╰──────────────────────────────────────────╯" + ConsoleColors.RESET);
            System.out.print(ConsoleColors.YELLOW + "  Choice: " + ConsoleColors.RESET);
            int bankChoice = getUserChoice();
            if (bankChoice > 0 && bankChoice <= BD_BANKS.size()) {
                bankName = BD_BANKS.get(bankChoice - 1);
            } else if (bankChoice == BD_BANKS.size() + 1) {
                System.out.print(ConsoleColors.YELLOW + "  Bank Name: " + ConsoleColors.RESET);
                bankName = scanner.nextLine().trim();
            } else {
                return; // Cancel if invalid bank choice
            }
        }

        // Final duplicate check: same name AND same type AND (if bank) same bank name
        String finalBankName = bankName;
        boolean exists = dataStorage.getFundAccounts().stream()
                .anyMatch(a -> a.getName().equalsIgnoreCase(name) && 
                               a.getType() == selectedType && 
                               (selectedType != FundType.BANK || a.getBankName().equalsIgnoreCase(finalBankName)));
        
        if (exists) {
            String typeInfo = (selectedType == FundType.BANK) ? bankName : selectedType.getDisplayName();
            System.out.println(ConsoleColors.RED + "  Error: Account '" + name + "' (" + typeInfo + ") already exists!" + ConsoleColors.RESET);
            Thread.sleep(1500);
            return;
        }

        System.out.print(ConsoleColors.CYAN + "  Initial Balance (BDT): " + ConsoleColors.RESET);
        String balanceStr = scanner.nextLine().trim();
        double balance = 0;
        try {
            balance = balanceStr.isEmpty() ? 0 : Double.parseDouble(balanceStr);
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.RED + "  Invalid balance format! Setting to 0." + ConsoleColors.RESET);
            Thread.sleep(1000);
        }

        FundAccount newAccount = new FundAccount(name, selectedType, bankName, balance);
        dataStorage.getFundAccounts().add(newAccount);
        
        if (balance != 0) {
            String typeStr = balance > 0 ? "ADD" : "DELETE";
            dataStorage.getFundTransactions().add(new FundTransaction(name, selectedType, bankName, Math.abs(balance), typeStr, "Account Opening"));
        }

        dataManager.saveData(dataStorage);
        System.out.println("\n" + ConsoleColors.GREEN_BOLD + "  ✅ ACCOUNT INITIALIZED SUCCESSFULLY!" + ConsoleColors.RESET);
        Thread.sleep(1500);
    }

    private void manageMoney(boolean isAdd) throws InterruptedException {
        List<FundAccount> accounts = dataStorage.getFundAccounts();
        if (accounts.isEmpty()) {
            System.out.println(ConsoleColors.RED + "  Error: No accounts found!" + ConsoleColors.RESET);
            Thread.sleep(1000);
            return;
        }

        Utils.clearConsole();
        Utils.printHeader(isAdd ? "DEPOSIT MONEY" : "WITHDRAW MONEY");
        System.out.println();

        System.out.println(ConsoleColors.PURPLE_BOLD + "  ╭──────────────────────────────────────────╮");
        System.out.printf (ConsoleColors.WHITE_BOLD + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", "  SELECT TARGET ACCOUNT:");
        System.out.println("  ├──────────────────────────────────────────┤");
        for (int i = 0; i < accounts.size(); i++) {
            FundAccount acc = accounts.get(i);
            String icon = getAccountIcon(acc.getType());
            String name = acc.getName(); if(name.length() > 14) name = name.substring(0, 11) + "...";
            String bal = String.format("%.1f", acc.getBalance());
            
            // Format: Serial. Icon Name [Balance BDT]
            String label = String.format("  %d. %s %-12s [%8s BDT]", (i + 1), icon, name, bal);
            System.out.printf (ConsoleColors.CYAN_BOLD + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", label);
        }
        System.out.println("  ├──────────────────────────────────────────┤");
        System.out.printf (ConsoleColors.RED + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", "  0. CANCEL OPERATION");
        System.out.println("  ╰──────────────────────────────────────────╯" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Select: " + ConsoleColors.RESET);
        int choice = getUserChoice();
        if (choice == 0) return;
        if (choice < 1 || choice > accounts.size()) return;

        FundAccount selectedAccount = accounts.get(choice - 1);

        double amount = 0;
        if (isAdd) {
            System.out.print(ConsoleColors.GREEN_BOLD + "  Amount to Deposit (BDT): " + ConsoleColors.RESET);
            try {
                amount = Math.abs(Double.parseDouble(scanner.nextLine().trim()));
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.RED + "  Invalid amount!" + ConsoleColors.RESET);
                Thread.sleep(1000);
                return;
            }
        } else {
            System.out.print(ConsoleColors.RED_BOLD + "  Amount to Withdraw (BDT) [or type 'ALL']: " + ConsoleColors.RESET);
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("ALL")) {
                amount = Math.max(0, selectedAccount.getBalance());
            } else {
                try {
                    amount = Math.abs(Double.parseDouble(input));
                } catch (NumberFormatException e) {
                    System.out.println(ConsoleColors.RED + "  Invalid amount!" + ConsoleColors.RESET);
                    Thread.sleep(1000);
                    return;
                }
            }
        }

        System.out.print(ConsoleColors.CYAN + "  Reference/Note: " + ConsoleColors.RESET);
        String note = scanner.nextLine().trim();

        if (isAdd) {
            selectedAccount.addMoney(amount);
            dataStorage.getFundTransactions().add(new FundTransaction(selectedAccount.getName(), selectedAccount.getType(), selectedAccount.getBankName(), amount, "ADD", note));
        } else {
            if (selectedAccount.getBalance() < amount) {
                System.out.println(ConsoleColors.RED_BOLD + "\n  ⚠️ INSUFFICIENT BALANCE! Current: " + String.format("%.2f", selectedAccount.getBalance()) + " BDT" + ConsoleColors.RESET);
                System.out.print(ConsoleColors.YELLOW + "  Allow " + selectedAccount.getName() + " to go negative? (y/n): " + ConsoleColors.RESET);
                if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    System.out.println(ConsoleColors.YELLOW + "  Transaction cancelled." + ConsoleColors.RESET);
                    Thread.sleep(1000);
                    return;
                }
            }
            selectedAccount.deleteMoney(amount);
            dataStorage.getFundTransactions().add(new FundTransaction(selectedAccount.getName(), selectedAccount.getType(), selectedAccount.getBankName(), amount, "DELETE", note));
        }

        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN_BOLD + "  ✅ TRANSACTION COMPLETED!" + ConsoleColors.RESET);
        Thread.sleep(1500);
    }

    private void viewRecords() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("ACCOUNT STATEMENT");
        System.out.println();

        List<FundTransaction> transactions = dataStorage.getFundTransactions();
        if (transactions.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "  No history recorded." + ConsoleColors.RESET);
        } else {
            int start = Math.max(0, transactions.size() - 25);
            System.out.println(ConsoleColors.PURPLE_BOLD + "  ┌─────────────────────┬──────────────┬────────────┬──────────────────┐");
            System.out.println("  │     Date & Time     │   Account    │   Amount   │   Reference      │");
            System.out.println("  ├─────────────────────┼──────────────┼────────────┼──────────────────┤" + ConsoleColors.RESET);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy HH:mm");
            for (int i = transactions.size() - 1; i >= start; i--) {
                FundTransaction tx = transactions.get(i);
                String color = tx.getType().equals("ADD") ? ConsoleColors.GREEN : ConsoleColors.RED;
                String sign = tx.getType().equals("ADD") ? "+" : "-";
                
                String date = tx.getTimestamp().format(formatter);
                String acc = tx.getAccountName(); if(acc.length() > 12) acc = acc.substring(0, 9) + "...";
                String amt = sign + String.format("%.1f", tx.getAmount());
                String note = tx.getNote(); if(note.length() > 16) note = note.substring(0, 13) + "...";

                System.out.printf(ConsoleColors.PURPLE_BOLD + "  │ " + ConsoleColors.CYAN + "%-19s " + ConsoleColors.PURPLE_BOLD + "│ " + ConsoleColors.RESET + "%-12s " + ConsoleColors.PURPLE_BOLD + "│ " + color + "%10s " + ConsoleColors.PURPLE_BOLD + "│ " + ConsoleColors.RESET + "%-16s " + ConsoleColors.PURPLE_BOLD + "│\n", 
                    date, acc, amt, note);
            }
            System.out.println(ConsoleColors.PURPLE_BOLD + "  └─────────────────────┴──────────────┴────────────┴──────────────────┘" + ConsoleColors.RESET);
        }

        System.out.println(ConsoleColors.PURPLE + "\n  Press Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private void removeTransaction() throws InterruptedException {
        List<FundTransaction> transactions = dataStorage.getFundTransactions();
        if (transactions.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "  No records to undo." + ConsoleColors.RESET);
            Thread.sleep(1000);
            return;
        }

        Utils.clearConsole();
        Utils.printHeader("UNDO / REVERT RECORD");
        System.out.println(ConsoleColors.YELLOW + "  Note: Undoing a record will revert the account's balance." + ConsoleColors.RESET);
        System.out.println();

        int start = Math.max(0, transactions.size() - 20);
        
        // --- TABLE STRUCTURE DEFINITION (Strictly Fixed Alignment) ---
        // ID: 4 cells (space + 2-digit ID + space)
        // Date: 16 cells (space + 14-char date + space)
        // Account: 17 cells (space + 15-cell emoji/text + space)
        // Name: 17 cells (space + 15-char name + space)
        // Amount: 12 cells (space + sign + space + 8-char amount + space)
        
        // 1. Top Border (4+16+17+17+12 = 66 dashes)
        System.out.println(ConsoleColors.PURPLE_BOLD + "  ╭────┬────────────────┬─────────────────┬─────────────────┬────────────╮");
        
        // 2. Header Row
        System.out.print(ConsoleColors.PURPLE_BOLD + "  │");
        System.out.print(ConsoleColors.YELLOW_BOLD + " ID "); // 4
        System.out.print(ConsoleColors.PURPLE_BOLD + "│");
        System.out.print(ConsoleColors.CYAN_BOLD + "   Date Time    "); // 16
        System.out.print(ConsoleColors.PURPLE_BOLD + "│");
        System.out.print(ConsoleColors.WHITE_BOLD + "     Account     "); // 17
        System.out.print(ConsoleColors.PURPLE_BOLD + "│");
        System.out.print(ConsoleColors.BLUE_BOLD + "      Name       "); // 17
        System.out.print(ConsoleColors.PURPLE_BOLD + "│");
        System.out.print(ConsoleColors.GREEN_BOLD + "   Amount   "); // 12
        System.out.println(ConsoleColors.PURPLE_BOLD + "│");
        
        // 3. Middle Separator
        System.out.println(ConsoleColors.PURPLE_BOLD + "  ├────┼────────────────┼─────────────────┼─────────────────┼────────────┤");

        for (int i = start; i < transactions.size(); i++) {
            FundTransaction tx = transactions.get(i);
            String icon = getAccountIcon(tx.getAccountType());
            String sign = tx.getType().equals("ADD") ? "+" : "-";
            String amountColor = tx.getType().equals("ADD") ? ConsoleColors.GREEN_BOLD : ConsoleColors.RED_BOLD;
            String timeStr = tx.getTimestamp().format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm")); // 14 chars
            
            String accName = tx.getAccountName();
            if (accName.length() > 15) accName = accName.substring(0, 12) + "...";
            
            String platform = (tx.getAccountType() != null) ? tx.getAccountType().getDisplayName() : "Unknown";
            if (platform.length() > 12) platform = platform.substring(0, 9) + "...";

            // Emoji padding: Icon=2 cells, Platform=N cells. Total content width = 15.
            int currentCells = 2 + 1 + platform.length(); 
            String paddedAccount = icon + " " + platform + " ".repeat(Math.max(0, 15 - currentCells));

            System.out.print(ConsoleColors.PURPLE_BOLD + "  │");
            
            // ID (4)
            System.out.print(ConsoleColors.YELLOW + String.format(" %2d ", (i + 1)));
            System.out.print(ConsoleColors.PURPLE_BOLD + "│");
            
            // Date (16) - " dd-MM-yy HH:mm " (1 space + 14 chars + 1 space = 16 cells)
            System.out.print(ConsoleColors.CYAN + String.format(" %s ", timeStr));
            System.out.print(ConsoleColors.PURPLE_BOLD + "│");
            
            // Account (17)
            System.out.print(ConsoleColors.WHITE_BOLD + " " + paddedAccount + " ");
            System.out.print(ConsoleColors.PURPLE_BOLD + "│");
            
            // Name (17)
            System.out.print(ConsoleColors.BLUE_BOLD + String.format(" %-15s ", accName));
            System.out.print(ConsoleColors.PURPLE_BOLD + "│");
            
            // Amount (12)
            System.out.print(amountColor + String.format(" %s %8.1f ", sign, tx.getAmount()));
            System.out.println(ConsoleColors.PURPLE_BOLD + "│" + ConsoleColors.RESET);
        }
        
        // 4. Bottom Border
        System.out.println(ConsoleColors.PURPLE_BOLD + "  ╰────┴────────────────┴─────────────────┴─────────────────┴────────────╯");
        
        System.out.printf (ConsoleColors.RED + "  %2d. BACK / CANCEL%n", 0);
        System.out.println(ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Enter ID to Undo: " + ConsoleColors.RESET);
        int choice = getUserChoice();
        if (choice == 0) return;
        
        if (choice > 0 && choice <= transactions.size()) {
            FundTransaction txToRemove = transactions.get(choice - 1);
            
            FundAccount account = dataStorage.getFundAccounts().stream()
                .filter(a -> a.getName().equalsIgnoreCase(txToRemove.getAccountName()) &&
                            (txToRemove.getAccountType() == null || a.getType() == txToRemove.getAccountType()) &&
                            (txToRemove.getBankName() == null || a.getBankName().equalsIgnoreCase(txToRemove.getBankName())))
                .findFirst().orElse(null);
            
            if (account != null) {
                if (txToRemove.getType().equals("ADD")) {
                    account.deleteMoney(txToRemove.getAmount());
                    System.out.println(ConsoleColors.YELLOW + "  Reverting: Subtracted " + txToRemove.getAmount() + " from " + getAccountIcon(account.getType()) + " " + account.getName() + ConsoleColors.RESET);
                } else {
                    account.addMoney(txToRemove.getAmount());
                    System.out.println(ConsoleColors.YELLOW + "  Reverting: Added " + txToRemove.getAmount() + " back to " + getAccountIcon(account.getType()) + " " + account.getName() + ConsoleColors.RESET);
                }
            } else {
                System.out.println(ConsoleColors.RED + "  Warning: Account '" + txToRemove.getAccountName() + "' no longer exists. Record removed only." + ConsoleColors.RESET);
            }

            transactions.remove(choice - 1);
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN_BOLD + "  ✅ RECORD REVERTED SUCCESSFULLY!" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "  Invalid ID!" + ConsoleColors.RESET);
        }
        Thread.sleep(1500);
    }

    private void setManualBalance() throws InterruptedException {
        List<FundAccount> accounts = dataStorage.getFundAccounts();
        if (accounts.isEmpty()) return;

        Utils.clearConsole();
        Utils.printHeader("MANUAL BALANCE ADJUSTMENT");
        System.out.println(ConsoleColors.RED_BOLD + "  ⚠️ WARNING: This bypasses transaction history! Use only to fix errors." + ConsoleColors.RESET);
        System.out.println();

        for (int i = 0; i < accounts.size(); i++) {
            FundAccount acc = accounts.get(i);
            System.out.printf("  %d. %s %-15s [Current: %.2f BDT]\n", (i+1), getAccountIcon(acc.getType()), acc.getName(), acc.getBalance());
        }
        System.out.print(ConsoleColors.YELLOW + "\n  Select Account: " + ConsoleColors.RESET);
        int choice = getUserChoice();
        if (choice < 1 || choice > accounts.size()) return;

        FundAccount acc = accounts.get(choice - 1);
        System.out.print(ConsoleColors.CYAN + "  Enter New Correct Balance for " + acc.getName() + ": " + ConsoleColors.RESET);
        try {
            double newBalance = Double.parseDouble(scanner.nextLine().trim());
            double diff = newBalance - acc.getBalance();
            acc.setBalance(newBalance);
            
            // Log it as an adjustment
            String note = "Manual Adjustment (" + (diff >= 0 ? "+" : "") + String.format("%.1f", diff) + ")";
            dataStorage.getFundTransactions().add(new FundTransaction(acc.getName(), acc.getType(), acc.getBankName(), Math.abs(diff), (diff >= 0 ? "ADD" : "DELETE"), note));
            
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN_BOLD + "  ✅ BALANCE UPDATED SUCCESSFULLY!" + ConsoleColors.RESET);
        } catch (NumberFormatException e) {
            System.out.println(ConsoleColors.RED + "  Invalid input." + ConsoleColors.RESET);
        }
        Thread.sleep(1500);
    }

    private void removeAccount() throws InterruptedException {
        List<FundAccount> accounts = dataStorage.getFundAccounts();
        if (accounts.isEmpty()) return;

        Utils.clearConsole();
        Utils.printHeader("CLOSE ACCOUNT");
        System.out.println();

        System.out.println(ConsoleColors.PURPLE_BOLD + "  ╭──────────────────────────────────────────╮");
        System.out.printf ("  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", "  SELECT ACCOUNT TO REMOVE:");
        System.out.println("  ├──────────────────────────────────────────┤");
        for (int i = 0; i < accounts.size(); i++) {
            String label = String.format("  %d. %s %s", (i + 1), getAccountIcon(accounts.get(i).getType()), accounts.get(i).getName());
            System.out.printf (ConsoleColors.WHITE_BOLD + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", label);
        }
        System.out.println("  ├──────────────────────────────────────────┤");
        System.out.printf (ConsoleColors.RED + "  │ %-40s " + ConsoleColors.PURPLE_BOLD + "│%n", "  0. CANCEL");
        System.out.println("  ╰──────────────────────────────────────────╯" + ConsoleColors.RESET);
        System.out.print(ConsoleColors.YELLOW + "  Enter ID: " + ConsoleColors.RESET);
        int choice = getUserChoice();
        if (choice == 0) return;
        if (choice < 1 || choice > accounts.size()) return;

        FundAccount target = accounts.get(choice - 1);
        System.out.print(ConsoleColors.RED_BOLD + "  Are you sure you want to close '" + target.getName() + "'? This will NOT delete its transaction history. (y/n): " + ConsoleColors.RESET);
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println(ConsoleColors.YELLOW + "  Operation cancelled." + ConsoleColors.RESET);
            Thread.sleep(1000);
            return;
        }

        FundAccount removed = accounts.remove(choice - 1);
        dataManager.saveData(dataStorage);
        System.out.println(ConsoleColors.GREEN_BOLD + "  ✅ ACCOUNT CLOSED PERMANENTLY." + ConsoleColors.RESET);
        Thread.sleep(1500);
    }

    private void generatePdfReport() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("PDF REPORT");
        try {
            String dest = "Money_Manager_Report.pdf";
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    ConfigManager.getSignaturePath(), ReportType.FUND_REPORT, null, null);
            System.out.println(ConsoleColors.GREEN_BOLD + "\n  ✅ FINANCIAL STATEMENT SAVED: " + ConsoleColors.CYAN + dest + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "\n  ❌ FAILED TO GENERATE PDF!" + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE + "\n  Press Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private void openPdf(String dest) {
        try {
            File pdfFile = new File(dest);
            if (pdfFile.exists() && java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop.getDesktop().open(pdfFile);
            }
        } catch (Exception e) {}
    }

    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print(ConsoleColors.RED + "  Error! Enter ID: " + ConsoleColors.RESET);
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
}
