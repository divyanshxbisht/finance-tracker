# Test Cases for Personal Finance Tracker

1. Add Income with valid amount
   - Input: Amount=1000, Description="Salary", Category="Salary", Date="2026-09-29"
   - Expected: Income added with ID 1

2. Add Expense with valid amount
   - Input: Amount=500, Description="Food", Category="Food", Date="2026-09-29"
   - Expected: Expense added with ID 2

3. Add Income with invalid amount (negative)
   - Input: Amount=-100
   - Expected: InvalidAmountException caught, error message shown

4. Add Expense with invalid amount (zero)
   - Input: Amount=0
   - Expected: InvalidAmountException caught

5. Add Recurring Expense
   - Input: Amount=3000, Description="Rent", Category="Rent", Date="2026-09-29", Frequency="Monthly"
   - Expected: Recurring expense added with next ID

6. View Transactions when empty
   - Expected: "No transactions to display."

7. View Transactions after adding
   - Expected: List of transactions with IDs and details

8. View Income Transactions
   - Expected: Only income transactions shown

9. View Expense Transactions
   - Expected: Only expense transactions shown

10. Calculate Balance with no transactions
    - Expected: Total Income=0, Total Expenses=0, Balance=0

11. Calculate Balance after adding income and expense
    - Expected: Balance = income - expense

12. Search Transaction by existing ID
    - Input: ID=1
    - Expected: Transaction details displayed

13. Search Transaction by non-existing ID
    - Input: ID=999
    - Expected: "Transaction not found."

14. Update Transaction amount
    - Input: ID=1, new amount=2000
    - Expected: Transaction updated

15. Update Transaction with invalid amount
    - Input: ID=1, amount=-500
    - Expected: Error message, amount unchanged

16. Delete Transaction
    - Input: ID=1
    - Expected: Transaction removed, IDs shift

17. Delete non-existing transaction
    - Input: ID=999
    - Expected: "Transaction not found."

18. Category Summary
    - Expected: Summary per category with totals

19. Save Data to file
    - Expected: File data/transactions.txt created with pipe-separated records

20. Load Data from file
    - Expected: Transactions loaded and displayed count

21. Load Data when file missing
    - Expected: Message "File not found. Starting with empty data."

22. Custom Exception triggering
    - Already covered in invalid amount tests

23. Thread execution (Start Background Report)
    - Expected: Background thread starts and waits for request

24. Thread communication (notify/wait)
    - Trigger report request via setting flag (not directly in menu but internal)

25. Synchronization test (conceptual)
    - Multiple threads accessing transaction array; safety via synchronized methods

26. Recursion in total calculation
    - Verify recursive methods return correct sums

27. Array shifting after delete
    - Ensure no nulls remain except last element

28. Maximum transaction limit
    - Try to add beyond 500 transactions; expect warning

29. Byte stream backup
    - Create backup.dat and verify it contains transaction count

30. Byte stream load backup
    - Read backup.dat and display count