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
        String sql = "SELECT * FROM transactions";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allTransaction.add(new Transaction(
                        resultSet.getInt("id_transaction"),
                        resultSet.getTimestamp("transaction_date"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transaction_type"),
                        resultSet.getString("label")
                ));
            }
        }


        return allTransaction;
    }

    @Override
    public Transaction save(Transaction toSave) throws SQLException {
        String sql = "INSERT INTO transactions ( transaction_date, amount, transaction_type, label) VALUES (?,?,?,?)";

        try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
            insertStatement.setTimestamp(1,toSave.getTransactionDate());
            insertStatement.setDouble(2,toSave.getAmount());
            insertStatement.setString(3,toSave.getTransactionType());
            insertStatement.setString(4,toSave.getLabel());

            insertStatement.executeUpdate();

        }

        return toSave;
    }

    @Override
    public Transaction update(int id, Transaction toUpdate) throws SQLException {
        String sql = "UPDATE transactions SET  transaction_date = ?, amount = ?,  transaction_type = ?, label = ? WHERE id_transaction = ?";
        try (PreparedStatement updateSql = connection.prepareStatement(sql)){
            updateSql.setTimestamp(1,toUpdate.getTransactionDate());
            updateSql.setDouble(2,toUpdate.getAmount());
            updateSql.setString(3,toUpdate.getTransactionType());
            updateSql.setString(4,toUpdate.getLabel());
            updateSql.setInt(5,id);


            updateSql.executeUpdate();
        }
        return toUpdate;
    }

    @Override
    public void delete(int id) throws SQLException {
   String sql = "DELETE FROM transactions WHERE id_transaction = ?";
   try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
       preparedStatement.setInt(1,id);
       preparedStatement.executeUpdate();
   }
    }
}
