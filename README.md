# Personal Finance Tracker

## Project Objective
This is a console-based backend-only Java application for managing personal income and expenses, developed as a college mini-project for Object Oriented Programming course.

## Problem Statement
Students need to demonstrate understanding of OOP concepts through a meaningful real-life project without using advanced frameworks or libraries beyond the course syllabus.

## Features
1. Add Income
2. Add Expense
3. Add Recurring Expense
4. View all transactions
5. View income transactions
6. View expense transactions
7. Calculate total income
8. Calculate total expenses
9. Calculate current balance
10. Search transaction by ID
11. Update transaction
12. Delete transaction
13. Category-wise expense summary
14. Save data to file
15. Load data from file
16. Generate financial report
17. Start background report thread
18. Exit application

## Class Hierarchy
- Transaction (abstract class)
  - Income
  - Expense
    - RecurringExpense
- FinanceManager (implements Reportable)
- ReportThread (extends Thread)
- FinanceReport (simple POJO)
- Custom Exceptions: InvalidAmountException, InvalidTransactionException
- Reportable (interface)

## OOP Concepts Used
- Classes and Objects
- Constructors (default and parameterized)
- Method Overloading
- Method Overriding
- Inheritance (single and multilevel)
- Polymorphism (runtime)
- Abstract Class
- Interface
- Encapsulation
- Composition (has-a)
- Static and Inner Classes (demonstrated in FinanceManager)
- Final keyword
- Exception Handling (try-catch-finally, throw, throws, custom exceptions)
- Multithreading (Thread, Runnable, start/run, join, synchronization, wait/notify, thread priority)
- File I/O (FileReader, FileWriter, FileInputStream, FileOutputStream)
- Recursion
- Arrays

## How to Compile
Open command prompt in src directory and run:
```
javac *.java
```

## How to Run
```
java Main
```
Follow the on-screen menu.

## Sample Execution
```
========================================
       PERSONAL FINANCE TRACKER       
========================================
1. Add Income
2. Add Expense
3. View Transactions
4. View Income
5. View Expenses
6. Calculate Balance
7. Search Transaction
8. Update Transaction
9. Delete Transaction
10. Category Summary
11. Save Data
12. Load Data
13. Start Background Report
0. Exit
Enter choice: 1
--- Add Income ---
Enter amount: 5000
Enter description: Salary
Enter category: Salary
Enter date (YYYY-MM-DD): 2026-09-29
Income added successfully with ID: 1
```

## Limitations
- Maximum 500 transactions (array size)
- No persistent storage for recurring frequency beyond text format
- Simple backup only stores transaction count

## Future Improvements
- Use dynamic arrays or vectors (if allowed)
- Add data validation for date format
- Implement sorting of transactions
- Add more reporting features