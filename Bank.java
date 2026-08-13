
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Bank {

    ArrayList<Account> accounts = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    AccountDAO accountDAO = new AccountDAO();
    TransactionDAO transactionDAO = new TransactionDAO();

    public void createAccount(Account account) {
        if (account.getBalance() < 0) {
            System.out.println("Initial balance cannot be negative!");
            return;
        }

        // for (Account acc : accounts) {
        //     if (acc.getAccountNo() == account.getAccountNo()) {
        //         System.out.println("Account Number is already occupied!!");
        //         return;
        //     }
        // }
        // accounts.add(account);
        // saveAccounts();
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

        Account sender = accountDAO.searchAccount(senderAcc);
        Account receiver = accountDAO.searchAccount(receiverAcc);

        if (sender == null) {
            System.out.println("Sender account not found!");
            return;
        }
        if (receiver == null) {
            System.out.println("Receiver account not found!");
            return;
        }

        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than 0!");
            return;
        }

        // Check balance
        if (sender.getBalance() < amount) {
            System.out.println("Insufficient Balance!");
            return;
        }
        // Transfer

        double newSenderBal = sender.getBalance() - amount;
        double newReceiverBal = receiver.getBalance() + amount;

        boolean senderUpdated = accountDAO.updateBalance(senderAcc, newSenderBal);
        boolean receiverUpdated = accountDAO.updateBalance(receiverAcc, newReceiverBal);

        if (senderUpdated && receiverUpdated) {
            Transaction transaction = new Transaction("Transfer", amount, senderAcc, receiverAcc);
            transactionDAO.saveTransaction(transaction);
            System.out.println("Transfer Successful!");
        } else {
            System.out.println("Transfer failed!");
        }
    }

    public void saveAccounts() {
        try {
            FileWriter writer = new FileWriter("accounts.txt");

            for (Account account : accounts) {
                writer.write(
                        account.getAccountNo() + ","
                        + account.getAccountName() + ","
                        + account.getAccountType() + ","
                        + account.getBalance() + "\n"
                );
            }
            writer.close();
            System.out.println("Account saved successfully!");

        } catch (IOException e) {
            System.out.println("Error while saving accounts!");
        }
    }

    public void loadAccounts() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int accountNo = Integer.parseInt(data[0]);
                String accountName = data[1];
                String accountType = data[2];
                double balance = Double.parseDouble(data[3]);

                Account account = new Account(
                        accountNo,
                        accountName,
                        accountType,
                        balance
                );
                accounts.add(account);

            }
            reader.close();
            System.out.println("Accounts loaded successfully!");
        } catch (IOException e) {
            System.out.println("No previous account data found!");

        }

    }

    public void viewTransactions() {
        ArrayList<Transaction> transDAO = transactionDAO.getAllTransaction();

        if (transactions.isEmpty()) {
            System.out.println("No transaction found!");
            return;

        }

        System.out.println("===Transaction History===");

        for (Transaction transaction : transDAO) {
            System.out.println(transaction);
        }
    }

    public void saveTransaction() {

        try {
            FileWriter writer = new FileWriter("transactions.txt");

            for (Transaction transaction : transactions) {
                writer.write(transaction.getType() + ","
                        + transaction.getAmount() + ","
                        + transaction.getSender() + ","
                        + transaction.getReceiver() + ","
                        + transaction.getDateTime() + "\n"
                );

            }
            writer.close();
            System.out.println("Transaction Saved successfully!!");

        } catch (IOException e) {
            System.out.println("Error while saving transactions");

        }
    }

    public void loadTransaction() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("transactions.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                String type = data[0];
                double amount = Double.parseDouble(data[1]);
                int senderAccount = Integer.parseInt(data[2]);
                int receiverAccount = Integer.parseInt(data[3]);
                String dateTime = data[4];

                Transaction transaction = new Transaction(
                        type,
                        amount,
                        senderAccount,
                        receiverAccount,
                        dateTime
                );
                transactions.add(transaction);
            }
            reader.close();
            System.out.println("Transaction loaded successfully!!");

        } catch (IOException e) {
            System.out.println("Error while loading transaction!");
        }

    }

}
