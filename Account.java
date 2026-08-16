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

    public void displayAccount() {

        System.out.println("----------------------------");
        System.out.println("Account ID   : " + accountId);
        System.out.println("Account Name : " + customerName);
        System.out.println("Balance      : ₹" + balance);
        System.out.println("----------------------------");
    }
}