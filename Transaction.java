
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private int senderAccount;
    private int receiveAccount;
    private String dateTime;

    public Transaction(String type, double amount, int senderAccount, int receiverAccount) {
        this.type = type;
        this.amount = amount;
        this.senderAccount = senderAccount;
        this.receiveAccount = receiverAccount;
        this.dateTime = dateTime;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        this.dateTime = LocalDateTime.now().format(formatter);

    }

    @Override
    public String toString() {
        return String.format("Type= %s , Amount= %.2f, Sender Account = %d, Receiver Account= %d, dateTime = %s", type, amount, senderAccount, receiveAccount, dateTime);

    }

}
