import java.io.*;
import java.util.Scanner;

public class FinanceManager implements Reportable {
    private static final int MAX_TRANSACTIONS = 500;
    private Transaction[] transactions = new Transaction[MAX_TRANSACTIONS];
    private int transactionCount = 0;

    // For thread communication
    private boolean reportRequested = false;
    private final Object reportLock = new Object();

    // Add Income (overloaded)
    public void addIncome(Scanner scanner) {
        System.out.println("--- Add Income ---");
        try {
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be greater than zero.");
            }
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter category (e.g., Salary, Freelance, Scholarship, Gift, Other): ");
            String category = scanner.nextLine();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            int id = generateTransactionId();
            Income income = new Income(id, amount, description, category, date);
            addTransaction(income);
            System.out.println("Income added successfully with ID: " + id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Add Expense (overloaded)
    public void addExpense(Scanner scanner) {
        System.out.println("--- Add Expense ---");
        try {
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be greater than zero.");
            }
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter category (e.g., Food, Transport, Education, Shopping, Entertainment, Rent, Bills, Healthcare, Other): ");
            String category = scanner.nextLine();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            int id = generateTransactionId();
            Expense expense = new Expense(id, amount, description, category, date);
            addTransaction(expense);
            System.out.println("Expense added successfully with ID: " + id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Add Recurring Expense (overloaded)
    public void addRecurringExpense(Scanner scanner) {
        System.out.println("--- Add Recurring Expense ---");
        try {
            System.out.print("Enter amount: ");
            double amount = Double.parseDouble(scanner.nextLine());
            if (amount <= 0) {
                throw new InvalidAmountException("Amount must be greater than zero.");
            }
            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter category (e.g., Rent, Subscription, Bills): ");
            String category = scanner.nextLine();
            System.out.print("Enter date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.print("Enter frequency (e.g., Monthly, Weekly): ");
            String frequency = scanner.nextLine();

            int id = generateTransactionId();
            RecurringExpense recurring = new RecurringExpense(id, amount, description, category, date, frequency);
            addTransaction(recurring);
            System.out.println("Recurring expense added successfully with ID: " + id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format.");
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method overloading: addTransaction with Transaction object
    public synchronized void addTransaction(Transaction transaction) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            System.out.println("Cannot add more transactions. Maximum limit reached.");
            return;
        }
        transactions[transactionCount] = transaction;
        transactionCount++;
        System.out.println("Transaction added to array.");
    }

    // Method overloading: addTransaction with parameters
    public synchronized void addTransaction(int id, double amount, String description, String category, String date, String type) {
        if (transactionCount >= MAX_TRANSACTIONS) {
            System.out.println("Cannot add more transactions. Maximum limit reached.");
            return;
        }
        Transaction t;
        if (type.equalsIgnoreCase("INCOME")) {
            t = new Income(id, amount, description, category, date);
        } else {
            t = new Expense(id, amount, description, category, date);
        }
        transactions[transactionCount] = t;
        transactionCount++;
    }

    // Generate transaction ID
    private int generateTransactionId() {
        if (transactionCount == 0) {
            return 1;
        }
        int maxId = 0;
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getTransactionId() > maxId) {
                maxId = transactions[i].getTransactionId();
            }
        }
        return maxId + 1;
    }

    // View all transactions
    public synchronized void viewTransactions() {
        if (transactionCount == 0) {
            System.out.println("No transactions to display.");
            return;
        }
        System.out.println("--- All Transactions ---");
        for (int i = 0; i < transactionCount; i++) {
            System.out.println((i + 1) + ". " + transactions[i].toString());
        }
    }

    // View income transactions
    public synchronized void viewIncome() {
        boolean found = false;
        System.out.println("--- Income Transactions ---");
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equalsIgnoreCase("INCOME")) {
                transactions[i].displayDetails();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No income transactions found.");
        }
    }

    // View expense transactions
    public synchronized void viewExpenses() {
        boolean found = false;
        System.out.println("--- Expense Transactions ---");
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getType().equalsIgnoreCase("EXPENSE")) {
                transactions[i].displayDetails();
                System.out.println();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expense transactions found.");
        }
    }

    // Calculate total income using recursion
    public synchronized double calculateTotalIncome() {
        return calculateTotalIncomeRecursive(transactions, transactionCount - 1);
    }

    private double calculateTotalIncomeRecursive(Transaction[] arr, int index) {
        if (index < 0) {
            return 0;
        }
        double sum = 0;
        if (arr[index].getType().equalsIgnoreCase("INCOME")) {
            sum = arr[index].getAmount();
        }
        return sum + calculateTotalIncomeRecursive(arr, index - 1);
    }

    // Calculate total expenses using recursion
    public synchronized double calculateTotalExpenses() {
        return calculateTotalExpensesRecursive(transactions, transactionCount - 1);
    }

    private double calculateTotalExpensesRecursive(Transaction[] arr, int index) {
        if (index < 0) {
            return 0;
        }
        double sum = 0;
        if (arr[index].getType().equalsIgnoreCase("EXPENSE")) {
            sum = arr[index].getAmount();
        }
        return sum + calculateTotalExpensesRecursive(arr, index - 1);
    }

    // Calculate balance
    public synchronized void calculateBalance() {
        double totalIncome = calculateTotalIncome();
        double totalExpense = calculateTotalExpenses();
        double balance = totalIncome - totalExpense;
        System.out.println("--- Balance Calculation ---");
        System.out.println("Total Income: ₹" + totalIncome);
        System.out.println("Total Expenses: ₹" + totalExpense);
        System.out.println("Current Balance: ₹" + balance);
    }

    // Search transaction by ID
    public synchronized void searchTransaction(Scanner scanner) {
        System.out.print("Enter transaction ID to search: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Transaction t = findTransaction(id);
            if (t != null) {
                System.out.println("Transaction found:");
                t.displayDetails();
            } else {
                System.out.println("Transaction with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    // Update transaction
    public synchronized void updateTransaction(Scanner scanner) {
        System.out.print("Enter transaction ID to update: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            int index = -1;
            for (int i = 0; i < transactionCount; i++) {
                if (transactions[i].getTransactionId() == id) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println("Transaction with ID " + id + " not found.");
                return;
            }
            System.out.println("Current transaction:");
            transactions[index].displayDetails();
            System.out.println("Enter new details (leave blank to keep current value):");
            System.out.print("New amount (current: " + transactions[index].getAmount() + "): ");
            String amountStr = scanner.nextLine();
            double amount = transactions[index].getAmount();
            if (!amountStr.trim().isEmpty()) {
                try {
                    amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        throw new InvalidAmountException("Amount must be greater than zero.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount format. Keeping current amount.");
                } catch (InvalidAmountException e) {
                    System.out.println("Error: " + e.getMessage() + ". Keeping current amount.");
                }
            }
            System.out.print("New description (current: " + transactions[index].getDescription() + "): ");
            String description = scanner.nextLine();
            if (description.trim().isEmpty()) {
                description = transactions[index].getDescription();
            }
            System.out.print("New category (current: " + transactions[index].getCategory() + "): ");
            String category = scanner.nextLine();
            if (category.trim().isEmpty()) {
                category = transactions[index].getCategory();
            }
            System.out.print("New date (current: " + transactions[index].getDate() + "): ");
            String date = scanner.nextLine();
            if (date.trim().isEmpty()) {
                date = transactions[index].getDate();
            }
            // Update transaction
            transactions[index].setAmount(amount);
            transactions[index].setDescription(description);
            transactions[index].setCategory(category);
            transactions[index].setDate(date);
            System.out.println("Transaction updated successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    // Delete transaction
    public synchronized void deleteTransaction(Scanner scanner) {
        System.out.print("Enter transaction ID to delete: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            int index = -1;
            for (int i = 0; i < transactionCount; i++) {
                if (transactions[i].getTransactionId() == id) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                System.out.println("Transaction with ID " + id + " not found.");
                return;
            }
            // Shift elements left
            for (int i = index; i < transactionCount - 1; i++) {
                transactions[i] = transactions[i + 1];
            }
            transactions[transactionCount - 1] = null;
            transactionCount--;
            System.out.println("Transaction with ID " + id + " deleted successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
        }
    }

    // Category-wise summary
    public synchronized void categorySummary() {
        if (transactionCount == 0) {
            System.out.println("No transactions to summarize.");
            return;
        }
        // We'll use two parallel arrays for categories and totals (simple approach)
        String[] categories = new String[transactionCount];
        double[] totals = new double[transactionCount];
        int uniqueCount = 0;

        for (int i = 0; i < transactionCount; i++) {
            String cat = transactions[i].getCategory();
            boolean found = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (categories[j].equalsIgnoreCase(cat)) {
                    totals[j] += transactions[i].getAmount();
                    found = true;
                    break;
                }
            }
            if (!found) {
                categories[uniqueCount] = cat;
                totals[uniqueCount] = transactions[i].getAmount();
                uniqueCount++;
            }
        }

        System.out.println("--- Category-wise Expense Summary ---");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println(categories[i] + ": ₹" + totals[i]);
        }
    }

    // Save data to file using FileWriter
    public synchronized void saveData() {
        try (FileWriter writer = new FileWriter("data\\transactions.txt")) {
            for (int i = 0; i < transactionCount; i++) {
                writer.write(transactions[i].toString());
                writer.write(System.lineSeparator());
            }
            System.out.println("Data saved successfully to data\\transactions.txt");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Load data from file using FileReader
    public synchronized void loadData() {
        File file = new File("data\\transactions.txt");
        if (!file.exists()) {
            System.out.println("File data\\transactions.txt not found. Starting with empty data.");
            return;
        }
        try (FileReader reader = new FileReader(file);
             BufferedReader br = new BufferedReader(reader)) {
            String line;
            transactionCount = 0; // reset before loading
            while ((line = br.readLine()) != null && transactionCount < MAX_TRANSACTIONS) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String type = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    String description = parts[3];
                    String category = parts[4];
                    String date = parts[5];
                    Transaction t;
                    if (type.equalsIgnoreCase("INCOME")) {
                        t = new Income(id, amount, description, category, date);
                    } else {
                        t = new Expense(id, amount, description, category, date);
                    }
                    transactions[transactionCount] = t;
                    transactionCount++;
                }
            }
            System.out.println("Data loaded successfully. Loaded " + transactionCount + " transactions.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing data: " + e.getMessage());
        }
    }

    // Demonstrate byte streams: create a simple backup of transaction count
    public synchronized void backupDataUsingByteStreams() {
        try (FileOutputStream fos = new FileOutputStream("data\\backup.dat")) {
            // Write transaction count as 4 bytes
            byte[] countBytes = new byte[4];
            countBytes[0] = (byte) (transactionCount & 0xFF);
            countBytes[1] = (byte) ((transactionCount >> 8) & 0xFF);
            countBytes[2] = (byte) ((transactionCount >> 16) & 0xFF);
            countBytes[3] = (byte) ((transactionCount >> 24) & 0xFF);
            fos.write(countBytes);
            System.out.println("Backup created: transaction count saved to data\\backup.dat");
        } catch (IOException e) {
            System.out.println("Error creating backup: " + e.getMessage());
        }
    }

    // Load backup using byte streams
    public synchronized void loadBackupUsingByteStreams() {
        File file = new File("data\\backup.dat");
        if (!file.exists()) {
            System.out.println("Backup file data\\backup.dat not found.");
            return;
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] bytes = new byte[4];
            int bytesRead = fis.read(bytes);
            if (bytesRead != 4) {
                System.out.println("Error reading backup file.");
                return;
            }
            int count = ((bytes[3] & 0xFF) << 24) |
                        ((bytes[2] & 0xFF) << 16) |
                        ((bytes[1] & 0xFF) << 8) |
                         (bytes[0] & 0xFF);
            System.out.println("Backup loaded: transaction count = " + count);
            // Note: we don't restore transactions, just demonstrate byte streams
        } catch (IOException e) {
            System.out.println("Error loading backup: " + e.getMessage());
        }
    }

    // Find transaction by ID (returns object)
    public synchronized Transaction findTransaction(int id) {
        for (int i = 0; i < transactionCount; i++) {
            if (transactions[i].getTransactionId() == id) {
                return transactions[i];
            }
        }
        return null;
    }

    // Implement Reportable interface
    @Override
    public synchronized void generateReport() {
        System.out.println("=== Financial Report ===");
        System.out.println("Total Transactions: " + transactionCount);
        System.out.println("Total Income: ₹" + calculateTotalIncome());
        System.out.println("Total Expenses: ₹" + calculateTotalExpenses());
        System.out.println("Balance: ₹" + (calculateTotalIncome() - calculateTotalExpenses()));
        categorySummary();
        System.out.println("========================");
    }

    // Thread control for background report
    public synchronized void startBackgroundReport() {
        ReportThread reportThread = new ReportThread(this);
        reportThread.setPriority(Thread.NORM_PRIORITY);
        reportThread.start();
        System.out.println("Background report thread started.");
        // Optionally wait for thread to finish (if we want to join)
        // Uncomment below to join
        // try {
        //     reportThread.join();
        //     System.out.println("Background report thread finished.");
        // } catch (InterruptedException e) {
        //     Thread.currentThread().interrupt();
        // }
    }

    // Getter for reportRequested (used by thread)
    public synchronized boolean isReportRequested() {
        return reportRequested;
    }

    // Setter for reportRequested
    public synchronized void setReportRequested(boolean requested) {
        this.reportRequested = requested;
        if (requested) {
            notify(); // Notify waiting thread
        }
    }

    // Getter for reportLock
    public synchronized Object getReportLock() {
        return reportLock;
    }
}