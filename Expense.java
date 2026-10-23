public class Expense extends Transaction {
    // Default constructor
    public Expense() {
        super(0, 0.0, "", "", "", "EXPENSE");
    }

    // Parameterized constructor
    public Expense(int transactionId, double amount, String description, String category, String date) {
        super(transactionId, amount, description, category, date, "EXPENSE");
    }

    // Override abstract method
    @Override
    public void displayDetails() {
        System.out.println("Expense Transaction:");
        System.out.println("  ID: " + getTransactionId());
        System.out.println("  Amount: ₹" + getAmount());
        System.out.println("  Description: " + getDescription());
        System.out.println("  Category: " + getCategory());
        System.out.println("  Date: " + getDate());
        System.out.println("  Type: " + getType());
    }
}