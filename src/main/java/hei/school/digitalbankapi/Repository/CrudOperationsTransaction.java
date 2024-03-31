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
import java.util.UUID;

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
                        resultSet.getObject("id_transaction", UUID.class),
                        resultSet.getTimestamp("transaction_date"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transaction_type"),
                        resultSet.getString("label"),
                        resultSet.getObject("id_account", UUID.class)
                ));
            }
        }

        return allTransaction;
    }

    @Override
    public Transaction save(Transaction toSave) throws SQLException {
        String sql = "INSERT INTO transactions (  id_transaction, transaction_date, amount, transaction_type, label, id_account) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
            insertStatement.setObject(1,toSave.getIdTransaction());
            insertStatement.setTimestamp(2,toSave.getTransactionDate());
            insertStatement.setDouble(3,toSave.getAmount());
            insertStatement.setString(4,toSave.getTransactionType());
            insertStatement.setString(5,toSave.getLabel());
            insertStatement.setObject(6, toSave.getIdAccount());

            insertStatement.executeUpdate();

        }

        return toSave;
    }

    @Override
    public Transaction update(UUID id, Transaction toUpdate) throws SQLException {
        String sql = "UPDATE transactions SET  transaction_date = ?, amount = ?,  transaction_type = ?, label = ? WHERE id_transaction = ?";
        try (PreparedStatement updateSql = connection.prepareStatement(sql)){
            updateSql.setTimestamp(1,toUpdate.getTransactionDate());
            updateSql.setDouble(2,toUpdate.getAmount());
            updateSql.setString(3,toUpdate.getTransactionType());
            updateSql.setString(4,toUpdate.getLabel());
            updateSql.setObject(5, toUpdate.getIdAccount());
            updateSql.setObject(6,id);


            updateSql.executeUpdate();
        }
        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
   String sql = "DELETE FROM transactions WHERE id_transaction = ?";
   try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
       preparedStatement.setObject(1,id);
       preparedStatement.executeUpdate();
   }
    }
}
