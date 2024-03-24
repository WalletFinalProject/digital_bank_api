package hei.school.digitalbankapi.Controller;

import hei.school.digitalbankapi.DatabaseConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController {

    @PostMapping("/make")
    public String makeWithdrawal(@RequestParam int accountId,
                                 @RequestParam double amount) {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            double balance = getBalance(connection, accountId);
            boolean authorizeCredits = isCreditAuthorized(connection, accountId);
            double balanceWithCredit = balance + (authorizeCredits ? getCreditAmount(connection, accountId) : 0.0);

            if (balanceWithCredit >= amount) {
                double newBalance = balance - amount;
                updateBalance(connection, accountId, newBalance);
                return "Withdrawal successfully completed";
            } else {
                return "Error: the balance does not cover the amount requested";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error during query execution";
        }
    }

    private double getBalance(Connection connection, int accountId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT balance FROM accounts WHERE id_account = ?");
        statement.setInt(1, accountId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("balance");
        }
        throw new IllegalArgumentException("Account not found");
    }

    private boolean isCreditAuthorized(Connection connection, int accountId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT authorize_credits FROM accounts WHERE id_account = ?");
        statement.setInt(1, accountId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getBoolean("authorize_credits");
        }
        throw new IllegalArgumentException("Account not found");
    }

    private double getCreditAmount(Connection connection, int accountId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT credit_amount FROM accounts WHERE id_account = ?");
        statement.setInt(1, accountId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("credit_amount");
        }
        throw new IllegalArgumentException("Account not found");
    }

    private void updateBalance(Connection connection, int accountId, double newBalance) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = ? WHERE id_account = ?");
        statement.setDouble(1, newBalance);
        statement.setInt(2, accountId);
        statement.executeUpdate();
    }
}
