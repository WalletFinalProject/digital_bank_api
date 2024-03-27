package hei.school.digitalbankapi.Repository;

import hei.school.digitalbankapi.Entity.BalanceHistory;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class CrudOperationsBalance implements CrudOperations<BalanceHistory>{
    @Override
    public List<BalanceHistory> findAll() throws SQLException {
        return null;
    }

    @Override
    public BalanceHistory save(BalanceHistory toSave) throws SQLException {
        return null;
    }

    @Override
    public BalanceHistory update(UUID id, BalanceHistory toUpdate) throws SQLException {
        return null;
    }

    @Override
    public void delete(UUID id) throws SQLException {

    }
}
