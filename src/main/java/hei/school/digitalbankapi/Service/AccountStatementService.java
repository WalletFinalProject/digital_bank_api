package hei.school.digitalbankapi.Service;

import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.AccountStatement;
import hei.school.digitalbankapi.Repository.CrudOperationsAccountStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountStatementService {
    private final CrudOperationsAccountStatement crudOperationsAccountStatement;
    private final Connection connection;

    @Autowired
    public AccountStatementService(CrudOperationsAccountStatement crudOperationsAccountStatement, Connection connection) {
        this.crudOperationsAccountStatement = crudOperationsAccountStatement;
        this.connection = connection;
    }

    public List<AccountStatement> getAccountStatement() throws SQLException {
        return crudOperationsAccountStatement.findAll();
    }

    public AccountStatement createAccountStatement(AccountStatement toSave) throws SQLException {
        return crudOperationsAccountStatement.save(toSave);
    }

    public AccountStatement updateAccountStatement(UUID id, AccountStatement toUpdate) throws SQLException {
        return crudOperationsAccountStatement.update(id, toUpdate);
    }

    public void deleteAccountStatement(UUID id) throws SQLException {
        crudOperationsAccountStatement.delete(id);
    }

    public List<AccountStatement> getAccountStatementByAccountId(UUID idAccount) throws SQLException {
        String sql = "SELECT * FROM account_statement WHERE id_account = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, idAccount);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AccountStatement> accountStatements = new ArrayList<>();
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
                accountStatements.add(accountStatement);
            }
            return accountStatements;
        }
    }

    public List<AccountStatement> getAccountStatementById(UUID id) throws SQLException {
        return crudOperationsAccountStatement.findById(id);
    }
}
