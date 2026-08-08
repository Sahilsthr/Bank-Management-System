
public class Transaction {

    private String type;
    private double amount;
    private int accountNo;

    public Transaction(String type, double amount, int accountNo) {
        this.type = type;
        this.amount = amount;
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return String.format("Type= %s , Amount= %.2f, Account Number = %d", type, amount, accountNo);

    }

}
