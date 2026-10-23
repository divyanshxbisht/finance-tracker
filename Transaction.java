public abstract class Transaction {
    private int transactionId;
    private double amount;
    private String description;
    private String category;
    private String date;
    private String type; // "INCOME" or "EXPENSE"

    // Default constructor
    public Transaction() {
        this.transactionId = 0;
        this.amount = 0.0;
        this.description = "";
        this.category = "";
        this.date = "";
        this.type = "";
    }

    // Parameterized constructor
    public Transaction(int transactionId, double amount, String description, String category, String date, String type) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.date = date;
        this.type = type;
    }

    // Getters and Setters
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayDetails();

    // toString for easy printing
    @Override
    public String toString() {
        return transactionId + "|" + type + "|" + amount + "|" + description + "|" + category + "|" + date;
    }
}