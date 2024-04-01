package hei.school.digitalbankapi.Service;


import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.Transaction;
import hei.school.digitalbankapi.Repository.CrudOperationsTransaction;
import hei.school.digitalbankapi.Repository.TransactionInformations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class TransactionService {

    private CrudOperationsTransaction repository;
    private TransactionInformations informations;
    private JdbcTemplate jdbcTemplate;

    public TransactionService(CrudOperationsTransaction repository, TransactionInformations informations, JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.informations = informations;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> getAllTransaction() throws SQLException {
        return  repository.findAll();
    }

    public List<Transaction> getById(UUID id) throws SQLException{
        return  informations.findByid(id);
    }
    public Transaction createTransaction(Transaction toSave) throws  SQLException{
        return repository.save(toSave);
    }

    public Transaction updateTransaction(UUID id, Transaction toUpdate) throws  SQLException{
        return  repository.update(id,toUpdate);
    }

    public void deleteTransaction(UUID id) throws  SQLException{
        repository.delete(id);
    }
    public List<Map<String, Object>> getTransactionsByAccountAndCategory(UUID idAccount, LocalDate startDate, LocalDate endDate) {
        String sql = "SELECT a.id_account, c.category_name, SUM(t.amount) as total " +
                "FROM transactions t " +
                "INNER JOIN category c ON t.id_transaction = c.id_transaction " +
                "INNER JOIN accounts a ON t.id_account = a.id_account " +
                "WHERE t.transaction_date >= ? AND t.transaction_date <= ? AND t.id_account = ? " +
                "GROUP BY a.id_account, c.category_name";
        return jdbcTemplate.queryForList(sql, startDate, endDate, idAccount);
    }
    public List<Map<String, Object>> getIncomeAndExpenses(UUID idAccount, LocalDate startDate, LocalDate endDate, String groupBy) {
        String sql = "SELECT DATE_TRUNC(?, t.transaction_date) as period, " +
                "SUM(CASE WHEN t.amount > 0 THEN t.amount ELSE 0 END) as income, " +
                "SUM(CASE WHEN t.amount < 0 THEN t.amount ELSE 0 END) as expenses " +
                "FROM transactions t " +
                "WHERE t.transaction_date >= ? AND t.transaction_date <= ? AND t.id_account = ? " +
                "GROUP BY period " +
                "ORDER BY period";
        return jdbcTemplate.queryForList(sql, groupBy, startDate, endDate, idAccount);
    }

    public List<Transaction> getTransactionById(UUID id) throws SQLException {
        return repository.findById(id);
    }
}
