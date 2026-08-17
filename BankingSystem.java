import java.util.LinkedHashMap;
import java.util.Scanner;

public class BankingSystem {

    static LinkedHashMap<Integer, Account> accounts =
            new LinkedHashMap<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Exit");

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
                    System.out.println("Thank you for using Banking System!"
                    );
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 3);

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

        Account account =
                new Account(accountId, name);

        accounts.put(accountId, account);

        System.out.println(
                "Account created successfully!"
        );

        account.displayAccount();
    }

    static void deposit() {

    System.out.println("\n===== DEPOSIT =====");

    System.out.print("Enter Account ID: ");
    int accountId = scanner.nextInt();

    Account account = accounts.get(accountId);

    if (account == null) {

        System.out.println(
                "Account not found! Account ID: " + accountId
        );

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
    System.out.println(
            "Current Balance: ₹" + account.getBalance()
    );
}
}