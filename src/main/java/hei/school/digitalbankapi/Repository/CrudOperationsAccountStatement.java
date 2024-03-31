package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.AccountStatement;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
public class CrudOperationsAccountStatement implements CrudOperations<AccountStatement>{

    private Connection connection;

    public CrudOperationsAccountStatement(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<AccountStatement> findAll() throws SQLException {
        List<AccountStatement> allAccountStatements = new ArrayList<>();
        String sql = "SELECT * FROM account_statement";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                AccountStatement accountStatement = new AccountStatement();
                accountStatement.setAccountStatementId(resultSet.getObject("id_account_statement", UUID.class));
                accountStatement.setAccountStatementDate(resultSet.getTimestamp("account_statement_date"));
                accountStatement.setReference(resultSet.getString("reference"));
                accountStatement.setMotif(resultSet.getString("motif"));
                accountStatement.setCreditMGA(resultSet.getDouble("credit_MGA"));
                accountStatement.setDebitMGA(resultSet.getDouble("debit_MGA"));
                accountStatement.setBalance(resultSet.getDouble("balance"));
                accountStatement.setIdAccount(resultSet.getObject("id_account", UUID.class));
                allAccountStatements.add(accountStatement);
            }
        }

        return allAccountStatements;
    }

    @Override
    public AccountStatement save(AccountStatement toSave) throws SQLException {
        String sql = "INSERT INTO account_statement (id_account_statement, account_statement_date, reference, motif, credit_MGA, debit_MGA, balance, id_account) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getAccountStatementId());
            insertStatement.setTimestamp(2, toSave.getAccountStatementDate());
            insertStatement.setString(3, toSave.getReference());
            insertStatement.setString(4, toSave.getMotif());
            insertStatement.setDouble(5, toSave.getCreditMGA());
            insertStatement.setDouble(6, toSave.getDebitMGA());
            insertStatement.setDouble(7, toSave.getBalance());
            insertStatement.setObject(8, toSave.getIdAccount());
            insertStatement.executeUpdate();
        }

        return toSave;
    }

    @Override
    public AccountStatement update(UUID id, AccountStatement toUpdate) throws SQLException {
        String sql = "UPDATE account_statement SET account_statement_date = ?, reference = ?, motif = ?, credit_MGA = ?, debit_MGA = ?, balance = ?,  WHERE id_account = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setTimestamp(1, toUpdate.getAccountStatementDate());
            updateSql.setString(2, toUpdate.getReference());
            updateSql.setString(3, toUpdate.getMotif());
            updateSql.setDouble(4, toUpdate.getCreditMGA());
            updateSql.setDouble(5, toUpdate.getDebitMGA());
            updateSql.setDouble(6, toUpdate.getBalance());
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM account_statement WHERE id_account_statement = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
    }

