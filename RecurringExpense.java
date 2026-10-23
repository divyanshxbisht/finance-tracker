public class RecurringExpense extends Expense {
    private String frequency; // e.g., "Monthly", "Weekly"

    // Default constructor
    public RecurringExpense() {
        super();
        this.frequency = "";
    }

    // Parameterized constructor
    public RecurringExpense(int transactionId, double amount, String description, String category, String date, String frequency) {
        super(transactionId, amount, description, category, date);
        this.frequency = frequency;
    }

    // Getter and Setter for frequency
    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    // Override displayDetails to show frequency
    @Override
    public void displayDetails() {
        System.out.println("Recurring Expense Transaction:");
        System.out.println("  ID: " + getTransactionId());
        System.out.println("  Amount: ₹" + getAmount());
        System.out.println("  Description: " + getDescription());
        System.out.println("  Category: " + getCategory());
        System.out.println("  Date: " + getDate());
        System.out.println("  Type: " + getType());
        System.out.println("  Frequency: " + frequency);
    }
}