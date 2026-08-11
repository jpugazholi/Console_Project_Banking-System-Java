import java.util.HashMap;
import java.util.Scanner;

public class BankingSystem {

    static HashMap<Integer, Account> accounts = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        createAccount();

        deposit();

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

        // HashMap direct lookup
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
}