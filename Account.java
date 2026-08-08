
public class Account {

    private int accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;

    //constructor
    public Account(int accountNumber, String accountHolderName, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = balance;

    }

    // getter
    public int getAccountNo() {
        return accountNumber;

    }

    public String getAccountName() {
        return accountHolderName;
    }

    public String getAccountType() {
        return accountType;

    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return String.format("Account Number: %d, Account Holder name: %s, Account Type: %s, Balance: ₹%.2f", accountNumber, accountHolderName, accountType, balance);

    }

    // setter
    public void setBalance(double balance) {
        this.balance = balance;

    }

}
