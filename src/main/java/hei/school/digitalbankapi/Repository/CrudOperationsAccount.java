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
        String sql = "SELECT * FROM accounts";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                allAccount.add(new Account(
                        resultSet.getInt("id_account"),
                        resultSet.getBoolean("authorize_credits"),
                        resultSet.getDate("creation_date"),
                        resultSet.getDate("update_date"),
                        resultSet.getString("client_name"),
                        resultSet.getString("client_firstname"),
                        resultSet.getDate("birth_date"),
                        resultSet.getDouble("net_monthly_salary"),
                        resultSet.getInt("id_transaction"),
                        resultSet.getDouble("balance"),
                        resultSet.getDouble("credit_amount")
                ));
            }
        }


        return allAccount;
    }

    @Override
    public Account save(Account toSave) throws SQLException {
        String sql = "INSERT INTO accounts (authorize_credits,creation_date,update_date,client_name, client_firstname,birth_date, net_monthly_salary,id_transaction,balance,credit_amount) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try(PreparedStatement insertStatement = connection.prepareStatement(sql)){
            insertStatement.setBoolean(1,toSave.isAuthorizeCredits());
            insertStatement.setDate(2,toSave.getCreationDate());
            insertStatement.setDate(3,toSave.getUpdateDate());
            insertStatement.setString(4,toSave.getClientName());
            insertStatement.setString(5,toSave.getClientFirstname());
            insertStatement.setDate(6,toSave.getBirthDate());
            insertStatement.setDouble(7,toSave.getNetMonthlySalary());
            insertStatement.setInt(8,toSave.getIdTransaction());
            insertStatement.setDouble(9,toSave.getBalance());
            insertStatement.setDouble(10,toSave.getCreditAmount());
            insertStatement.executeUpdate();

        }

        return toSave;
    }

    @Override
    public Account update(int id, Account toUpdate) throws SQLException {
        String sql = "UPDATE accounts SET  authorize_credits = ?,   creation_date = ?,  update_date = ?, client_name = ?, client_firstname = ?, birth_date, net_monthly_salary = ?,id_transaction = ?, balance = ?, credit_amount = ?, = ? WHERE id_account = ?";
        try (PreparedStatement updateSql = connection.prepareStatement(sql)){
            updateSql.setBoolean(1,toUpdate.isAuthorizeCredits());
            updateSql.setDate(2,toUpdate.getCreationDate());
            updateSql.setDate(3,toUpdate.getUpdateDate());
            updateSql.setString(4,toUpdate.getClientName());
            updateSql.setString(5, toUpdate.getClientFirstname());
            updateSql.setDate(6,toUpdate.getBirthDate());
            updateSql.setDouble(7,toUpdate.getNetMonthlySalary());
            updateSql.setInt(8,toUpdate.getIdTransaction());
            updateSql.setDouble(9,toUpdate.getBalance());
            updateSql.setDouble(10,toUpdate.getCreditAmount());
            updateSql.setInt(11,id);

            updateSql.executeUpdate();
        }
 return  toUpdate;
    }
    @Override
    public void delete(int id) throws SQLException {
       String sql = "DELETE FROM accounts WHERE id_account = ?";
         try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
             preparedStatement.setInt(1,id);
             preparedStatement.executeUpdate();
         }
    }
}
