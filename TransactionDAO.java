
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
            e.printStackTrace();
        }
    }

    public ArrayList<Transaction> getAllTransaction() {
        ArrayList<Transaction> transaction = new ArrayList<>();

        String sql = "SELECT * FROM transactions";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt(1);
                String type = rs.getString(2);
                Double amount = rs.getDouble(3);
                int sender_account = rs.getInt(4);
                int receiver_account = rs.getInt(5);
                String date_time = rs.getString(6);

                Transaction t = new Transaction(type, amount, sender_account, receiver_account, date_time);
                transaction.add(t);

            }

        } catch (Exception e) {
            System.out.println("Error while loading transaction history!");
        }
        return transaction;
    }

}
