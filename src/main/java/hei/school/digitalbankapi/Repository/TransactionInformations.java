package hei.school.digitalbankapi.Repository;


import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TransactionInformations {

    private Connection connection;

    public List<Transaction> findByid(UUID id) throws SQLException{
        List<Transaction> allTransactions = new ArrayList<>();
        String sql = "SELECT " +
                "    t.id_transaction, " +
                "    t.transaction_date, " +
                "    t.amount, " +
                "    t.transaction_type, " +
                "    t.label, " +
                "    a.id_account, " +
                "    a.authorize_credits, " +
                "    a.creation_date AS account_creation_date, " +
                "    a.update_date AS account_update_date, " +
                "    a.client_name, " +
                "    a.client_firstname, " +
                "    a.birth_date, " +
                "    a.net_monthly_salary, " +
                "    a.balance, " +
                "    a.credit_amount " +
                "FROM " +
                "    transactions t " +
                "JOIN " +
                "    accounts a ON t.id_transaction = a.id_transaction " +
                "WHERE " +
                "    t.id_transaction = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Transaction transaction = new Transaction(
                        UUID.fromString(resultSet.getString("id_transaction")),
                        resultSet.getTimestamp("transaction_date"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transaction_type"),
                        resultSet.getString("label"),
                        new Account(
                                UUID.fromString(resultSet.getString("id_account")),
                                resultSet.getBoolean("authorize_credits"),
                                resultSet.getDate("account_creation_date"),
                                resultSet.getDate("account_update_date"),
                                resultSet.getString("client_name"),
                                resultSet.getString("client_firstname"),
                                resultSet.getDate("birth_date"),
                                resultSet.getDouble("net_monthly_salary"),
                                resultSet.getDouble("balance"),
                                resultSet.getDouble("credit_amount")
                        ).getIdAccount()
                );
                allTransactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return allTransactions;
    }
}
