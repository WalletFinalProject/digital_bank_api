package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.Account;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class CrudOperationsAccount implements CrudOperations<Account> {
    private Connection connection;

    public CrudOperationsAccount(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> allAccount = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Account account = new Account();
                account.setIdAccount(resultSet.getObject("id_account", UUID.class));
                account.setAuthorizeCredits(resultSet.getBoolean("authorize_credits"));
                account.setCreationDate(resultSet.getDate("creation_date"));
                account.setUpdateDate(resultSet.getDate("update_date"));
                account.setClientName(resultSet.getString("client_name"));
                account.setClientFirstname(resultSet.getString("client_firstname"));
                account.setBirthDate(resultSet.getDate("birth_date"));
                account.setNetMonthlySalary(resultSet.getDouble("net_monthly_salary"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setCreditAmount(resultSet.getDouble("credit_amount"));

                allAccount.add(account);
            }
        }

        return allAccount;
    }
    @Override
    public Account save(Account toSave) throws SQLException {
        String sql = "INSERT INTO accounts (id_account, authorize_credits, creation_date, update_date, client_name, client_firstname, birth_date, net_monthly_salary,balance, credit_amount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getIdAccount());
            insertStatement.setBoolean(2, toSave.isAuthorizeCredits());
            insertStatement.setDate(3, toSave.getCreationDate());
            insertStatement.setDate(4, toSave.getUpdateDate());
            insertStatement.setString(5, toSave.getClientName());
            insertStatement.setString(6, toSave.getClientFirstname());
            insertStatement.setDate(7, toSave.getBirthDate());
            insertStatement.setDouble(8, toSave.getNetMonthlySalary());
            insertStatement.setDouble(9, toSave.getBalance());
            insertStatement.setDouble(10, toSave.getCreditAmount());
            insertStatement.executeUpdate();
        }

        return toSave;
    }

    @Override
    public Account update(UUID id, Account toUpdate) throws SQLException {
        String sql = "UPDATE accounts SET authorize_credits = ?, creation_date = ?, update_date = ?, client_name = ?, client_firstname = ?, birth_date = ?, net_monthly_salary = ?, id_transaction = ?, balance = ?, credit_amount = ? WHERE id_account = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setBoolean(1, toUpdate.isAuthorizeCredits());
            updateSql.setDate(2, toUpdate.getCreationDate());
            updateSql.setDate(3, toUpdate.getUpdateDate());
            updateSql.setString(4, toUpdate.getClientName());
            updateSql.setString(5, toUpdate.getClientFirstname());
            updateSql.setDate(6, toUpdate.getBirthDate());
            updateSql.setDouble(7, toUpdate.getNetMonthlySalary());
            updateSql.setDouble(8, toUpdate.getBalance());
            updateSql.setDouble(9, toUpdate.getCreditAmount());
            updateSql.setObject(10, id);
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM accounts WHERE id_account = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
}