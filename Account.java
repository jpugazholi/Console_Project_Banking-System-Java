public class Account {

    private int accountId;
    private String customerName;
    private double balance;

    public Account(int accountId, String customerName) {

        this.accountId = accountId;
        this.customerName = customerName;
        this.balance = 0.0;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    // ================= DEPOSIT =================

    public void deposit(double amount) {
        balance += amount;
    }

    // ================= WITHDRAW =================

    public boolean withdraw(double amount) {

        if (amount > balance) {

            System.out.println(
                    "Insufficient funds! Available balance: ₹"
                            + balance
            );

            return false;
        }

        balance -= amount;
        return true;
    }

    // ================= DISPLAY ACCOUNT =================

    public void displayAccount() {

        System.out.println("----------------------------");
        System.out.println("Account ID   : " + accountId);
        System.out.println("Account Name : " + customerName);
        System.out.println("Balance      : ₹" + balance);
        System.out.println("----------------------------");
    }
}