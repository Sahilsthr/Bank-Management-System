
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TransactionDAO {

    public void saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions"
                + "(type,amount,sender_account, receiver_account, date_time)"
                + " VALUES(?,?,?,?,?)";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, transaction.getType());
            ps.setDouble(2, transaction.getAmount());
            ps.setInt(3, transaction.getSender());
            ps.setInt(4, transaction.getReceiver());
            ps.setString(5, transaction.getDateTime());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Transaction saved successfully!!");
            }

        } catch (Exception e) {
            System.out.println("Error while saving transaction!");
            e.printStackTrace();
        }
    }

    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> transaction = new ArrayList<>();

        String sql = "SELECT * FROM transactions";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {

                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                int sender_account = rs.getInt("sender_account");
                int receiver_account = rs.getInt("receiver_account");
                String date_time = rs.getString("date_time");

                Transaction t = new Transaction(type, amount, sender_account, receiver_account, date_time);
                transaction.add(t);

            }

        } catch (Exception e) {
            System.out.println("Error while loading transaction history!");
        }
        return transaction;
    }

}
