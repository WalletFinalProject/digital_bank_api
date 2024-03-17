package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.Transfer;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CrudOperationsTransfer implements CrudOperations<Transfer>{
    private Connection connection;

    public CrudOperationsTransfer(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Transfer> findAll() throws SQLException {
        List<Transfer> allTransfer = new ArrayList<>();
        String sql = "SELECT * FROM transfer";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allTransfer.add(new Transfer(
                        resultSet.getInt("transfer_id"),
                        resultSet.getInt("account_id_recipient"),
                        resultSet.getDouble("amount"),
                        resultSet.getString("transfer_reason"),
                        resultSet.getTimestamp("effective_date"),
                        resultSet.getTimestamp("registration_date"),
                        resultSet.getString("label"),
                        resultSet.getString("status")
                ));
            }
        }


        return allTransfer;
    }

    @Override
    public Transfer save(Transfer toSave) throws SQLException {
        String sql = "INSERT INTO transfer (account_id_recipient,amount ,transfer_reason,effective_date, registration_date, label,status) VALUES (?,?,?,?,?,?,?)";
        try(PreparedStatement insertSql = connection.prepareStatement(sql)){
            insertSql.setInt(1,toSave.getAccountIdRecipient());
            insertSql.setDouble(2,toSave.getAmount());
            insertSql.setString(3,toSave.getTransferReason());
            insertSql.setTimestamp(4,toSave.getEffectiveDate());
            insertSql.setTimestamp(5,toSave.getRegistrationDate());
            insertSql.setString(6,toSave.getLabel());
            insertSql.setString(7,toSave.getStatus());

            insertSql.executeUpdate();
        }
        return toSave;
    }

    @Override
    public void update(int id, Transfer toUpdate) throws SQLException {
      String sql = "UPDATE transfer SET  account_id_recipient = ?,   amount = ?,  transfer_reason = ?, effective_date = ?, registration_date = ?, label = ?  status  = ? WHERE transfer_id = ?";

      try(PreparedStatement updateSql = connection.prepareStatement(sql)){
          updateSql.setInt(1,toUpdate.getAccountIdRecipient());
          updateSql.setDouble(2,toUpdate.getAmount());
          updateSql.setString(3,toUpdate.getTransferReason());
          updateSql.setTimestamp(4,toUpdate.getEffectiveDate());
          updateSql.setTimestamp(5,toUpdate.getRegistrationDate());
          updateSql.setString(6,toUpdate.getLabel());
          updateSql.setString(7,toUpdate.getStatus());
          updateSql.setInt(8,id);

          updateSql.executeUpdate();
      }
    }

    @Override
    public void delete(int id) throws SQLException {
    String sql = "DELETE FROM transfer WHERE transfer_id = ?";

    try (PreparedStatement deleteSql = connection.prepareStatement(sql)){
        deleteSql.setInt(1,id);
        deleteSql.executeUpdate();
    }
    }
}
