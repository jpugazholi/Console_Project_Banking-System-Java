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
        System.out.println("4. Exit");

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
                System.out.println("Thank you for using Banking System!");
                break;

            default:
                System.out.println("Invalid choice!");
        }

    } while (choice != 4);

    scanner.close();
}

    // ================= CREATE ACCOUNT =================

    static void createAccount() {

        System.out.println("===== CREATE ACCOUNT =====");

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

        Account account = accounts.get(accountId);

        if (account == null) {

            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {

            System.out.println("Invalid deposit amount!");
            return;
        }

        account.deposit(amount);

        System.out.println("Amount deposited successfully!");
        System.out.println("Current Balance: ₹" + account.getBalance());
    }

    // ================= WITHDRAW =================

    static void withdraw() {

        System.out.println("\n===== WITHDRAW =====");

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = accounts.get(accountId);

        if (account == null) {

            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {

            System.out.println("Invalid withdrawal amount!");
            return;
        }

        if (amount > account.getBalance()) {

            System.out.println("Insufficient funds!");
            return;
        }

        account.withdraw(amount);

        System.out.println("Amount withdrawn successfully!");
        System.out.println("Current Balance: ₹" + account.getBalance());
    }
}