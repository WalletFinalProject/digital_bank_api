package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class CrudOperationsAccount implements CrudOperations<Account>{
    private Connection connection;

    public CrudOperationsAccount(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> allAccount = new ArrayList<>();
        String sql = "SELECT * FROM account";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allAccount.add(new Account(
                        resultSet.getInt("account_id"),
                        resultSet.getString("client_name"),
                        resultSet.getString("client_firstName"),
                        resultSet.getDate("birth_date"),
                        resultSet.getDouble("monthly_pay"),
                        resultSet.getString("overdrawn_status"),
                        resultSet.getString("account_type")
                ));
            }
        }


        return allAccount;
    }

    @Override
    public Account save(Account toSave) throws SQLException {
        String sql = "INSERT INTO account (client_name,client_firstname,birth_date,monthly_pay, overdrawn_status, account_type) VALUES (?,?,?,?,?,?)";

        try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
            insertStatement.setString(1,toSave.getClientName());
            insertStatement.setString(2,toSave.getClientFirstName());
            insertStatement.setDate(3,toSave.getBirthDate());
            insertStatement.setDouble(4,toSave.getMonthlyPay());
            insertStatement.setString(5,toSave.getOverdrawnStatus());
            insertStatement.setString(6,toSave.getAccountType());

            insertStatement.executeUpdate();

        }

        return toSave;
    }

    @Override
    public void update(int id, Account toUpdate) throws SQLException {
        String sql = "UPDATE account SET  client_name = ?,   client_firstname = ?,  birth_date = ?, monthly_pay = ?, overdrawn_status = ?, account_type = ? WHERE account_id = ?";
        try (PreparedStatement updateSql = connection.prepareStatement(sql)){
            updateSql.setString(1,toUpdate.getClientName());
            updateSql.setString(2,toUpdate.getClientFirstName());
            updateSql.setDate(3,toUpdate.getBirthDate());
            updateSql.setDouble(4,toUpdate.getMonthlyPay());
            updateSql.setString(5, toUpdate.getOverdrawnStatus());
            updateSql.setString(6,toUpdate.getAccountType());
            updateSql.setInt(7,id);

            updateSql.executeUpdate();
        }

    }
    @Override
    public void delete(int id) throws SQLException {
       String sql = "DELETE FROM account WHERE account_id = ?";
         try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
             preparedStatement.setInt(1,id);
             preparedStatement.executeUpdate();
         }
    }
}
