public class Account {

    private int accountId;
    private String customerName;
    private double balance;

    // Constructor
    public Account(int accountId, String customerName) {
        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = 0.0;
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

    // Deposit
    public void deposit(double amount) {

        if (amount <= 0) {
            System.out.println("Invalid deposit amount!");
            return;
        }

        balance += amount;
    }

    // Withdraw
    public void withdraw(double amount)
            throws InsufficientFundsException {

        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount!");
            return;
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient funds! Available balance: ₹" + balance
            );
        }

        balance -= amount;
    }

    // Display Account
    public void displayAccount() {

        System.out.println("----------------------------");
        System.out.println("Account ID   : " + accountId);
        System.out.println("Account Name : " + customerName);
        System.out.println("Balance      : ₹" + balance);
        System.out.println("----------------------------");
    }
}