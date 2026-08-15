import java.util.ArrayList;
import java.util.List;

public class Account {

    private int accountId;
    private String customerName;
    private double balance;

    // Transaction history
    private List<Transaction> transactions;

    // Constructor
    public Account(int accountId, String customerName) {

        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = 0.0;

        transactions = new ArrayList<>();
    }

    // Get Account ID
    public int getAccountId() {
        return accountId;
    }

    // Get Customer Name
    public String getCustomerName() {
        return customerName;
    }

    // Get Balance
    public double getBalance() {
        return balance;
    }

    // Get Transaction History
    public List<Transaction> getTransactions() {
        return transactions;
    }

    // ================= DEPOSIT =================

    public void deposit(double amount) {

        balance += amount;

        transactions.add(
                new Transaction("DEPOSIT", amount)
        );
    }

    // ================= WITHDRAW =================

    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount > balance) {

            throw new InsufficientFundsException(
                    "Insufficient funds! Available balance: ₹"
                            + balance
            );
        }

        balance -= amount;

        transactions.add(
                new Transaction("WITHDRAW", amount)
        );
    }

    // ================= DISPLAY ACCOUNT =================

    public void displayAccount() {

        System.out.println("----------------------------");
        System.out.println("Account ID   : " + accountId);
        System.out.println("Account Name : " + customerName);
        System.out.println("Balance      : ₹" + balance);
        System.out.println("----------------------------");
    }

    // ================= DISPLAY TRANSACTIONS =================

    public void displayTransactions() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {

            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction : transactions) {

            System.out.println(
                    transaction.getType()
                            + " : ₹"
                            + transaction.getAmount()
            );
        }
    }

    // ================= REVERSE LAST TRANSACTION =================

public void reverseLastTransaction()
        throws InsufficientFundsException {

    if (transactions.isEmpty()) {

        System.out.println("No transaction to reverse!");
        return;
    }

    Transaction lastTransaction =
            transactions.remove(transactions.size() - 1);

    String type = lastTransaction.getType();
    double amount = lastTransaction.getAmount();

    if (type.equals("DEPOSIT")) {

        if (amount > balance) {

            // Put transaction back if reversal is not possible
            transactions.add(lastTransaction);

            throw new InsufficientFundsException(
                    "Cannot reverse deposit. Insufficient balance!"
            );
        }

        balance -= amount;

    } else if (type.equals("WITHDRAW")) {

        balance += amount;
    }

    System.out.println(
            "Last transaction reversed successfully!"
    );

    System.out.println(
            "Reversed: " + type + " ₹" + amount
    );

    System.out.println(
            "Current Balance: ₹" + balance
    );
}
}