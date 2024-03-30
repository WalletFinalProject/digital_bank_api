package hei.school.digitalbankapi.Service;

import hei.school.digitalbankapi.Entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class StatementService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Transaction> getStatementOfAccount(UUID accountId, String startDate, String endDate) {
        String sql = "SELECT * FROM transactions WHERE id_transaction = ? AND transaction_date BETWEEN ? AND ? ORDER BY transaction_date DESC";
        return jdbcTemplate.query(sql, new Object[]{accountId, startDate, endDate}, (rs, rowNum) ->
                new Transaction(
                        rs.getObject("id_transaction", UUID.class),
                        rs.getTimestamp("transaction_date"),
                        rs.getDouble("amount"),
                        rs.getString("transaction_type"),
                        rs.getString("label")
                )
        );
    }
}
