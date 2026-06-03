package attendance;

import attendance.ConsoleColors;
import attendance.Utils;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.LinkedHashMap;

public class ExpenseService {

    private final DataStorage dataStorage;
    private final DataManager dataManager;
    private final Scanner scanner;
    private final String signatureImagePath;

    public ExpenseService(DataStorage dataStorage, DataManager dataManager, String signatureImagePath) {
        this.dataStorage = dataStorage;
        this.dataManager = dataManager;
        this.scanner = new Scanner(System.in);
        this.signatureImagePath = signatureImagePath;
    }

    private void displayExpenseMenu() {
        Utils.clearConsole();
        Utils.printHeader("Expense Management");
        Utils.printDigitalClock();
        System.out.println();
        System.out.println(String.format("%s  %2d. Mark Today's Expenses as Received%s", ConsoleColors.CYAN, 1, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. View Pending Buys%s", ConsoleColors.CYAN, 2, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Mark Expense as Received%s", ConsoleColors.CYAN, 3, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Mark Expenses as Received by Date Range type%s", ConsoleColors.CYAN, 4, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. View Expenses by Date%s", ConsoleColors.CYAN, 5, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. View All Expenses%s", ConsoleColors.CYAN, 6, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Update Status to Pending%s", ConsoleColors.CYAN, 7, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Remove Expense%s", ConsoleColors.CYAN, 8, ConsoleColors.RESET));
        System.out.println(String.format("%s  %2d. Generate PDF Report%s", ConsoleColors.CYAN, 9, ConsoleColors.RESET));
        System.out.println(ConsoleColors.RED + "  0. Back to Main Menu" + ConsoleColors.RESET);
        System.out.println();
        Utils.printFooter();
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
    }

    public void manageExpenses() throws InterruptedException {
        while (true) {
            displayExpenseMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    markTodaysExpensesAsReceived();
                    break;
                case 2: // NEW
                    viewPendingBuys(); // Call the new method
                    break;
                case 3: // Shifted from 2
                    listAndMarkExpenseAsReceived();
                    break;
                case 4: // Shifted from 3
                    viewAndMarkExpensesAsReceived();
                    break;
                case 5: // Shifted from 4
                    viewExpensesByDate();
                    break;
                case 6: // Shifted from 5
                    viewAllExpensesWithStatus();
                    break;
                case 7: // Shifted from 6
                    updateStatusToPending();
                    break;
                case 8: // Shifted from 7
                    removeExpense();
                    break;
                case 9: // Shifted from 8
                    generateExpensePdfReport();
                    break;
                case 0:
                    return;
                default:
                    System.out.println(ConsoleColors.RED + "Invalid choice. Please try again." + ConsoleColors.RESET);
                    Thread.sleep(1000);
            }
        }
    }

    private void markTodaysExpensesAsReceived() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Mark Today's Expenses as Received");
        System.out.println();

        List<DailyBuy> todaysBuys = dataStorage.getDailyBuys().stream()
                .filter(buy -> buy.getDate().equals(LocalDate.now()) && "Pending".equalsIgnoreCase(buy.getStatus()))
                .collect(Collectors.toList());

        if (todaysBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No pending expenses for today." + ConsoleColors.RESET);
        } else {
            for (DailyBuy buy : todaysBuys) {
                buy.setStatus("Received");
            }
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "All of today's expenses have been marked as 'Received'." + ConsoleColors.RESET);
        }
        Thread.sleep(2000);
    }

    public void viewPendingBuys() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Pending Buy Records");
        Utils.printDigitalClock();
        System.out.println();

        List<DailyBuy> allBuys = dataStorage.getDailyBuys();
        List<DailyBuy> pendingBuys = allBuys.stream()
                .filter(buy -> "Pending".equalsIgnoreCase(buy.getStatus()))
                .collect(Collectors.toList());

        if (pendingBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No pending buy records found." + ConsoleColors.RESET);
        } else {
            // Group pending buys by date
            Map<LocalDate, List<DailyBuy>> buysByDate = pendingBuys.stream()
                    .collect(Collectors.groupingBy(DailyBuy::getDate));

            double overallTotalPending = 0.0;

            // Sort dates for display
            List<LocalDate> sortedDates = buysByDate.keySet().stream()
                    .sorted()
                    .collect(Collectors.toList());

            for (LocalDate date : sortedDates) {
                System.out.println("\n" + ConsoleColors.CYAN_BOLD + "=== Pending for " + Utils.formatDateWithDay(date) + " ===" + ConsoleColors.RESET);
                double dailyTotalPending = 0.0;
                List<DailyBuy> buysForDate = buysByDate.get(date);
                for (int i = 0; i < buysForDate.size(); i++) {
                    // Use the printDailyBuyDetailsWithStatus helper for consistent output
                    printDailyBuyDetailsWithStatus(buysForDate.get(i), i);
                    dailyTotalPending += buysForDate.get(i).getPrice();
                }
                System.out.println(ConsoleColors.YELLOW_BOLD
                        + String.format("  Total - %.2f BDT", dailyTotalPending) + ConsoleColors.RESET);
                overallTotalPending += dailyTotalPending;
            }
            System.out.println(ConsoleColors.GREEN_BOLD + "\nFinal Overall Pending Amount: "
                    + String.format("%.2f", overallTotalPending) + " BDT" + ConsoleColors.RESET);
        }

        System.out.println();
        Utils.printFooter();
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private void listAndMarkExpenseAsReceived() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Mark Expense as Received");
            System.out.println();

            List<DailyBuy> pendingBuys = dataStorage.getDailyBuys().stream()
                    .filter(buy -> "Pending".equalsIgnoreCase(buy.getStatus()))
                    .sorted(Comparator.comparing(DailyBuy::getDate).thenComparing(DailyBuy::getTime))
                    .collect(Collectors.toList());

            if (pendingBuys.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No pending expenses to mark." + ConsoleColors.RESET);
                Thread.sleep(2000);
                return;
            }

            for (int i = 0; i < pendingBuys.size(); i++) {
                printDailyBuyDetailsWithStatus(pendingBuys.get(i), i);
            }

            System.out.print(ConsoleColors.YELLOW + "Enter the number of the expense to mark as 'Received' (0 to cancel): " + ConsoleColors.RESET);
            int choice = getUserChoice();

            if (choice == 0) {
                return;
            }

            if (choice > 0 && choice <= pendingBuys.size()) {
                DailyBuy selectedBuy = pendingBuys.get(choice - 1);
                selectedBuy.setStatus("Received");
                dataManager.saveData(dataStorage);
                System.out.println(ConsoleColors.GREEN + "Expense '" + selectedBuy.getItemName() + "' marked as 'Received'." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
            }
            Thread.sleep(1500);
        }
    }

    private void viewAndMarkExpensesAsReceived() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Mark Expenses as Received");
        System.out.println();

        List<DailyBuy> pendingBuys = dataStorage.getDailyBuys().stream()
                .filter(buy -> "Pending".equalsIgnoreCase(buy.getStatus()))
                .sorted(Comparator.comparing(DailyBuy::getDate).thenComparing(DailyBuy::getTime))
                .collect(Collectors.toList());

        if (pendingBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No pending expenses to mark." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        Map<LocalDate, List<DailyBuy>> groupedByDate = pendingBuys.stream()
                .collect(Collectors.groupingBy(DailyBuy::getDate, LinkedHashMap::new, Collectors.toList()));

        Map<Integer, LocalDate> dateMap = new LinkedHashMap<>();
        int counter = 1;
        for (Map.Entry<LocalDate, List<DailyBuy>> entry : groupedByDate.entrySet()) {
            dateMap.put(counter, entry.getKey());
            System.out.println();
            System.out.println(String.format("%s%2d. %s%s", ConsoleColors.CYAN_BOLD, counter++, ConsoleColors.BLUE_BOLD, entry.getKey().format(DateTimeFormatter.ofPattern("eeee, d MMMM yyyy"))));
            System.out.println(ConsoleColors.GRAY + "------------------------------------------------------------------" + ConsoleColors.RESET);
            for (DailyBuy buy : entry.getValue()) {
                System.out.println(String.format("%s %-25s %s %8.2f BDT %s at %s %s",
                        ConsoleColors.CYAN, buy.getItemName(),
                        ConsoleColors.YELLOW, buy.getPrice(),
                        ConsoleColors.PURPLE, buy.getTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                        ConsoleColors.RESET));
            }
        }
        System.out.println(ConsoleColors.GRAY + "------------------------------------------------------------------" + ConsoleColors.RESET);

        System.out.print(ConsoleColors.YELLOW + "\nEnter numbers to mark as received (e.g., 1, 2-4, 5): " + ConsoleColors.RESET);
        String input = scanner.nextLine();

        try {
            java.util.Set<Integer> numbersToProcess = parseInputNumbers(input);
            boolean updated = false;
            for (Integer number : numbersToProcess) {
                LocalDate dateToMark = dateMap.get(number);
                if (dateToMark != null) {
                    for (DailyBuy buy : dataStorage.getDailyBuys()) {
                        if (buy.getDate().equals(dateToMark) && "Pending".equalsIgnoreCase(buy.getStatus())) {
                            buy.setStatus("Received");
                            updated = true;
                        }
                    }
                }
            }
            if (updated) {
                dataManager.saveData(dataStorage);
                System.out.println(ConsoleColors.GREEN + "Selected expenses have been marked as 'Received'." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.YELLOW + "No expenses were updated." + ConsoleColors.RESET);
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Invalid input format." + ConsoleColors.RESET);
        }

        Thread.sleep(2000);
    }

    private java.util.Set<Integer> parseInputNumbers(String input) {
        java.util.Set<Integer> numbers = new java.util.HashSet<>();
        String[] parts = input.replace(",", " ").split("\\s+");
        for (String part : parts) {
            if (part.contains("-")) {
                String[] range = part.split("-");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);
                for (int i = start; i <= end; i++) {
                    numbers.add(i);
                }
            } else {
                if(!part.trim().isEmpty()){
                    numbers.add(Integer.parseInt(part.trim()));
                }
            }
        }
        return numbers;
    }


    private void viewExpensesByDate() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("View Expenses by Date");
        System.out.println();
        System.out.print(ConsoleColors.YELLOW + "Enter date (DD-MM-YYYY): " + ConsoleColors.RESET);
        String dateStr = scanner.nextLine();
        try {
            // Pre-process date string to handle single-digit months/days
            String[] parts = dateStr.split("-");
            if (parts.length == 3) {
                String day = String.format("%02d", Integer.parseInt(parts[0]));
                String month = String.format("%02d", Integer.parseInt(parts[1]));
                String year = parts[2];
                dateStr = day + "-" + month + "-" + year;
            }
            LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            List<DailyBuy> buysForDate = dataStorage.getDailyBuys().stream()
                    .filter(buy -> buy.getDate().equals(date))
                    .sorted(Comparator.comparing(DailyBuy::getTime))
                    .collect(Collectors.toList());

            if (buysForDate.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No expenses found for " + date + ConsoleColors.RESET);
            } else {
                double dailyTotal = 0;
                System.out.println(ConsoleColors.BLUE_BOLD + "\nExpenses for " + date.format(DateTimeFormatter.ofPattern("eeee, d MMMM yyyy")) + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GRAY + "------------------------------------------------------------------" + ConsoleColors.RESET);
                for (DailyBuy buy : buysForDate) {
                     System.out.println(String.format("%s %-25s %s %8.2f BDT %s at %s %s",
                            ConsoleColors.CYAN, buy.getItemName(),
                            ConsoleColors.YELLOW, buy.getPrice(),
                            ConsoleColors.PURPLE, buy.getTime().format(DateTimeFormatter.ofPattern("hh:mm a")),
                            ConsoleColors.RESET));
                    dailyTotal += buy.getPrice();
                }
                 System.out.println(ConsoleColors.GRAY + "------------------------------------------------------------------" + ConsoleColors.RESET);
                System.out.println(String.format("%sTotal: %.2f BDT%s", ConsoleColors.GREEN_BOLD, dailyTotal, ConsoleColors.RESET));
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Invalid date format. Please use DD-MM-YYYY." + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }


    private void viewAllExpensesWithStatus() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("All Expenses");
        System.out.println();

        List<DailyBuy> allBuys = dataStorage.getDailyBuys().stream()
                .sorted(Comparator.comparing(DailyBuy::getDate).thenComparing(DailyBuy::getTime))
                .collect(Collectors.toList());

        if (allBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No expenses found." + ConsoleColors.RESET);
        } else {
            for (int i = 0; i < allBuys.size(); i++) {
                printDailyBuyDetailsWithStatus(allBuys.get(i), i);
            }

            double totalReceived = allBuys.stream()
                    .filter(buy -> "Received".equalsIgnoreCase(buy.getStatus()))
                    .mapToDouble(DailyBuy::getPrice)
                    .sum();

            double totalPending = allBuys.stream()
                    .filter(buy -> "Pending".equalsIgnoreCase(buy.getStatus()))
                    .mapToDouble(DailyBuy::getPrice)
                    .sum();
            
            double grandTotal = totalReceived + totalPending;

            System.out.println(ConsoleColors.BLUE_BOLD + "\n=== Expense Summary ===" + ConsoleColors.RESET);
            System.out.println(String.format("%s  %-20s %s %.2f BDT",
                    ConsoleColors.GREEN, "Received Total:", ConsoleColors.WHITE_BOLD, totalReceived));
            System.out.println(String.format("%s  %-20s %s %.2f BDT",
                    ConsoleColors.YELLOW, "Pending Total:", ConsoleColors.WHITE_BOLD, totalPending));
            System.out.println(ConsoleColors.BLUE_BOLD + "  -------------------------------------" + ConsoleColors.RESET);
            System.out.println(String.format("%s  %-20s %s %.2f BDT",
                    ConsoleColors.CYAN, "Grand Total:", ConsoleColors.WHITE_BOLD, grandTotal));
            System.out.println(ConsoleColors.BLUE_BOLD + "=======================================" + ConsoleColors.RESET);
        }
        System.out.println(ConsoleColors.PURPLE + "\nPress Enter to return..." + ConsoleColors.RESET);
        scanner.nextLine();
    }

    private void updateStatusToPending() throws InterruptedException {
        while (true) {
            Utils.clearConsole();
            Utils.printHeader("Update Status to Pending");
            System.out.println();

            List<DailyBuy> receivedBuys = dataStorage.getDailyBuys().stream()
                    .filter(buy -> "Received".equalsIgnoreCase(buy.getStatus()))
                    .sorted(Comparator.comparing(DailyBuy::getDate).thenComparing(DailyBuy::getTime))
                    .collect(Collectors.toList());

            if (receivedBuys.isEmpty()) {
                System.out.println(ConsoleColors.YELLOW + "No received expenses to update." + ConsoleColors.RESET);
                Thread.sleep(2000);
                return;
            }

            for (int i = 0; i < receivedBuys.size(); i++) {
                printDailyBuyDetailsWithStatus(receivedBuys.get(i), i);
            }

            System.out.print(ConsoleColors.YELLOW + "Enter the number of the expense to mark as 'Pending' (0 to cancel): " + ConsoleColors.RESET);
            int choice = getUserChoice();

            if (choice == 0) {
                return;
            }

            if (choice > 0 && choice <= receivedBuys.size()) {
                DailyBuy selectedBuy = receivedBuys.get(choice - 1);
                selectedBuy.setStatus("Pending");
                dataManager.saveData(dataStorage);
                System.out.println(ConsoleColors.GREEN + "Expense '" + selectedBuy.getItemName() + "' marked as 'Pending'." + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
            }
            Thread.sleep(1500);
        }
    }

    private void removeExpense() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Remove Expense");
        System.out.println();

        List<DailyBuy> allBuys = dataStorage.getDailyBuys().stream()
            .sorted(Comparator.comparing(DailyBuy::getDate).thenComparing(DailyBuy::getTime))
            .collect(Collectors.toList());

        if (allBuys.isEmpty()) {
            System.out.println(ConsoleColors.YELLOW + "No expenses to remove." + ConsoleColors.RESET);
            Thread.sleep(2000);
            return;
        }

        for (int i = 0; i < allBuys.size(); i++) {
            printDailyBuyDetailsWithStatus(allBuys.get(i), i);
        }

        System.out.print(ConsoleColors.YELLOW + "Enter the number of the expense to remove (0 to cancel): " + ConsoleColors.RESET);
        int choice = getUserChoice();

        if (choice > 0 && choice <= allBuys.size()) {
            DailyBuy selectedBuy = allBuys.get(choice - 1);
            System.out.print(ConsoleColors.RED + "Are you sure you want to remove '" + selectedBuy.getItemName() + "'? (y/n): " + ConsoleColors.RESET);
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println(ConsoleColors.YELLOW + "Removal cancelled." + ConsoleColors.RESET);
                Thread.sleep(1000);
                return;
            }
            dataStorage.getDailyBuys().remove(selectedBuy);
            dataManager.saveData(dataStorage);
            System.out.println(ConsoleColors.GREEN + "Expense '" + selectedBuy.getItemName() + "' has been removed." + ConsoleColors.RESET);
        } else if (choice != 0) {
            System.out.println(ConsoleColors.RED + "Invalid choice." + ConsoleColors.RESET);
        }
         Thread.sleep(1500);
    }

    private void generateExpensePdfReport() throws InterruptedException {
        Utils.clearConsole();
        Utils.printHeader("Generate Expense Report PDF");
        Utils.printDigitalClock();
        System.out.println();

        try {
            String dest = "Expense_Report.pdf";
            PdfGenerator.generateReport(dataStorage, dest, dataStorage.getStudentName(), dataStorage.getFaculty(),
                    dataStorage.getStudentId(), dataStorage.getRegistrationNumber(), dataStorage.getSession(),
                    this.signatureImagePath, ReportType.EXPENSE_REPORT, null, null);
            System.out.println(
                    ConsoleColors.GREEN + "Expense Report PDF generated successfully: " + dest + ConsoleColors.RESET);
            openPdf(dest);
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error generating Expense Report PDF: " + e.getMessage() 
                    + ConsoleColors.RESET);
            e.printStackTrace();
        }
        Thread.sleep(2000);
    }

    private void openPdf(String dest) {
        try {
            File pdfFile = new File(dest);
            if (pdfFile.exists()) {
                if (java.awt.Desktop.isDesktopSupported()) {
                    java.awt.Desktop.getDesktop().open(pdfFile);
                } else {
                    System.out.println(ConsoleColors.YELLOW + "Desktop not supported, cannot open PDF automatically." + ConsoleColors.RESET);
                }
            } else {
                System.out.println(ConsoleColors.RED + "PDF file not found at: " + dest + ConsoleColors.RESET);
            }
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED + "Error opening PDF file: " + e.getMessage() + ConsoleColors.RESET);
        }
    }



    private void printDailyBuyDetailsWithStatus(DailyBuy buy, int index) {
        String statusColor = "Received".equalsIgnoreCase(buy.getStatus()) ? ConsoleColors.GREEN : ConsoleColors.YELLOW;
        System.out.println(String.format("%s  %2d. %-25s %s Price: %8.2f BDT %s Date: %s %s Status: %s%s%s",
                ConsoleColors.WHITE_BOLD, (index + 1), buy.getItemName(),
                ConsoleColors.YELLOW, buy.getPrice(),
                ConsoleColors.BLUE, Utils.formatDateWithDay(buy.getDate()),
                statusColor, buy.getStatus(), ConsoleColors.RESET,
                 ConsoleColors.GRAY + "\n     ------------------------------------------------------------------" + ConsoleColors.RESET
                ));
    }


    private int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println(ConsoleColors.RED + "Invalid input. Please enter a number." + ConsoleColors.RESET);
            scanner.next(); // Consume the invalid input
            System.out.print(ConsoleColors.YELLOW + "Enter your choice: " + ConsoleColors.RESET);
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }
}