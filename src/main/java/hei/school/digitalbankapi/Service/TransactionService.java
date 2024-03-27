package hei.school.digitalbankapi.Service;


import hei.school.digitalbankapi.Entity.Transaction;
import hei.school.digitalbankapi.Repository.CrudOperationsTransaction;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

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

    public Transaction updateTransaction(UUID id, Transaction toUpdate) throws  SQLException{
        return  repository.update(id,toUpdate);
    }

    public void deleteTransaction(UUID id) throws  SQLException{
        repository.delete(id);
    }
}
