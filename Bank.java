
import java.util.ArrayList;

public class Bank {

    AccountDAO accountDAO = new AccountDAO();
    TransactionDAO transactionDAO = new TransactionDAO();

    public void createAccount(Account account) {
        if (account.getBalance() < 0) {
            System.out.println("Initial balance cannot be negative!");
            return;
        }
        accountDAO.createAccount(account);
        System.out.println("Account created successfully!");
    }

    public void viewAccount() {
        ArrayList<Account> dbAccounts = accountDAO.getAllAccounts();

        for (Account account : dbAccounts) {
            System.out.println(account);
        }

    }

    public void searchAccount(int accountNo) {
        Account account = accountDAO.searchAccount(accountNo);

        if (account != null) {
            System.out.println(account);

        } else {
            System.out.println("account not found!");
        }

    }

    public void depositMoney(int accountNo, double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0!");
            return;
        }
        Account account = accountDAO.searchAccount(accountNo);

        if (account == null) {
            System.out.println("Account not found!");
            return;

        }
        double newBalance = account.getBalance() + amount;

        boolean updated = accountDAO.updateBalance(accountNo, newBalance);

        if (updated) {
            Transaction transaction = new Transaction("Deposit", amount, accountNo, -1);
            transactionDAO.saveTransaction(transaction);
            System.out.println("Money deposited successfully!");
        } else {
            System.out.println("Failed to desposit money");
        }

    }

    public void withdrawMoney(int accountNo, double amountWithdraw) {
        if (amountWithdraw <= 0) {
            System.out.println("Deposit amount must be greater than 0!");
            return;
        }

        Account account = accountDAO.searchAccount(accountNo);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        if (account.getBalance() < amountWithdraw) {
            System.out.println("Insufficient balance!");
            return;
        }

        double newBalance = account.getBalance() - amountWithdraw;
        boolean updated = accountDAO.updateBalance(accountNo, newBalance);

        if (updated) {
            Transaction transaction = new Transaction("Withdraw", amountWithdraw, accountNo, -1);
            transactionDAO.saveTransaction(transaction);
            System.out.println("Money withdrawn successfully!");

        } else {
            System.out.println("Failed to withdraw money!");
        }

    }

    public void deleteAccount(int delAcc) {

        boolean deleted = accountDAO.deleteAccount(delAcc);

        if (deleted) {

            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account Not Found!");
        }
    }

    public void checkBalance(int accountNo) {
        Account account = accountDAO.searchAccount(accountNo);

        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        System.out.println("Balance: ₹" + account.getBalance());

    }

    public void transferMoney(int senderAcc, int receiverAcc, double amount) {

        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than 0!");
            return;
        }
        boolean transferred = accountDAO.transferMoney(senderAcc, receiverAcc, amount);

        if (transferred) {
            Transaction transaction = new Transaction("Transfer", amount, senderAcc, receiverAcc);

            transactionDAO.saveTransaction(transaction);

            System.out.println("Transefer Successfull!");

        } else {
            System.out.println("Transfer failed!!");
        }

    }

    public void viewTransactions() {
        ArrayList<Transaction> transDAO = transactionDAO.getAllTransaction();

        if (transDAO.isEmpty()) {
            System.out.println("No transaction found!");
            return;

        }

        System.out.println("===Transaction History===");

        for (Transaction transaction : transDAO) {
            System.out.println(transaction);
        }
    }

}
