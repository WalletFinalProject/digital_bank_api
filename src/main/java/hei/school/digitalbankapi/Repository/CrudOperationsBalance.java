package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.BalanceHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrudOperationsBalance implements CrudOperations<BalanceHistory>{
    private Connection connection;

    public CrudOperationsBalance(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<BalanceHistory> findAll() throws SQLException {
        List<BalanceHistory> allBalanceHistory = new ArrayList<>();
        String sql = "SELECT * FROM balance_history";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                BalanceHistory balanceHistory = new BalanceHistory();
                balanceHistory.setIdBalance(resultSet.getObject("id_balance_history", UUID.class));
                balanceHistory.setMoneyLoan(resultSet.getDouble("money_loan"));
                balanceHistory.setLoanInterest(resultSet.getDouble("loan_interest"));
                balanceHistory.setPrincipalAmount(resultSet.getDouble("principal_amount"));
                allBalanceHistory.add(balanceHistory);
            }
    }
        return allBalanceHistory;
    }

    @Override
    public BalanceHistory save(BalanceHistory toSave) throws SQLException {
        String sql = "INSERT INTO balance_history (id_balance_history, principal_amount, money_loan, loan_interest) VALUES (?, ?, ?, ?)";

        try (PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setObject(1, toSave.getIdBalance());
            insertStatement.setDouble(2, toSave.getLoanInterest());
            insertStatement.setDouble(3, toSave.getMoneyLoan());
            insertStatement.setDouble(4, toSave.getPrincipalAmount());
            insertStatement.executeUpdate();
        }

        return toSave;
    }

    @Override
    public BalanceHistory update(UUID id, BalanceHistory toUpdate) throws SQLException {
        String sql = "UPDATE balance_history SET principal_amount = ?, money_loan = ?, loan_interest = ? WHERE id_account = ?";

        try (PreparedStatement updateSql = connection.prepareStatement(sql)) {
            updateSql.setDouble(1, toUpdate.getPrincipalAmount());
            updateSql.setDouble(2, toUpdate.getMoneyLoan());
            updateSql.setDouble(3, toUpdate.getLoanInterest());
            updateSql.executeUpdate();
        }

        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM balance_history WHERE id_balance_history = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
