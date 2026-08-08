import java.util.ArrayList;
import java.util.Scanner;

public class BankingSystem {

    static ArrayList<Account> accounts = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
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
                    System.out.println("Thank you for using Banking System!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        scanner.close();
    }

    // ================= CREATE ACCOUNT =================

    static void createAccount() {

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        scanner.nextLine();

        // Manual loop for duplicate account validation
        for (Account account : accounts) {

            if (account.getAccountId() == accountId) {

                System.out.println("Account ID already exists!");
                return;
            }
        }

        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        Account account = new Account(accountId, name);

        accounts.add(account);

        System.out.println("Account created successfully!");
    }

    // ================= DEPOSIT =================

    static void deposit() {

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {

            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {

            System.out.println("Invalid amount!");
            return;
        }

        account.deposit(amount);

        System.out.println("Amount deposited successfully!");
        System.out.println("Current Balance: ₹" + account.getBalance());
    }

    // ================= WITHDRAW =================

    static void withdraw() {

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {

            System.out.println("Account not found!");
            return;
        }

        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (account.withdraw(amount)) {

            System.out.println("Amount withdrawn successfully!");
            System.out.println("Current Balance: ₹" + account.getBalance());

        } else {

            System.out.println("Withdrawal failed!");
            System.out.println("Check amount or available balance.");
        }
    }

    // ================= CHECK BALANCE =================

    static void checkBalance() {

        System.out.print("Enter Account ID: ");
        int accountId = scanner.nextInt();

        Account account = findAccount(accountId);

        if (account == null) {

            System.out.println("Account not found!");
            return;
        }

        account.displayAccount();
    }

    // ================= FIND ACCOUNT =================

    static Account findAccount(int accountId) {

        // Manual search loop - required for Week 1

        for (Account account : accounts) {

            if (account.getAccountId() == accountId) {

                return account;
            }
        }

        return null;
    }
}