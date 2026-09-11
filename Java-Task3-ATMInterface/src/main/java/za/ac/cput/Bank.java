package za.ac.cput;

import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    // Add an account
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Find an account using User ID
    public Account findAccount(String userId) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)) {
                return account;
            }
        }

        return null;
    }

    // Check if User ID and PIN are correct
    public Account authenticate(String userId, String pin) {
        for (Account account : accounts) {
            if (account.getUserId().equals(userId)
                    && account.getPin().equals(pin)) {
                return account;
            }
        }

        return null;
    }
}



















