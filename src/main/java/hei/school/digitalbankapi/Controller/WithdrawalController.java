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
import java.util.UUID;

@RestController
@RequestMapping("/withdrawals")
public class WithdrawalController {

    @PostMapping("/make")
    public String makeWithdrawal(@RequestParam UUID idAccount,
                                 @RequestParam double amount) {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            double balance = getBalance(connection, idAccount);
            boolean authorizeCredits = isCreditAuthorized(connection, idAccount);
            double netMonthlySalary = getNetMonthlySalary(connection, idAccount);
            double balanceWithCredit = balance + (authorizeCredits ? getCreditAmount(connection, idAccount) : 0.0);

            if (amount > (netMonthlySalary / 3)) {
                return "Error: The withdrawal amount cannot exceed one-third of the monthly salary";
            }

            if (balanceWithCredit >= amount) {
                double newBalance = balance - amount;
                updateBalance(connection, idAccount, newBalance);
                return "Withdrawal successfully completed. New balance: " + newBalance;
            } else {
                return "Error: the balance does not cover the amount requested";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error during query execution";
        }
    }

    private double getBalance(Connection connection, UUID accountId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT balance FROM accounts WHERE id_account = ?");
        statement.setObject(1, accountId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("balance");
        }
        throw new IllegalArgumentException("Account not found");
    }

    private boolean isCreditAuthorized(Connection connection, UUID accountId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT authorize_credits FROM accounts WHERE id_account = ?");
        statement.setObject(1, accountId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getBoolean("authorize_credits");
        }
        throw new IllegalArgumentException("Account not found");
    }

    private double getCreditAmount(Connection connection, UUID accountId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT credit_amount FROM accounts WHERE id_account = ?");
        statement.setObject(1, accountId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("credit_amount");
        }
        throw new IllegalArgumentException("Account not found");
    }

    private double getNetMonthlySalary(Connection connection, UUID accountId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT net_monthly_salary FROM accounts WHERE id_account = ?");
        statement.setObject(1, accountId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("net_monthly_salary");
        }
        throw new IllegalArgumentException("Account not found");
    }

    private void updateBalance(Connection connection, UUID accountId, double newBalance) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = ? WHERE id_account = ?");
        statement.setDouble(1, newBalance);
        statement.setObject(2, accountId);
        statement.executeUpdate();
    }
}
