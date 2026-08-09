
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private int senderAccount;
    private int receiverAccount;
    private String dateTime;

    //constructor for new transaction
    public Transaction(String type, double amount, int senderAccount, int receiverAccount) {
        this.type = type;
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateTime = LocalDateTime.now().format(formatter);

    }

    //constructor for load transaction
    public Transaction(String type, double amount, int senderAccount, int receiverAccount, String dateTime) {
        this.type = type;
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.dateTime = dateTime;

    }

    @Override
    public String toString() {
        return String.format("Type= %s , Amount= %.2f, Sender Account = %d, Receiver Account= %d, dateTime = %s", type, amount, senderAccount, receiverAccount, dateTime);

    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public int getSender() {
        return senderAccount;
    }

    public int getReceiver() {
        return receiverAccount;
    }

    public String getDateTime() {
        return dateTime;
    }
}
