public class FinanceReport {
    private double totalIncome;
    private double totalExpenses;
    private double balance;

    public FinanceReport(double totalIncome, double totalExpenses) {
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.balance = totalIncome - totalExpenses;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getBalance() {
        return balance;
    }

    public void display() {
        System.out.println("=== Financial Report ===");
        System.out.println("Total Income: ₹" + totalIncome);
        System.out.println("Total Expenses: ₹" + totalExpenses);
        System.out.println("Balance: ₹" + balance);
        System.out.println("========================");
    }
}