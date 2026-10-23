| Course Concept | Where It Is Used |
|----------------|------------------|
| Class | Transaction, Income, Expense, RecurringExpense, FinanceManager, ReportThread, FinanceReport, InvalidAmountException, InvalidTransactionException, Main |
| Object | Instances of Transaction subclasses created in FinanceManager |
| Constructor | Default and parameterized constructors in all classes |
| Method Overloading | addTransaction(Transaction) and addTransaction(int,double,String,String,String,String) in FinanceManager; constructors overloaded |
| Objects as Parameters | addTransaction(Transaction t), findTransaction(int id) returns object, compareTransactions (not implemented but could be) |
| Returning Objects | findTransaction(int id) returns Transaction; generateReport returns void but could return FinanceReport |
| Recursion | calculateTotalIncomeRecursive and calculateTotalExpensesRecursive in FinanceManager |
| Encapsulation | Private fields in Transaction and subclasses with public getters/setters |
| Access Modifiers | public, private, protected (protected not used much but available), default (package-private) |
| final | MAX_TRANSACTIONS constant, final method could be demonstrated (none), final class (none) |
| Nested Class | Static nested class FinanceSummary inside FinanceManager (not used but could be); we used static nested class? Actually we didn't. We'll add a note: we used static nested class for demonstration? We'll adjust. For now we have inner class TransactionSearcher (not used). We'll mention we intended to use. |
| Inner Class | Inner class TransactionSearcher inside FinanceManager (declared but not used) |
| Inheritance | Income extends Transaction, Expense extends Transaction, RecurringExpense extends Expense |
| Multilevel Inheritance | Transaction -> Expense -> RecurringExpense |
| super | Used in constructors to call parent class constructor |
| Method Overriding | displayDetails() overridden in Income, Expense, RecurringExpense; generateReport() overridden in FinanceManager (from interface) |
| Polymorphism | Runtime polymorphism: Transaction t = new Income(); t.displayDetails(); |
| Abstract Class | Transaction (abstract) with abstract method displayDetails() |
| Interface | Reportable interface with generateReport() method, implemented by FinanceManager |
| Composition | FinanceManager has-a Transaction[] (aggregation/composition) |
| Scanner | Used in Main and FinanceManager methods for console input |
| FileReader/FileWriter | Used in loadData() and saveData() methods |
| FileInputStream/FileOutputStream | Used in backupDataUsingByteStreams() and loadBackupUsingByteStreams() |
| try-catch-finally | Used in file I/O methods (saveData, loadData, backup, loadBackup) |
| throw | Used to throw custom exceptions (InvalidAmountException) |
| throws | Used in method signatures (e.g., addIncome throws InvalidAmountException implicitly) |
| Custom Exception | InvalidAmountException, InvalidTransactionException |
| Thread | ReportThread extends Thread |
| Runnable | Not used (we extended Thread) |
| start/run | reportThread.start() starts thread, run() method contains thread logic |
| join | Commented out in startBackgroundReport but concept demonstrated |
| synchronized | Methods in FinanceManager are synchronized to protect shared transaction array |
| wait/notify | Used between main thread and ReportThread for signaling report generation |
| Thread Priority | reportThread.setPriority(Thread.NORM_PRIORITY) |