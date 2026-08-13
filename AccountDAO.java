
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {

    public void createAccount(Account account) {
        String sql = "INSERT INTO accounts"
                + " (account_no, account_name, account_type, balance)"
                + " VALUES(?,?,?,?)";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, account.getAccountNo());
            ps.setString(2, account.getAccountName());
            ps.setString(3, account.getAccountType());
            ps.setDouble(4, account.getBalance());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Account saved to database");
            }

        } catch (SQLException e) {
            System.out.println("Error while creating account!");
            e.printStackTrace();
        }
    }

    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();

        String sql = "SELECT * FROM accounts";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int accountNo = rs.getInt("account_no");
                String accountName = rs.getString("account_name");
                String accountType = rs.getString("account_type");
                double balance = rs.getDouble("balance");

                Account account = new Account(accountNo, accountName, accountType, balance);
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println("Error while loading accounts!");
            e.printStackTrace();
        }

        return accounts;
    }

    public Account searchAccount(int accounNo) {

        String sql = "SELECT * FROM accounts WHERE account_no = ?";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accounNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Account(
                        rs.getInt("account_no"),
                        rs.getString("account_name"),
                        rs.getString("account_type"),
                        rs.getDouble("balance")
                );

            }

        } catch (Exception e) {
            System.out.println("Error while searching account!");
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateBalance(int accountNo, double newBalance) {

        String sql = "UPDATE accounts SET balance = ? WHERE account_no = ?";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, accountNo);

            int rows = ps.executeUpdate();

            return rows > 0;
        } catch (Exception e) {
            System.out.println("Error while updating balance!");
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteAccount(int accountNo) {

        String sql = "DELETE FROM accounts WHERE account_no = ?";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountNo);
            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Error while deleting account");
            e.printStackTrace();
            return false;
        }

    }

    public boolean transferMoney(int senderAcc, int receiverAcc, double amount) {

        Connection con = null;

        try {

            con = DatabaseConnection.getConnection();
            con.setAutoCommit(false);

            //get sender balance\
            String selectSQL = "SELECT balance FROM WHERE account_no = ?";

            PreparedStatement senderPS = con.prepareStatement(selectSQL);

            ResultSet senderRS = senderPS.executeQuery();

            if (!senderRS.next()) {
                System.out.println("Sender account not found!");
                con.rollback();
                return false;
            }

            double senderBalance = senderRS.getDouble("balance");

            //get receiver balance 
            PreparedStatement receiverPS = con.prepareStatement(selectSQL);
            ResultSet receiverRS = receiverPS.executeQuery();

            if (!receiverRS.next()) {
                System.out.println("Receiver account is not found!");
                con.rollback();
                return false;

            }

            double receiverBalance = senderRS.getDouble("balance");

            if (amount < 0) {
                System.out.println("Transfer amount must  be greater than zero!");
                con.rollback();
                return false;

            }

            if (amount > senderBalance) {
                System.out.println("Insufficient Balance!!");
                con.rollback();
                return false;
            }

            //Update sender and receiver
            String updateSQL = "UPDATE accounts SET balance = ? WHERE account_no = ?";

            PreparedStatement updateSender = con.prepareStatement(updateSQL);
            updateSender.setDouble(1, senderBalance - amount);
            updateSender.setInt(2, senderAcc);
            int senderRows = updateSender.executeUpdate();

            PreparedStatement updateReceiver = con.prepareStatement(updateSQL);
            updateReceiver.setDouble(1, receiverBalance + amount);
            updateReceiver.setInt(2, receiverAcc);

            int receiverRow = updateReceiver.executeUpdate();

            //check both update
            if (senderRows > 0 && receiverRow > 0) {

                con.commit();
                System.out.println("Transfer successfully");
                return true;
            } else {
                con.rollback();

                System.out.println("Transfer failed");
                return false;
            }
        } catch (Exception e) {
            try {
                if (con != null) {
                    con.rollback();

                }

            } catch (Exception rollbackError) {
                rollbackError.printStackTrace();
            }

            System.out.println("Transaction failed!");
            e.printStackTrace();

            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
