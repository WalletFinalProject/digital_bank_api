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
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
public class IncomingTransferController {
    @PostMapping("/incoming-transfer")
    public String incomingTransfer(@RequestParam UUID accountId,
                                   @RequestParam double amount,
                                   @RequestParam String motif,
                                   @RequestParam LocalDateTime effectiveDate,
                                   @RequestParam LocalDateTime registrationDate) {
        try (Connection connection = DatabaseConfiguration.getConnection()) {
            UUID transactionId = UUID.randomUUID();
            PreparedStatement insertTransaction = connection.prepareStatement(
                    "INSERT INTO transactions (id_transaction, transaction_date, amount, transaction_type, label) VALUES (?, ?, ?, ?, ?)"
            );
            insertTransaction.setObject(1, transactionId);
            insertTransaction.setTimestamp(2, java.sql.Timestamp.valueOf(effectiveDate));
            insertTransaction.setDouble(3, amount);
            insertTransaction.setString(4, "credit");
            insertTransaction.setString(5, motif);
            insertTransaction.executeUpdate();
            double currentBalance = getBalance(connection, accountId);
            double newBalance = currentBalance + amount;
            updateBalance(connection, accountId, newBalance);

            return "Incoming transfer successfully completed";
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

    private void updateBalance(Connection connection, UUID accountId, double newBalance) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = ? WHERE id_account = ?");
        statement.setDouble(1, newBalance);
        statement.setObject(2, accountId);
        statement.executeUpdate();
    }
    }

