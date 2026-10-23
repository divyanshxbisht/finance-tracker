import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FinanceManager financeManager = new FinanceManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("========================================");
            System.out.println("       PERSONAL FINANCE TRACKER       ");
            System.out.println("========================================");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expense");
            System.out.println("3. View Transactions");
            System.out.println("4. View Income");
            System.out.println("5. View Expenses");
            System.out.println("6. Calculate Balance");
            System.out.println("7. Search Transaction");
            System.out.println("8. Update Transaction");
            System.out.println("9. Delete Transaction");
            System.out.println("10. Category Summary");
            System.out.println("11. Save Data");
            System.out.println("12. Load Data");
            System.out.println("13. Start Background Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    financeManager.addIncome(scanner);
                    break;
                case 2:
                    financeManager.addExpense(scanner);
                    break;
                case 3:
                    financeManager.viewTransactions();
                    break;
                case 4:
                    financeManager.viewIncome();
                    break;
                case 5:
                    financeManager.viewExpenses();
                    break;
                case 6:
                    financeManager.calculateBalance();
                    break;
                case 7:
                    financeManager.searchTransaction(scanner);
                    break;
                case 8:
                    financeManager.updateTransaction(scanner);
                    break;
                case 9:
                    financeManager.deleteTransaction(scanner);
                    break;
                case 10:
                    financeManager.categorySummary();
                    break;
                case 11:
                    financeManager.saveData();
                    break;
                case 12:
                    financeManager.loadData();
                    break;
                case 13:
                    financeManager.startBackgroundReport();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }

        scanner.close();
    }
}