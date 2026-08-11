import java.util.HashMap;
import java.util.Scanner;

public class BankingSystem {

    static HashMap<Integer, Account> accounts = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        createAccount();

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
}