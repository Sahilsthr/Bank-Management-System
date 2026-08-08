
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Bank {

    ArrayList<Account> accounts = new ArrayList<>();

    public void createAccount(Account account) {

        for (Account acc : accounts) {

            if (acc.getAccountNo() == account.getAccountNo()) {
                System.out.println("Account Number is already occupied!!");
                return;
            }
        }
        accounts.add(account);
        saveAccounts();

        System.out.println("Account created successfully!");
    }

    public void viewAccount() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    public void searchAccount(int accountNo) {
        boolean found = false;
        for (Account account : accounts) {
            if (account.getAccountNo() == accountNo) {
                System.out.println(account);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Account Not found!");
        }
    }

    public void depositMoney(int num, double amount) {
        boolean found = false;
        for (Account account : accounts) {
            if (account.getAccountNo() == num) {
                double newBalance = account.getBalance() + amount;
                account.setBalance(newBalance);
                saveAccounts();
                System.out.println("Money deposited successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Account Not found!");
        }
    }

    public void withdrawMoney(int withAcc, double amountWithdraw) {

        boolean found = false;

        for (Account account : accounts) {

            if (account.getAccountNo() == withAcc) {

                found = true;

                if (account.getBalance() >= amountWithdraw) {

                    double newBalance = account.getBalance() - amountWithdraw;
                    account.setBalance(newBalance);
                    saveAccounts();

                    System.out.println("Money withdrawn successfully!");

                } else {

                    System.out.println("Insufficient Balance!");

                }

                break;
            }
        }

        if (!found) {
            System.out.println("Account Not Found!");
        }
    }

    public void deleteAccount(int delAcc) {

        Account toRemove = null;

        for (Account account : accounts) {
            if (account.getAccountNo() == delAcc) {
                toRemove = account;
                break;
            }
        }

        if (toRemove != null) {
            accounts.remove(toRemove);
            saveAccounts();
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account Not Found!");
        }
    }

    public void checkBalance(int checkAcc) {
        boolean found = false;
        for (Account account : accounts) {
            if (account.getAccountNo() == checkAcc) {
                System.out.println("Balance: " + account.getBalance());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Account Not Found!");
        }
    }

    public void transferMoney(int senderAcc, int receiverAcc, double amount) {

        Account sender = null;
        Account receiver = null;

        // Find sender and receiver
        for (Account account : accounts) {

            if (account.getAccountNo() == senderAcc) {
                sender = account;
            }

            if (account.getAccountNo() == receiverAcc) {
                receiver = account;
            }
        }

        // Check both accounts exist
        if (sender == null || receiver == null) {
            System.out.println("One or both accounts not found!");
            return;
        }

        // Check amount
        if (amount <= 0) {
            System.out.println("Invalid transfer amount!");
            return;
        }

        // Check balance
        if (sender.getBalance() < amount) {
            System.out.println("Insufficient Balance!");
            return;
        }

        // Transfer
        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        saveAccounts();

        System.out.println("Transfer Successful!");
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
}
