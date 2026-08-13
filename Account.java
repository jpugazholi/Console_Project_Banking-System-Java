public class Account {

    private int accountId;
    private String accountHolderName;
    private double balance;

    public Account(int accountId, String accountHolderName) {
        this.accountId = accountId;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {

    if (amount <= 0 || amount > balance) {
        return false;
    }

    balance -= amount;
    return true;
}
    public void displayAccount() {
        System.out.println("----------------------------");
        System.out.println("Account ID   : " + accountId);
        System.out.println("Account Name : " + accountHolderName);
        System.out.println("Balance      : ₹" + balance);
        System.out.println("----------------------------");
    }
}