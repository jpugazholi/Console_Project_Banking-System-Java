import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BankingSystem {

    static HashMap<Integer, Account> accounts = new HashMap<>();

    static HashMap<String, java.util.List<Integer>> customerIndex =
            new HashMap<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Close Account");
            System.out.println("6. Transfer");
            System.out.println("7. Find Customer Accounts");
            System.out.println("8. Reverse Last Transaction");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    closeAccount();
                    break;

                case 6:
                    transfer();
                    break;

                case 7:
                    findCustomerAccounts();
                    break;

                case 8:
                    reverseLastTransaction();
                    break;

                case 9:
                    System.out.println("Thank you for using Banking System!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 9);

        scanner.close();
    }

    // ================= CREATE ACCOUNT =================

    static void createAccount() {

        System.out.println("\n===== CREATE ACCOUNT =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        if (accounts.containsKey(accountId)) {

            System.out.println("Account ID already exists!");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        Account account = new Account(accountId, name);

        accounts.put(accountId, account);

        customerIndex
                .computeIfAbsent(name, k -> new ArrayList<>())
                .add(accountId);

        System.out.println("Account created successfully!");

        account.displayAccount();
    }

    // ================= DEPOSIT =================

    static void deposit() {

        System.out.println("\n===== DEPOSIT =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        try {

            Account account = findAccount(accountId);

            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();

            if (amount <= 0) {

                System.out.println("Invalid deposit amount!");
                return;
            }

            account.deposit(amount);

            System.out.println("Amount deposited successfully!");
            System.out.println(
                    "Current Balance: ₹" + account.getBalance()
            );

        } catch (AccountNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= WITHDRAW =================

    static void withdraw() {

        System.out.println("\n===== WITHDRAW =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        try {

            Account account = findAccount(accountId);

            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();

            if (amount <= 0) {

                System.out.println("Invalid withdrawal amount!");
                return;
            }

            account.withdraw(amount);

            System.out.println("Amount withdrawn successfully!");
            System.out.println(
                    "Current Balance: ₹" + account.getBalance()
            );

        } catch (AccountNotFoundException |  InsufficientFundsException e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= CHECK BALANCE =================

    static void checkBalance() {

        System.out.println("\n===== CHECK BALANCE =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        try {

            Account account = findAccount(accountId);

            System.out.println("----------------------------");
            System.out.println(
                    "Account ID   : " + account.getAccountId()
            );
            System.out.println(
                    "Account Name : " + account.getCustomerName()
            );
            System.out.println(
                    "Balance      : ₹" + account.getBalance()
            );
            System.out.println("----------------------------");

        } catch (AccountNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= CLOSE ACCOUNT =================

    static void closeAccount() {

        System.out.println("\n===== CLOSE ACCOUNT =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        try {

            Account account = findAccount(accountId);

            accounts.remove(accountId);

            // Remove account ID from customer index
            String customerName = account.getCustomerName();

            java.util.List<Integer> accountIds =
                    customerIndex.get(customerName);

            if (accountIds != null) {

                accountIds.remove(Integer.valueOf(accountId));

                if (accountIds.isEmpty()) {
                    customerIndex.remove(customerName);
                }
            }

            System.out.println("Account closed successfully!");

        } catch (AccountNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= TRANSFER =================

    static void transfer() {

        System.out.println("\n===== TRANSFER =====");

        System.out.print("Enter Sender Account ID: ");
        int fromId = scanner.nextInt();

        System.out.print("Enter Receiver Account ID: ");
        int toId = scanner.nextInt();

        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();

        try {

            // Check both accounts BEFORE withdrawing
            Account fromAccount = findAccount(fromId);
            Account toAccount = findAccount(toId);

            if (fromId == toId) {

                System.out.println(
                        "Cannot transfer to the same account!"
                );
                return;
            }

            if (amount <= 0) {

                System.out.println(
                        "Invalid transfer amount!"
                );
                return;
            }

            if (amount > fromAccount.getBalance()) {

                throw new InsufficientFundsException(
                        "Insufficient funds! Available balance: ₹"
                                + fromAccount.getBalance()
                );
            }

            // Withdraw from sender
            fromAccount.withdraw(amount);

            try {

                // Deposit into receiver
                toAccount.deposit(amount);

                System.out.println(
                        "Transfer successful!"
                );

                System.out.println(
                        "Sender Balance: ₹"
                                + fromAccount.getBalance()
                );

                System.out.println(
                        "Receiver Balance: ₹"
                                + toAccount.getBalance()
                );

            } catch (Exception e) {

                // Rollback sender balance
                fromAccount.deposit(amount);

                System.out.println(
                        "Transfer failed!"
                );

                System.out.println(
                        "Transaction rolled back successfully."
                );
            }

        } catch (AccountNotFoundException | InsufficientFundsException e) {

            System.out.println(e.getMessage());
        }
    }

    // ================= REVERSE LAST TRANSACTION =================

static void reverseLastTransaction() {

    System.out.println("\n===== REVERSE LAST TRANSACTION =====");

    System.out.print("Enter Account ID: ");
    int accountId = scanner.nextInt();

    try {

        Account account = findAccount(accountId);

        account.reverseLastTransaction();

    } catch (AccountNotFoundException | InsufficientFundsException e) {

        System.out.println(e.getMessage());
    }
}

    // ================= FIND CUSTOMER ACCOUNTS =================

static void findCustomerAccounts() {

    System.out.println("\n===== CUSTOMER ACCOUNT SEARCH =====");

    scanner.nextLine();

    System.out.print("Enter Customer Name: ");
    String name = scanner.nextLine();

    java.util.List<Integer> accountIds =
            customerIndex.get(name);

    if (accountIds == null || accountIds.isEmpty()) {

        System.out.println(
                "No accounts found for customer: " + name
        );

        return;
    }

    System.out.println(
            "Accounts belonging to " + name + ":"
    );

    for (Integer accountId : accountIds) {

        Account account = accounts.get(accountId);

        if (account != null) {

            System.out.println(
                    "Account ID: " + accountId
                            + " | Balance: ₹"
                            + account.getBalance()
            );
        }
    }
}

    // ================= FIND ACCOUNT =================

    static Account findAccount(int accountId)
            throws AccountNotFoundException {

        if (!accounts.containsKey(accountId)) {

            throw new AccountNotFoundException(
                    "Account not found! Account ID: " + accountId
            );
        }

        return accounts.get(accountId);
    }
}