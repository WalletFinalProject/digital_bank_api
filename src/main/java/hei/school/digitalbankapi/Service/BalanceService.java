package hei.school.digitalbankapi.Service;

import hei.school.digitalbankapi.Entity.Account;
import hei.school.digitalbankapi.Entity.BalanceHistory;
import hei.school.digitalbankapi.Repository.CrudOperationsBalance;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BalanceService {
    private CrudOperationsBalance repository;

    public BalanceService(CrudOperationsBalance repository) {
        this.repository = repository;
    }
    public List<BalanceHistory> getBalanceHistory() throws SQLException {
        return repository.findAll();
    }

    public BalanceHistory createBalanceHistory(BalanceHistory toSave) throws SQLException{
        return  repository.save(toSave);
    }

    public BalanceHistory updateBalanceHistory(UUID id, BalanceHistory toUpdate) throws SQLException{
        return repository.update(id,toUpdate);
    }

    public void deleteBalanceHistory(UUID id) throws SQLException{
        repository.delete(id);
    }
}
