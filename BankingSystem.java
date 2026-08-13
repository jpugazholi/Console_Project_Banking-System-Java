import java.util.HashMap;
import java.util.Scanner;

public class BankingSystem {

    static HashMap<Integer, Account> accounts = new HashMap<>();
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
            System.out.println("6. Exit");

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
                    System.out.println(
                            "Thank you for using Banking System!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

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

        } catch (AccountNotFoundException |
                 InsufficientFundsException e) {

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

            findAccount(accountId);

            accounts.remove(accountId);

            System.out.println("Account closed successfully!");

        } catch (AccountNotFoundException e) {

            System.out.println(e.getMessage());
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