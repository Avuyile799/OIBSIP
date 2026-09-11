package za.ac.cput;

public class Main {

    public static void main(String[] args) {

        // Create the bank
        Bank bank = new Bank();

        // Create accounts
        Account account1 = new Account(
                "Avuyile",
                "Twesha",
                "1001",
                "1234",
                6000.00
        );

        Account account2 = new Account(
                "Ayabulela",
                "Doe",
                "1002",
                "5678",
                4000.00
        );

        // Add accounts to the bank
        bank.addAccount(account1);
        bank.addAccount(account2);

        // Create the ATM
        ATM atm = new ATM(bank);

        // Start login
        if (atm.login()) {

            // Show ATM menu after successful login
            atm.showMenu();
        }
    }
}