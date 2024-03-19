package hei.school.digitalbankapi.Service;


import hei.school.digitalbankapi.Entity.Transaction;
import hei.school.digitalbankapi.Repository.CrudOperationsTransaction;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionService {

    private CrudOperationsTransaction repository;

    public TransactionService(CrudOperationsTransaction repository) {
        this.repository = repository;
    }

    public List<Transaction> getAllTransaction() throws SQLException {
        return  repository.findAll();
    }

    public Transaction createTransaction(Transaction toSave) throws  SQLException{
        return repository.save(toSave);
    }

    public void updateTransaction(int id, Transaction toUpdate) throws  SQLException{
        repository.update(id,toUpdate);
    }

    public void deleteTransaction(int id) throws  SQLException{
        repository.delete(id);
    }
}
