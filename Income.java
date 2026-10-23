public class Income extends Transaction {
    // Default constructor
    public Income() {
        super(0, 0.0, "", "", "", "INCOME");
    }

    // Parameterized constructor
    public Income(int transactionId, double amount, String description, String category, String date) {
        super(transactionId, amount, description, category, date, "INCOME");
    }

    // Override abstract method
    @Override
    public void displayDetails() {
        System.out.println("Income Transaction:");
        System.out.println("  ID: " + getTransactionId());
        System.out.println("  Amount: ₹" + getAmount());
        System.out.println("  Description: " + getDescription());
        System.out.println("  Category: " + getCategory());
        System.out.println("  Date: " + getDate());
        System.out.println("  Type: " + getType());
    }
}