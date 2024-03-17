package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CrudOperationsTransaction implements CrudOperations<Transaction>{
    private Connection connection;

    public CrudOperationsTransaction(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Transaction> findAll() throws SQLException {
        List<Transaction> allTransaction = new ArrayList<>();
        String sql = "SELECT * FROM transaction";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allTransaction.add(new Transaction(
                        resultSet.getInt("transaction_id"),
                        resultSet.getTimestamp("transaction_date"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transaction_type"),
                        resultSet.getInt("account_id")
                ));
            }
        }


        return allTransaction;
    }

    @Override
    public Transaction save(Transaction toSave) throws SQLException {
        String sql = "INSERT INTO account ( transaction_date, amount, transaction_type, account_id) VALUES (?,?,?,?)";

        try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
            insertStatement.setTimestamp(1,toSave.getTransactionDate());
            insertStatement.setDouble(2,toSave.getAmount());
            insertStatement.setString(3,toSave.getTransactionType());
            insertStatement.setInt(4,toSave.getAccountId());

            insertStatement.executeUpdate();

        }

        return toSave;
    }

    @Override
    public void update(int id, Transaction toUpdate) throws SQLException {
        String sql = "UPDATE account SET  transaction_date = ?, amount = ?,  transaction_type = ?, account_id = ? WHERE account_id = ?";
        try (PreparedStatement updateSql = connection.prepareStatement(sql)){
            updateSql.setTimestamp(1,toUpdate.getTransactionDate());
            updateSql.setDouble(2,toUpdate.getAmount());
            updateSql.setString(3,toUpdate.getTransactionType());
            updateSql.setInt(4,toUpdate.getAccountId());
            updateSql.setInt(5,id);


            updateSql.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
   String sql = "DELETE FROM transaction WHERE transaction_id = ?";
   try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
       preparedStatement.setInt(1,id);
       preparedStatement.executeUpdate();
   }
    }
}
