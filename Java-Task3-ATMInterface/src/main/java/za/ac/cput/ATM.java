package za.ac.cput;

import java.util.ArrayList;
import java.util.Scanner;

public class ATM {

    private Bank bank;
    private Account currentAccount;
    private ArrayList<Transaction> transactions;
    private Scanner scanner;

    public ATM(Bank bank) {
        this.bank = bank;
        this.transactions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Login
    public boolean login() {

        int attempts = 0;

        while (attempts < 3) {

            System.out.print("Enter User ID: ");
            String userId = scanner.nextLine();

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            Account account = bank.authenticate(userId, pin);

            if (account != null) {
                currentAccount = account;

                System.out.println("\nLogin successful!");
                System.out.println("Welcome, "
                        + account.getFirstName() + " "
                        + account.getLastName() + "!");

                return true;
            }

            attempts++;

            System.out.println("Incorrect User ID or PIN.");

            if (attempts < 3) {
                System.out.println("Attempts remaining: " + (3 - attempts));
            }
        }

        System.out.println("\nToo many incorrect attempts.");
        System.out.println("Access denied.");

        return false;
    }

    // Main ATM menu
    public void showMenu() {

        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    showTransactionHistory();
                    break;

                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    System.out.println("Thank you for using the ATM.");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 5);
    }

    // Transaction History
    private void showTransactionHistory() {

        System.out.println("\n===== TRANSACTION HISTORY =====");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    // Withdraw
    private void withdraw() {

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (currentAccount.withdraw(amount)) {

            transactions.add(new Transaction(
                    "Withdrawal",
                    amount,
                    "Cash withdrawal"
            ));

            System.out.println("Withdrawal successful.");
            System.out.println("New balance: R" + currentAccount.getBalance());

        } else {
            System.out.println("Insufficient Funds.");
        }
    }

    // Deposit
    private void deposit() {

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        currentAccount.deposit(amount);

        transactions.add(new Transaction(
                "Deposit",
                amount,
                "Cash deposit"
        ));

        System.out.println("Deposit successful.");
        System.out.println("New balance: R" + currentAccount.getBalance());
    }

    // Transfer
    private void transfer() {

        scanner.nextLine();

        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.nextLine();

        Account recipient = bank.findAccount(recipientId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return;
        }

        if (currentAccount.withdraw(amount)) {

            recipient.deposit(amount);

            transactions.add(new Transaction(
                    "Transfer",
                    amount,
                    "Transfer to account " + recipientId
            ));

            System.out.println("Transfer successful.");
            System.out.println("New balance: R" + currentAccount.getBalance());

        } else {
            System.out.println("Insufficient Funds.");
        }
    }
}